package shypovskikh.com.SQliteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import shypovskih.com.SpringDAO.SpringDAO;
import shypovskikh.com.DataClasses.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements SpringDAO{
	private SimpleJdbcInsert jdbcInsert; 
	private JdbcTemplate jdbcTemplate; 
	private DataSource dataSource; 
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name", "author");
		this.dataSource = dataSource;
	}

	public void insert(MP3 mp3) {
		//String sql = "insert into mp3 (name, author) VALUES (?,?)";
		//jdbcTemplate.update(sql, new Object[] {mp3.getName(), mp3.getAuthor()});
	
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", mp3.getName());
		param.addValue("author", mp3.getAuthor());
		jdbcInsert.execute(param);
	}
	
	public int batchInsert(List<MP3> listMp3) {
		String sql = "insert into mp3 (name, author) values(:name, :author)";
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(listMp3.toArray());
	//	int[] updateCount = jdbcTemplate.batchUpdate(sql, batch);
		return 0; // updateCount;
	}

	public void delete(MP3 mp3) {
	         delete(mp3.getName());	
	}
	
	public void delete(String param) {
		String sql = "delete from MP3 where name=:param";
		Object[] obj = new Object[] {param};   
		jdbcTemplate.update(sql, obj);

	}

	public List<Map<String, Object>> gelAllMP3() {
		String sql = "select * from MP3";
		return jdbcTemplate.queryForList(sql);

	}

	public List<MP3> getMP3ByName(String name) {
		String sql = "Select * from MP3 where uper(name) like:name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%"+name.toUpperCase()+"%");
		Object[] obj = new Object[] {name};   
		return jdbcTemplate.query(sql, obj, new MP3RowMapper());
	}

	public List<MP3> getMP3ByAuthor(String author) {
		String sql = "Select * from MP3 where upper(author) like:author";
		MapSqlParameterSource params = new MapSqlParameterSource();
		Object[] obj = new Object[] {author};   
		return jdbcTemplate.query(sql, obj, new MP3RowMapper());
	}

	public MP3 getMP3ById(int id) {
		String sql = "Select * from MP3 where id:id";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id",id, Types.INTEGER);
		Object[] obj = new Object[] {id};   
		
	    return jdbcTemplate.queryForObject(sql,obj, new MP3RowMapper());
	}

	public void insert(List<MP3> mp3) {
	   for(MP3 list : mp3)
		   insert(list);
		
	}
	
	
	private static final class MP3RowMapper implements RowMapper<MP3>{

		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {

            MP3 mp3 = new MP3();
            mp3.setId(rs.getInt("id"));
            mp3.setName(rs.getString("name"));
            mp3.setAuthor(rs.getString("author"));
         
			return mp3;
		}
		
		
	}

}
