
package orbartal.spring.flume;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import orbartal.spring.flume.api.rest.model.FlumSendRequest;

public class FlumControllerTest {

	private static final String BASE_API_PATH = "/v1/flume";
	private static final String CLEAR_API_PATH = BASE_API_PATH+"/clear";
	private static final String SEND_API_PATH = BASE_API_PATH+"/send";
	private static final String RETRIEVE_API_PATH = BASE_API_PATH+"/retrieve";

	private static final String APPLICATION_JSON_VALUE = "application/json";

	@SuppressWarnings("unchecked")
	@Test
	public void testSend1() throws InterruptedException {

		List<String> expected = Arrays.asList("A", "B", "C");
		FlumSendRequest request = new FlumSendRequest();
		request.setHost("localhost");
		request.setPort(44444);
		request.setMessages(expected);
		
		Response response1 = RestAssured.given()
				.contentType(APPLICATION_JSON_VALUE)
				.request(Method.POST, CLEAR_API_PATH);

		Response response2 = RestAssured.given()
				.contentType(APPLICATION_JSON_VALUE)
				.body(request)
				.request(Method.POST, SEND_API_PATH);
		
		Thread.sleep(10*1000);
		Response response3 = RestAssured.given()
				.contentType(APPLICATION_JSON_VALUE)
				.body(request)
				.request(Method.GET, RETRIEVE_API_PATH);

		String body = response3.getBody().asString();
		List<String> actual = new Gson().fromJson(body, List.class);
		
		Assert.assertEquals(HttpStatus.SC_OK, response1.getStatusCode());
		Assert.assertEquals(HttpStatus.SC_OK, response2.getStatusCode());
		Assert.assertEquals(HttpStatus.SC_OK, response3.getStatusCode());
		Assert.assertEquals(expected, actual);
	}

}
