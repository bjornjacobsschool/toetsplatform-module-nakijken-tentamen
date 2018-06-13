package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;

import java.util.List;

public interface IAntwoord_op_VraagDAO {

    void saveAntwoorden(Antwoord_op_Vraag antwoord);

    void updateAntwoord(Antwoord_op_Vraag antwoord);

    List<Antwoord_op_Vraag> loadAntwoorden(String vraagId,String vraagVersie, String studentNummer, String tentamenCode, String tentamenVersie);
}

