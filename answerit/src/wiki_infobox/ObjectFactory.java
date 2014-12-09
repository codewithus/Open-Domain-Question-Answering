
package wiki_infobox;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wiki_infobox package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetFirstResponse_QNAME = new QName("http://wiki_infobox/", "get_firstResponse");
    private final static QName _InfoBoxResponse_QNAME = new QName("http://wiki_infobox/", "info_boxResponse");
    private final static QName _GetFirst_QNAME = new QName("http://wiki_infobox/", "get_first");
    private final static QName _InfoBox_QNAME = new QName("http://wiki_infobox/", "info_box");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wiki_infobox
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InfoBox }
     * 
     */
    public InfoBox createInfoBox() {
        return new InfoBox();
    }

    /**
     * Create an instance of {@link GetFirst }
     * 
     */
    public GetFirst createGetFirst() {
        return new GetFirst();
    }

    /**
     * Create an instance of {@link InfoBoxResponse }
     * 
     */
    public InfoBoxResponse createInfoBoxResponse() {
        return new InfoBoxResponse();
    }

    /**
     * Create an instance of {@link GetFirstResponse }
     * 
     */
    public GetFirstResponse createGetFirstResponse() {
        return new GetFirstResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFirstResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wiki_infobox/", name = "get_firstResponse")
    public JAXBElement<GetFirstResponse> createGetFirstResponse(GetFirstResponse value) {
        return new JAXBElement<GetFirstResponse>(_GetFirstResponse_QNAME, GetFirstResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoBoxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wiki_infobox/", name = "info_boxResponse")
    public JAXBElement<InfoBoxResponse> createInfoBoxResponse(InfoBoxResponse value) {
        return new JAXBElement<InfoBoxResponse>(_InfoBoxResponse_QNAME, InfoBoxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFirst }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wiki_infobox/", name = "get_first")
    public JAXBElement<GetFirst> createGetFirst(GetFirst value) {
        return new JAXBElement<GetFirst>(_GetFirst_QNAME, GetFirst.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoBox }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wiki_infobox/", name = "info_box")
    public JAXBElement<InfoBox> createInfoBox(InfoBox value) {
        return new JAXBElement<InfoBox>(_InfoBox_QNAME, InfoBox.class, null, value);
    }

}
