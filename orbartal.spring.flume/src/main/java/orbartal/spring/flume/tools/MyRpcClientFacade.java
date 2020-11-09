
package orbartal.spring.flume.tools;

import java.nio.charset.Charset;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

public class MyRpcClientFacade {

	private RpcClient client;
	private String hostname;
	private int port;

	public MyRpcClientFacade(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		this.client =  RpcClientFactory.getDefaultInstance(hostname, port);
				
				//.g.getThriftInstance(hostname, port);
	}

	public void sendDataToFlume(String data) {
		// Create a Flume Event object that encapsulates the sample data
		Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

		// Send the event
		try {
			client.append(event);
		} catch (EventDeliveryException e) {
			client.close();
			client = null;
			this.client = RpcClientFactory.getThriftInstance(hostname, port);
		}
	}
	
	//curl localhost:44444 --header "Content-Type: application/json" --data '[{"headers":{},"body":"body123"}]'

	public void cleanUp() {
		// Close the RPC connection
		client.close();
	}

}
