package shypovskih.com.SpringDAO;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import shypovskikh.com.DataClasses.MP3;
import shypovskikh.com.SQliteDAO.SQLiteDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
       MP3 mp3 = new MP3();
       mp3.setAuthor("DDT");
       mp3.setName("Outumn");
       
       ApplicationContext appl = new ClassPathXmlApplicationContext("ApplContext.xml");
       SQLiteDAO sqLiteDAO = (SQLiteDAO)appl.getBean("sqliteDAO");
       
       sqLiteDAO.insert(mp3);
       ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) sqLiteDAO.gelAllMP3();
     for(Map<String, Object> l : list) {
    	   System.out.print(l.get("id")+"  ");
    	   System.out.print(l.get("name")+"  ");
    	   System.out.print(l.get("author"));
    	   System.out.println();

       }
       
     ArrayList<MP3> ddt = (ArrayList<MP3>) sqLiteDAO.getMP3ByAuthor("Michael Jackson");
     for(Map<String, Object> l : list) {
    	
     }
       
       System.out.println("Michael Jackson:"+sqLiteDAO.getMP3ByAuthor("Michael Jackson"));
    
       sqLiteDAO.delete(mp3);
       ArrayList<Map<String, Object>> list2 = (ArrayList<Map<String, Object>>) sqLiteDAO.gelAllMP3();
       for(Map<String, Object> l : list2) {
      	   System.out.print(l.get("id")+"  ");
      	   System.out.print(l.get("name")+"  ");
      	   System.out.print(l.get("author"));
      	   System.out.println();

         }
       
    }
    
    
    
}
