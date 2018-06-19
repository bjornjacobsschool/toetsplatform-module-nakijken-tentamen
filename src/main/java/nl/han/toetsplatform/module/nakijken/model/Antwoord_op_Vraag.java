package nl.han.toetsplatform.module.nakijken.model;

public class Antwoord_op_Vraag {

    private String vraagId;
    private String vraagVersie;
    private int studentNummer;
    private String tentamenCode;
    private String tentamenVersie;
    private String antwoord;
    private int behaaldePunten;
    private String nakijkComment;

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

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

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

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    public int getBehaaldePunten() {
        return behaaldePunten;
    }

    public void setBehaaldePunten(int behaaldePunten) {
        this.behaaldePunten = behaaldePunten;
    }

    public String getNakijkComment() {
        return nakijkComment;
    }

    public void setNakijkComment(String nakijkComment) {
        this.nakijkComment = nakijkComment;
    }
}
