package nl.han.toetsplatform.module.nakijken.model;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tentamen {
    private String tentamenCode;
    private String tentamenVersie;
    private String tentamenMomentId;
    private String tentamenNaam;
    private Time tentamenTijdsduur;
    private Date tentamenKlaarzetDatum;
    private Timestamp tentamenVersieDatum;
    private String tentamenVersieOmschrijving;
//    private String toegestaandeHulpmiddelen;
//    private String vak;
//
//    private boolean isVerzegeld;
//    private String hash;
//    private List<Vraag> vragen;
//    private List<Klas> gemaaktDoorKlassen;


//    public List<Klas> getGemaaktDoorKlassen() {
//        return gemaaktDoorKlassen;
//    }
//
//    public void setGemaaktDoorKlassen(List<Klas> gemaaktDoorKlassen) {
//        this.gemaaktDoorKlassen = gemaaktDoorKlassen;
//    }
//
//    public Tentamen() {
//        vragen = new ArrayList<>();
//    }

    public String getTentamenCode() {
        return tentamenCode;
    }

    public void setTentamenCode(String tentamenCode) {
        this.tentamenCode = tentamenCode;
    }

    public String getTentamenNaam() {
        return tentamenNaam;
    }

    public void setTentamenNaam(String tentamenNaam) {
        this.tentamenNaam = tentamenNaam;
    }

    public String getTentamenVersieOmschrijving() {
        return tentamenVersieOmschrijving;
    }

    public void setTentamenVersieOmschrijving(String tentamenVersieOmschrijving) {
        this.tentamenVersieOmschrijving = tentamenVersieOmschrijving;
    }

//    public String getToegestaandeHulpmiddelen() {
//        return toegestaandeHulpmiddelen;
//    }
//
//    public void setToegestaandeHulpmiddelen(String toegestaandeHulpmiddelen) {
//        this.toegestaandeHulpmiddelen = toegestaandeHulpmiddelen;
//    }

    public Time getTentamenTijdsduur() {
        return tentamenTijdsduur;
    }

    public void setTentamenTijdsduur(Time tentamenTijdsduur) {
        this.tentamenTijdsduur = tentamenTijdsduur;
    }

    public String getTentamenVersie() {
        return tentamenVersie;
    }

    public void setTentamenVersie(String tentamenVersie) {
        this.tentamenVersie = tentamenVersie;
    }

    public String getTentamenMomentId() {
        return tentamenMomentId;
    }

    public void setTentamenMomentId(String tentamenMomentId) {
        this.tentamenMomentId = tentamenMomentId;
    }

    public Date getTentamenKlaarzetDatum() {
        return tentamenKlaarzetDatum;
    }

    public void setTentamenKlaarzetDatum(Date tentamenKlaarzetDatum) {
        this.tentamenKlaarzetDatum = tentamenKlaarzetDatum;
    }

    public Timestamp getTentamenVersieDatum() {
        return tentamenVersieDatum;
    }

    public void setTentamenVersieDatum(Timestamp tentamenVersieDatum) {
        this.tentamenVersieDatum = tentamenVersieDatum;
    }

    //    public boolean isVerzegeld() {
//        return isVerzegeld;
//    }
//
//    public void setVerzegeld(boolean verzegeld) {
//        isVerzegeld = verzegeld;
//    }
//
//    public String getHash() {
//        return hash;
//    }
//
//    public void setHash(String hash) {
//        this.hash = hash;
//    }
//
//    public List<Vraag> getVragen() {
//        return vragen;
//    }
//
//    public void setVragen(List<Vraag> vragen) {
//        this.vragen = vragen;
//    }
//
//    public String getVak() {
//        return vak;
//    }
//
//    public void setVak(String vak) {
//        this.vak = vak;
//    }
}