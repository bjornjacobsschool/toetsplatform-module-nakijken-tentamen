package nl.han.toetsplatform.module.nakijken.guice;

import com.google.inject.AbstractModule;
import nl.han.toetsplatform.module.nakijken.data.data.stub.StubStorageDao;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

public class StubGuiceModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(StorageDao.class).to(StubStorageDao.class);
    }
}
