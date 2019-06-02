package se.axesslab.argupedia.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.axesslab.argupedia.service.ArgumentService;

/**
 * @author Joel Holmberg
 */
@RestController("/")
public class ArgumentController {

	@Autowired
	private ArgumentService argumentService;

	@RequestMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return argumentService.graph(limit == null ? 100 : limit);
	}
}
