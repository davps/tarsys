package com.logicalnode.tarsys.web;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.services.impl.TarifasManagerImpl;

@Controller
@RequestMapping(value = "/api/crear", produces = "application/json")
@Transactional
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
	public HomeController() {}
	
	
    @RequestMapping(method = GET) 
    public @ResponseBody List<Consumo> list(){
    	logger.info("GET, list()");
    	return null;
    }
    
    @RequestMapping(method = POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    @ResponseBody
    public Consumo create(@RequestBody Consumo consumo){
    	logger.info("POST, create()" + consumo);
    	return consumo;
    }
    
	
}
