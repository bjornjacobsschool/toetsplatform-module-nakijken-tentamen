package nl.han.toetsplatform.module.nakijken.guice;

import com.google.inject.AbstractModule;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.applicationlayer.TentamenNakijken;
import nl.han.toetsplatform.module.nakijken.data.data.IAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.IResultaatDAO;
import nl.han.toetsplatform.module.nakijken.data.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.IVraagVanTentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLResultaatDAO;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.sql.SQLVraagVanTentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.data.stub.StubStorageDao;
import nl.han.toetsplatform.module.nakijken.serviceagent.GatewayServiceAgent;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Configure Guice here..
        bind(ITentamenNakijken.class).to(TentamenNakijken.class);
        bind(IGatewayServiceAgent.class).to(GatewayServiceAgent.class);
        bind(ITentamenNakijken.class).to(TentamenNakijken.class);
        bind(IVraagDAO.class).to(SQLVraagDAO.class);
        bind(IResultaatDAO.class).to(SQLResultaatDAO.class);
        bind(IVraagVanTentamenDAO.class).to(SQLVraagVanTentamenDAO.class);
        bind(IAntwoordOpVraagDAO.class).to(SQLAntwoordOpVraagDAO.class);
        bind(IGatewayServiceAgent.class).to(GatewayServiceAgent.class);

    }


}
