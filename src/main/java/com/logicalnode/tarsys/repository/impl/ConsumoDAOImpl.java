package com.logicalnode.tarsys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.repository.ConsumoDAO;

@Repository
public class ConsumoDAOImpl implements ConsumoDAO{

	private static final Logger logger = LoggerFactory.getLogger(ConsumoDAOImpl.class);

	private JdbcTemplate template;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertConsumo;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertConsumo = new SimpleJdbcInsert(dataSource).withTableName("consumo").usingGeneratedKeyColumns("id");
	}

	private final ParameterizedRowMapper<Consumo> rowMapper = new ParameterizedRowMapper<Consumo>() {
		public Consumo mapRow(ResultSet rs, int rowNum) throws SQLException{
			Consumo consumo = new Consumo();
			consumo.setId(rs.getLong("id"));
			consumo.setConstante(rs.getDouble("constante"));
			consumo.setFactura(rs.getLong("factura"));
			consumo.setLectura(rs.getLong("lectura"));
			consumo.setTipo(TipoDeConsumo.valueOf(rs.getString("tipo_de_consumo")));
			consumo.setVersion(rs.getInt("version"));
			return consumo;
		}
	};

	@Override
	public Consumo create(Consumo consumo) {
		logger.info("Consumo.create() ; El consumo por persistir es: {}", consumo);
		
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put("constante", consumo.getConstante());
		parameters.put("factura", consumo.getFactura());
		parameters.put("lectura", consumo.getLectura());
		parameters.put("tipo_de_consumo", consumo.getTipo());
		parameters.put("version", consumo.getVersion());
		
		Number newId = insertConsumo.executeAndReturnKey(parameters);
		consumo.setId(newId.longValue());
		return consumo;

//		String sql = "INSERT INTO `consumo` (`constante`,`factura`,`lectura`,`tipo_de_consumo`,`version`) VALUES (?,?,?,?,?)";
//		this.template.update(sql, 
//							 consumo.getConstante(),
//							 consumo.getFactura(),
//							 consumo.getLectura(),
//							 consumo.getTipo().toString(),
//							 consumo.getVersion()
//							 );
//		return null;
	}

	@Override
	public Consumo update(Consumo consumo) {
		logger.info("Consumo.update() ; El consumo por persistir es: {}", consumo);
		String sql = "UPDATE `consumo` SET constante = ?, factura = ?, lectura = ?, tipo_de_consumo = ?, version = ? WHERE id = ?";
		this.template.update(sql, 
							 consumo.getConstante(),
							 consumo.getFactura(),
							 consumo.getLectura(),
							 consumo.getTipo().toString(),
							 consumo.getVersion(),
							 consumo.getId()
							 );
		return null;
	}

	@Override
	public void remove(Consumo consumo) {
		this.removeById(consumo.getId());
		
	}

	@Override
	public void removeById(Long id) {
		this.template.update("DELETE FROM `consumo` WHERE id = ?", id);
	}

	@Override
	public void flush(Consumo item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consumo clear(Consumo item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumo merge(Consumo item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countEntities() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Consumo> findAll() {
		return this.template.query("SELECT * FROM `tarsysdb`.`consumo`", this.rowMapper);
	}

	@Override
	public Consumo find(Long id) {
		String sql = "SELECT * FROM `tarsysdb`.`consumo` WHERE id=:id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Consumo.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Consumo> findAllForFactura(Long idFactura) {
		return this.template.query("SELECT * FROM `tarsysdb`.`consumo` WHERE factura=?", this.rowMapper, idFactura);
	}

}
