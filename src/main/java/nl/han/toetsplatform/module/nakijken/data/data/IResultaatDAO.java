package nl.han.toetsplatform.module.nakijken.data.data;

import nl.han.toetsplatform.module.nakijken.model.Resultaat;

import java.util.List;

public interface IResultaatDAO {

    void saveResultaten(Resultaat resultaat);


    void updateResultaat(Resultaat resultaat);

    List<Resultaat> loadResultaten(String studentnummer, String tentamencode, String tentamenversie);
}
