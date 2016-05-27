

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.*;
import org.xml.sax.helpers.DefaultHandler;
public class Handller_pars extends DefaultHandler{
	static String curid;
	StringBuilder all_data = new StringBuilder();
	String title_all;
public static Map<String, HashMap<String, listing>> database = new TreeMap<String,HashMap<String,listing>>();
	StringBuilder body_data=new StringBuilder();
	StringBuilder categ_data = new StringBuilder();
 boolean flag_title = false,page=false,flag_readid=false,doc_id=false;
 StringBuilder data = new StringBuilder();
 StringBuilder str = new StringBuilder();
 boolean rev=false,text=false;
 StringBuilder btitle = new StringBuilder();
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
         //create a new Employee and put it in Map
     	++Driver.count;
     	
     } 
	 else if (qName.equalsIgnoreCase("title")) 
	 {
		 if (page==true)
		 {
			  page=false;
	    	  flag_title=true;
		 }
	 }
	 else if(qName.equalsIgnoreCase("id")&&flag_readid==true)
	 {
		 flag_readid= false;
		 doc_id=true;
	 }
	 else if(qName.equalsIgnoreCase("revision")) 
     {
		 rev=true;
     }
	 else if(qName.equalsIgnoreCase("text")&&rev==true) 
     {
		 rev=false;
         text=true;
         all_data.setLength(0);
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
		if (flag_readid==false)
	     	flag_readid=true;
     }
     else if(qName.equalsIgnoreCase("text")) 
     {
        text_handling();
        text=false;
     	all_data.setLength(0);
     	
     }
     else if(qName.equalsIgnoreCase("mediawiki"))
     {
    	 try
     	{
     	Writeing_to_file.write_db();
     	 body_data.setLength(0);
       	 btitle.setLength(0);
       	 categ_data.setLength(0);
       	 str.setLength(0);
     	}catch(Exception e){
     		     		
     	}
    	 
     }
	 }
	 catch (Exception e){}
 }
 private void text_handling() throws Exception{
	 
	 try{
		 int length,i;
		 length=all_data.length();
		 //System.out.print(all_data);
		 
	     boolean flag_infobox = false;
	     for(i=0;i<length;++i)
	     {
	     	char current1 = all_data.charAt(i);
	    	 if (current1=='{')
	    	 {
	    		 if(i+9<length&&all_data.substring(i+1, i+9).equalsIgnoreCase("{infobox"))
	    			 {
	    				 i=9+i;
	     				boolean flag_addtext=false;
	     				int count_par=2;
	     				flag_infobox=true;
	     				while(flag_infobox)
	        			{
	        				if(i<length)
	        					break;
	     					char current = all_data.charAt(i);
	        				//System.out.print(current);
	        				if(current=='{')
	        					++count_par;
	        				else if(current=='}')
	        					--count_par;
	        				if(i>=length||count_par==0)
	        				{
	        					++i;
	        					flag_infobox = false;
	        					break;
	        				}
	        				//Add Text Between [ and ] to process
	        				if(current=='['&&all_data.charAt(i+1)=='[')
	        				{
	        					if(flag_addtext==false)
		        					flag_addtext=true;
	        				}
	        				else if(current==']'&&all_data.charAt(i+1)==']')
	        				{
	        					flag_addtext=false;
	        					help_parsing.infobox_handle(str.toString());
	        					str.setLength(0);
	        				}
	        				
	        				if(flag_addtext)
	        				{
	        					if((current>64&&current<91)||(current>98&&current<123))
	        				{ str.append(current);
	        				}
	        				else
	        				{
	        					help_parsing.infobox_handle(str.toString());
	        					str.setLength(0);
	        				}
	        					
	        				}
	        				++i;
	        			}	        				
	     				
	    			 }//Infobox-Done
	    			 else if(i+6<length&&all_data.substring(i+1, i+6).equalsIgnoreCase("{cite"))
	    			 {
	    					 boolean flag_cite=true;
	    					 int location_para;
	    					 i=i+6;
	    					 location_para=2;
	    					 while(flag_cite)
	    					 {
	    						 if(i<length)
	    							 break;
	    						 char current;
	    						 current=all_data.charAt(i);
	    						 if(current=='{')
	    							 ++location_para;
	    						 else if(current=='}')
	    							 --location_para;
	    						 if(i>=length||location_para==0)
	    	    					{
	    							 	++i;
	    	    						flag_cite=false;
	    	    						break;
	    	    						
	    	    					}
	    	    					++i;
	    					 }
	    					 
	    			 }
	    				 else if(i+4<length&&all_data.substring(i+1, i+4).equalsIgnoreCase("{gr"))
	    				 {
	    					 i=4+i;
	    					 int location_par;
	    					 location_par=2;
	    					 boolean flag_gr;
	    					 flag_gr=true;
	    					 while(flag_gr)
	    					 {
	    						 if(i<length)
	    							 break;
		    					char current;
		    					current = all_data.charAt(i);
		    					if(current=='{')
		    						++location_par;
		    					else if(current=='}')
		    						--location_par;
		    					if(i>length+1||location_par==0)
		    					{
		    						++i;
		    						flag_gr=false;
		    						break;
		    					}
		    					++i;
	    					 }	    					 
	    				 }
	    				 else if(i+7<length&&all_data.substring(i+1, i+7).equalsIgnoreCase("{coord"))
	    				 {
	    				     i=7+i;
	    				     int location_par;
	    				     boolean flag_cord;
	    					 flag_cord=true;
	    					location_par=2;
	    					 
	    					 while(flag_cord==true&&i<length)
	    					 {
	    						 char current;
	    						 current = all_data.charAt(i);
	    						 if(current=='{')
	    						  ++location_par;
	    						 if(current=='}')
	    						 --location_par;
	    						 if(i>length+1||location_par==0)
	    						 {
	    							 ++i;
	    							 flag_cord=false;
	    							 break;
	    						 }
	    						 ++i;
	    					 }
	    					 
	    				 }
	    		 
	    	 }
	    	 else if(all_data.charAt(i)=='[')
	    	 {
	    		 if(i+11<length&&all_data.substring(i+1, i+11).equalsIgnoreCase("[Category:"))
	    		 {
	    			 
	    				
	    				 i=11+i;
	    				 boolean is_category=true;
	    				 int location_para=2;
	    				 while(is_category==true)
	     				{	char current_char;
	     					current_char=all_data.charAt(i);
	     					if(current_char==']')
	     						--location_para;
	    					else if(current_char=='[')
	    						++location_para;
	    					else
	    					{
	    						if((current_char >64&&current_char<91)||(current_char>96&&current_char<123))
	        					{
	    							categ_data.append(all_data.charAt(i));
	    							
	        					}
	    						else
	    						{
	    							
	        							help_parsing.handle_category(categ_data.toString());
	        							if(categ_data.length()>0)
	        							categ_data.setLength(0);
	    						}
	    						
	    					}
	     					if(location_para==0||i>=length)
    						{
    							is_category=false;
    							help_parsing.handle_category(categ_data.toString());
    							categ_data.setLength(0);
    						}
	     					++i;
	     				}
	    				 
	    			
	    		 }
	    		//We have to ignore all the data inside 
	    		 else if(i+8<length&&all_data.substring(i+1, i+8).equalsIgnoreCase("[image:"))
	    			{
	    			 i=8+i;
	    			 int location_par;
	    			 boolean flag_imagear;
	    			 location_par=2;
	    			 flag_imagear=true;
	    			 while(i<length&&flag_imagear==true)
	    				{
	    				 char current_char; 
	    				 current_char= all_data.charAt(i);
	    				 if(current_char=='[')
	    						++location_par;
	    					else if(current_char==']')
	    						--location_par;
	    				 if(location_par==0||i>=length)
	    					{
	    					 	++i;
	    						flag_imagear=false;
	    						break;
	    						
	    					}
	    				 ++i;

	    				 
	    				}
	    			 
	    			}
	    		 //We have to ignore all the data inside
	    		 
	    		 else if(i+7<length&&all_data.substring(i+1, i+7).equalsIgnoreCase("[file:"))
	    		 {
	    			 i=7+i;
	    			 int location_par;
	    			 boolean flag_FILE;
	    			 location_par=2;
	    			 flag_FILE=true;
	    			 while(i<length&&flag_FILE==true)
	    				{
	    				 char current_char; 
	    				 current_char= all_data.charAt(i);
	    				 if(current_char=='[')
	    						++location_par;
	    					else if(current_char==']')
	    						--location_par;
	    				 if(location_par==0||i>=length)
	    					{
	    					 	++i;
	    						flag_FILE=false;
	    						break;
	    						
	    					}
	    				 ++i;
	    				 
	    				}
	    		 }
	    			}
	    	 else if(all_data.charAt(i)=='<')
	    	 {
	    		 String s="!--";
	    		
	    		 if(i+4<length&&all_data.substring(i+1,i+4).equals(s))
	    		 {
	    			 int close_tag,x;
	    			 i=4+i;
	    			 close_tag=all_data.indexOf("-->",i);
	    			 x=close_tag+2;
	    			 if(close_tag>0&&length>x)
    					 i=close_tag+2;
	    		 } 
	    		 else
     			{
 					while(all_data.charAt(i)!=' '&&i<length)
 					{
 						
 						++i;
 					}
     			}
	    		
	    	 }
	    	 else{
	    		 //main body parsing all text 
	    		 char current_char;
	    		 current_char= all_data.charAt(i);
	    		 if (current_char=='#')
	    		 {
	    			 while(current_char!=' '&&i<length)
	    			 {
	    				 current_char=all_data.charAt(i);
	    				 ++i;
	    			 }
	    			 
	    		 }
	    		 else
		    		{
	    			 if((current_char>=65&&current_char<=90))
	    			 {
	    				 body_data.append(current_char);
	    			 }
	    			 else if(current_char>96&&current_char<123)
	    			 {
	    				 body_data.append(current_char);
	    			 }
	    			 else
	    			 {
	    				 help_parsing.body_handler(body_data.toString());
	    				 if (body_data.length()>0) {
	    				 	body_data.setLength(0);
	    				 }
	    				 
	    			 }
	    			 
		    		}
	    		 
	    	 		}
	    	 }
	    	 
	     }catch(Exception e){}
	}
@Override
 public void characters(char ch[], 
    int start, int length) throws SAXException {
    if (flag_title) {
    	title_all =new String(ch, start, length);
    }
    else if(text)
    {
    	//Start Reading..!!
    	 all_data.append(ch, start, length);
    	
    }
  //Getting the ID of document
    else if(doc_id==true)
    {
    	btitle.setLength(0);
    	//curid contains current document id <id></id> after title 
    	curid = new String(ch,start,length);
    //om-req
    	int i,len_title;
    	len_title=title_all.length();
    	for(i=0;i<len_title;++i)
    	{
    		
    		if((title_all.charAt(i)>=48&&title_all.charAt(i)<=57)||(title_all.charAt(i)>=65&&title_all.charAt(i)<=90)||(title_all.charAt(i)>=97&&title_all.charAt(i)<=122))
    		{
    			btitle.append(title_all.charAt(i));
    		}
    		else
    		{
    			help_parsing.handle_title(btitle.toString());
    			btitle.setLength(0);
    			
    		}
    	}
    	doc_id=false;
    }
    
 }

}


