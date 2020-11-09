package orbartal.spring.flume.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import orbartal.spring.flume.api.rest.model.FlumSendRequest;
import orbartal.spring.flume.tools.MyRestClient;

@Component
public class FlumeApplication {

	private final List<String> messages = new ArrayList<>();

	public void send(FlumSendRequest r) {
		MyRestClient client = new MyRestClient(r.getHost(), r.getPort());
		for (String message : r.getMessages()) {
				client.sendDataToFlume(message);
		}
		client.cleanUp();
	}

	public void receive(String body) {
		messages.add(body);
	}

	public void clear() {
		messages.clear();
	}

	public List<String> retrieve() {
		return messages;
	}

}
