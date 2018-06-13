package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Vraag_van_Tentamen;

import java.util.List;

public interface IVraag_van_TentamenDAO {

    //void saveResultaten(Vraag_van_Tentamen tentamen);

    List<Vraag_van_Tentamen> loadTentamenVragen(String tentamenCode, String tentamenVersie, String vraagId, String vraagVersie);
}
