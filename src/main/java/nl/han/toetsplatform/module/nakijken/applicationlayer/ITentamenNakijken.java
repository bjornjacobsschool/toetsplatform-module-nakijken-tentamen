package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.nakijken.model.UitgevoerdTentamen;

import java.util.List;

public interface ITentamenNakijken {
    String getTestMessage();

    List<UitgevoerdTentamen> getUitgevoerdeTentamens() throws GatewayCommunicationException;
    List<Tentamen> getTentamens();
}
