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

import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.repository.FacturaDAO;

@Controller
@RequestMapping(produces = "application/json")
@Transactional
public class FacturaController {

	private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);
	
	@Autowired private FacturaDAO facturaDAO;
	
	public FacturaController() {}
	
	public FacturaController(FacturaDAO facturaDAO) {
		this.facturaDAO = facturaDAO;
	}
	
    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas", method = GET) 
    public @ResponseBody List<Factura> list(@PathVariable Long idEstudio){
    	logger.info("GET, list()");
    	return this.facturaDAO.findAllForEstudio(idEstudio);
//    	return Factura.findAllFacturas();
    }
    
    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}",  method = GET) 
    public @ResponseBody Factura show(@PathVariable Long idEstudio, @PathVariable Long idFactura){
    	logger.info("GET, show()");
    	return null;
    }
    
    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas", method = POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    @ResponseBody
    public Factura create(@PathVariable Long idEstudio, @RequestBody Factura factura){
    	logger.info("POST, create()");
    	logger.info(factura.toString());
//    	Assert.notNull(factura);
    	factura.setEstudio(idEstudio);
    	return this.facturaDAO.create(factura);
    }

    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}", method = PUT, consumes = "application/json") 
    @ResponseStatus(OK)
    @ResponseBody
    public void update(@RequestBody Factura factura, @PathVariable Long idEstudio, @PathVariable Long idFactura){
    	logger.info("PUT, update() " + factura);
    	this.facturaDAO.update(factura);
    }
    
    @RequestMapping(value = "/api/estudios/{idEstudio}/facturas/{idFactura}", method = DELETE) 
    @ResponseStatus(OK)
    public void delete(@PathVariable Long idEstudio, @PathVariable Long idFactura){
    	logger.info("DELETE, delete()");
    	this.facturaDAO.removeById(idFactura);
//    	Factura.findFactura(idFactura).remove();
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
