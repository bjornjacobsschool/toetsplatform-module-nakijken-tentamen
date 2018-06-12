package nl.han.toetsplatform.module.nakijken.model;

import java.util.UUID;

/**
 * Created by chico_000 on 11-6-2018.
 */
public class IngevuldeVraag {
    private UUID id;
    private String gegevenAntwoord;

    public IngevuldeVraag(String gegevenAntwoord) {
        this.gegevenAntwoord = gegevenAntwoord;
    }

    public String getGegevenAntwoord() {
        return gegevenAntwoord;
    }

    public void setGegevenAntwoord(String gegevenAntwoord) {
        this.gegevenAntwoord = gegevenAntwoord;
    }


}
