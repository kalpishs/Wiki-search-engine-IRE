import java.util.HashSet;
import javax.xml.parsers.SAXParser;
import java.util.Set;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class Driver {
	public static Set<String> stop_words_hash = new HashSet<String>();
	static int base,count=0;
	static boolean entry_first_time=false;
	public static String args_1;
	public static void main(String[] args) {
		args_1=args[1];
		int i;
		for(i=0;i<=435;++i)
    	{
    		stop_words_hash.add(help_parsing.stop_words[i]);
    		
    	}
		 try {	
			
	         File inFile = new File(args[0]);
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         Handller_pars handler = new Handller_pars();
	         saxParser.parse(inFile, handler);
	         
		 }catch (Exception e) {
	         e.printStackTrace();
	      }
		 
	}

}