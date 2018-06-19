package nl.han.toetsplatform.module.nakijken.applicationlayer;


import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.VragenbankVraagDto;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;

import java.sql.SQLException;
import java.util.List;

public interface ITentamenNakijken {

    List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException;

    void opslaan(Antwoord_op_Vraag nagekekenTentamen) throws GatewayCommunicationException, SQLException;

    List<VragenbankVraagDto> getVragen() throws GatewayCommunicationException;

    void update(Antwoord_op_Vraag nagekekenTentamen) throws GatewayCommunicationException, SQLException;

     List<Antwoord_op_Vraag> getNagekekenVragen();

     void postNagekekenTentamen(NagekekenTentamenDto nagekekenTentamen);
}
