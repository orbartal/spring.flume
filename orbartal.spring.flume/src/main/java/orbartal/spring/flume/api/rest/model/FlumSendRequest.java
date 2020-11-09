package orbartal.spring.flume.api.rest.model;

import java.util.List;

public class FlumSendRequest {

	private String host;
	private int port;
	private List<String> messages;

	public String getHost() {
		return host;
	}

	public void setHost(String hostname) {
		this.host = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
