package nl.han.toetsplatform.module.nakijken.serviceagent;

import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.models.GatewayTestMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GatewayServiceAgentTests {
    private GatewayServiceAgent _sut;

    @Before
    public void initialize() {
        _sut = new GatewayServiceAgent();
    }

    @Test
    public void testOfServiceAgentHalloWereldVanGatewayTerugKrijgt() throws GatewayCommunicationException {// i
        GatewayTestMessage response = _sut.get("test/Henk", GatewayTestMessage.class);
        assertEquals("Hallo Henk!", response.message);
    }
}
