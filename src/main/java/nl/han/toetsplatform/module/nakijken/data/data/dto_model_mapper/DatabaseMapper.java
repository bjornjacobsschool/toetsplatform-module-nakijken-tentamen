package nl.han.toetsplatform.module.nakijken.data.data.dto_model_mapper;

import nl.han.toetsapplicatie.apimodels.dto.*;

import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.data.data.sql.*;
import nl.han.toetsplatform.module.nakijken.data.data.stub.StubStorageDao;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.*;
import nl.han.toetsplatform.module.nakijken.serviceagent.GatewayServiceAgent;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DatabaseMapper {
    private SQLLoader myLoader = new SQLLoader();
    @Inject
    private StorageDao myStorage;
    private GatewayServiceAgent myGateway = new GatewayServiceAgent();

    public void fillDatabase(UitgevoerdTentamenDto uitgevoerdTentamen) {
        try {
            Tentamen nieuwTentamen = new Tentamen();
            KlaargezetTentamenDto klaargezetTentamen;
            SamengesteldTentamenDto  samengesteldTentamen;
            List<IngevuldeVraagDto> ingevuldeVragen;

            klaargezetTentamen = myGateway.get("/tentamens/klaargezet/" + uitgevoerdTentamen.getId().toString(), KlaargezetTentamenDto.class);
            samengesteldTentamen = myGateway.get("/tentamens/samengesteld/" + uitgevoerdTentamen.getId().toString(), SamengesteldTentamenDto.class);
            ingevuldeVragen = uitgevoerdTentamen.getVragen();

            int versie = uitgevoerdTentamen.getVersie().getNummer();
            long versieDatum = uitgevoerdTentamen.getVersie().getDatum();
            long klaargezet = klaargezetTentamen.getStartdatum();
            List<VragenbankVraagDto> tentamenVragen = samengesteldTentamen.getVragen();

            nieuwTentamen.setTentamenCode(uitgevoerdTentamen.getId().toString());
            nieuwTentamen.setTentamenVersie(Integer.toString(versie));
            nieuwTentamen.setTentamenNaam(uitgevoerdTentamen.getNaam());
            nieuwTentamen.setTentamenTijdsduur(klaargezetTentamen.getTijdsduur());
            nieuwTentamen.setTentamenKlaarzetDatum(new Date(klaargezet));
            nieuwTentamen.setTentamenVersieDatum(new Timestamp(versieDatum));
            nieuwTentamen.setTentamenVersieOmschrijving(uitgevoerdTentamen.getVersie().getOmschrijving());

            SQLTentamenDAO tentamenDAO = new SQLTentamenDAO(myStorage, myLoader);

            if(tentamenDAO.loadTentamens( nieuwTentamen.getTentamenCode(), "%").size() == 0){

                tentamenDAO.saveTentamen(nieuwTentamen);

                for(int i = 0; i < tentamenVragen.size(); i++){
                    VragenbankVraagDto tempTentamenVraag = tentamenVragen.get(i);
                    fillVragen(tempTentamenVraag);
                    fillVraagInTentamen(uitgevoerdTentamen, tempTentamenVraag);
                }
            }

            for(int i = 0; i < ingevuldeVragen.size(); i++){
                IngevuldeVraagDto tempIngevuldeVraag = ingevuldeVragen.get(i);
                fillAntwoordOpVraag(tempIngevuldeVraag, uitgevoerdTentamen);
            }

            fillStudenten(uitgevoerdTentamen.getStudent());

        } catch (GatewayCommunicationException e) {
            e.printStackTrace();
        }
    }


    public void fillVragen(VragenbankVraagDto vraag){
        Vraag nieuweVraag = new Vraag();
        int versie = vraag.getVersie().getNummer();
        long versieDatum = vraag.getVersie().getDatum();

        nieuweVraag.setVraagId(vraag.getId().toString());
        nieuweVraag.setVraagVersie(Integer.toString(versie));
        nieuweVraag.setVraagNaam(vraag.getNaam());
        nieuweVraag.setVraagType(vraag.getVraagtype());
        nieuweVraag.setVraagThema(vraag.getThema());
        nieuweVraag.setVraagCorrecteAntwoorden(vraag.getNakijkModel());
        nieuweVraag.setVraagNakijkInstructies(vraag.getNakijkInstructies());
        nieuweVraag.setNakijkModelVersie(Integer.toString(versie));
        nieuweVraag.setVraagStelling(vraag.getVraagData());
        nieuweVraag.setVraagVersieDatum(new Timestamp(versieDatum));
        nieuweVraag.setVraagVersieOmschrijving(vraag.getVersie().getOmschrijving());

        SQLVraagDAO vraagDAO = new SQLVraagDAO(myLoader, myStorage);
        vraagDAO.saveVraag(nieuweVraag);
    }

    public void fillVraagInTentamen(UitgevoerdTentamenDto uitgevoerdTentamen, VragenbankVraagDto vraag){
        VraagVanTentamen vraagInTentamen = new VraagVanTentamen();
        int tentamenVersie = uitgevoerdTentamen.getVersie().getNummer();
        int vraagVersie = vraag.getVersie().getNummer();

        vraagInTentamen.setTentamenCode(uitgevoerdTentamen.getId().toString());
        vraagInTentamen.setTentamenVersie(Integer.toString(tentamenVersie));
        vraagInTentamen.setVraagId(vraag.getId().toString());
        vraagInTentamen.setVraagVersie(Integer.toString(vraagVersie));
        vraagInTentamen.setAantalPunten(vraag.getPunten());

        SQLVraagVanTentamenDAO vraagVanTentamenDAO = new SQLVraagVanTentamenDAO(myStorage,myLoader);
    }

    public void fillAntwoordOpVraag(IngevuldeVraagDto ingevuldeVraag, UitgevoerdTentamenDto uitgevoerdTentamen) {
        AntwoordOpVraag nieuwAntwoord = new AntwoordOpVraag();
        int versie = ingevuldeVraag.getVersie().getNummer();
        int tentamenversie = uitgevoerdTentamen.getVersie().getNummer();

        nieuwAntwoord.setVraagId(ingevuldeVraag.getId().toString());
        nieuwAntwoord.setVraagVersie(Integer.toString(versie));
        nieuwAntwoord.setStudentNummer(uitgevoerdTentamen.getStudent().getStudentNummer());
        nieuwAntwoord.setTentamenCode(uitgevoerdTentamen.getId().toString());
        nieuwAntwoord.setTentamenVersie(Integer.toString(tentamenversie));
        nieuwAntwoord.setAntwoord(ingevuldeVraag.getGegevenAntwoord());

        SQLAntwoordOpVraagDAO antwoordDAO = new SQLAntwoordOpVraagDAO(myStorage, myLoader);
        antwoordDAO.saveAntwoorden(nieuwAntwoord);
    }

    public  void fillStudenten(StudentDto student){
        Student nieuwstudent = new Student();

        nieuwstudent.setStudentNummer(student.getStudentNummer());
        nieuwstudent.setKlas(student.getKlas());

        SQLStudentDAO studentDAO = new SQLStudentDAO(myStorage, myLoader);
        studentDAO.saveStudent(nieuwstudent);
    }

    public List<NagekekenVraagDto> naarNagekenTentamenDTO(List<IngevuldeVraagDto> ingevuldeVragen, List<AntwoordOpVraag> negekekenVragen){
        List<NagekekenVraagDto> nagekenVragen = new ArrayList<>();

        for(IngevuldeVraagDto ingevuldeVraag : ingevuldeVragen){
            for(AntwoordOpVraag nagekekenVraag : negekekenVragen){
               if( ingevuldeVraag.getId().toString().equals(nagekekenVraag.getTentamenCode())){
                   NagekekenVraagDto nieuweNagekenVraag = new NagekekenVraagDto();
                   nieuweNagekenVraag.setId(UUID.fromString(nagekekenVraag.getVraagId()));
                   nieuweNagekenVraag.setBehaaldePunten(nagekekenVraag.getBehaaldePunten());
                   nieuweNagekenVraag.setNakijkComment(nagekekenVraag.getNakijkComment());
                   nieuweNagekenVraag.setVersie(ingevuldeVraag.getVersie());
                   nagekenVragen.add(nieuweNagekenVraag);
               }
            }
        }
    return nagekenVragen;
    }

    public NagekekenTentamenDto createNagekenTentameDTO(UitgevoerdTentamenDto uitgevoerdTentamen, List<NagekekenVraagDto> nagekekenVragen, int cijfer){
        NagekekenTentamenDto nieuwNagekenTentamen = new NagekekenTentamenDto();

        nieuwNagekenTentamen.setId(uitgevoerdTentamen.getId());
        nieuwNagekenTentamen.setCijfer(cijfer);
        nieuwNagekenTentamen.setVersie(uitgevoerdTentamen.getVersie());
        nieuwNagekenTentamen.setStudent(uitgevoerdTentamen.getStudent());
        nieuwNagekenTentamen.setVragen(nagekekenVragen);

        return nieuwNagekenTentamen;
    }
}
