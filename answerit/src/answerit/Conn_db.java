package answerit;


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

public class Conn_db {
	  MongoClient mongoClient;   DB db ;

	public String get_data(String collname,String ty) {
		String tx="";
		try{
		mongoClient = new MongoClient( "localhost" , 27017 );
        //String json = "{idp:'ddsssd',opl:'dddee'}";
     db= mongoClient.getDB( "answeritdb" );
		
		DBCollection tab=db.getCollection(collname);
		System.out.println("Collec "+collname+" Type "+ty+" Found : "+tab.count());
		if(tab.count()==0)
		{
		
			tx="not found";
			
			return tx;
		}
		else
		{
			if(ty.equals("first_text"))
			{
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
			
		
			}
			
			
			
		}
		
		mongoClient.close();
		
		}
		catch(Exception e)
		{
			System.out.print("Error on  db conn"+e);
		}
		return tx;
	
	}
	
	
	public String get_infobox(String collname,String verb) {
		String tx="";
		try{
			mongoClient = new MongoClient( "localhost" , 27017 );
	        //String json = "{idp:'ddsssd',opl:'dddee'}";
	     db= mongoClient.getDB( "answeritdb" );
			
			DBCollection tab=db.getCollection(collname);
			
			  BasicDBObject query = new BasicDBObject();
	           
		        BasicDBObject field = new BasicDBObject();
		        field.put("infobox", 1);
		        DBCursor cursor = tab.find(query,field);
		        //System.out.println("Collection name "+collname+" Verb "+verb+" Size "+cursor.size());
				while (cursor.hasNext()) 
				{
				    BasicDBObject obj = (BasicDBObject) cursor.next();
				    System.out.print("infobox  : "+obj.getString("infobox"));
				    BasicDBObject target = (BasicDBObject) obj.get("infobox");
				    tx=tx +(String) target.get(verb);
				}
		        
		
	}
		catch(Exception e)
		{
			System.out.print("Error on getinfo"+e);
		}
		return tx;
}
	
	
	
	public void ins_qus(String qu)
	{
		
		try{
			mongoClient = new MongoClient( "localhost" , 27017 );
	        //String json = "{idp:'ddsssd',opl:'dddee'}";
	     db= mongoClient.getDB( "answeritdb" );
			
			DBCollection tab=db.getCollection("qus");
			
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("ques", qu);
			DBCursor cursor = tab.find(whereQuery);
			if(cursor.size()==0)
			{
				BasicDBObject document = new BasicDBObject();
				document.put("ques", qu);
				
				tab.insert(document);
				//System.out.print("Question added");
			}
						
			/**/
			
		}
		catch(Exception ex)
		{
			System.out.print("Error "+ex);
			
		}
		
	}
}


