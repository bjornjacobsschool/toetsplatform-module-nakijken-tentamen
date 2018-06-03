package nl.han.toetsplatform.module.nakijken.serviceagent;

import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;

public interface IGatewayServiceAgent {
    <T> T get(String resourceUrl, Class<T> type) throws GatewayCommunicationException;

    <T> void post(String resourceUrl, T entity) throws GatewayCommunicationException;
}
