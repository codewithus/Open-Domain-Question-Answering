package pos;
import javax.jws.WebService;
 import java.util.ArrayList;
import java.util.ListIterator;

import javax.jws.WebService;

import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
@WebService
public class pos_tag {
LexicalizedParser lp;
	public  pos_tag()
	{
		lp = new LexicalizedParser(System.getProperty("user.dir")+"/englishPCFG.ser.gz");
	}
    public String tag_word(String word)
	{
		 String val=new String("");
		  Tree parse = lp.apply(word);
			ArrayList<TaggedWord> tw=parse.taggedYield();
		
			ListIterator<TaggedWord> li=tw.listIterator();
			 while(li.hasNext())
		      {
		    	  TaggedWord tug=(TaggedWord) li.next();
		    	  
		    	 val=val+tug.word()+"_"+tug.tag()+" ";
		      }
			 val=val.substring(0,val.length()-1);
	    return val;
	}
 
}
