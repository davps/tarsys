package com.logicalnode.tarsys.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.repository.FacturaDAO;

@Repository
public class FacturaDAOImpl implements FacturaDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FacturaDAOImpl.class);

	private JdbcTemplate template;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertFactura;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertFactura = new SimpleJdbcInsert(dataSource).withTableName("factura").usingGeneratedKeyColumns("id");
	}

	private final ParameterizedRowMapper<Factura> rowMapper = new ParameterizedRowMapper<Factura>() {
		public Factura mapRow(ResultSet rs, int rowNum) throws SQLException{
			Factura factura = new Factura();
			factura.setId(rs.getLong("id"));
			factura.setEstudio(rs.getLong("estudio"));
			factura.setNumeroDeFactura(rs.getLong("numero_de_factura"));
			factura.setVersion(rs.getInt("version"));
			return factura;
		}
	};
	
	@Override
	public Factura create(Factura factura) {
		logger.info("Factura.create() ; La factura por persistir es: {}", factura);
		
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("estudio", factura.getEstudio());
		parameters.put("numero_de_factura", factura.getNumeroDeFactura());
		parameters.put("version", factura.getVersion());
		
		Number newId = insertFactura.executeAndReturnKey(parameters);
		factura.setId(newId.longValue());
		logger.info("devolviendo factura persistida: " + factura);
		return factura;
		
//		String sql = "INSERT INTO `factura` (`estudio`,`numero_de_factura`,`version`) VALUES (?,?,?)";
//		this.template.update(sql, 
//							 factura.getEstudio(),
//							 factura.getNumeroDeFactura(),
//							 factura.getVersion()
//							 );
//		return null;
	}

	@Override
	public Factura update(Factura factura) {
		logger.info("Factura.create() ; La factura por persistir es: {}", factura);
		String sql = "UPDATE `factura` SET estudio = ?, numero_de_factura = ?, version = ? WHERE id = ?";
		this.template.update(sql, 
							 factura.getEstudio(),
							 factura.getNumeroDeFactura(),
							 factura.getVersion(),
							 factura.getId()
							 );
		return null;
	}

	@Override
	public void remove(Factura factura) {
		this.removeById(factura.getId());
	}

	@Override
	public void removeById(Long id) {
		this.template.update("DELETE FROM `factura` WHERE id = ?", id);
	}

	@Override
	public void flush(Factura item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Factura clear(Factura item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura merge(Factura item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countEntities() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Factura> findAll() {
		return this.template.query("SELECT * FROM `tarsysdb`.`factura`", this.rowMapper);
	}

	@Override
	public Factura find(Long id) {
		String sql = "SELECT * FROM `tarsysdb`.`factura` WHERE id=:id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Factura.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Factura> findAllForEstudio(Long idEstudio) {
		return this.template.query("SELECT * FROM `tarsysdb`.`factura` WHERE estudio=?", this.rowMapper, idEstudio);
	}

}
