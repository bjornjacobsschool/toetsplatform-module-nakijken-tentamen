package nl.han.toetsplatform.module.nakijken.data.data;

import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;

import java.util.List;

public interface TentamenDAO {

    List<NagekekenTentamenDto> getNaTeKijkenTentamens(String vak);

    void slaNagekekenTentamenOp(NagekekenTentamenDto nagekekenTentamen);

    void setNaTeKijkenTentamens(List<NagekekenTentamenDto> naTeKijkenTentamens);
}
