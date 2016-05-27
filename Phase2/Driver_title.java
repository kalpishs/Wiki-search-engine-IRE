
import javax.xml.parsers.SAXParser;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class Driver_title {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {	
			 System.out.println("intialized");
	         File inFile = new File("enwiki-latest-pages-articles.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         Map_title handler = new Map_title();
	         saxParser.parse(inFile, handler);
	         System.out.println("success");
	     	 }catch (Exception e) {
	         e.printStackTrace();
	      }
	}

}
