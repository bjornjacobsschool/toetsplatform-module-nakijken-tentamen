package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Tentamen_Moment;

import java.util.List;

public interface ITentamen_MomentDAO {

    //void saveResultaten(Tentamen_Moment tentamen);

    List<Tentamen_Moment> loadTentamenMomenten();
}
