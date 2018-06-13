package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import java.util.List;

public interface ITentamenDAO {

   // void saveTentamen(Tentamen tentamen);

    List<Tentamen> loadTentamens(String tentamencode, String tentamenversie);

}
