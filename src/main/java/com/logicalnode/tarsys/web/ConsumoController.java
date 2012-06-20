package com.logicalnode.tarsys.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.repository.ConsumoDAO;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.repository.impl.EstudioDAOImpl;

@Controller
@RequestMapping(produces = "application/json")
@Transactional
public class ConsumoController {

	private static final Logger logger = LoggerFactory.getLogger(ConsumoController.class);
	
	@Autowired private ConsumoDAO consumoDAO;
	@Autowired private EstudioDAOImpl estudioDAO;
	
	public ConsumoController() {}
	
	public ConsumoController(ConsumoDAO consumoDAO, EstudioDAOImpl estudioDAO) {
		this.consumoDAO = consumoDAO;
		this.estudioDAO = estudioDAO;
	}

    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}/consumos", method = GET) 
    public @ResponseBody List<Consumo> list(@PathVariable Long idEstudio, @PathVariable Long idFactura){
    	logger.info("GET, list()");

    	List<Consumo> consumoList = this.consumoDAO.findAllForFactura(idFactura);
    	logger.info("la lista de consumo para:" +  
    				"[factura:" + idFactura + 
    				"] y [estudio: " + idEstudio + 
    				"] es " + consumoList);
    	if(consumoList != null ){
    		if(consumoList.size() > 0){
    			logger.info("retorna los consumos de la base de datos");
    			return consumoList;
    		}
    	}

		Consumo energia 								= new Consumo(TipoDeConsumo.Energia);
		Consumo energiaEnPunta 							= new Consumo(TipoDeConsumo.EnergiaEnPunta);
		Consumo energiaFueraDePunta  					= new Consumo(TipoDeConsumo.EnergiaFueraDePunta);
		Consumo excesoDePotenciaReservada  				= new Consumo(TipoDeConsumo.ExcesoDePotenciaReservada);
		Consumo excesoDePotenciaReservadaEnPunta  		= new Consumo(TipoDeConsumo.ExcesoDePotenciaReservadaEnPunta);
		Consumo excesoDePotenciaReservadaFueraDePunta  	= new Consumo(TipoDeConsumo.ExcesoDePotenciaReservadaFueraDePunta);
		Consumo potenciaReservada  						= new Consumo(TipoDeConsumo.PotenciaReservada);
		Consumo potenciaReservadaEnPunta  				= new Consumo(TipoDeConsumo.PotenciaReservadaEnPunta);
		Consumo potenciaReservadaFueraDePunta  			= new Consumo(TipoDeConsumo.PotenciaReservadaFueraDePunta);

    	Estudio estudio = this.estudioDAO.find(idEstudio);
    	String c = estudio.getCategoria();
    	logger.info("la categoria es: {}", c);

    	if( c.equals("261") || c.equals("371") || c.equals("531") || c.equals("831") || c.equals("152") || c.equals("262") || c.equals("372") || c.equals("532") || c.equals("832") ){ 
    			consumoList.add(potenciaReservada);
    			consumoList.add(excesoDePotenciaReservada);
    			consumoList.add(energiaEnPunta);
    			consumoList.add(energiaFueraDePunta);
		}
		if(c.equals("153") || c.equals("263") || c.equals("373") || c.equals("533") || c.equals("833") ){ 
				consumoList.add(energia);
		}
		if( c.equals("731") || c.equals("732") ){ 
				consumoList.add(potenciaReservadaEnPunta);
				consumoList.add(potenciaReservadaFueraDePunta);
				consumoList.add(energiaEnPunta);
				consumoList.add(energiaFueraDePunta);
				consumoList.add(excesoDePotenciaReservadaEnPunta);
				consumoList.add(excesoDePotenciaReservadaFueraDePunta);
		}

    	for (Consumo consumo : consumoList) {
			consumo.setFactura(idFactura);
		}
    	logger.info("retorna nuevos consumos ({} en total) : {}", consumoList.size(), consumoList);
    	return consumoList;
    }
    
    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}/consumos/{idConsumo}",  method = GET) 
    public @ResponseBody Consumo show(@PathVariable Long idEstudio, @PathVariable Long idFactura, @PathVariable Long idConsumo){
    	logger.info("GET, show()");
    	return null;
    }

    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}/consumos", method = POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    @ResponseBody
    public Consumo create(@PathVariable Long idEstudio, @PathVariable Long idFactura, @RequestBody Consumo consumo){
    	logger.info("POST, create()");
//    	logger.info(consumo.toString());
//    	Assert.notNull(consumo);
//    	consumo.setFactura(idFactura);
    	logger.info( "  idEstudio:" + idEstudio + 
    				 ", idFactura:" + idFactura + 
    				 ", consumo.getFactura():{}" + consumo.getFactura() );
    	return this.consumoDAO.create(consumo);
    }

    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}/consumos/{idConsumo}", method = PUT, consumes = "application/json") 
    @ResponseStatus(OK)
    @ResponseBody
    public void update(@RequestBody Consumo consumo, @PathVariable Long idEstudio, @PathVariable Long idFactura, @PathVariable Long idConsumo){
    	logger.info("PUT, update() " + consumo);
    	this.consumoDAO.update(consumo);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Notification onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        return new Notification(error.getField() + " " + error.getDefaultMessage());
    }

    @ExceptionHandler(DescriptionUniquenessViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Notification onMethodArgumentNotValidException(DescriptionUniquenessViolationException ex) {
        return new Notification(format("Entity with id '%s' already exists", ex.getDescription()));
    }
    
    
}
