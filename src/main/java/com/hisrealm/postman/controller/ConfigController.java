package com.hisrealm.postman.controller;

import com.hisrealm.postman.dw.DataWareHouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingjingli
 * @version 1.0
 * @date 2021/2/19 20:01
 */
@RestController
@RequestMapping(value = "/api/config", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ConfigController {

	private final DataWareHouseService dataWareHouseService;

	@GetMapping(value="/updateConfig")
	public boolean updateConfig(String configKey,String value){
		return dataWareHouseService.updateConfig(configKey,value);
	}

}
