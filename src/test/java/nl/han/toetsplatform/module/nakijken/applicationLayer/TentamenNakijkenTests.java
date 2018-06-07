package nl.han.toetsplatform.module.nakijken.applicationLayer;


import nl.han.toetsplatform.module.nakijken.applicationlayer.TentamenNakijken;
import nl.han.toetsplatform.module.nakijken.data.TentamenDAO;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.models.NagekekenTentamen;
import nl.han.toetsplatform.module.nakijken.serviceagent.IGatewayServiceAgent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TentamenNakijkenTests {
    private NagekekenTentamen nagekekenTentamen;

    @Mock
    private IGatewayServiceAgent _gatewayServiceAgentMock;

    @Mock
    private TentamenDAO _tentamenDAOMock;

    @InjectMocks
    private TentamenNakijken _sut;

    @Before
    public void initialize()throws SQLException{
        nagekekenTentamen = Mockito.spy(new NagekekenTentamen());
    }

    @Test
    public void opslaanShouldCallGetOnGatewayServiceAgentWithCorrectParameter() throws GatewayCommunicationException, SQLException {
        _sut.opslaan(nagekekenTentamen);
        verify(_gatewayServiceAgentMock, times(1))
                .post("", nagekekenTentamen);
    }

    @Test
    public void opslaanShouldCallExecuteQueryOnStorageDao() throws GatewayCommunicationException, SQLException {
        _sut.opslaan(nagekekenTentamen);
        verify(_tentamenDAOMock).slaNagekekenTentamenOp(nagekekenTentamen);
    }
}
