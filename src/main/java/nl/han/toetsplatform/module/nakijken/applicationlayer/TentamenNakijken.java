package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.models.NagekekenTentamen;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import javax.inject.Inject;
import java.sql.SQLException;
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
    public void opslaan(NagekekenTentamen nagekekenTentamen) throws GatewayCommunicationException, SQLException {
        _tentamenDAO.slaNagekekenTentamenOp(nagekekenTentamen);

        //Tentamen versturen naar gateway
        //todo URL specificeren, gateway moet nog gemaakt worden.
        this._gatewayServiceAgent.post("", nagekekenTentamen);
        System.out.println("gecommuniceerd met de gateway");
    }

    @Override
    public List<NagekekenTentamen> getNaTeKijkenTentamens(String vak) {
        return _tentamenDAO.getNaTeKijkenTentamens(vak);
    }

    @Override
    public String getTestMessage() {
        return "Welkom";
    }
}
