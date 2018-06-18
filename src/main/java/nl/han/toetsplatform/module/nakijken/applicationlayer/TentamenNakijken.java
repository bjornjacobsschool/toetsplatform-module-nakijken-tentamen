package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.inject.Inject;
import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.VragenbankVraagDto;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;
    private TentamenDAO _tentamenDAO;

//    @Inject
//    public TentamenNakijken(IGatewayServiceAgent _gatewayServiceAgent, TentamenDAO _tentamenDAO){
//        this.serviceAgent = _gatewayServiceAgent;
//        this._tentamenDAO = _tentamenDAO;
//
//    }

    @Inject
    public TentamenNakijken(IGatewayServiceAgent serviceAgent) {
        this.serviceAgent = serviceAgent;
    }

    @Override
    public void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException {
        _tentamenDAO.slaNagekekenTentamenOp(nagekekenTentamen);
        this.serviceAgent.post("/tentamens/nagekeken", nagekekenTentamen);
    }

    @Override
    public List<VragenbankVraagDto> getVragen() throws GatewayCommunicationException {
        ArrayList<VragenbankVraagDto> vragen = new ArrayList<>();
        vragen.addAll(Arrays.asList(this.serviceAgent.get("/vragenbank", VragenbankVraagDto[].class)));
        return vragen;
    }

    @Override
    public List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException {
        ArrayList<UitgevoerdTentamenDto> uitgevoerdTentamenDtos = new ArrayList<>();
        uitgevoerdTentamenDtos.addAll(Arrays.asList(this.serviceAgent.get("tentamens/uitgevoerd", UitgevoerdTentamenDto[].class)));
        return uitgevoerdTentamenDtos;
    }
}
