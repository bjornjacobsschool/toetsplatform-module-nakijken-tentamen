package nl.han.toetsplatform.module.nakijken.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by chico_000 on 12-6-2018.
 */
public class UitgevoerdTentamen {
    private UUID id;
    private String naam;
    private String hash;

    private Student student;
    private Versie versie;
    private List<IngevuldeVraag> vragen;

    public Student getStudent() {
        return student;
    }

    public UitgevoerdTentamen(Student student) {
        this.student = student;
    }

    public void setVragen(List<IngevuldeVraag> vragen) {
        this.vragen = vragen;
    }

    public List<IngevuldeVraag> getVragen() {
        return vragen;
    }
}
