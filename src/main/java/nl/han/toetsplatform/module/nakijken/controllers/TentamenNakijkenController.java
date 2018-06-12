package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsplatform.module.nakijken.model.Student;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.nakijken.model.UitgevoerdTentamen;
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
    private List<UitgevoerdTentamen> uitgevoerdeTentamens;
    private Runnable onTerugClick;
    private UitgevoerdTentamen uitgevoerdTentamen;
    private int indexVraag;

    @FXML
    private ListView<UitgevoerdTentamen> studentenListView;
    private ObservableList<UitgevoerdTentamen> studentenListData = FXCollections.observableArrayList();

    public void initialize() {

    }

    public void setOnTerugClick(Runnable onTerugClick) {
        this.onTerugClick = onTerugClick;
    }

    public void setNaTeKijkenTentamen(List<UitgevoerdTentamen> uitgevoerdeTentamens) {
        this.uitgevoerdeTentamens = uitgevoerdeTentamens;
    }

    public void setStudentenListView() {
        this.studentenListData.addAll(this.uitgevoerdeTentamens);
        this.studentenListView.setItems(studentenListData);
        studentenListView.setCellFactory(param -> new ListCell<UitgevoerdTentamen>() {
            @Override
            protected void updateItem(UitgevoerdTentamen item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getStudent() == null) {
                    setText(null);
                } else {
                    setText(item.getStudent().getNaam());
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
        this.indexVraag = 0;
        this.updateVraag();
    }

    public void handleVolgendeBtnClick(ActionEvent actionEvent) {
        this.indexVraag += 1;
        this.updateVraag();
    }

    public void handleVorigeBtnClick(ActionEvent actionEvent) {
        this.indexVraag -=1;
        this.updateVraag();
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
