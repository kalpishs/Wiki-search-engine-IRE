import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;
import java.io.*;

public class Map_title extends DefaultHandler {
	String title_all;
	String id_print;
	int flag=0;
	boolean flag_title = false,page=false,flag_readid=false,doc_id=false, id_1 = false;
	String str[] = new String[4];
	private FileWriter fw;
	private BufferedWriter bw;
	@Override
 
	public void startDocument(){
	 }
 @Override
 public void endDocument(){}
 @Override
 public void startElement(String nameSpaceURI,String localName,String qName,Attributes atts) throws SAXException {
	try{
	 if (qName.equalsIgnoreCase("page")) 
     {
     	page=true;     	
     } 
	 else if (qName.equalsIgnoreCase("title")) 
	 {
		 if (page==true)
		 {
			  page=false;
	    	  flag_title=true;
		 }
	 }
	 else if(qName.equalsIgnoreCase("id")&& id_1==true)
	 {
		 doc_id=true;
		 flag_readid=true;
		 
	 }
	}catch(Exception e){ 
		System.out.println("non starting tag");
	}
	 
	 }
 
 @Override  
 public void endElement(String nameSpaceURI,String localName,String qName) throws SAXException{
	 try{
	 if(qName.equalsIgnoreCase("title")) 
     {
		flag_title=false;
		if (id_1 ==false)
	     	{
			id_1  = true;
	     	}
     }
	 else if(qName.equalsIgnoreCase("id")&& id_1==true)
	 {
		 id_1 = false;
		 flag_readid = false;
	 }
	 }
	 catch (Exception e){}
 }
 public void characters(char ch[], 
    int start, int length) throws SAXException 
    {
	
	 		if (flag_title==true) 
	 		{
	 			title_all =new String(ch, start, length);
	 			str[2]=title_all;
	 			str[3]="\n";
	 			flag=1;
	 		}
	 		else if (flag_readid==true) 
	 		{
	 			id_print =new String(ch, start, length);
	 			str[0]=id_print;
	 			str[1]="=";
	 			if(flag==1)
	 			{
	 				String str_write = str[0] + str[1] + str[2] + str[3];
	 				//System.out.print(str_write);
	 				try {
						fw=new FileWriter("Title/title_id",true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 				bw=new BufferedWriter(fw);
	 				try {
						bw.write(str_write);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 				try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 				flag=0;
	 			}
	 					
	 		}
	 		else if(doc_id==true)
	 		{
	 			doc_id=false;
	 		}
	 		
    
    
 }
}
