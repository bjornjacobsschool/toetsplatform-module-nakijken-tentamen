package nl.han.toetsplatform.module.nakijken.config;

import com.google.inject.Module;
import nl.han.toetsplatform.module.nakijken.guice.GuiceModule;

import java.net.URL;

public class ConfigTentamenNakijkenModule {
    public static URL getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles fxmlFile) {
        return ConfigTentamenNakijkenModule.class.getResource("/fxml/"+fxmlFile+".fxml");
    }

    public static Module getModule() {
        return new GuiceModule();
    }
}
