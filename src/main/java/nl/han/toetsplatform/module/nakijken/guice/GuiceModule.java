package nl.han.toetsplatform.module.nakijken.guice;

import com.google.inject.AbstractModule;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.applicationlayer.TentamenNakijken;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.serviceagent.GatewayServiceAgent;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Configure Guice here..
        bind(ITentamenNakijken.class).to(TentamenNakijken.class);
        bind(IGatewayServiceAgent.class).to(GatewayServiceAgent.class);
    }
}
