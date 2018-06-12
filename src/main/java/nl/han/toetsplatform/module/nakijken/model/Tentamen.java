package nl.han.toetsplatform.module.nakijken.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by chico_000 on 7-6-2018.
 */
public class Tentamen {
    private UUID id;
    private String naam;
    private String beschrijving;
    private String toegestaandeHulpmiddelen;
    private String vak;
    private int tijdsduur;
    private Versie versie;
    private boolean isVerzegeld;
    private String hash;
    private List<Vraag> vragen;
    private List<Klas> gemaaktDoorKlassen;
    private List<Student> studenten;

    public List<Student> getStudenten() {
        return studenten;
    }

    public void setStudenten(List<Student> studenten) {
        this.studenten = studenten;
    }

    public List<Klas> getGemaaktDoorKlassen() {
        return gemaaktDoorKlassen;
    }

    public void setGemaaktDoorKlassen(List<Klas> gemaaktDoorKlassen) {
        this.gemaaktDoorKlassen = gemaaktDoorKlassen;
    }

    public Tentamen() {
        vragen = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getToegestaandeHulpmiddelen() {
        return toegestaandeHulpmiddelen;
    }

    public void setToegestaandeHulpmiddelen(String toegestaandeHulpmiddelen) {
        this.toegestaandeHulpmiddelen = toegestaandeHulpmiddelen;
    }

    public int getTijdsduur() {
        return tijdsduur;
    }

    public void setTijdsduur(int tijdsduur) {
        this.tijdsduur = tijdsduur;
    }

    public Versie getVersie() {
        return versie;
    }

    public void setVersie(Versie versie) {
        this.versie = versie;
    }

    public boolean isVerzegeld() {
        return isVerzegeld;
    }

    public void setVerzegeld(boolean verzegeld) {
        isVerzegeld = verzegeld;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<Vraag> getVragen() {
        return vragen;
    }

    public void setVragen(List<Vraag> vragen) {
        this.vragen = vragen;
    }

    public String getVak() {
        return vak;
    }

    public void setVak(String vak) {
        this.vak = vak;
    }
}