package nl.han.toetsplatform.module.nakijken.applicationlayer;

import com.google.inject.Inject;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.*;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    private IGatewayServiceAgent serviceAgent;

    @Override
    public String getTestMessage() {
        return "Welkom";
    }

    @Override
    public List<UitgevoerdTentamen> getUitgevoerdeTentamens() throws GatewayCommunicationException {
        Class<List<UitgevoerdTentamen>> uitgevoerdeTentamensListClass = (Class) List.class;
        List<UitgevoerdTentamen> uitgevoerdeTentamens = this.serviceAgent.get("tentamens/uitgevoerd", uitgevoerdeTentamensListClass);
        return uitgevoerdeTentamens;
    }

    @Override
    public List<Tentamen> getTentamens() {
        ArrayList<Tentamen> list = new ArrayList<>();
        ArrayList<Klas> AppKlassen = new ArrayList<Klas>();
        ArrayList<Klas> SwaKlassen = new ArrayList<Klas>();
        Klas ASDAN = new Klas("ASD-A N");
        Klas ASDBN = new Klas("ASD-B N");
        AppKlassen.add(ASDAN);
        SwaKlassen.add(ASDAN);
        SwaKlassen.add(ASDBN);

        Student student1 = new Student("student1");
        Student student2 = new Student("student2");
        Student student3 = new Student("student3");
        ArrayList<Student> studenten = new ArrayList<Student>();
        studenten.add(student1);
        studenten.add(student2);
        studenten.add(student3);
        ArrayList<Student> studenten2 = new ArrayList<Student>();
        studenten2.add(student1);
        studenten2.add(student2);
        ASDAN.setStudenten(studenten);
        ASDBN.setStudenten(studenten2);

        Tentamen tentamen1 = new Tentamen();
        tentamen1.setNaam("App Algorithmes 1");
        tentamen1.setBeschrijving("Beschrijving veld");
        tentamen1.setToegestaandeHulpmiddelen("Hulpmiddeleen");
        tentamen1.setVak("APP");
        tentamen1.setTijdsduur(90);
        Versie versie = new Versie();
        tentamen1.setGemaaktDoorKlassen(AppKlassen);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        versie.setDatum(dateFormat.format(date));
        versie.setNumber("1.0");
        versie.setOmschrijving("Eerste versie...");
        tentamen1.setVersie(versie);

        tentamen1.setVerzegeld(false);
        tentamen1.setHash("Hashh");
        List<Vraag> vragen = new ArrayList<>();
        Vraag vraag1 = new Vraag();
        vraag1.setNaam("Bomenvraag");
        vraag1.setVraagType("Graph");
        vraag1.setThema("Grahp Thema");
        vraag1.setVersie(versie);
        vraag1.setPunten(5);
        vraag1.setNakijkInstrucites("Nakijkinstructies voor het nakijken van de vraag");
        vraag1.setNakijkModel("Nakijkmodel van de vraag");
        vraag1.setVraagData("Data voor de vraag");
        vragen.add(vraag1);

        Vraag vraag2 = new Vraag();
        vraag2.setNaam("Bomenvraag");
        vraag2.setVraagType("Graph");
        vraag2.setThema("Grahp Thema");
        vraag2.setVersie(versie);
        vraag2.setPunten(5);
        vraag2.setNakijkInstrucites("Nakijkinstructies voor het nakijken van de vraag");
        vraag2.setNakijkModel("Nakijkmodel van de vraag");
        vraag2.setVraagData("Data voor de vraag");
        vragen.add(vraag2);

        tentamen1.setVragen(vragen);

        Tentamen tentamen = new Tentamen();
        tentamen.setVak("SWA");
        tentamen.setNaam("SWA 1");
        tentamen.setBeschrijving("Beschrijving veld");
        tentamen.setToegestaandeHulpmiddelen("Hulpmiddeleen");
        tentamen.setTijdsduur(90);
        tentamen.setGemaaktDoorKlassen(SwaKlassen);
        tentamen.setVersie(new Versie());

        tentamen.setStudenten(studenten);
        tentamen1.setStudenten(studenten2);
        list.add(tentamen1);
        list.add(tentamen);
        return list;
    }

    @Inject
    public TentamenNakijken(IGatewayServiceAgent serviceAgent) {
        this.serviceAgent = serviceAgent;
    }
}
