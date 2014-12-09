/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki_infobox;

import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import java.net.URI;
import java.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Owner
 */
public class Read_wikicont {
       String txt;String title,coll,turl;
     LexicalizedParser lp;
    ArrayList noun_array;
    
    public Read_wikicont()
    {
        
    }
    public Read_wikicont(String st)
    {
        lp = new LexicalizedParser(System.getProperty("user.dir")+"/englishPCFG.ser.gz");
           turl="http://en.wikipedia.org/wiki/"+st;
    }
   public String read_infobox(String pa)
    {
         txt=new String("infobox:{");
         
         try {
       Connection con= Jsoup.connect("http://en.wikipedia.org/wiki/"+pa);
             	con.timeout(0);
            Document doc =con.get();
            doc.select(".reference").remove();
            Elements el=doc.select(".infobox");
          
            // System.out.println("Info Size :"+el.size());
             if(el.size()>0)
             {
        // System.out.println(el.html()+"\n\n");
         
            Elements thr=el.get(0).select("tr");
            
         /* Elements cap=el.get(1).select("caption");
             System.out.println("\n Cap "+cap.html());*/
             
          
           //System.out.println("Len "+thr.size());
           if(thr.size()>2)
           {
         for(int i=0;i<thr.size();i++)
          {
              Elements eld=thr.get(i).select("th");
             // System.out.println(" thead "+eld.html());
              Elements trd=thr.get(i).select("td");
              if(trd.text().length()!=0&&eld.text().length()!=0)
              {
           //   System.out.println("Value "+eld.text()+" - "+trd.text());
                  String va=trd.text().replaceAll(",", "&#44;");
                  va=va.replaceAll(":", "&#58;");
                  va=va.replaceAll("\"","&#8220;");
              txt=txt+val_title(eld.text())+":\""+va+"\",";
              }
          }
         txt=txt.substring(0, txt.length()-1);
         txt=txt+"}";
           }
           else
           {
            
               txt=txt+"det:\"noting\"}";
           }
             }
             
         else
           {
              
               txt=txt+"det:\"noting\"}";
           }
         
         
         txt=txt+",etc:{";
         Elements tit=doc.select("#firstHeading");
            Element elt=tit.get(0);
            title=elt.text();
         
             txt=txt+"Title:\""+elt.text()+"\","+"url :\""+"http://en.wikipedia.org/wiki/"+pa+"\"}";
             
         
         
         
        // txt=txt.replaceAll(",", "#");
     //  mongo_con ob=new mongo_con(val_title(jTextField2.getText()).trim(), txt);
            //System.out.println(txt);
         
        //
         
        } catch (Exception ex) {
            System.out.println("Error in Wiki Read Info "+ex.getMessage());
        }
         
         return txt;
    }
    
    
    public String get_ti(String pa)
    {
        String t="";
        try{
        Connection con= Jsoup.connect("http://en.wikipedia.org/wiki/"+pa);
             	con.timeout(0);
            Document doc =con.get();
            Elements tit=doc.select("#firstHeading");
            Element elt=tit.get(0);
            t=elt.text();
        }catch(Exception ex)
            
        {
            System.out.println("Error on getting title "+ex.getMessage());
        }
        return t;
    }
    
    public String get_title()
    {
        return title;
    }
    
    public String get_ur()
    {
        return  turl;
    }
    
    
    
   public String val_title(String title)
    {
        String tmp=title.toLowerCase();
       // System.out.println("Tit "+title+"  Va  "+title.matches(".*\\s+.*"));
        if(title.contains(" "))
        {
            tmp=tmp.replaceAll(" ", "_");
        }
        if(title.contains("("))
        {
            tmp=tmp.substring(0,tmp.indexOf("("));
            
        }
        if(title.contains("-"))
        {
            tmp=tmp.replaceAll("-", "_");
        }
        if(title.contains("•"))
        {
            tmp=tmp.replaceAll("•", "");
        }
         
        
      //  tmp=tmp+"\"";
        return tmp;
        
    }
    
    public String fir_par(String url)
    {
        
       String  txt="first_text:{val:\"";
       String cont="";
       try{
        Connection con= Jsoup.connect(url);
    con.timeout(0);
		Document doc =con.get();
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
		    	 cont=cont+paragraphs.get(j).text();
			}
		    }
		    else
		    {
		    	cont=cont+paragraphs.get(0).text();
		    }
                    doc=null;
                    con=null;
                    
                    cont=cont.replaceAll(",", "&#44;");
                  cont=cont.replaceAll(":", "&#58;");
                  cont=cont.replaceAll("\"","&#8220;");
		    txt=txt+cont+"\"}";
                    
       }catch(Exception e)
       {
           System.out.println("Error in Wiki Read first par "+e.getMessage());
       }
       return txt;
    }
    
    public ArrayList<String> readwiki(String url)
{
    ArrayList<String> arro=new ArrayList<String>();
  
try{

     // System.out.println("Searching Wiki:  "+url);
    Connection con= Jsoup.connect(url);
    con.timeout(0);
    Document doc =con.get();
    doc.select("sup").remove();
    doc.select("span.unicode.haudio").remove();
    doc.select("table.infobox.vcard").remove();
    doc.select("span.nowrap").remove();
    doc.select("span#coordinates").remove();
    Elements paragraphs = doc.select(".mw-content-ltr p");
   
    int i=1;

    String txt="";
   
  
  //  System.out.println("par size "+paragraphs.size());
    for (int j = 0; j <paragraphs.size(); j++) {
     
             txt=txt+paragraphs.get(j).text();
        
    }
    
    doc=null;
    con=null;

    if(txt.toLowerCase().endsWith(" may refer to:")||txt.toLowerCase().endsWith("most commonly refers to:")||txt.toLowerCase().endsWith("may also refer to:")||txt.toLowerCase().endsWith("could refer to:"))
    {
       
     
            
        System.out.print("May Refer Error");
        
      
    }
    else
    {
        
        
  
        System.out.println("Reading Completed..");
        txt= txt.replaceAll("\\(", "");
   txt= txt.replaceAll("\\)", "");
        String arr[]=txt.split("\\.");
        
    arro.addAll(Arrays.asList(arr));
        //System.out.println("Size : "+arro.size());
        int si=arro.size();
        int io=0;
        
        try
        {
        
        while(io!=si)
        {
            if(arro.get(io).toString().length()==arro.get(io).lastIndexOf(" ")+2)
            {
                arro.set(io, arro.get(io)+"."+arro.get(io+1).toString());
                arro.remove(io+1);
              /* int si1=arro.size();
                int iop=io+1;
                while(iop!=si1-1)
                {
                    arro.set(iop, arro.get(iop+1).toString());
                    si1=arro.size();
                    iop++;
                }*/
                
            }
            //System.out.println("Word"+arro.get(io).toString()+" Length "+arro.get(io).length()+" Last Space "+arro.get(io).lastIndexOf(" "));
           
            si=arro.size();
            io++;
        }
        
        }
        catch(Exception e)
        {
          System.out.println("Error in Wiki Read Full Text "+e.getMessage());
        }
        
  
        
  
    
    }
    }
    catch(Exception ex)
    {
              System.out.println("Error in Wiki Read Full Text "+ex.getMessage());
        //ex.printStackTrace();
    }
    
    return arro;
   
}
    
    public String tag_word(String sub,ArrayList<String> st)
    {
        System.out.println("Content being parsed please wait...!");
          noun_array=new ArrayList<String>();
        String full_txt="fulltext : {";
      // ArrayList<String> verb_array=new ArrayList<String>();
       Map verb_array=new HashMap();
        ListIterator li1=st.listIterator();
        try
        {
            
       
      /*  noun_array=new ArrayList<String>();
        String full_txt="fulltext : {";
      // ArrayList<String> verb_array=new ArrayList<String>();
       Map verb_array=new HashMap();
        ListIterator li1=st.listIterator();*/
     //    System.out.println("Size to tag : "+ st.size());
        
        while(li1.hasNext())
        {
            
        String query=li1.next().toString();
      
        Tree parse = lp.apply(query);
     
    
       ArrayList<TaggedWord> tw=parse.taggedYield();
       
  
      ListIterator li=tw.listIterator();
        //System.out.println("\nWord : "+query);
           
      while(li.hasNext())
      {
         
          TaggedWord tw1=(TaggedWord) li.next();
          
        /*  if(tw1.tag().equals("NN"))
          {
              noun_array.add(tw1.value());
              System.out.print(" Noun : "+tw1.value());
          }
           if(tw1.tag().equals("VBZ"))
          {
              if(!verb_array.contains(tw1.value()))
              {
              verb_array.add(tw1.value());
              System.out.print(" Verb : "+tw1.value()+" Value"+query.substring(query.indexOf(tw1.value())+tw1.value().length(),query.length()));
              }
             
          }*/
          
          
            if(tw1.tag().equals("VBZ"))
          {
              //System.out.println("Adding "+tw1.value()+" Val "+tw1.tag()+" Quer = "+query);
              if(!verb_array.containsKey(tw1.value()))
              {
              //verb_array.put(tw1.value(), query.substring(query.indexOf(tw1.value())+tw1.value().length(),query.length()));
                  verb_array.put(tw1.value(), query);
             // System.out.print(" Verb : "+tw1.value()+" Value"+query.substring(query.indexOf(tw1.value())+tw1.value().length(),query.length()));
              }
              else
              {
                  //verb_array.put(tw1.value(), verb_array.get(tw1.value()) +"|"+query.substring(query.indexOf(tw1.value())+tw1.value().length(),query.length()));
                  verb_array.put(tw1.value(), verb_array.get(tw1.value()) +"|"+query);
              }
             
              
              break;// break IS USED FOR ADD ONLY FIRST VERB IN A SENTANCE
             
          }
        
         // System.out.println(tw1.toString());
      }
       
     
      
        }
        }
        catch(Exception ex)
       {
           System.out.println("Error on tag "+ex.getMessage());
       }
         if(verb_array.containsKey("'s"))
        {
        verb_array.remove("'s");
        }
        Iterator iter = verb_array.entrySet().iterator();
 //int siz=1;
			while (iter.hasNext()) {
                         /* if(siz>3)
                          {
                              break;
                              
                          }*/
                         // else{
                            Map.Entry mEntry = (Map.Entry) iter.next();
                            
                              if(!isInteger(mEntry.getKey().toString()))
                              {
                               full_txt=full_txt+mEntry.getKey()+":\""+val_value(mEntry.getValue().toString())+"\",";
                          // System.out.println("Map Key "+mEntry.getKey()+"\n Value "+val_value(mEntry.getValue().toString()));
                              }
                              else
                              {
                                   full_txt=full_txt+"_"+mEntry.getKey()+":\""+val_value(mEntry.getValue().toString())+"\",";
                              }
                        //  }
                          // siz++;
                        }
                        
                        full_txt=full_txt.substring(0,full_txt.length()-1);
                        full_txt=full_txt+"}";
                        return full_txt;
                        
    }
    
    public String val_value(String txt)
    {
        String tmp=txt;
       tmp=tmp.replaceAll(",", "&#44;");
                  tmp=tmp.replaceAll(":", "&#58;");
                   tmp=tmp.replaceAll("\"","&#8220;");
        
        return tmp;
    }
    
    public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } 
    catch(NumberFormatException e) { 
        return false; 
    }
    return  true;
    }
}
