import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Map.*;



public class Query_processor {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
	Set<String> stop_words_hash = new HashSet<>();
        for(int i=0;i<435;++i)
  	{
  		stop_words_hash.add(help_parsing.stop_words[i]);
  		
  	}
      Comparator <word> c = new Comparator<word>(){
			public int compare(word u1, word u2) 
		      {
				int result;
				result=u1.term.compareTo(u2.term);
		        return result;
		      }
		};
    String path_index;
		path_index= "Index/count_doc_list_Db"; 
		 //DS to hold ter index
		List<word> ter_index = new ArrayList<word>();  
	    System.out.println("enter the number of quarries you want to process:"); 
		BufferedReader br_inp = new BufferedReader(new InputStreamReader(System.in));
		int no_of_queries;
		no_of_queries= Integer.parseInt(br_inp.readLine());
		//read ter index in main mem
		BufferedReader br_ter_index = new BufferedReader(new FileReader("Index/Ter_index_db"));
		Map<String,Integer> master_data = new HashMap<>();
		String next;
		next= br_ter_index.readLine();
		while(next!=null)
		{
			long from_index;
			String term;
			int term_index =next.indexOf(":");
			
			term = next.substring(0,term_index);
			from_index=Long.parseLong(next.substring(term_index+1));
			word w = new word(term,from_index);
			ter_index.add(w);	
			next=br_ter_index.readLine();
		}
		
		Reader file_ter_index=new FileReader("Title/Index_Ter");
		br_ter_index.close();
		List<title_map_docid_list> title_index = new ArrayList<title_map_docid_list>();
		
		Comparator<title_map_docid_list> c1 = new Comparator<title_map_docid_list>() 
        {
            public int compare(title_map_docid_list u1, title_map_docid_list u2)
            {
            	int result;
            	result=(int)(u1.doc_id-u2.doc_id);
                return result;
            }
        };
		BufferedReader br_title = new BufferedReader(file_ter_index);
		String read_title;
		read_title= br_title.readLine();
		while(read_title!=null)
		{
			String doc_id;
			long offset;
			int index_of_eq=read_title.indexOf("=");
			doc_id = read_title.substring(0,index_of_eq);
			offset = Long.parseLong(read_title.substring(index_of_eq+1));
			long parsing_long_doc_id=Long.parseLong(doc_id);
			title_index.add(new title_map_docid_list(parsing_long_doc_id,offset));
			read_title = br_title.readLine();
		}
		br_title.close();
		for (int i = 0; i < no_of_queries; ++i) 
		{
			System.out.println("enter your query:");
			String read = br_inp.readLine();
			Pattern pt = Pattern.compile("[^a-zA-Z0-9 :]");
	        Matcher match= pt.matcher(read);
	        while(match.find())
	        {
	            String s= match.group();
	        read=read.replaceAll("\\"+s, "");
	        }
	        StringTokenizer st = new StringTokenizer(read," ");
	        long start;
	        start=System.currentTimeMillis();
	        boolean t_prev=false,b_prev=false,r_prev=false,e_prev=false,c_prev=false,i_prev=false;
	        while(st.hasMoreElements())
	        {
	        	int lbcount=2,ltcount=10000;
	        	
	        	int lecount=1,lccount=30;
	        	String str;
	        	int licount=25;
	        	str=st.nextToken().toLowerCase();
	        	if(str.contains("t:")||t_prev==true)
	            {
	        		//System.out.println("im am in");
	        		t_prev=true;
	        		//ltcount=ltcount*1000;
	        		if(str.indexOf(":")>0)
	        		{
	        		int index_of_col=str.indexOf(":");
	        		index_of_col++;
	               str=str.substring(index_of_col);
	        		}
	               b_prev=false;r_prev=false;e_prev=false;c_prev=false;i_prev=false;
	            }
	        	if(str.contains("e:")||e_prev==true)
	            {
	                e_prev=true;
	                lecount*=1000;
	                if(str.indexOf(":")>0)
	                {
	                	str=str.substring(str.indexOf(":")+1);
	                }
	               b_prev=false;r_prev=false;t_prev=false;c_prev=false;i_prev=false;
	            }
	        	if(str.contains("b:")||str.contains("r:")||b_prev==true||r_prev==true)
	        	{
	        		b_prev=true;
	        		lbcount*=1000;
	        		r_prev=true;
	        		if(str.indexOf(":")>0)
	                {
	        		str=str.substring(str.indexOf(":")+1);
	                }
	        		t_prev=false;c_prev=false;i_prev=false;e_prev=false;
	        	}
	        	if(str.contains("c:")||c_prev==true)
	            {
	        		c_prev=true;
	                lccount*=1000;
	                if(str.indexOf(":")>0)
	                str=str.substring(str.indexOf(":")+1);
	                b_prev=false;r_prev=false;t_prev=false;e_prev=false;i_prev=false;
	            }
	        	if(str.contains("i:")||i_prev==true)
	            {
	        		i_prev=true;
	                licount*=1000;
	                if(str.indexOf(":")>0)
	                str=str.substring(str.indexOf(":")+1);
	                b_prev=false;r_prev=false;t_prev=false;e_prev=false;c_prev=false;
	            }
	        	str=str.trim();
	        	int index_of_word;
	        	long start_index=0;
	        	Stemmer s = new Stemmer();
	        	int length_str=str.length();
	        	s.add(str.toCharArray(),length_str);
	        	str=s.stem();
	        	index_of_word=Collections.binarySearch(ter_index, new word(str, 0), c);
	        	if(index_of_word>3)
	                start_index= ter_index.get(index_of_word-3).offset;
	        	else if(index_of_word>=0&&index_of_word<=3)
	        		start_index=0;
	        	else
	        	{
	        		index_of_word=index_of_word*(-1);
	        		if(index_of_word<=2)
	        			start_index=0;
	        		else if(index_of_word>2)
	        			start_index = ter_index.get(index_of_word-2).offset;
	        			
	        	}
	        RandomAccessFile file = new RandomAccessFile("Index/second_index_db", "r");
	        file.seek(start_index);
	        long database_index=-1;
	        for (int j = 0; j <=124; ++j) 
	        {
				
	        	String next_record = file.readLine();
	        	if(next_record.indexOf(":")>=0)
	        	{
	        		String term_next = next_record.substring(0,next_record.indexOf(":"));
	        		if(str.equals(term_next))
	                {
	        			int index_of_colon=next_record.indexOf(":")+1;
	                    database_index=Long.parseLong(next_record.substring(index_of_colon));
	                }
	        	}
	        }
	        file.close();
	        	if(database_index!=-1)
	        	{
	        		RandomAccessFile file1 = new RandomAccessFile(path_index, "r");
	        		StringBuilder sb = new StringBuilder();
	        		file1.seek(database_index);
	        		for(int k=0;k<35000;++k)
	                {
	                    char ch;
	                    ch= (char) file1.readByte();
	                    if(ch!='\n')
	                    	sb.append(ch);
	                    else
	                        break;
	                    
	                }
	        		file1.close();
	        		String next_record = sb.toString();
	        		int no_of_rec = 1;
	        		String word_name;
	        		if(next_record.indexOf("-")>0)
	        		{	
	        		word_name = next_record.substring(0,next_record.indexOf("-"));
	        		next_record = next_record.substring(next_record.indexOf("-")+1);
	        		if(next_record.indexOf("?")>0)
					{
	        			 no_of_rec = Integer.parseInt(next_record.substring(0,next_record.indexOf("?")));	
	        			next_record = next_record.substring(next_record.indexOf("?")+1);
					}
	        		if(word_name.length()<=0)
	        			continue;
					}
	        		
                    StringTokenizer st1 = new StringTokenizer(next_record,";");
                    int local_counter=0;
                    while(st1.hasMoreElements())
                    {
                    	++local_counter;
                    	String next_doc = st1.nextToken();
                        if(local_counter>45000)
                            break;
                        if(!st1.hasMoreElements()&&!next_record.endsWith(";"))
                        {
                            //IF this is the last token, then dont perform the operations
                            break;
                        }
                        if(next_doc.indexOf(":")==-1)
                        	break;
                        String doc_id  = next_doc.substring(0, next_doc.indexOf(":"));
                        next_doc = next_doc.substring(next_doc.indexOf(":")+1);
                        int body=0,title=0,info=0,cat=0,ext=0;
    				 	if(next_doc.indexOf(",")==-1)
    				 	{
    				 		
    				 	}
    				 	int index_of;
    				 	 String value1;
    				 	 index_of=next_doc.indexOf(",");
    				 	 value1=next_doc.substring(0,index_of);
    				 	 index_of++;
    				 	 next_doc = next_doc.substring(index_of);
    				 	 index_of=next_doc.indexOf(",");
    				 	 String value2=next_doc.substring(0,index_of);
    				 	 index_of++;
    				 	 next_doc = next_doc.substring(index_of);
    				 	index_of=next_doc.indexOf(",");
    				 	 String value3=next_doc.substring(0,index_of);
    				 	 index_of++;
    				 	 next_doc = next_doc.substring(index_of);
    				 	index_of=next_doc.indexOf(",");
    				 	String value4=next_doc.substring(0,index_of);
    				 	++index_of;
    				 	next_doc = next_doc.substring(index_of);
    				 	 String value5 = next_doc;
    				 	if(value1.isEmpty()==false)
    	                {
    	                    title=Integer.parseInt(value1);
    	                }
    				 	if(value2.isEmpty()==false)
    				 	{
    				 		cat=Integer.parseInt(value2);
    				 		
    				 	}
    				 	if(value3.isEmpty()==false)
    				 	{
    				 		info=Integer.parseInt(value3);
    				 		
    				 	}
    				 	if(value4.isEmpty()==false)
    				 	{
    				 		body=Integer.parseInt(value4);
    				 		
    				 	}
    				 	if(value5.isEmpty()==false)
    				 	{
    				 		ext=Integer.parseInt(value5);
    				 		
    				 	}
    				 	
                       	int total;
    				 	total =(body*lbcount) +(title*ltcount)+(ext*lecount)+(cat*lccount)+(info*licount);
    				 	double log_data = (double)15000000/no_of_rec;
    				 	int log_val;
    				 	log_val = (int)Math.log(log_data);
    				 	 if(master_data.containsKey(doc_id))
                        {  int cur;
                        	cur= master_data.get(doc_id);
                            master_data.remove(doc_id);
                            total=total+cur*15;                          
                        }
                        int to_int_total=(int)total;
    				 	total = to_int_total *log_val; 
                        master_data.put(doc_id,total);
    				 	
    				 	
    				 	
                    }
                    
	        	}
			
	        
	        
	        
	        }//while has more end
	        
	        int count;
	        Set<Entry<String, Integer>> set = master_data.entrySet();
	        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
	       	count=0;
	        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
	        {
	        	@SuppressWarnings("unused")
				int work;
	            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
	            {
	            	int result;
	            	result=(o2.getValue()).compareTo( o1.getValue() );
	                return result;
	            }
	        });
	      //  System.out.println("Size of list: "+list.size());
	        for(Map.Entry<String, Integer> entry:list)
	        {
	        	String out_title="";
	        	 int index_of_word;
	            long doc_id =Long.parseLong(entry.getKey());
	            index_of_word = Collections.binarySearch(title_index, new title_map_docid_list(doc_id, 0), c1);
	            long start_index = 0;
	            RandomAccessFile file2 = new RandomAccessFile("Title/Index_Title_Mapping", "r");
	            if(index_of_word>=2)
	            {
	            	int temp =index_of_word-2;
	            	start_index=title_index.get(temp).offset;
	            
	            }
	            else if(index_of_word<0)
	            {
	                index_of_word=index_of_word*-1;
	                if(index_of_word<=2)
	                	start_index=0;
	                else
	                {
	                	int temp =index_of_word-2;
	                    start_index = title_index.get(temp).offset;
	                }
	            }
	           file2.seek(start_index);
	           
	           long lout_title=0;
       		   long last_title=0;
	           String reading="";
	           for(int i1=0;i1<102;++i1)
	           {
	                 reading = file2.readLine();
	                 int index_eq;
	                // System.out.println("353Read this:  "+reading);
	                 if(reading.indexOf("=")==-1)
	                	 continue;
	                 else
	                	 index_eq=reading.indexOf("=");
	                 long l_docid = Long.parseLong(reading.substring(0,index_eq));
	                  lout_title=Long.parseLong(reading.substring(index_eq+1));
	                 if(l_docid!=doc_id)
	                 {
	                    // System.out.print("\tEqual");
	                    
	                	 last_title=lout_title;
	                 }
	                 else
	                	 break;
	                 
	                 
	           }
	            
	           file2.close();
	            //Now read title from main title mapping file
	              RandomAccessFile file3 = new RandomAccessFile("Title/title_id", "r");
	            file3.seek(last_title);
	            reading=file3.readLine();
	              //System.out.println("Read this:  "+reading);
	            if(reading.indexOf("=")==-1)
               	 		continue;
	           out_title=reading.substring(reading.indexOf("=")+1);
	           int index_eq_=reading.indexOf("=");
	            String doc_id_result= reading.substring(0,index_eq_);
	            
	           master_data.clear(); 
	            System.out.println(out_title+"\n doc id is:-"+doc_id_result);
	            //System.out.println(reading);
	            ++count;
	            file3.close();
	            if(count==10)
	                break;
	        }
	        
	        
		        long  end = System.currentTimeMillis();
	       System.out.println("Time taken in Result Generation: "+(end - start) + "milisec");
	     
	        
	        
	        
		}
        
	}

}
