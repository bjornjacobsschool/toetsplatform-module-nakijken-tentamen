package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsplatform.module.nakijken.model.Klas;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.nakijken.model.Versie;
import nl.han.toetsplatform.module.nakijken.model.Vraag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TentamenNakijken implements ITentamenNakijken {
    @Override
    public String getTestMessage() {
        return "Welkom";
    }

    @Override
    public List<Tentamen> getTentamens() {
        ArrayList<Tentamen> list = new ArrayList<>();
        ArrayList<Klas> AppKlassen = new ArrayList<Klas>();
        ArrayList<Klas> SwaKlassen = new ArrayList<Klas>();
        AppKlassen.add(new Klas("ASD-A N"));
        SwaKlassen.add(new Klas("ASD-A N"));
        SwaKlassen.add(new Klas("ASD-B N"));

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

        list.add(tentamen1);
        list.add(tentamen);
        return list;
    }


}
