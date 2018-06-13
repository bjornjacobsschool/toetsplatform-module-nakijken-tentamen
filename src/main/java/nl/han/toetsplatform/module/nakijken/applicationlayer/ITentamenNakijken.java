package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;

import java.sql.SQLException;

public interface ITentamenNakijken {

    void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException;

    void ophalen() throws GatewayCommunicationException, SQLException;

}
