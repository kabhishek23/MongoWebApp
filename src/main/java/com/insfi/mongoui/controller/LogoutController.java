package com.insfi.mongoui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

	@RequestMapping("/disconnect")
	public String disconnect() {
		return "Disconnected";
	}

}
