import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.StringTokenizer;

public class post_index_processing {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader file_db=new FileReader("Index/Final_Index_db");
		BufferedReader br_pre_index = new BufferedReader(file_db);
		File file_total_list = new File("Index/count_doc_list_Db");
		File file_sec_index=new File("Index/second_index_db");
		File file_ter_index =new File("Index/Ter_index_db");
		if (file_total_list.exists()==false) {
			file_total_list.createNewFile();
		}
		if(file_sec_index.exists()==false)
		{
			file_sec_index.createNewFile();
			
		}
		if(file_ter_index.exists()==false)
		{
			file_ter_index.createNewFile();
			
		}
		FileWriter fw_for_total = new FileWriter(file_total_list.getAbsoluteFile());
		BufferedWriter bw_for_total = new BufferedWriter(fw_for_total);
		FileWriter fw_for_sec_indx = new FileWriter(file_sec_index.getAbsoluteFile());
		BufferedWriter bw_for_sec_indx = new BufferedWriter(fw_for_sec_indx);
		FileWriter fw_for_ter_indx = new FileWriter(file_ter_index.getAbsoluteFile());
		BufferedWriter bw_for_ter_indx = new BufferedWriter(fw_for_ter_indx);
		int b_weight_count=2,t_weight_count=1000;
		long char_final=0, char_sec=0;
		String next_record = br_pre_index.readLine();
		int e_weight_count=1,c_weight_count=20;
		int i_weight_count=25;
		int count_lines=0;
		while(next_record!=null)
        {
			List<store> data = new ArrayList<>();
			String word_name;
			int count;
			if(next_record.indexOf("-")==-1)
			{
				next_record = br_pre_index.readLine();
				continue;
			}
			word_name=next_record.substring(0,next_record.indexOf("-"));
			
			//System.out.println("word is"+word_name);
			count=0;
			if(word_name.isEmpty()==true)
	          {	
				 next_record = br_pre_index.readLine();
				 continue;
			  }
			int temp_index=next_record.indexOf("-")+1;
			 next_record = next_record.substring(temp_index);
			 StringTokenizer st1 = new StringTokenizer(next_record,";");
			 while(st1.hasMoreElements())
			 {
				 	String next_doc = st1.nextToken();
				 	++count;
				 	store si = new store();
				 	si.list=next_doc;
				 	@SuppressWarnings("unused")
					String doc_id;
				 	int index_of=next_doc.indexOf(":");
				 	doc_id  = next_doc.substring(0, index_of);
				 	next_doc = next_doc.substring(next_doc.indexOf(":")+1);
				 	
				 	int body=0,title=0,info=0,cat=0,ext=0;
				 	if(next_doc.indexOf(",")==-1)
				 	{
				 		
				 	}
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
				 	
				 	
				 	//if(next_doc.indexOf("t:")>=0)
				 		//if(next_doc.indexOf("t:"))
				 	/*String s = next_doc;//t:1234b:3456
				 	//System.out.println("s "+s);
				 	Map<String, Integer> mp = new HashMap<String, Integer>();
				 	int prev = -1;
				 	String key = "";
				 	boolean flag =false;
				 	Set<String> ss = new HashSet<String>();
				 	ss.add("0");
				 	ss.add("1");
				 	ss.add("2");
				 	ss.add("3");
				 	ss.add("4");
				 	ss.add("5");
				 	ss.add("6");
				 	ss.add("7");
				 	ss.add("8");
				 	ss.add("9");
				 	//System.out.println(s.length());
				 	for(int i = 0; i < s.length(); i++)
				 	{
				 		//System.out.println(s.charAt(i));
				 		if(s.charAt(i) == 't' || s.charAt(i) == 'b' ||s.charAt(i) == 'c' ||s.charAt(i) == 'i' || s.charAt(i) == 'r'){
				 			if(flag){
				 				int x = Integer.parseInt(s.substring(prev,i));
				 				mp.put(key, x);
					// 			System.out.println("INserted :" +key+" "+x);					 			
					 			flag = false;
				 			}
				 			key = s.charAt(i)+"";
				 		}
				 		else if( ss.contains(s.charAt(i)+"")){
				 			//System.out.println("prev set");
				 			if(!flag){
				 				//System.out.println("prev set : "+i);
				 				prev = i;
				 				flag = true;
				 			}else{
				 				//do nothing
				 			}
				 			
				 		}
				 		if( i==s.length()-1){
				 			int x = Integer.parseInt(s.substring(prev,i+1));
				 			flag = false;
				 			mp.put(key, x);
				 		//	System.out.println("INserted :" +key+" "+x);
				 		}
				 	}
				 	
				 	if(mp.containsKey("t"))
				 	{
				 		title=mp.get("t");
				 	}
				 	if(mp.containsKey("b"))
				 	{
				 		body=mp.get("b");
				 	}
				 	if(mp.containsKey("i"))
				 	{
				 		info=mp.get("i");
				 	}
				 	if(mp.containsKey("c"))
				 	{
				 		cat=mp.get("c");
				 	}
				 	if(mp.containsKey("r"))
				 		ext=mp.get("r");*/
				 	 int total = (body*b_weight_count);
				 	 total=total+(title*t_weight_count);
				 	 total=total+(ext*e_weight_count);
				 	 total=total+(cat*c_weight_count);
				 	 total+=(info*i_weight_count);
				 	 si.ranking=total;
				 	 data.add(si);
			 }
			 Compare2 cmp2 =new Compare2();
			 Collections.sort(data,cmp2);
			 if(count_lines%100==0||count_lines==0)
	            {
				
				 bw_for_ter_indx.write(word_name);
				 bw_for_ter_indx.write(":"+char_sec);
				  bw_for_ter_indx.write("\n");
	            }
			bw_for_sec_indx.write(word_name);
			bw_for_sec_indx.write(":"+char_final);
			bw_for_sec_indx.write("\n");
			 //int templen=word_name.length();
			int temp_len_word=word_name.length();
			 char_sec+=temp_len_word;
			 char_sec=char_sec+(""+char_final).length()+2;
			
			 bw_for_total.write(word_name);
			 bw_for_total.write("-"+count);
			 bw_for_total.write("?");
			 String temp_count=""+count;
			 char_final = char_final+2+temp_count.length()+temp_len_word;
	      
	            for(int i=0;i<data.size();++i)
	            {
	            	
	            	//System.out.println(data.get(i).list);
	            	
	                bw_for_total.write(data.get(i).list);
	                char_final=char_final+data.get(i).list.length()+1;
	                //System.out.println(";");
	                bw_for_total.write(";");
	            }
	            ++count_lines;
	            ++char_final;
	            //System.out.println("\n");
	            bw_for_total.write("\n");
	            next_record=br_pre_index.readLine();
        }
		bw_for_sec_indx.close();
		System.out.println("Exit");
		bw_for_ter_indx.close();
		bw_for_total.close();
		br_pre_index.close();
	}

}
