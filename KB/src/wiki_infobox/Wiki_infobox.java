/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki_infobox;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Dinesh Kumar
 */
public class Wiki_infobox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       /* mainfrm ob=new mainfrm();
        ob.setVisible(true);
        ob.setDefaultCloseOperation(2);
        /*mongo_con o=new mongo_con("siri","{is:'Addas'}");*/
        
        
        Endpoint.publish("http://localhost:8087/KB", new Main_Con(args[0]));
	System.out.print("KB Web Service Started..!");
        
        
        
        
        
    }
}
