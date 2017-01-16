package com.insfi.mongoui.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public void index(HttpServletResponse http) {
		try {
			http.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
