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
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.repository.EstudioDAO;

@Repository
//@Transactional
public class EstudioDAOImpl implements EstudioDAO{

	private static final Logger logger = LoggerFactory.getLogger(EstudioDAOImpl.class);
	
	private JdbcTemplate template;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertEstudio;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertEstudio = new SimpleJdbcInsert(dataSource).withTableName("estudio").usingGeneratedKeyColumns("id");
	}
	
	private final ParameterizedRowMapper<Estudio> rowMapper = new ParameterizedRowMapper<Estudio>() {
		public Estudio mapRow(ResultSet rs, int rowNum) throws SQLException{
			Estudio estudio = new Estudio();
			estudio.setId(rs.getLong("id"));
			estudio.setCategoria(rs.getString("categoria"));
			estudio.setEmpresa(rs.getString("empresa"));
			estudio.setNombre(rs.getString("nombre"));
			estudio.setNumeroDeMedidor(rs.getLong("numeroDeMedidor"));
			estudio.setObservaciones(rs.getString("observaciones"));
			estudio.setVersion(rs.getInt("version"));
			return estudio;
		}
	};

	@Override
	public Estudio create(Estudio estudio) {
		logger.info("Estudio.create() ; El estudio por persistir es: {}", estudio);
		
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put("categoria", estudio.getCategoria());
		parameters.put("empresa", estudio.getEmpresa());
		parameters.put("nombre", estudio.getNombre());
		parameters.put("numeroDeMedidor", estudio.getNumeroDeMedidor());
		parameters.put("observaciones", estudio.getObservaciones());
		
		Number newId = insertEstudio.executeAndReturnKey(parameters);
		estudio.setId(newId.longValue());
		return estudio;
		
//		String sql = "INSERT INTO `estudio` (`categoria`,`empresa`,`nombre`,`numeroDeMedidor`,`observaciones`) VALUES (?,?,?,?,?)";
//		this.template.update(sql, 
//							 estudio.getCategoria(), 
//							 estudio.getEmpresa(),
//							 estudio.getNombre(),
//							 estudio.getNumeroDeMedidor(),
//							 estudio.getObservaciones()
//							 );
//		return null;
	}

	@Override
	public Estudio update(Estudio estudio) {
		logger.info("Estudio.update() ; El estudio por persistir es: {}", estudio);
		String sql = "UPDATE `estudio` SET categoria = ?, empresa = ?, nombre = ?, numeroDeMedidor = ?, observaciones = ? WHERE id = ?";
		this.template.update(sql, 
							 estudio.getCategoria(), 
							 estudio.getEmpresa(),
							 estudio.getNombre(),
							 estudio.getNumeroDeMedidor(),
							 estudio.getObservaciones(),
							 estudio.getId()
							 );
		return null;
	}

	@Override
	public void remove(Estudio estudio) {
		this.removeById(estudio.getId());
	}
	
	@Override
	public void removeById(Long id){
		this.template.update("DELETE FROM `estudio` WHERE id = ?", id);
	}

	@Override
	public void flush(Estudio item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Estudio clear(Estudio item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estudio merge(Estudio item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countEntities() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Estudio> findAll() {
		return this.template.query("SELECT * FROM `tarsysdb`.`estudio`", this.rowMapper);
	}

	@Override
	public Estudio find(Long id) {
//		logger.info(".find({})", id);
		
//		List<Long> paramList = new ArrayList<Long>();
//		paramList.add(id);
//		
////		Object[] parametros = new Object[1];
////		parametros[0] = id;
//		try {
//			return this.template.queryForObject("SELECT * FROM `tarsysdb`.`estudio` WHERE id=?", paramList.toArray(), Estudio.class);
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		} catch (IncorrectResultSetColumnCountException e) {
//			return null;
//		}

		String sql = "SELECT * FROM `tarsysdb`.`estudio` WHERE id=" + id;
		List<Estudio> estudioList = this.template.query(sql, this.rowMapper);
		if(estudioList.size() > 0){
			return estudioList.get(0);
		}
		return null;
		
//		String sql = "SELECT * FROM `tarsysdb`.`estudio` WHERE id=:id";
////		String sql = "SELECT id, categoria, empresa, nombre, numeroDeMedidor, observaciones, version FROM `tarsysdb`.`estudio` WHERE id=:id";
//		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
//		try {
//			return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Estudio.class);
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
	}
	
}
