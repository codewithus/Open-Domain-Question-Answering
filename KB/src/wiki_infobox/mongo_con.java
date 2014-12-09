/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki_infobox;

/**
 *
 * @author Dinesh Kumar
 */

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class mongo_con {
    String HOST="localhost";MongoClient mongoClient;
    
    
        
        //String json = "{idp:'ddsssd',opl:'dddee'}";
        
        DB db;
    public mongo_con(String host)
    {
        HOST=host;
        try {
            
            mongoClient = new MongoClient( HOST , 27017 );
               db = mongoClient.getDB( "answeritdb" );
        } catch (Exception ex) {
            System.out.println("Error on db conn "+ex.getMessage());
        }
    }
   public mongo_con(String clname,String src,String itit,String icol,String iur,String host)
    {
        
        try
        {
            HOST=host;
            mongoClient = new MongoClient( HOST , 27017 );
             db = mongoClient.getDB( "answeritdb" );
        DBCollection tab=db.getCollection(clname);
         
        DBObject dbObject = (DBObject)JSON.parse(src);
        
        tab.insert(dbObject);
        System.out.println("Done....!");
        
       DBCollection tab1=db.getCollection("index_tab");

	BasicDBObject document = new BasicDBObject();
	document.put("title", itit);
     document.put("coll", icol);
       document.put("url", iur);
				
	tab1.insert(document);
				//System.out.print("Question added");
			
	 System.out.println("Done index...! ");	
        
        mongoClient.close();
        }catch(Exception e)
        {
            System.out.println("Error "+e);
            e.printStackTrace();
        }
    }
    
    
    
    public String find_coll(String ur)
    {
        String coll="not found";
        try{
       
           
           //String json = "{idp:'ddsssd',opl:'dddee'}";
           String result="";
        
          // DBCollection tab=db.getCollection("index_tab");
           
          BasicDBObject query = new BasicDBObject();

    query.put("url", Pattern.compile("http://en.wikipedia.org/wiki/"+ur , Pattern.CASE_INSENSITIVE));

    DBCollection collection = db.getCollection("index_tab");
        DBCursor cursor = collection.find(query);
            System.out.println("Size "+cursor.size());
            if(cursor.size()>0)
            {
            while (cursor.hasNext()) {                
                  BasicDBObject obj = (BasicDBObject) cursor.next();
              //  System.out.println("Value "+obj.get("coll"));
                coll=obj.get("coll").toString();
            }
            return coll;
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Error on find collection"+e.getMessage());
        }
        return coll;
    }
    
    
    
    /*public void ins_qus(String title,String coll,String ur)
	{
		
		try{
			MongoClient mongoClient = new MongoClient( HOST , 27017 );
	        //String json = "{idp:'ddsssd',opl:'dddee'}";
	   DB  db= mongoClient.getDB( "answeritdb" );
			
			DBCollection tab=db.getCollection("index_tab");

				BasicDBObject document = new BasicDBObject();
				document.put("title", title);
                                document.put("coll", coll);
                                document.put("url", ur);
				
				tab.insert(document);
				//System.out.print("Question added");
			
				      System.out.println("Done index...! ");		
			
			
		}
		catch(Exception ex)
		{
			System.out.print("Error "+ex);
			
		}
		
	}*/
    
    
    
    
    
    public String get_infobox(String collname,String verb) {
		String tx="";
		try{
			
			
			DBCollection tab=db.getCollection(collname);
			
			  BasicDBObject query = new BasicDBObject();
	       
                          
		        BasicDBObject field = new BasicDBObject();
		        field.put("infobox", 1);
		        DBCursor cursor = tab.find(query,field);
		        //System.out.println("Collection name "+collname+" Verb "+verb+" Size "+cursor.size());
				while (cursor.hasNext()) 
				{
				    BasicDBObject obj = (BasicDBObject) cursor.next();
				  //  System.out.print("infobox  : "+obj.getString("infobox"));
				    BasicDBObject target = (BasicDBObject) obj.get("infobox");
				    tx=tx +(String) target.get(verb);
				}
	}
		catch(Exception e)
		{
			System.out.print("Error on getinfo"+e.getMessage());
		}
		return tx;
}
    
    public String info(String va,String berb)
    {
        String finalres="";
      DBObject dbObject = (DBObject) JSON.parse(va);
        
 
     // Object il=dbObject.get("infobox."+berb);
         finalres=(String)((DBObject)dbObject.get("infobox")).get(berb);
     
      return finalres;
   
    }
    
   
      public String fir_txt(String va)
    {
        String finalres="";
      DBObject dbObject = (DBObject) JSON.parse(va);
        
 
     // Object il=dbObject.get("infobox."+berb);
         finalres=(String)((DBObject)dbObject.get("first_text")).get("val");
     
      return finalres;
   
    }
    
    
  
    
    
    
    /*-----------------------Read First par--------------------*/
    
    public String get_data(String collname) {
		String tx="";
		try{
		
		
		DBCollection tab=db.getCollection(collname);
               BasicDBObject query = new BasicDBObject();
               BasicDBObject field = new BasicDBObject();
			        field.put("first_text", 1);
			        
			        DBCursor cursor = tab.find(query,field);
			while (cursor.hasNext()) 
			{

			    BasicDBObject obj = (BasicDBObject) cursor.next();
			    BasicDBObject target = (BasicDBObject) obj.get("first_text");
			    tx=tx +(String) target.get("val");
				}
			
			BasicDBObject query1 = new BasicDBObject();
	           
	        BasicDBObject field1 = new BasicDBObject();
	        field1.put("etc", 1);

	        DBCursor cursor1 = tab.find(query1,field1);
                while (cursor1.hasNext()) 
            {
	    BasicDBObject obj1 = (BasicDBObject) cursor1.next();
	    BasicDBObject target1 = (BasicDBObject) obj1.get("etc");
	    tx="<font size='10px'>"+(String) target1.get("Title")+"</font><br>"+tx ;
		}
		
		
		
		mongoClient.close();
		
		}
		catch(Exception e)
		{
			System.out.print("Error on  db getdata "+e.getMessage());
		}
		return tx;
	
	}
	
    
    
    
    
    
    
    
    
    
    
    
    
    
}
