package answerit;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Read_Wiki  {
Connection con;
Document doc;
String txt="";
String url;

public Read_Wiki()
{
	
}
	public Read_Wiki(String Cont)
	{
		
		
		/*Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(Cont);
		String tm=Cont;
		boolean found = matcher.find();
		        if(found)
		        {
		        	
		            System.out.println("Space Avaiable");
		            
		            tm=tm.replaceAll("\\s","_");
		            System.out.println("Final "+tm);
		        }*/
		
		
		       url="http://en.wikipedia.org/wiki/"+Cont;
		       
		       //Response.print("Connecting "+url);
		con= Jsoup.connect(url);
		    con.timeout(0);
		
	}
	
		public void run() throws Exception
		{
			
				txt="";
			 doc =con.get();
		    doc.select("sup").remove();
		    doc.select("span.unicode.haudio").remove();
		    doc.select("table.infobox.vcard").remove();
		    doc.select("span.nowrap").remove();
		    doc.select("span#coordinates").remove();
		    Elements paragraphs = doc.select(".mw-content-ltr p");
		    for (int j = 0; j <paragraphs.size(); j++)
		    {
		    	 txt=txt+paragraphs.get(j).text();
			}
			
		}
		
		
		
		public void read_url(String url) {
			try
			{
			con= Jsoup.connect(url);
		    con.timeout(0);
			txt="";
			 doc =con.get();
		    doc.select("sup").remove();
		    doc.select("span.unicode.haudio").remove();
		    doc.select("table.infobox.vcard").remove();
		    doc.select("span.nowrap").remove();
		    doc.select("span#coordinates").remove();
		    Elements paragraphs = doc.select(".mw-content-ltr p");
		    if(paragraphs.size()>2)
		    {
		    for (int j = 0; j <2; j++)
		    {
		    	 txt=txt+paragraphs.get(j).text();
			}
		    }
		    else
		    {
		    	txt=txt+paragraphs.get(0).text();
		    }
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			
		}
		
		
		public void readfirst_par() throws IOException
		{
			txt="";
			 doc =con.get();
		    doc.select("sup").remove();
		    doc.select("span.unicode.haudio").remove();
		    doc.select("span.nowrap:nth-child(2)").remove();
		    doc.select("#mw-content-text > p:nth-child(5) > span:nth-child(4)").remove();
		    doc.select("table.infobox.vcard").remove();
		    doc.select("span.nowrap").remove();
		    doc.select("span#coordinates").remove();
		    
		  
		    Elements paragraphs = doc.select(".mw-content-ltr p");
		    if(paragraphs.size()>2)
		    {
		    for (int j = 0; j <2; j++)
		    {
		    	 txt=txt+paragraphs.get(j).text();
			}
		    }
		    else
		    {
		    	txt=txt+paragraphs.get(0).text();
		    }
		    txt=txt+"  <br> "+doc.select("firstHeading").val();
		    
		}
		
		public String get_ur()
		{
			return url;
		}
		
		
		public String get_cont()
		{
			return txt;
		}
}
