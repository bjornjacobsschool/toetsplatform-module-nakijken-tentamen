package nl.han.toetsplatform.module.nakijken.controllers;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;


public class TestController {
    public AnchorPane mainContainer;
    ITentamenNakijken _tentamenNakijken;

    @Inject
    public TestController(ITentamenNakijken tentamenNakijken)
    {
        _tentamenNakijken = tentamenNakijken;
    }

    public void initialize() {
        mainContainer.getChildren().add(new Label(_tentamenNakijken.getTestMessage()));
    }
}
