package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent _gatewayServiceAgent;
    private TentamenDAO _tentamenDAO;

    @Inject
    public TentamenNakijken(IGatewayServiceAgent _gatewayServiceAgent, TentamenDAO _tentamenDAO){
        this._gatewayServiceAgent = _gatewayServiceAgent;
        this._tentamenDAO = _tentamenDAO;

    }

    @Override
    public void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException {
        _tentamenDAO.slaNagekekenTentamenOp(nagekekenTentamen);
        this._gatewayServiceAgent.post("/tentamens/nagekeken", nagekekenTentamen);
        System.out.println("gecommuniceerd met de gateway");
    }

    @Override
    public void ophalen() throws GatewayCommunicationException, SQLException {
        List<NagekekenTentamenDto> nagekekenTentamens = new ArrayList<>();
        //TODO url
        _tentamenDAO.setNaTeKijkenTentamens(this._gatewayServiceAgent.get("/tentamens/uitgevoerd", nagekekenTentamens.getClass()));
    }
}
