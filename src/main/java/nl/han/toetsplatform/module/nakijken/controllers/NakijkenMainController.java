package nl.han.toetsplatform.module.nakijken.controllers;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.config.ConfigTentamenNakijkenModule;
import nl.han.toetsplatform.module.nakijken.config.NakijkenTentamenFXMLFiles;

import java.io.IOException;
import java.util.List;


public class NakijkenMainController {
    public AnchorPane mainContainer;
    ITentamenNakijken _tentamenNakijken;
    private GuiceFXMLLoader fxmlLoader;
    private GuiceFXMLLoader.Result tentamenNakijkenView;
    private GuiceFXMLLoader.Result selecteerKlasView;

    @Inject
    public NakijkenMainController(ITentamenNakijken tentamenNakijken, GuiceFXMLLoader fxmlLoader) {
        _tentamenNakijken = tentamenNakijken;
        this.fxmlLoader = fxmlLoader;
    }

    public void initialize() throws IOException{
        GuiceFXMLLoader.Result selecteerKlasView = fxmlLoader.load(ConfigTentamenNakijkenModule.getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles.SelecteerKlas));
        mainContainer.getChildren().add(selecteerKlasView.getRoot());
        KlasSelectieController klasSelectieController = selecteerKlasView.getController();
        klasSelectieController.setOnSelectieNakijken(this::handleKlasSelectie);
    }

    private void handleTentamenTerugClick() {
        try {
            selecteerKlasView = fxmlLoader.load(ConfigTentamenNakijkenModule.getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles.SelecteerKlas));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KlasSelectieController klasSelectieController = selecteerKlasView.getController();
        klasSelectieController.setOnSelectieNakijken(this::handleKlasSelectie);
        switchView(selecteerKlasView);
    }

    private void handleKlasSelectie(List<UitgevoerdTentamenDto> uitgevoerdeTentamens) {
        try {
            tentamenNakijkenView = fxmlLoader.load(ConfigTentamenNakijkenModule.getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles.TentamenNakijken));
        } catch (IOException e) {
            e.printStackTrace();
        }
        switchView(tentamenNakijkenView);
        TentamenNakijkenController tentamenNakijkenController = tentamenNakijkenView.getController();
        tentamenNakijkenController.setNaTeKijkenTentamens(uitgevoerdeTentamens);
        tentamenNakijkenController.setStudentenListView();
        tentamenNakijkenController.setOnTerugClick(this::handleTentamenTerugClick);
    }

    /**
     * Helper methode om van view te switchen.
     * @param view
     */
    private void switchView(GuiceFXMLLoader.Result view) {
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(view.getRoot());
        setAnchorFull(mainContainer);
    }

    /**
     * Methode die het scherm vergroot
     * @param node
     */
    private void setAnchorFull(Node node){
        AnchorPane.setBottomAnchor(node, 0D);
        AnchorPane.setLeftAnchor(node, 0D);
        AnchorPane.setRightAnchor(node, 0D);
        AnchorPane.setTopAnchor(node, 0D);
    }
}
