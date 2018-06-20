package nl.han.toetsplatform.module.nakijken.data.data;

import nl.han.toetsplatform.module.nakijken.model.AntwoordOpVraag;

import java.util.List;

public interface IAntwoordOpVraagDAO {

    void saveAntwoorden(AntwoordOpVraag antwoord);

    void updateAntwoord(AntwoordOpVraag antwoord);

    List<AntwoordOpVraag> loadAntwoorden(String vraagId, String vraagVersie, String tentamenCode, String tentamenVersie);
    List<AntwoordOpVraag> loadAllAntwoorden();
}

