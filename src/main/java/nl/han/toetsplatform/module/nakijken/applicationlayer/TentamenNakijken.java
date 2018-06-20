package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.inject.Inject;
import nl.han.toetsapplicatie.apimodels.dto.*;
import nl.han.toetsplatform.module.nakijken.data.data.IAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.AntwoordOpVraag;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.sql.SQLException;
import java.util.*;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;
    private IAntwoordOpVraagDAO _antwoord_op_vraagDAO;
    private List<AntwoordOpVraag> nagekekenVragen = new ArrayList<>();


    @Inject
    public TentamenNakijken(IGatewayServiceAgent _gatewayServiceAgent, IAntwoordOpVraagDAO _antwoord_op_vraagDAO){
        this.serviceAgent = _gatewayServiceAgent;
        this._antwoord_op_vraagDAO = _antwoord_op_vraagDAO;

    }

    @Override
    public void opslaan(AntwoordOpVraag nagekekenvraag) throws GatewayCommunicationException, SQLException {
        _antwoord_op_vraagDAO.saveAntwoorden(nagekekenvraag);
        nagekekenVragen.add(nagekekenvraag);
    }

    @Override
    public List<VragenbankVraagDto> getVragen() throws GatewayCommunicationException {
        ArrayList<VragenbankVraagDto> vragen = new ArrayList<>();
        vragen.addAll(Arrays.asList(this.serviceAgent.get("/vragenbank", VragenbankVraagDto[].class)));

//        VragenbankVraagDto vraagDto1 = createTestVraag(vragen);
//        vragen.add(vraagDto1);

        return vragen;
    }


    @Override
    public List<UitgevoerdTentamenDto> getUitgevoerdeTentamens() throws GatewayCommunicationException {
        ArrayList<UitgevoerdTentamenDto> uitgevoerdTentamenDtos = new ArrayList<>();
        uitgevoerdTentamenDtos.addAll(Arrays.asList(this.serviceAgent.get("tentamens/uitgevoerd", UitgevoerdTentamenDto[].class)));

//        UitgevoerdTentamenDto uitgevoerdTentamenDto = createUitgevoerdTentamen(33333);
//        UitgevoerdTentamenDto uitgevoerdTentamenDto1 = createUitgevoerdTentamen(4533);
//        uitgevoerdTentamenDtos.add(uitgevoerdTentamenDto);
//        uitgevoerdTentamenDtos.add(uitgevoerdTentamenDto1);

        return uitgevoerdTentamenDtos;
    }

    public void update(AntwoordOpVraag nagekekenvraag) {
        _antwoord_op_vraagDAO.updateAntwoord(nagekekenvraag);
        nagekekenVragen.add(nagekekenvraag);
    }

    public List<AntwoordOpVraag> getNagekekenVragen() {
        return nagekekenVragen;
    }

    public boolean postNagekekenTentamen(NagekekenTentamenDto nagekekenTentamen)  {
        try {
            serviceAgent.post("/tentamens/nagekeken", nagekekenTentamen);
            return true;
        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Test methode als de server neit werkt.
    private UitgevoerdTentamenDto createUitgevoerdTentamen(Integer naam) {
        UitgevoerdTentamenDto uitgevoerdTentamenDto = new UitgevoerdTentamenDto();
        uitgevoerdTentamenDto.setNaam("App toets 1");

        //student
        StudentDto studentDto = new StudentDto();
        studentDto.setKlas("APP");
        studentDto.setStudentNummer(naam);
        uitgevoerdTentamenDto.setStudent(studentDto);

        //id
        UUID uuid = new UUID(0, 0);
        uitgevoerdTentamenDto.setId(uuid);

        //versie
        VersieDto versieDto = new VersieDto();
        Date date = new Date();
        versieDto.setDatum(date.getTime());
        versieDto.setNummer(1);
        versieDto.setOmschrijving("Eerste versie");
        uitgevoerdTentamenDto.setVersie(versieDto);

        //vraag 1
        IngevuldeVraagDto ingevuldeVraagDto = new IngevuldeVraagDto();
        ingevuldeVraagDto.setVersie(versieDto);
        UUID uuid1 = new UUID(1, 1);
        ingevuldeVraagDto.setId(uuid1);
        ingevuldeVraagDto.setGegevenAntwoord("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"5\",\"5\",\"+\",\"8\",\"*\"],\"stack\":[[5],[5,5],[10],[8,10],[80]],\"expressie\":\"5 5 + 8 *\"}");

        //vraag 2
        IngevuldeVraagDto ingevuldeVraagDto2 = new IngevuldeVraagDto();
        ingevuldeVraagDto2.setVersie(versieDto);
        UUID uuid2 = new UUID(2, 2);
        ingevuldeVraagDto2.setId(uuid2);
        ingevuldeVraagDto2.setGegevenAntwoord("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"5\",\"5\",\"+\",\"8\",\"*\"],\"stack\":[[5],[5,5],[10],[8,10],[80]],\"expressie\":\"5 5 + 8 *\"}");

        //add vragen
        List<IngevuldeVraagDto> ingevuldeVraagDtos = new ArrayList<>();
        ingevuldeVraagDtos.add(ingevuldeVraagDto);
        ingevuldeVraagDtos.add(ingevuldeVraagDto2);
        uitgevoerdTentamenDto.setVragen(ingevuldeVraagDtos);

        uitgevoerdTentamenDto.setHash("HASH432423");
        return uitgevoerdTentamenDto;
    }

    //Test methode als de server niet werkt.
    private VragenbankVraagDto createTestVraag(ArrayList<VragenbankVraagDto> vragen) {
        VragenbankVraagDto vraagDto = new VragenbankVraagDto();
        UUID uuid1 = new UUID(1, 1);
        vraagDto.setId(uuid1);
        vraagDto.setVraagData("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"5\",\"5\",\"+\",\"8\",\"*\"],\"stack\":[[5],[5,5],[10],[8,10],[80]],\"expressie\":\"5 5 + 8 *\"}");
        vraagDto.setVraagtype("nl.han.toetsplatform.plugin.TekenPlugin");
        vraagDto.setPunten(10);
        vragen.add(vraagDto);

        VragenbankVraagDto vraagDto1 = new VragenbankVraagDto();
        UUID uuid2 = new UUID(2, 2);
        vraagDto1.setId(uuid2);
        vraagDto1.setVraagData("{\"vraag\":\"Teken stap voor stap de stack bij de volgende expressie:\",\"laatstToegevoegd\":[\"6\",\"5\",\"+\",\"8\",\"+\"],\"stack\":[[6],[5,5],[10],[8,10],[18]],\"expressie\":\"5 5 + 8 +\"}");
        vraagDto1.setVraagtype("nl.han.toetsplatform.plugin.TekenPlugin");
        vraagDto1.setPunten(5);
        return vraagDto1;
    }

}
