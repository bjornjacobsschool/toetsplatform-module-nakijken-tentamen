package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;

import java.util.List;

public interface ITentamenNakijken {
    String getTestMessage();

    List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException;
}
