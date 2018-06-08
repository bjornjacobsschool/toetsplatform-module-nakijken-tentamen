package nl.han.toetsplatform.module.nakijken.applicationlayer;

import nl.han.toetsplatform.module.nakijken.model.Tentamen;

import java.util.List;

public interface ITentamenNakijken {
    String getTestMessage();
    List<Tentamen> getTentamens();
}
