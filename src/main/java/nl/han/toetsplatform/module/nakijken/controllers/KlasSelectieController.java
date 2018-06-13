package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Array;
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
    private ListView<String> tentamenListView;

    @FXML
    private ListView<String> klasListView;

    private ObservableList<UitgevoerdTentamenDto> tentamenListData = FXCollections.observableArrayList();

    private ObservableList<String> klasListData = FXCollections.observableArrayList();

    public void setOnSelectieNakijken(Consumer<List<UitgevoerdTentamenDto>> onSelectieNakijken) {
        this.onSelectieNakijken = onSelectieNakijken;
    }

    private Consumer<List<UitgevoerdTentamenDto>> onSelectieNakijken;

    @Inject
    public KlasSelectieController(ITentamenNakijken tentamenNakijken) {
        this.tentamenNakijken = tentamenNakijken;
    }

    @FXML
    public void initialize() {
        try {
            tentamenListData.addAll(this.tentamenNakijken.getUitgevoerdeTentamens());
        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
        }
        tentamenListView.setItems(filterTentamens(tentamenListData));

    }

    @FXML
    public void handleNakijkenButtonClick(ActionEvent actionEvent) throws IOException{
        runIfNotNull(onSelectieNakijken, filterOpKlasEnTentamen());
    }

    @FXML
    public void handleListViewMouseClick(MouseEvent event) {
        klasListView.getItems().clear();
        klasListData.addAll(filterKlas(tentamenListView.getSelectionModel().getSelectedItem()));
        klasListView.setItems(klasListData);
    }

    public ObservableList<String> filterTentamens(List<UitgevoerdTentamenDto> listToFilter) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (UitgevoerdTentamenDto tentamen: listToFilter) {
            if (!filteredList.contains(tentamen.getNaam())) {
                filteredList.add(tentamen.getNaam());
            }
        }

        return filteredList;
    }

    public ObservableList<String> filterKlas (String selectedItem) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (UitgevoerdTentamenDto tentamenDto: this.tentamenListData) {
            if (selectedItem.equals(tentamenDto.getNaam())) {
                if (!filteredList.contains(tentamenDto.getStudent().getKlas())){
                    filteredList.add(tentamenDto.getStudent().getKlas());
                }
            }
        }

        return filteredList;
    }

    public List<UitgevoerdTentamenDto> filterOpKlasEnTentamen() {
        String tentamen = this.tentamenListView.getSelectionModel().getSelectedItem();
        String klas = this.klasListView.getSelectionModel().getSelectedItem();

        ArrayList<UitgevoerdTentamenDto> filteredTentamens = new ArrayList<UitgevoerdTentamenDto>();

        for (UitgevoerdTentamenDto tentamenDto: this.tentamenListData) {
            if (tentamenDto.getNaam().equals(tentamen) && tentamenDto.getStudent().getKlas().equals(klas)) {
                filteredTentamens.add(tentamenDto);
            }
        }

        return filteredTentamens;
    }
}
