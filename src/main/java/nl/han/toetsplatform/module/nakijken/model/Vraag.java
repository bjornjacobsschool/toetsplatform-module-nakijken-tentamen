package nl.han.toetsplatform.module.nakijken.model;


import java.sql.Timestamp;
import java.util.UUID;

public class Vraag {

    private String vraagId;
    private String vraagVersie;
    private String vraagNaam;
    private String vraagType;
    private String vraagThema;
    private String vraagCorrecteAntwoorden;
    private String vraagNakijkInstructies;
    private String nakijkModelVersie;
    private String   vraagStelling;
    private Timestamp vraagVersieDatum;
    private String   vraagVersieOmschrijving;


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

//    public String getThema() {
//        return vraagThema;
//    }
//
//    public void setThema(String thema) {
//        this.vraagThema = thema;
//    }

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

    public String getVraagNaam() {
        return vraagNaam;
    }

    public void setVraagNaam(String vraagNaam) {
        this.vraagNaam = vraagNaam;
    }

    public String getVraagType() {
        return vraagType;
    }

    public void setVraagType(String vraagType) {
        this.vraagType = vraagType;
    }

    public String getVraagThema() {
        return vraagThema;
    }

    public void setVraagThema(String vraagThema) {
        this.vraagThema = vraagThema;
    }

    public String getVraagCorrecteAntwoorden() {
        return vraagCorrecteAntwoorden;
    }

    public void setVraagCorrecteAntwoorden(String vraagCorrecteAntwoorden) {
        this.vraagCorrecteAntwoorden = vraagCorrecteAntwoorden;
    }


    public String getVraagNakijkInstructies() {
        return vraagNakijkInstructies;
    }

    public void setVraagNakijkInstructies(String vraagNakijkInstructies) {
        this.vraagNakijkInstructies = vraagNakijkInstructies;
    }
}