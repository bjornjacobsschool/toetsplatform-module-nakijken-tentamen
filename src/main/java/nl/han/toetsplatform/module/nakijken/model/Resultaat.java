package nl.han.toetsplatform.module.nakijken.model;

public class Resultaat {

    private int studentNummer;
    private String tentamenCode;
    private String tentamenVersie;
    private float cijfer;
    //private String beoordelendeDocent;
    private String hash;


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

    public float getCijfer() {
        return cijfer;
    }

    public void setCijfer(float cijfer) {
        this.cijfer = cijfer;
    }

//    public String getBeoordelendeDocent() {
//        return beoordelendeDocent;
//    }
//
//    public void setBeoordelendeDocent(String beoordelendeDocent) {
//        this.beoordelendeDocent = beoordelendeDocent;
//    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
