package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.NakijkModel;

import java.util.List;

public interface INakijkmodelDAO {

   // void saveResultaten(NakijkModel tentamen);

    List<NakijkModel> loadNakijkmodel(String nakijkModelVersie, String vraagId, String vraagVersie);
}

