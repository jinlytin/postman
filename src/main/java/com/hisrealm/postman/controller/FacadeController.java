package com.hisrealm.postman.controller;

import com.hisrealm.postman.dw.DataWareHouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dingjingli
 * @version 1.0
 * @date 2021/2/19 20:02
 */
@RestController
@RequestMapping(value = "/api/listen", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FacadeController {


	private final DataWareHouseService dataWareHouseService;

	@GetMapping("/addListener")
	public void addListener( HttpServletRequest request, HttpServletResponse response) {


		AsyncContext asyncContext = request.startAsync();
		String configKey = asyncContext.getRequest().getParameter("configKey");
		dataWareHouseService.addListener(configKey,asyncContext);
	}


}
