package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.model.Klas;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;

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

    public void setOnSelectieNakijken(Consumer<List<Tentamen>> onSelectieNakijken) {
        this.onSelectieNakijken = onSelectieNakijken;
    }

    private Consumer<List<Tentamen>> onSelectieNakijken;

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
        ArrayList<Tentamen> gemaakteTentamens = new ArrayList<Tentamen>();
        gemaakteTentamens.add(tentamenListView.getSelectionModel().getSelectedItem());
        runIfNotNull(onSelectieNakijken, gemaakteTentamens);
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
