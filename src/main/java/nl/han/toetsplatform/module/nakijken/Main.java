package nl.han.toetsplatform.module.nakijken;

import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import com.google.inject.Module;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.han.toetsplatform.module.nakijken.config.ConfigTentamenNakijkenModule;
import nl.han.toetsplatform.module.nakijken.config.NakijkenTentamenFXMLFiles;

import java.util.List;

public class Main extends GuiceApplication {

    @Inject
    private GuiceFXMLLoader fxmlLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = fxmlLoader.load(ConfigTentamenNakijkenModule.getFXMLTentamenNakijken(NakijkenTentamenFXMLFiles.TestScherm), null).getRoot();
        primaryStage.setTitle("Nakijken tentamen module");
        primaryStage.setScene(new Scene(root, 1200, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init(List<Module> modules) throws Exception {
        modules.add(ConfigTentamenNakijkenModule.getModule());
    }
}
