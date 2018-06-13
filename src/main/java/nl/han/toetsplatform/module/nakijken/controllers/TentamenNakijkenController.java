package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.shared.plugin.Plugin;
import nl.han.toetsplatform.module.shared.plugin.PluginLoader;

import static nl.han.toetsplatform.module.nakijken.util.RunnableUtil.runIfNotNull;

import java.util.List;

/**
 * Created by chico_000 on 4-6-2018.
 */
public class TentamenNakijkenController {

    public AnchorPane vraagContainer;
    public AnchorPane nakijkContainer;
    private List<UitgevoerdTentamenDto> uitgevoerdeTentamens;
    private Runnable onTerugClick;
    private UitgevoerdTentamenDto uitgevoerdTentamen;
    private int indexVraag;
    private int maxIndex;

    @FXML
    private ListView<UitgevoerdTentamenDto> studentenListView;
    private ObservableList<UitgevoerdTentamenDto> studentenListData = FXCollections.observableArrayList();

    public void initialize() {

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
    public void handleTerugButtonClick(){
        runIfNotNull(onTerugClick);
    }

    public void btnNakijkenPressed(ActionEvent actionEvent) {
        this.uitgevoerdTentamen = this.studentenListView.getSelectionModel().getSelectedItem();
        this.maxIndex = this.uitgevoerdTentamen.getVragen().size();
        this.indexVraag = 0;
        this.updateVraag();
    }

    public void handleVolgendeBtnClick(ActionEvent actionEvent) {
        if (this.indexVraag == this.maxIndex) {
            //post nagekeken tentamen
        } else {
            this.indexVraag += 1;
            this.updateVraag();
        }
    }

    public void handleVorigeBtnClick(ActionEvent actionEvent) {
        if (this.indexVraag == 0) {
            this.indexVraag = this.maxIndex;
            this.updateVraag();
        } else {
            this.indexVraag -=1;
            this.updateVraag();
        }
    }

    public void updateVraag() {
        try {
            Plugin plugin = PluginLoader.getPlugin("nl.han.toetsapplicatie.plugin.VrijeTekstPlugin");
            Node vraagView = plugin.getVraagView("{}").getView();
            Node nakijkView = plugin.getNakijkView("", "", "", 0).getView();
            this.uitgevoerdTentamen.getVragen().get(this.indexVraag);
            vraagContainer.getChildren().add(vraagView);
            nakijkContainer.getChildren().add(nakijkView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
