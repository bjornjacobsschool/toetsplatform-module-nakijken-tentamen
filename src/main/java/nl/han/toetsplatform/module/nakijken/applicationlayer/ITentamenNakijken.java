package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.models.NagekekenTentamen;

import java.sql.SQLException;
import java.util.List;

public interface ITentamenNakijken {

    void opslaan(NagekekenTentamen nagekekenTentamen) throws GatewayCommunicationException, SQLException;

    List<NagekekenTentamen> getNaTeKijkenTentamens(String vak);

    String getTestMessage();
}
