package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.models.NagekekenTentamen;

import java.util.List;

public interface TentamenDAO {

    List<NagekekenTentamen> getNaTeKijkenTentamens(String vak);

    void slaNagekekenTentamenOp(NagekekenTentamen nagekekenTentamen);

    void setNaTeKijkenTentamens(List<NagekekenTentamen> naTeKijkenTentamens);
}
