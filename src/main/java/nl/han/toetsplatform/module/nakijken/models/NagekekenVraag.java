package nl.han.toetsplatform.module.nakijken.models;

public class NagekekenVraag {
    private String id;
    private String naam;
    private String vraagType;
    private String thema;
    private Versie versie;
    private int punten;
    private String nakijkInstrucites;

    private String nakijkModel;
    private String vraagData;

    private String antwoordData;
    private int gegevevenPunten;
    private String commentaarOpAntwoord;

    public String getAntwoordData() {
        return antwoordData;
    }

    public void setAntwoordData(String antwoordData) {
        this.antwoordData = antwoordData;
    }

    public int getGegevevenPunten() {
        return gegevevenPunten;
    }

    public void setGegevevenPunten(int gegevevenPunten) {
        this.gegevevenPunten = gegevevenPunten;
    }

    public String getCommentaarOpAntwoord() {
        return commentaarOpAntwoord;
    }

    public void setCommentaarOpAntwoord(String commentaarOpAntwoord) {
        this.commentaarOpAntwoord = commentaarOpAntwoord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVraagType() {
        return vraagType;
    }

    public void setVraagType(String vraagType) {
        this.vraagType = vraagType;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public Versie getVersie() {
        return versie;
    }

    public void setVersie(Versie versie) {
        this.versie = versie;
    }

    public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    public String getNakijkInstrucites() {
        return nakijkInstrucites;
    }

    public void setNakijkInstrucites(String nakijkInstrucites) {
        this.nakijkInstrucites = nakijkInstrucites;
    }

    public String getNakijkModel() {
        return nakijkModel;
    }

    public void setNakijkModel(String nakijkModel) {
        this.nakijkModel = nakijkModel;
    }

    public String getVraagData() {
        return vraagData;
    }

    public void setVraagData(String vraagData) {
        this.vraagData = vraagData;
    }
}
