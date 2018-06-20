package nl.han.toetsplatform.module.nakijken.data.data;

import nl.han.toetsplatform.module.nakijken.model.VraagVanTentamen;

import java.util.List;

public interface IVraagVanTentamenDAO {

    void saveVraagVanTentamen(VraagVanTentamen vraagVanTentamen);

    List<VraagVanTentamen> loadTentamenVragen(String tentamenCode, String tentamenVersie, String vraagId, String vraagVersie);
}
