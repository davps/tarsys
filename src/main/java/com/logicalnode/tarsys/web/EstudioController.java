package com.logicalnode.tarsys.web;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.repository.ConsumoDAO;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.repository.FacturaDAO;
import com.logicalnode.tarsys.services.Optimizador;
import com.logicalnode.tarsys.services.impl.TarifasManagerImpl;

@Controller
@RequestMapping(value = "/api/estudios", produces = "application/json")
@Transactional
public class EstudioController {

	private static final Logger logger = LoggerFactory.getLogger(EstudioController.class);
	
	@Autowired private EstudioDAO estudioDAO;
	@Autowired private FacturaDAO facturaDAO;
	@Autowired private ConsumoDAO consumoDAO;
	@Autowired private Optimizador optimizador;
	
	@Autowired
	@Qualifier("tarifasManager")
	private TarifasManagerImpl tarifaList;
	
	public EstudioController() {}

	public EstudioController(EstudioDAO estudioDAO, FacturaDAO facturaDAO, ConsumoDAO consumoDAO, 
			TarifasManagerImpl tarifaList, Optimizador optimizador) {
		this.estudioDAO = estudioDAO;
		this.facturaDAO = facturaDAO;
		this.consumoDAO = consumoDAO;
		this.tarifaList = tarifaList;
		this.optimizador = optimizador;
	}
	
    @RequestMapping(method = GET) 
    public @ResponseBody List<Estudio> list(){
    	logger.info("GET, list()");
    	logger.info(this.tarifaList.getListaDeTarifas().toString());
    	return this.estudioDAO.findAll();
    }
    
    @RequestMapping(value = "{id}",  method = GET) 
    public @ResponseBody Estudio show(@PathVariable("id") Long id){
    	logger.info("GET, show()");
    	return null;
    }

    @RequestMapping(value = "{id}/consumos",  method = GET) 
    public @ResponseBody List<Consumo> findAllConsumosByEstudio(@PathVariable("id") Long id){
    	logger.info("GET, findAllConsumosByEstudio()");
    	List<Consumo> consumoList = new ArrayList<Consumo>();
    	List<Factura> facturaList = this.facturaDAO.findAllForEstudio(id);
    	
    	for (Factura factura : facturaList) {
    		consumoList.addAll( this.consumoDAO.findAllForFactura(factura.getId()) );			
		}
    	return consumoList;
    }
    
    @RequestMapping(value = "{id}/optimizar",  method = GET) 
    public @ResponseBody AbstractTarifas optimizar(@PathVariable("id") Long idEstudio){
    	logger.info("GET, optimizar()");
    	Estudio estudio = this.estudioDAO.find(idEstudio);
    	AbstractTarifas tarifa = this.optimizador.calcularTarifaOptima(estudio);
    	logger.info("La tarifa optima es: {}", tarifa);
    	return tarifa;
    }

    @RequestMapping(method = POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    @ResponseBody
    public Estudio create(@RequestBody Estudio estudio){
    	logger.info("POST, create()  " + estudio.toString());
    	
    	if(this.estudioDAO.find(estudio.getId()) != null){
            logger.info("Cannot create entity. Another entity with given id already exists.");
            throw new DescriptionUniquenessViolationException(estudio.getId().toString());
    	}
    	Assert.notNull(estudio);
    	
    	return this.estudioDAO.create(estudio);
    }

    @RequestMapping(value = "{id}", method = PUT, consumes = "application/json") 
    @ResponseStatus(OK)
    @ResponseBody
    public Estudio update(@RequestBody Estudio estudio, @PathVariable Long id){
    	logger.info("PUT, update() " + estudio);
//    	Estudio otro = Estudio.findEstudio(estudio.getId());
//    	if(otro != null && !otro.getId().equals(id)){
//            logger.info("Cannot create entity. Another entity with given id already exists.");
//            throw new DescriptionUniquenessViolationException(estudio.getId().toString());
//    	}
    	return this.estudioDAO.update(estudio);
    }
    
    @RequestMapping(value = "{id}", method = DELETE) 
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id){
    	logger.info("DELETE, delete()");
    	this.estudioDAO.removeById(id);
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
