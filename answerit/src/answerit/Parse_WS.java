package answerit;

import java.io.IOException;
import java.io.PrintWriter;
import answerit.Weather;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.*;
import wiki_infobox.MainCon;
import wiki_infobox.MainConService;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parse_WS extends HttpServlet {
	
	Conn_db mcon;
	
	MainConService dbs=new MainConService();
	MainCon mdb;
	
	
	@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException
	{
		boolean res=false;
		
		String qur=req.getParameter("qus");
		if(qur.endsWith("?"))
		{
			qur=qur.substring(0,qur.length()-1);
		}
		PrintWriter pw=resp.getWriter();
		// Greeter greeter = new GreeterService().getGreeterPort();
		PosTagService ps=new PosTagService();
		PosTag pos=ps.getPosTagPort();
		  String message;	  Read_Wiki ob=new Read_Wiki();
		  String txt="";String he="";
		  resp.setContentType("text/html");
		  //message = greeter.sayHello(qur);
		  message=pos.tagWord(qur);
		  String ur="";
		  pw.print("<html><head><title>Open Domain Question Answering</title><link rel=\"stylesheet\" href=\"sty.css\">    ");
		  pw.println("</head><body onload='welcome()'><div id=\"sercont1\"><form action=\"parse\" method=\"get\" name=\"frm\" class='frm'>Enter Your Question &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"qus\" class='txt1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"hidden\" name=\"kl\" value=\"true\"> <input type=\"submit\" value=\"Submit\" class=\"but\"> &nbsp;&nbsp;&nbsp;(Ex :What is cloud computing)</form></div>");
		  String con[]=message.split("\\s");
		  
		  if(message.matches(".+?_JJ")&&con.length==1||message.matches(".+?_NN")&&con.length==1)
		  {
			  
					 
		
			  mdb=dbs.getMainConPort();
		
			  txt=mdb.getFirst(con[0].substring(0, con[0].length()-3));
			  if(txt.length()<1)
			  {
				  res=true;
			  }
			  mdb=null;
				 
		  }
			  
		  
		  
		  
		  
		  
		  else  if((message.matches(".+?_WP\\s.+?_VBZ\\s.+?_NN+$")&& con.length==3)||(message.matches(".+?_WP\\s.+?_VBZ\\s.+?_JJ+$")&&con.length==3))
		  {
			 				 
			  mdb=dbs.getMainConPort();
		
			  txt=mdb.getFirst(con[2].substring(0, con[2].length()-3));
			  if(txt.length()<1)
			  {
				  res=true;
			  }
			  mdb=null;

		  }
		  
		  else if((message.matches(".+?_WP\\s.+?_VBZ\\s.+?_NN\\s.+?_NN+$")&&con.length==4)||(message.matches(".+?_WP\\s.+?_VBZ\\s.+?_JJ\\s.+?_NN+$")&&con.length==4))
		  {
			// pw.print("Wait for Some Time Being Parsed...");
			  
			 
				 mdb=dbs.getMainConPort();
					
				  txt=mdb.getFirst(con[2].substring(0, con[2].length()-3)+"_"+con[3].substring(0, con[3].length()-3));
				  if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
			/*  try{
			 ob=new Read_Wiki(con[2].substring(0, con[2].length()-3)+"_"+con[3].substring(0, con[3].length()-3));
			  ob.readfirst_par();
			he=con[2].substring(0, con[2].length()-3)+" "+con[3].substring(0, con[3].length()-3);
			  txt=ob.get_cont();
			  }
			  catch(Exception e)
			  {
				  pw.print(e.getMessage());
				  e.printStackTrace();
			  }*/
			  
		  }
		  
		  else if((message.matches(".+?_WP\\s.+?_VBZ\\s.+?_NN\\s.+?_NNS+$")&&con.length==4)||(message.matches(".+?_WP\\s.+?_VBZ\\s.+?_JJ\\s.+?_NNS+$")&&con.length==4))
		  {
			// pw.print("Wait for Some Time Being Parsed...");
			  
				 mdb=dbs.getMainConPort();
					
				  txt=mdb.getFirst(con[2].substring(0, con[2].length()-3)+"_"+con[3].substring(0, con[3].length()-4));
				  if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
			  
		  }
		  
		  else if((message.matches(".+?_WP\\s.+?_VBZ\\s.+?_NNS\\s.+?_NN+$")&&con.length==4))
		  {
			// pw.print("Wait for Some Time Being Parsed...");
			  
			
				 
				 mdb=dbs.getMainConPort();
					
				  txt=mdb.getFirst(con[2].substring(0, con[2].length()-3)+"_"+con[3].substring(0, con[3].length()-4));
				  if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
			  
		  }
		  
		  else if((message.matches("[Ww]hen_WRB\\s.+?_VBD\\s.+?_NNS\\s.+?_VBN+$")&&con.length==4))
		  {
			  //txt="Go to infobox";
			  
			  mdb=dbs.getMainConPort();
			txt=  mdb.infoBox(con[2].substring(0,con[2].length()-4),con[3].substring(0,con[3].length()-4));
			 
			 if(txt.length()<1)
			  {
				  res=true;
			  }
			  mdb=null;
		  }
		  else if((message.matches("[Ww]hen_WRB\\s.+?_VBD\\s.+?_NN\\s.+?_VBN+$")&&con.length==4))
		  {
			  
			  
			  mdb=dbs.getMainConPort();
				txt=  mdb.infoBox(con[2].substring(0,con[2].length()-3),con[3].substring(0,con[3].length()-4));
				 
				 if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
			  
			  
			  
			  
		  }
		  else if((message.matches(".+?_WRB\\s.+?_VBD\\s.+?_NN\\s.+?_NN\\s.+?_VBN+$")&&con.length==5)||(message.matches(".+?_WRB\\s.+?_VBD\\s.+?_JJ\\s.+?_NN\\s.+?_VBN+$")&&con.length==5))
		  {

			  mdb=dbs.getMainConPort();
			  String co=con[2].substring(0,con[2].length()-3)+"_"+con[3].substring(0,con[3].length()-3);
				txt=  mdb.infoBox(co,con[4].substring(0,con[4].length()-4));
				 
				 if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
				  
		  }
		  
		  else if(message.matches(".+?_WP\\s.+?_VBZ\\s.+?_NN\\s.+?_IN\\s.+?_NN+$")&&con.length==5)
		  {
			  System.out.print(" Here in \n\n");

			  mdb=dbs.getMainConPort();
				txt=  mdb.infoBox(con[4].substring(0,con[4].length()-3),con[2].substring(0,con[2].length()-3));
				 
				 if(txt.length()<1)
				  {
					  res=true;
				  }
				  mdb=null;
			  
			  
			  
		  }
		  else if(message.matches("[Ww]eather_VB\\s.+?_IN\\s.+?$"))
		{
			  Weather wobj=new Weather();
			txt=  wobj.getWeather(qur.substring(qur.indexOf("in")+3,qur.length()));
					  if(txt.length()>0)
					  {
						  res=true;
					  }
					  else
					  {
						  txt="Cannot Find..";
					  }
		 }
		  /*  else if(message.matches("[Ww]eather_VB\\s.+?_IN\\s.+?_NNS+$"))
			{
				  Weather wobj=new Weather();
				txt= wobj.getWeather(qur.substring(qur.indexOf("in"),qur.length()));
						  if(txt.length()>0)
						  {
							  res=true;
						  }
						  else
						  {
							  txt="Cannot Find..";
						  }
			 }
		  else if(message.matches("[Ww]eather_VB\\s.+?_IN\\s.+?_NN\\s.+?_NNS+$"))
			{
				  Weather wobj=new Weather();
				txt= wobj.getWeather(con[2].substring(0,con[2].length()-3)+" "+con[3].substring(0,con[3].length()-4));
						  if(txt.length()>0)
						  {
							  res=true;
						  }
						  else
						  {
							  txt="Cannot Find..";
						  }
			 }*/
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  else
		  {
			  he="";
			 txt="<br><br><b>We are not ready to parse this question type..  :(    soon we will fix it </b> &nbsp;&nbsp;&nbsp;";
		  }
		  
		//  pw.print("<h3>"+he+"</h3>");
		  //JSP may stand for:
		  if(txt.toLowerCase().endsWith(" may refer to:")||txt.toLowerCase().endsWith("most commonly refers to:")||txt.toLowerCase().endsWith("may also refer to:")||txt.toLowerCase().endsWith("could refer to:"))
		    {
			/*  String goo=readgoogle(qur, ob.get_ur(),pw);
			 if((!goo.equals(ob.get_ur())))
			 {
				 ob=new Read_Wiki();
				 ob.read_url(goo);
				 pw.print("<div id=\"wiki\"><p>"+ob.get_cont()+"<p></div>");
			 }*/
			  
			  
			  txt="<br><br><b>Duplicate Values found..  :(    soon we will fix it </b> &nbsp;&nbsp;&nbsp;";
		    }
		  else
		  {
			  if(res)
			  {
				 // pw.print("Answer Found..!");
				  mcon=new Conn_db();
				  mcon.ins_qus(qur);
			  }
		  pw.print("<div id=\"wiki\"><p>"+txt+"</p></div>");
		
		
		  }
		  pw.print(message);
		  pw.print("</body></html>");
	}
	
	
	
	//------------ code not in use------------------------//
	
	
	
	/*public String readgoogle(String qur,String ur,PrintWriter pw)
    {
		String lik=ur;
        System.out.println(" Google Serach....");
            try {
                 Connection con;
 
		// need http protocol
                Document doc1;
                pw.print(qur+"<br>");
                if(qur.contains(" "))
                {
                    String st=qur.replace(" ","%20");
                        con= Jsoup.connect("http://www.google.com/search?hl=en&q="+st);
                }
                else
                {
             con= Jsoup.connect("http://www.google.com/search?hl=en&q="+qur);
               // con= Jsoup.connect("https://www.google.com/#q="+qur);
                }
    con.timeout(0);
    con.userAgent("mozilla");
		doc1 = con.get();
 
		// get page title
		String title = doc1.title();
		System.out.println("title : " + title);
 
		// get all links
		Elements links = doc1.select("cite");
                Elements goo=doc1.select("h3.r");
		for (int i = 0; i < links.size(); i++) {
                    
              
 
			// get the value from href attribute
			System.out.println("\nlink : " + links.get(i).text()+" text : "+goo.get(i).text());
			if(links.get(i).text().contains("wikipedia"))
                        {
                            
                            
                        //   readwiki("http://"+links.get(i).text());
                          
                            int pos=ur.indexOf("//");
                                System.out.println(pos);
                                String ur1=ur.substring(7,ur.length());
                                 pw.println("<br>"+links.get(i).text().toLowerCase()+" Comp "+ur1);
                            if(!links.get(i).text().toLowerCase().equals(ur1))
                            {

                             lik="http://"+ links.get(i).text();
                             break;
                            }
                     
                        }
                      
 con=null;
		}
               
 
	} catch (Exception e) {
	
      pw.print(e);
                
	}
           
            return lik;
}
	
	
	
	
	*/
	
	
	
	
	
}
