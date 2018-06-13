package nl.han.toetsplatform.module.nakijken.model;


import java.sql.Timestamp;

public class Vraag {

    private String vraagId;
    private String vraagVersie;
    private String thema;
    private String nakijkModelVersie;
    private String   vraagStelling;
    private String   vraagPlugin;
    private boolean   vraagOefenvraag;
    private String   vraagGemaaktDoor;
    private Timestamp vraagVersieDatum;
    private String   vraagVersieOmschrijving;



//    private String naam;
//    private String vraagType;

//    private String vraagData;
    //private int punten;
   // private String nakijkInstrucites;



    public String getVraagId() {
        return vraagId;
    }

    public void setVraagId(String vraagId) {
        this.vraagId = vraagId;
    }

//    public String getNaam() {
//        return naam;
//    }
//
//    public void setNaam(String naam) {
//        this.naam = naam;
//    }
//
//    public String getVraagType() {
//        return vraagType;
//    }
//
//    public void setVraagType(String vraagType) {
//        this.vraagType = vraagType;
//    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getVraagVersie() {
        return vraagVersie;
    }

    public void setVraagVersie(String vraagVersie) {
        this.vraagVersie = vraagVersie;
    }
//
//    public int getPunten() {
//        return punten;
//    }
//
//    public void setPunten(int punten) {
//        this.punten = punten;
//    }

//    public String getNakijkInstrucites() {
//        return nakijkInstrucites;
//    }
//
//    public void setNakijkInstrucites(String nakijkInstrucites) {
//        this.nakijkInstrucites = nakijkInstrucites;
//    }

    public String getNakijkModelVersie() {
        return nakijkModelVersie;
    }

    public void setNakijkModelVersie(String nakijkModelVersie) {
        this.nakijkModelVersie = nakijkModelVersie;
    }

//    public String getVraagData() {
//        return vraagData;
//    }
//
//    public void setVraagData(String vraagData) {
//        this.vraagData = vraagData;
//    }


    public String getVraagStelling() {
        return vraagStelling;
    }

    public void setVraagStelling(String vraagStelling) {
        this.vraagStelling = vraagStelling;
    }

    public String getVraagPlugin() {
        return vraagPlugin;
    }

    public void setVraagPlugin(String vraagPlugin) {
        this.vraagPlugin = vraagPlugin;
    }

    public boolean isVraagOefenvraag() {
        return vraagOefenvraag;
    }

    public void setVraagOefenvraag(boolean vraagOefenvraag) {
        this.vraagOefenvraag = vraagOefenvraag;
    }

    public String getVraagGemaaktDoor() {
        return vraagGemaaktDoor;
    }

    public void setVraagGemaaktDoor(String vraagGemaaktDoor) {
        this.vraagGemaaktDoor = vraagGemaaktDoor;
    }

    public Timestamp getVraagVersieDatum() {
        return vraagVersieDatum;
    }

    public void setVraagVersieDatum(Timestamp vraagVersieDatum) {
        this.vraagVersieDatum = vraagVersieDatum;
    }

    public String getVraagVersieOmschrijving() {
        return vraagVersieOmschrijving;
    }

    public void setVraagVersieOmschrijving(String vraagVersieOmschrijving) {
        this.vraagVersieOmschrijving = vraagVersieOmschrijving;
    }
}