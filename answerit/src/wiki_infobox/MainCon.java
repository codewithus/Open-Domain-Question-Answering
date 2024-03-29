
package wiki_infobox;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Main_Con", targetNamespace = "http://wiki_infobox/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MainCon {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "get_first")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get_first", targetNamespace = "http://wiki_infobox/", className = "wiki_infobox.GetFirst")
    @ResponseWrapper(localName = "get_firstResponse", targetNamespace = "http://wiki_infobox/", className = "wiki_infobox.GetFirstResponse")
    @Action(input = "http://wiki_infobox/Main_Con/get_firstRequest", output = "http://wiki_infobox/Main_Con/get_firstResponse")
    public String getFirst(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "info_box")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "info_box", targetNamespace = "http://wiki_infobox/", className = "wiki_infobox.InfoBox")
    @ResponseWrapper(localName = "info_boxResponse", targetNamespace = "http://wiki_infobox/", className = "wiki_infobox.InfoBoxResponse")
    @Action(input = "http://wiki_infobox/Main_Con/info_boxRequest", output = "http://wiki_infobox/Main_Con/info_boxResponse")
    public String infoBox(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
