
package orbartal.spring.flume.api.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbartal.spring.flume.api.rest.model.FlumSendRequest;
import orbartal.spring.flume.application.FlumeApplication;

@RestController
@RequestMapping("/v1/flume")
public class FlumController {

	private FlumeApplication flumeApplication;

	public FlumController(FlumeApplication flumeApplication) {
		this.flumeApplication = flumeApplication;
	}

	@PostMapping(value = "/send")
	public void send(@RequestBody FlumSendRequest r) {
		flumeApplication.send(r);
	}

	@RequestMapping("/receive")
	public void receive(@RequestBody String body) {
		flumeApplication.receive(body);
	}

	@PostMapping(value = "/clear")
	public void clear() {
		flumeApplication.clear();
	}
	
	@GetMapping(value = "/retrieve")
	public List<String> retrieve() {
		return flumeApplication.retrieve();
	}

}
