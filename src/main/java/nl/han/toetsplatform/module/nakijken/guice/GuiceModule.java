package nl.han.toetsplatform.module.nakijken.guice;

import com.google.inject.AbstractModule;
import nl.han.toetsplatform.module.nakijken.applicationlayer.ITentamenNakijken;
import nl.han.toetsplatform.module.nakijken.applicationlayer.TentamenNakijken;
import nl.han.toetsplatform.module.nakijken.data.*;
import nl.han.toetsplatform.module.nakijken.data.sql.*;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Configure Guice here..
        bind(ITentamenNakijken.class).to(TentamenNakijken.class);
        bind(INakijkmodelDAO.class).to(SQLNakijkModelDAO.class);
        bind(IVraagDAO.class).to(SQLVraagDAO.class);
        bind(IResultaatDAO.class).to(SQLResultaatDAO.class);
        bind(IVraag_van_TentamenDAO.class).to(SQLVraagVanTentamenDAO.class);
        bind(IAntwoord_op_VraagDAO.class).to(SQLAntwoordOpVraagDAO.class);
    }
}
