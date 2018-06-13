package nl.han.toetsplatform.module.nakijken.model;

public class Vraag_van_Tentamen {


    private String tentamenCode;
    private String tentamenVersie;
    private String vraagId;
    private String vraagVersie;
    private float aantalPunten;

    public String getTentamenCode() {
        return tentamenCode;
    }

    public void setTentamenCode(String tentamenCode) {
        this.tentamenCode = tentamenCode;
    }

    public String getTentamenVersie() {
        return tentamenVersie;
    }

    public void setTentamenVersie(String tentamenVersie) {
        this.tentamenVersie = tentamenVersie;
    }

    public String getVraagId() {
        return vraagId;
    }

    public void setVraagId(String vraagId) {
        this.vraagId = vraagId;
    }

    public String getVraagVersie() {
        return vraagVersie;
    }

    public void setVraagVersie(String vraagVersie) {
        this.vraagVersie = vraagVersie;
    }

    public float getAantalPunten() {
        return aantalPunten;
    }

    public void setAantalPunten(float aantalPunten) {
        this.aantalPunten = aantalPunten;
    }
}
