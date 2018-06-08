package nl.han.toetsplatform.module.nakijken.controllers;

import javafx.fxml.FXML;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import static nl.han.toetsplatform.module.nakijken.util.RunnableUtil.runIfNotNull;

import java.util.List;

/**
 * Created by chico_000 on 4-6-2018.
 */
public class TentamenNakijkenController {

    private List<Tentamen> naTeKijkenTentamens;
    private Runnable onTerugClick;

    public void setOnTerugClick(Runnable onTerugClick) {
        this.onTerugClick = onTerugClick;
    }

    public void setNaTeKijkenTentamens(List<Tentamen> naTeKijkenTentamens) {
        this.naTeKijkenTentamens = naTeKijkenTentamens;
    }

    @FXML
    public void handleTerugButtonClick(){
        runIfNotNull(onTerugClick);
    }
}
