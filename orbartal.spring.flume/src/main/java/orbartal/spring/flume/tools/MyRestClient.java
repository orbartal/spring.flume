
package orbartal.spring.flume.tools;

public class MyRestClient {

	private String hostname;
	private int port;

	public MyRestClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void sendDataToFlume(String data) {
		try {
			MyHttpApi api = new MyHttpApi(this.hostname, this.port);
			api.writeData(data);
			api.getResponse();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void cleanUp() {}

}
