/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki_infobox;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Make_DB  extends Thread
{
    String info,full,first,url="";String txt;
    
    String title,coll,turl,dbhost;
String doc="{";Read_wikicont wiki;
    public Make_DB(String in,String fu,String fir,String ur,String host) 
    {
        info=in;
        fu=full;
        first=fir;
        url=ur;
        dbhost=host;
         wiki=new Read_wikicont(ur);
          start();
          System.out.println("Thread Started.. "+ur+" Id "+getId());
    }
    
    @Override
    public void run()
    {
        System.out.println("Value in= "+info.length());
        if(info.length()==0)
        {
           // System.out.println("Read Wikkkkkkki");
        doc=doc+wiki.read_infobox(url);
        }
        else
        {
            doc=doc+info;
        }
      //  System.out.println("Infobox \n "+doc+"\n\n");
        ArrayList<String> arro=wiki.readwiki("http://en.wikipedia.org/wiki/"+url);
      doc=doc+","+wiki.tag_word("Google",arro);
        System.out.println("Parsing Done..!");
        
      if(first.length()==0)
      {
         
           doc=doc+","+wiki.fir_par("http://en.wikipedia.org/wiki/"+url);
      }
      else
      {
        doc=doc+","+first;
      }
       doc=doc+"}";
      //  System.out.println("\n\n FullJson "+doc);
         coll=url.toLowerCase();
         
    mongo_con ob=new mongo_con(wiki.val_title(url.toLowerCase()), doc,wiki.get_ti(url), coll, wiki.get_ur(),dbhost);

    //ob.ins_qus();
    interrupt();
        System.out.println(url+" Thread Stoped..!"+" id "+getId());
    }
    
}
