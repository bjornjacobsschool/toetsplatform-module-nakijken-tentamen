package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Vraag;

import java.util.List;

public interface IVraagDAO {

    //void saveResultaten(Vraag tentamen);

    List<Vraag> loadVragen(String vraagId, String vraagVersie, String ThemaNaam);
}
