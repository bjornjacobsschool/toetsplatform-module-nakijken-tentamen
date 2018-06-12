package nl.han.toetsplatform.module.nakijken.model;

/**
 * Created by chico_000 on 7-6-2018.
 */
public class Student {
    private String naam;
    private int studentNummer;

    public Student(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }
}
