package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.inject.Inject;
import nl.han.toetsapplicatie.apimodels.dto.NagekekenTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsapplicatie.apimodels.dto.VragenbankVraagDto;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.data.IAntwoord_op_VraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;
    private IAntwoord_op_VraagDAO _antwoord_op_vraagDAO;
    private List<Antwoord_op_Vraag> nagekekenVragen = new ArrayList<>();


    @Inject
    public TentamenNakijken(IGatewayServiceAgent _gatewayServiceAgent, IAntwoord_op_VraagDAO _antwoord_op_vraagDAO){
        this.serviceAgent = _gatewayServiceAgent;
        this._antwoord_op_vraagDAO = _antwoord_op_vraagDAO;

    }

//    @Inject
//    public TentamenNakijken(IGatewayServiceAgent serviceAgent) {
//        this.serviceAgent = serviceAgent;
//    }

//    @Override
//    public void opslaan(NagekekenTentamenDto nagekekenTentamen) throws GatewayCommunicationException, SQLException {
//        _tentamenDAO.slaNagekekenTentamenOp(nagekekenTentamen);
//        this.serviceAgent.post("/tentamens/nagekeken", nagekekenTentamen);
//    }

        @Override
    public void opslaan(Antwoord_op_Vraag nagekekenvraag) throws GatewayCommunicationException, SQLException {
            _antwoord_op_vraagDAO.saveAntwoorden(nagekekenvraag);
            nagekekenVragen.add(nagekekenvraag);
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

    public void update(Antwoord_op_Vraag nagekekenvraag) throws GatewayCommunicationException, SQLException {
        _antwoord_op_vraagDAO.updateAntwoord(nagekekenvraag);
        nagekekenVragen.add(nagekekenvraag);
    }

    public List<Antwoord_op_Vraag> getNagekekenVragen() {
        return nagekekenVragen;
    }

    public void postNagekekenTentamen(NagekekenTentamenDto nagekekenTentamen)  {
        try {
            serviceAgent.post("POST /tentamens/nagekeken", nagekekenTentamen);
        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
        }
    }

}
