package io.kermoss.cmd.infra.transporter.strategies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import feign.Client;
import io.kermoss.props.KermossProperties;
import io.kermoss.props.Layer;
import io.kermoss.props.Transport;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientStrategyTest {
	@Mock
	private KermossProperties kermossProperties;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private Client client;
	@InjectMocks
	private HttpClientStrategy httpClientStrategyUnderTest;
     
	@Test
    public void testGetMethodFactory() {
		Transport transport = new Transport();
		transport.setDefaultLayer(Layer.HTTP);
		when(kermossProperties.getTransport()).thenReturn(transport);
		assertThat(httpClientStrategyUnderTest.get()).isInstanceOf(RestCommandTransporterStrategy.class);
    	
    }
	
	@Test
    public void testGetMethodFactoryShouldReturnFeign() {
		Transport transport = new Transport();
		transport.setDefaultLayer(Layer.FEIGN);
		when(kermossProperties.getTransport()).thenReturn(transport);
		assertThat(httpClientStrategyUnderTest.get()).isInstanceOf(FeignCommandTransporterStrategy.class);
    	
	}


}