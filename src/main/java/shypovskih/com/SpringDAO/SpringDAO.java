package shypovskih.com.SpringDAO;

import java.util.List;
import java.util.Map;

import shypovskikh.com.DataClasses.MP3;

public interface SpringDAO {
	
	public void insert(MP3 mp3);
	
	public void insert(List<MP3> mp3);
	
	public void delete(MP3 mp3);
	
	public List<Map<String, Object>> gelAllMP3();
	
	public List<MP3> getMP3ByName(String name);
	
	public List<MP3> getMP3ByAuthor(String name);
	
	public MP3 getMP3ById(int id);

}
