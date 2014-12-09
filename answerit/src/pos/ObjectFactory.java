
package pos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pos package. 
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

    private final static QName _TagWordResponse_QNAME = new QName("http://pos/", "tag_wordResponse");
    private final static QName _TagWord_QNAME = new QName("http://pos/", "tag_word");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TagWordResponse }
     * 
     */
    public TagWordResponse createTagWordResponse() {
        return new TagWordResponse();
    }

    /**
     * Create an instance of {@link TagWord }
     * 
     */
    public TagWord createTagWord() {
        return new TagWord();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagWordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pos/", name = "tag_wordResponse")
    public JAXBElement<TagWordResponse> createTagWordResponse(TagWordResponse value) {
        return new JAXBElement<TagWordResponse>(_TagWordResponse_QNAME, TagWordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TagWord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pos/", name = "tag_word")
    public JAXBElement<TagWord> createTagWord(TagWord value) {
        return new JAXBElement<TagWord>(_TagWord_QNAME, TagWord.class, null, value);
    }

}
