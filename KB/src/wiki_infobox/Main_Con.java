/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki_infobox;

import javax.jws.WebService;

/**
 *
 * @author Owner
 */
@WebService
public class Main_Con {
  String txt;String title,coll,turl;
   Read_wikicont wiki;
   String dbhost;
   
   public Main_Con()
   {
   }
    public Main_Con(String dbhost) {
        wiki=new Read_wikicont();
    }
    
    public String get_first(String st)    
    {
        String fir="";     String fir1="";
          mongo_con con=new mongo_con(dbhost);
        String cname=con.find_coll(st);
        System.out.print("\n"+cname+"\t");
//        System.out.println(st+"Cname == "+cname);
        if(!cname.equals("not found"))
        {
            //System.out.println("in a collection name "+cname);
          
           fir1=con.get_data(cname);
            System.out.print("Found"+"\t");
        }
        else
        {
            try {
                 /* System.out.println("Searching wiki ");
              String inf=wiki.read_infobox(st);
                 System.out.println("text  "+inf);
                con.info("{"+inf+"}",jTextField1.getText());*/
                 System.out.print("Searching Wiki"+"\t");
            fir=wiki.fir_par("http://en.wikipedia.org/wiki/"+st);
			System.out.println(fir);
                fir1=con.fir_txt("{"+fir+"}}");
                
               Make_DB obj=new Make_DB("", "", fir,st.trim(),dbhost);
                System.out.print("Done"+"\t");
                 
            } catch (Exception ex) {
              System.out.println("Error On Main_con "+ex.getMessage());
            }
        }
       
        con=null;
         return fir1;
    }
    public String info_box(String st,String verb)
    {
        String info="";
        mongo_con con=new mongo_con(dbhost);
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
