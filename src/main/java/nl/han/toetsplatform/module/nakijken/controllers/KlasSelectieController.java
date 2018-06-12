package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


import static nl.han.toetsplatform.module.nakijken.util.RunnableUtil.runIfNotNull;

/**
 * Created by chico_000 on 4-6-2018.
 */
public class KlasSelectieController {
    private ITentamenNakijken tentamenNakijken;

    @FXML
    private ListView<Tentamen> tentamenListView;

    @FXML
    private ListView<Klas> klasListView;

    private ObservableList<Tentamen> tentamenListData = FXCollections.observableArrayList();

    private ObservableList<Klas> klasListData = FXCollections.observableArrayList();

    public void setOnSelectieNakijken(Consumer<List<UitgevoerdTentamen>> onSelectieNakijken) {
        this.onSelectieNakijken = onSelectieNakijken;
    }

    private Consumer<List<UitgevoerdTentamen>> onSelectieNakijken;

    @Inject
    public KlasSelectieController(ITentamenNakijken tentamenNakijken) {
        this.tentamenNakijken = tentamenNakijken;
    }

    @FXML
    public void initialize() {

        tentamenListData.addAll(this.tentamenNakijken.getTentamens());
        tentamenListView.setItems(tentamenListData);
        tentamenListView.setCellFactory(param -> new ListCell<Tentamen>() {
            @Override
            protected void updateItem(Tentamen item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNaam() == null) {
                    setText(null);
                } else {
                    setText(item.getNaam());
                }
            }
        });
    }

    @FXML
    public void handleNakijkenButtonClick(ActionEvent actionEvent) throws IOException{
        //getcall naar gateway voor een lijst van uitgevoerde tentamens
        List<UitgevoerdTentamen> uitgevoerdeTentamens = new ArrayList<UitgevoerdTentamen>();
        try {
            uitgevoerdeTentamens.addAll(this.tentamenNakijken.getUitgevoerdeTentamens());
        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
        }
//        Student student1 = new Student("student1");
//        Student student2 = new Student("student2");
//        Student student3 = new Student("student3");
//        List<UitgevoerdTentamen> tentamens = new ArrayList<UitgevoerdTentamen>();
//        UitgevoerdTentamen tentamen1 = new UitgevoerdTentamen(student1);
//        UitgevoerdTentamen tentamen2 = new UitgevoerdTentamen(student2);
//        UitgevoerdTentamen tentamen3 = new UitgevoerdTentamen(student3);
//        tentamens.add(tentamen1);
//        tentamens.add(tentamen2);
//        tentamens.add(tentamen3);
//        IngevuldeVraag vraag1 = new IngevuldeVraag("ja");
//        IngevuldeVraag vraag2 = new IngevuldeVraag("nee");
//        IngevuldeVraag vraag3 = new IngevuldeVraag("Jeen");
//        List<IngevuldeVraag> vragen = new ArrayList<IngevuldeVraag>();
//        vragen.add(vraag1);
//        vragen.add(vraag2);
//        vragen.add(vraag3);
//        tentamen1.setVragen(vragen);
//        tentamen2.setVragen(vragen);
//        tentamen3.setVragen(vragen);

        runIfNotNull(onSelectieNakijken, uitgevoerdeTentamens);
    }

    @FXML
    public void handleListViewMouseClick(MouseEvent event) {
        klasListView.getItems().clear();
        klasListData.addAll(tentamenListView.getSelectionModel().getSelectedItem().getGemaaktDoorKlassen());
        klasListView.setItems(klasListData);
        klasListView.setCellFactory(param -> new ListCell<Klas>() {
            @Override
            protected void updateItem(Klas item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNaam() == null) {
                    setText(null);
                } else {
                    setText(item.getNaam());
                }
            }
        });
    }
}
