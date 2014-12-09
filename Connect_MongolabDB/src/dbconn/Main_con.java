package dbconn;
import javax.jws.WebService;
import javax.jws.WebMethod;


@WebService

public class Main_con {
	Mongo_Con ob;
	Read_wikicont wiki;
	String dbhost="";
	
	public Main_con()
	{
		dbhost="mongodb://dinesh:mypass6@ds055690.mongolab.com:55690/answeritdb";
		ob=new Mongo_Con(dbhost);
		wiki=new Read_wikicont();
	}
	
	@WebMethod
	public String get_first(String st)    
    {
		String fir="";     String fir1="Not Found..";
		  String cname=ob.find_coll(st);
		if(!cname.equals("not found"))
        {
            //System.out.println("in a collection name "+cname);
          
           fir1=ob.get_data(cname);
            System.out.print("Found"+"\t");
        }
		else
		{
			 fir1=wiki.fir_par("http://en.wikipedia.org/wiki/"+st);
				//System.out.println(fir);
			 fir1=ob.fir_txt("{"+fir1+"}}");
			 Make_DB obj=new Make_DB("", "", fir,st.trim(),dbhost);
		}
		return fir1;
    }
	@WebMethod
	  public String info_box(String st,String verb)
	    {
	        String info="";
	        Mongo_Con con=new Mongo_Con(dbhost);
	        String cname=con.find_coll(st);
	        System.out.print("\n"+cname+"\t");
	        if(!cname.equals("not found"))
	        {

	           info=con.get_infobox(cname,verb);
	           System.out.print("Found"+"\t");
	        }
	        else
	        {
	            try {
	                System.out.print("Searching wiki \t");
	                String inf=wiki.read_infobox(st);
	               // System.out.println("text  "+inf);
	               info=con.info("{"+inf+"}",verb);
	                 Make_DB obj=new Make_DB(inf, "", "",st,dbhost);
	                System.out.print("Done");
	                 
	                 
	            } catch (Exception ex) {
	                System.out.println("Error On Main_con "+ex.getMessage());
	            }
	        }
	        con=null;
	        return info;
	    }
	    

}
