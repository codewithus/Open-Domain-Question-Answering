package dbconn;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class Mongo_Con 
{
	String uri="";
	MongoClient mongoClient;DB db;
	public  Mongo_Con(String uri)
	{
		this.uri=uri;
		
		try
		{
		MongoClientURI muri = new MongoClientURI(uri);
		mongoClient = new MongoClient(muri);
	    db = mongoClient.getDB( "answeritdb" );
		}
		catch(Exception e)
		{
			System.out.println("Cannot Connect db "+e.getMessage());
		}
	}
	
	 public Mongo_Con (String clname,String src,String itit,String icol,String iur,String host)
	    {
	        
	        try
	        {
	            this.uri=host;
	            MongoClientURI muri = new MongoClientURI(uri);
	    		mongoClient = new MongoClient(muri);
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
	 /*Get infobox*/
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
	
	/*Check table exists in mongodb server using index table*/
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
	
	
	/*Get First paragraph*/
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
	    tx="<h3>"+(String) target1.get("Title")+"</h3>"+tx ;
		}
		
		
		
		mongoClient.close();
		
		}
		catch(Exception e)
		{
			System.out.print("Error on  db getdata "+e.getMessage());
		}
		return tx;
	
	}
	
	  public String fir_txt(String va)
	    {
	        String finalres="";
	      DBObject dbObject = (DBObject) JSON.parse(va);
	        
	 
	     // Object il=dbObject.get("infobox."+berb);
	         finalres=(String)((DBObject)dbObject.get("first_text")).get("val");
	     
	      return finalres;
	   
	    }

}
