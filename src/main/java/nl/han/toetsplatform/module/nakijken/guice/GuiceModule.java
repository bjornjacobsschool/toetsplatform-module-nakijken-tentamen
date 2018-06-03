package nl.han.toetsplatform.module.nakijken.guice;

import com.google.inject.AbstractModule;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.applicationlayer.TentamenNakijken;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Configure Guice here..
        bind(ITentamenNakijken.class).to(TentamenNakijken.class);
    }
}
