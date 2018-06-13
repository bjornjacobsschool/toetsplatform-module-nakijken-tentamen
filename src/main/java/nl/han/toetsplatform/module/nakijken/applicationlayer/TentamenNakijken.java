package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;

    @Override
    public String getTestMessage() {
        return "Welkom";
    }

    @Override
    public List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException {
        ArrayList<UitgevoerdTentamenDto> uitgevoerdTentamenDtos = new ArrayList<>();
        uitgevoerdTentamenDtos.addAll(Arrays.asList(this.serviceAgent.get("tentamens/uitgevoerd", UitgevoerdTentamenDto[].class)));
        return uitgevoerdTentamenDtos;
    }

    @Inject
    public TentamenNakijken(IGatewayServiceAgent serviceAgent) {
        this.serviceAgent = serviceAgent;
    }
}
