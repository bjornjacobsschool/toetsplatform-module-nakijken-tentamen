package nl.han.toetsplatform.module.nakijken.controllers;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.config.ConfigTentamenNakijkenModule;
import nl.han.toetsplatform.module.nakijken.config.NakijkenTentamenFXMLFiles;

import java.io.IOException;


public class TestController {
    public AnchorPane mainContainer;
    ITentamenNakijken _tentamenNakijken;
    private GuiceFXMLLoader fxmlLoader;

    @Inject
    public TestController(ITentamenNakijken tentamenNakijken, GuiceFXMLLoader fxmlLoader) {
        _tentamenNakijken = tentamenNakijken;
        this.fxmlLoader = fxmlLoader;
    }

    public void initialize() throws IOException{
//        mainContainer.getChildren().add(new Label(_tentamenNakijken.getTestMessage()));
        GuiceFXMLLoader.Result selecteerKlasView = fxmlLoader.load(ConfigTentamenNakijkenModule.getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles.VraagNakijken));
        mainContainer.getChildren().add(selecteerKlasView.getRoot());
    }
}
