package nl.han.toetsplatform.module.nakijken.controllers;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsapplicatie.apimodels.dto.*;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.data.data.dto_model_mapper.DatabaseMapper;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.stub.StubStorageDao;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;
import nl.han.toetsplatform.module.shared.plugin.Plugin;
import nl.han.toetsplatform.module.shared.plugin.PluginLoader;

import static nl.han.toetsplatform.module.nakijken.util.RunnableUtil.runIfNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by chico_000 on 4-6-2018.
 */
public class TentamenNakijkenController {

    private ITentamenNakijken tentamenNakijken;

    @FXML
    private AnchorPane vraagContainer;
    @FXML
    private AnchorPane nakijkContainer;
    @FXML
    private TextArea toelichtingTextArea;

    private Runnable onTerugClick;
    private int indexVraag;
    private int maxIndex;

    private List<VragenbankVraagDto> alleVragen;

    @FXML
    private Label totaalAantalPuntenLabel;
    @FXML
    private TextField behaaldePuntenField;
    @FXML
    private ListView<UitgevoerdTentamenDto> studentenListView;
    private ObservableList<UitgevoerdTentamenDto> studentenListData = FXCollections.observableArrayList();
    private List<UitgevoerdTentamenDto> uitgevoerdeTentamens;
    private UitgevoerdTentamenDto uitgevoerdTentamen;
    private StubStorageDao _stoarageDAO = new StubStorageDao();
    private SQLLoader _sqlLoader = new SQLLoader();
    private SQLAntwoordOpVraagDAO antwoordOpVraagDAO = new SQLAntwoordOpVraagDAO(_stoarageDAO, _sqlLoader);
    private DatabaseMapper databaseMapper = new DatabaseMapper();

    List<NagekekenTentamenDto> nagekekenTentamens;
    NagekekenTentamenDto huidigNagekekenTentamen;

    @Inject
    public TentamenNakijkenController(ITentamenNakijken tentamenNakijken) {
        this.tentamenNakijken = tentamenNakijken;
    }

    public void initialize() throws GatewayCommunicationException {
        alleVragen = tentamenNakijken.getVragen();
        totaalAantalPuntenLabel.setText("0");
        toelichtingTextArea.setText(" ");
        nagekekenTentamens = new ArrayList<>();
    }

    public void setOnTerugClick(Runnable onTerugClick) {
        this.onTerugClick = onTerugClick;
    }

    public void setNaTeKijkenTentamens(List<UitgevoerdTentamenDto> uitgevoerdeTentamens) {
        this.uitgevoerdeTentamens = uitgevoerdeTentamens;
    }

    public void setStudentenListView() {
        this.studentenListData.addAll(this.uitgevoerdeTentamens);
        this.studentenListView.setItems(studentenListData);
        studentenListView.setCellFactory(param -> new ListCell<UitgevoerdTentamenDto>() {
            @Override
            protected void updateItem(UitgevoerdTentamenDto item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getStudent() == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item.getStudent().getStudentNummer()));
                }
            }
        });
    }

    @FXML
    public void handleTerugButtonClick() {
        runIfNotNull(onTerugClick);
    }

    public void btnNakijkenPressed(ActionEvent actionEvent) {
        this.uitgevoerdTentamen = this.studentenListView.getSelectionModel().getSelectedItem();
        this.maxIndex = this.uitgevoerdTentamen.getVragen().size() - 1;
        this.indexVraag = 0;

        //Kijk of het huidig geselecteerde tentamen al bestaat in de nagekekenTentamens
        if (!containsName(nagekekenTentamens, uitgevoerdTentamen.getStudent())) {
            huidigNagekekenTentamen = new NagekekenTentamenDto();
        } else {
            for (NagekekenTentamenDto nagekekenTentamenDto : nagekekenTentamens) {
                if (nagekekenTentamenDto.getStudent().equals(uitgevoerdTentamen.getStudent()))
                    huidigNagekekenTentamen = nagekekenTentamenDto;
            }
        }

        List<Antwoord_op_Vraag> antwoordenList = antwoordOpVraagDAO.loadAllAntwoorden();
        boolean antwoordBestaat = false;
        Antwoord_op_Vraag bestaandeAntwoord = new Antwoord_op_Vraag();
        for(Antwoord_op_Vraag antwoord : antwoordenList){
            if(antwoord.getVraagId().equals(uitgevoerdTentamen.getVragen().get(indexVraag).getId().toString())
                    && antwoord.getStudentNummer() == uitgevoerdTentamen.getStudent().getStudentNummer()
                    && antwoord.getTentamenCode().equals(uitgevoerdTentamen.getId().toString())){
                antwoordBestaat = true;
                bestaandeAntwoord = antwoord;
            }
        }

        if (antwoordBestaat){
            behaaldePuntenField.setText(String.valueOf(bestaandeAntwoord.getBehaaldePunten()));
            toelichtingTextArea.setText(bestaandeAntwoord.getNakijkComment());
        }

        this.updateVraag();
    }

    public boolean containsName(final List<NagekekenTentamenDto> list, final StudentDto student) {
        return list.stream().map(NagekekenTentamenDto::getStudent).filter(student::equals).findFirst().isPresent();
    }

    public void handleVolgendeBtnClick(ActionEvent actionEvent) {
        try {
            Antwoord_op_Vraag tempNagekekenVraag = new Antwoord_op_Vraag();
            tempNagekekenVraag.setVraagId(uitgevoerdTentamen.getVragen().get(indexVraag).getId().toString());
            tempNagekekenVraag.setVraagVersie(String.valueOf(uitgevoerdTentamen.getVragen().get(indexVraag).getVersie().getNummer()));
            tempNagekekenVraag.setTentamenCode(uitgevoerdTentamen.getId().toString());
            tempNagekekenVraag.setTentamenVersie(String.valueOf(uitgevoerdTentamen.getVersie().getNummer()));
            tempNagekekenVraag.setStudentNummer(uitgevoerdTentamen.getStudent().getStudentNummer());
            tempNagekekenVraag.setAntwoord(uitgevoerdTentamen.getVragen().get(indexVraag).getGegevenAntwoord());
            tempNagekekenVraag.setBehaaldePunten(Integer.parseInt(behaaldePuntenField.getText()));
            tempNagekekenVraag.setNakijkComment(toelichtingTextArea.getText());

            List<Antwoord_op_Vraag> antwoordenList = antwoordOpVraagDAO.loadAllAntwoorden();
            boolean antwoordBestaat = false;

            for(Antwoord_op_Vraag antwoord : antwoordenList){
                if(antwoord.getVraagId().equals(tempNagekekenVraag.getVraagId()) && antwoord.getStudentNummer() == tempNagekekenVraag.getStudentNummer() && antwoord.getTentamenCode().equals(tempNagekekenVraag.getTentamenCode())){
                    antwoordBestaat = true;
                }
            }

            if(!antwoordBestaat) {
                tentamenNakijken.opslaan(tempNagekekenVraag);
            } else {
                tentamenNakijken.update(tempNagekekenVraag);
            }
        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (this.indexVraag == this.maxIndex) {
           List<Antwoord_op_Vraag> nagekekenVragen = tentamenNakijken.getNagekekenVragen();
            List<NagekekenVraagDto> nagekenVragen = databaseMapper.naarNagekenTentamenDTO(uitgevoerdTentamen.getVragen() , nagekekenVragen);
           int behaaldePunten = 0;
           int totaalPuntenAantal = 0;

           for(Antwoord_op_Vraag nageken : nagekekenVragen){
              behaaldePunten+=nageken.getBehaaldePunten();
               //Krijg de vraagdetails van de huidige vraag
               UUID id = UUID.fromString(nageken.getVraagId());
               for (VragenbankVraagDto vraag : alleVragen) {
                   if (vraag.getId().equals(id))
                       totaalPuntenAantal += vraag.getPunten();
               }
           }

          int cijfer =  berekenCijcer(totaalPuntenAantal, behaaldePunten);
            NagekekenTentamenDto nagekekenTentamen = databaseMapper.createNagekenTentameDTO(uitgevoerdTentamen,nagekenVragen, cijfer);

            tentamenNakijken.postNagekekenTentamen(nagekekenTentamen);
        } else {
            this.indexVraag += 1;
        }

        updateVraag();
    }

    public void handleVorigeBtnClick(ActionEvent actionEvent) {
        if (this.indexVraag == 0) {
            this.indexVraag = this.maxIndex;
        } else {
            this.indexVraag -= 1;
        }
        updateVraag();
    }

    /**
     * Update de view naar het huidig geselecteerd tentamen.
     */
    public void updateVraag() {
        vraagContainer.getChildren().clear();
        nakijkContainer.getChildren().clear();


        // TODO Opslaan lokale database -> Michiel


        //Krijg de vraagdetails van de huidige vraag
        UUID id = uitgevoerdTentamen.getVragen().get(indexVraag).getId();
        VragenbankVraagDto vraagDto = null;
        for (VragenbankVraagDto vraag : alleVragen) {
            if (vraag.getId().equals(id))
                vraagDto = vraag;
        }
        if (vraagDto == null) {
            // handle it
        }

        //Test data omdat er nog geen correcte test data bestaat.
        System.out.println(vraagDto.getVraagData());
        vraagDto.setVraagData("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"5\",\"5\",\"+\",\"8\",\"*\"],\"stack\":[[5],[5,5],[10],[8,10],[80]],\"expressie\":\"5 5 + 8 *\"}");
        vraagDto.setVraagtype("nl.han.toetsplatform.plugin.TekenPlugin");
        vraagDto.setPunten(10);
        uitgevoerdTentamen.getVragen().get(indexVraag).setGegevenAntwoord("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"5\",\"5\",\"+\",\"8\",\"*\"],\"stack\":[[5],[5,5],[10],[8,10],[80]],\"expressie\":\"5 5 + 8 *\"}");
        //////


        totaalAantalPuntenLabel.setText("Deze vraag is " + vraagDto.getPunten() + " punten waard.");
        try {

            Plugin plugin = PluginLoader.getPlugin(vraagDto.getVraagtype());
            Node vraagView = plugin.getVraagView(vraagDto.getVraagData()).getView();
            Node nakijkView = plugin.getNakijkView(vraagDto.getVraagData(), uitgevoerdTentamen.getVragen().get(indexVraag).getGegevenAntwoord(), vraagDto.getVraagData(), vraagDto.getPunten()).getView();
            vraagContainer.getChildren().add(vraagView);
            nakijkContainer.getChildren().add(nakijkView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int berekenCijcer(int totaalPunten, int behaaldePunten){
        return behaaldePunten/totaalPunten*9 +1 ;
    }
}
