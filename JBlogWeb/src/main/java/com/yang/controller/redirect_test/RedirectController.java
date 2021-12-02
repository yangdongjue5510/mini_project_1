package com.yang.controller.redirect_test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedirectController {

	List<Integer> list = new ArrayList<>();
	int i = 0;

	public String getListString() {
		StringBuilder sb = new StringBuilder();
		list.stream().forEach(integer -> sb.append(integer));
		return sb.toString();
	}

	@GetMapping("/result")
	@ResponseBody
	public String getResult() {
		return "get method" +getListString();
	}

	@PostMapping("/result")
	@ResponseBody
	public String postResult() {
		return "post method" +getListString();
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/redirect")
	public String getRedirect() {
		return "redirect:/result";
	}

	@PostMapping("/redirect")
	public String postRedirect() {
		list.add(++i);
		return "redirect:/result";
	}

	@GetMapping("/forward")
	public String getForward() {
		return "forward:/result";
	}

	@PostMapping("/forward")
	public String postForward() {
		list.add(++i);
		return "forward:/result";
	}
}
