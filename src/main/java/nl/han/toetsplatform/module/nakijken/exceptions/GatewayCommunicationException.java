package nl.han.toetsplatform.module.nakijken.exceptions;

public class GatewayCommunicationException  extends Throwable {
    public GatewayCommunicationException() {
        super("Er kan geen verbinding worden gemaakt met de API Gateway");
    }
}
