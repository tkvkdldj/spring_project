package com.welcomeshop.www;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class web_Controller {
	PrintWriter pw = null;
	@GetMapping("/ajaxok.do")
	public String ajaxok() {
		return null;
	}
}
