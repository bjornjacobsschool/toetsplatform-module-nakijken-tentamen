package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;
    private TentamenDAO _tentamenDAO;

    public TentamenNakijken(IGatewayServiceAgent _gatewayServiceAgent, TentamenDAO _tentamenDAO){
        this.serviceAgent = _gatewayServiceAgent;
        this._tentamenDAO = _tentamenDAO;

    }

    @Override
    public void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException {
        _tentamenDAO.slaNagekekenTentamenOp(nagekekenTentamen);
        this.serviceAgent.post("/tentamens/nagekeken", nagekekenTentamen);
        System.out.println("gecommuniceerd met de gateway");
    }

    @Override
    public void ophalen() throws GatewayCommunicationException, SQLException {
        List<NagekekenTentamenDto> nagekekenTentamens = new ArrayList<>();
        //TODO url
        _tentamenDAO.setNaTeKijkenTentamens(this.serviceAgent.get("/tentamens/uitgevoerd", nagekekenTentamens.getClass()));
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
