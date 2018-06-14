package nl.han.toetsplatform.module.nakijken.applicationlayer;


import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;

import java.sql.SQLException;
import java.util.List;

public interface ITentamenNakijken {

    List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException;

    void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException;


}
