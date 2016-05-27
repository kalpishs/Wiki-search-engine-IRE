import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class merge_index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
			int isnull=0,val=2329,j,val_add,rec_read=0,rec_write=0,last;
			System.out.println("merge funcitonInitalized");
			val_add=val+2;
			String read;
			long charcount=0;
			Comparator<term> compares = new Comp();
			BufferedReader br[] = new BufferedReader[val+2];
			for(j=1;j<=val;++j)
			{
				FileReader file_rd=new FileReader("crap/db"+j);
				br[j] = new BufferedReader(file_rd);
			}
	        //Initialize variables, boolean array to mark completeness of a text file
			boolean[] array_mark = new boolean[val_add];
			 for(int k=1;k<val_add;++k)
				{
				 array_mark[k]=true;
				 }
			File out_file_index = new File("Index/Final_Index_db");
			File out_file_secondry = new File("Index/Second_Index");
			 if (out_file_index.exists()==false) 
		        {
		           out_file_index.createNewFile();
		        }
			 if (out_file_secondry.exists()==false) 
		        {
		           out_file_secondry.createNewFile();
		        }
			 FileWriter fw_index = new FileWriter(out_file_index.getAbsoluteFile());
			 FileWriter fw_sec_index = new FileWriter(out_file_secondry.getAbsoluteFile());
			 BufferedWriter bw_index = new BufferedWriter(fw_index);
			 BufferedWriter bw_sec_index = new BufferedWriter(fw_sec_index);
			 System.out.println("Starting to read");
			 PriorityQueue<term> my_data =  new PriorityQueue<term>(val_add,compares); 
			
			 for(j=1;j<=val;++j)
		     {
				 if(array_mark[j]==true)
				 {
					 read = br[j].readLine();
					 if(read!=null)
					 {
						 term t = new term();
						 String word;
						 int index_of;
						 index_of=read.indexOf('-');
						 word = read.substring(0,index_of);
						 t.word=word;
						 index_of++;
						 read=read.substring(index_of);
						 t.list=read;
						 t.doc_no=j;
						 my_data.add(t);
						 ++rec_read;
					 }
					 else
					 {
						 ++isnull;
						 array_mark[j]=false;
						 System.out.print("Completed File"+j);
						 System.out.print("at: "+rec_read+"\n");
						 br[j].close();
					 }
					 
				 }		 
				 
		     }
			 String lout_list=null;
			 term top = my_data.poll();
			 String lout_word=null,Out_posting_list,Out_word;
			 last=top.doc_no;
			 Out_posting_list=top.list;
			 int nonecount=0;
			 Out_word=top.word;
			 while(val > isnull)
		        {
				 if(nonecount>0)
					 nonecount=0;
				 
				 if(array_mark[last]==false)
		            {
					 term ltop;
					 ltop = my_data.poll();
					 lout_word=ltop.word;lout_list=ltop.list;
					 nonecount++;
					 last=ltop.doc_no;
		            }
				 else
		            {nonecount--;
					 read = br[last].readLine();
		            
					 	if(read!=null)
					 	{
					 		
					 		term lt = new term();
					 		lt.doc_no=last;
					 		String word;
					 		int index_of;
					 		index_of=read.indexOf('-');
					 		word = read.substring(0,index_of);
					 		lt.word=word;
					 		index_of++;
					 		read=read.substring(index_of);
					 		lt.list=read;
					 		my_data.add(lt);
			                rec_read++;
			                term ltop1;
			                ltop1=my_data.poll();
			                lout_list=ltop1.list;
		                    lout_word=ltop1.word;
		                    last=ltop1.doc_no;
					 	}
					 	else{
					 		if(array_mark[last]==true)
					 			++isnull;
					 		array_mark[last]=false;
					 		System.out.println("Completed File"+last);
					 		System.out.print("at"+rec_read+"\n");
					 		br[last].close();
					 	}
		            }
				 if(Out_word!=null&&lout_word.equals(Out_word)==true)
				 {
					 Out_posting_list=Out_posting_list+lout_list;
				 }
				 else{
					 if(rec_write%100==0)
                     {                       
                         bw_sec_index.write(Out_word+charcount+"\n");
                     } 
					 bw_index.write(Out_word+"-"+Out_posting_list+"\n");
					 int length_out_word=Out_word.length();
					 int legth_posting_list=Out_posting_list.length();
					 charcount=2+charcount+length_out_word+legth_posting_list;
					 
				               
                     Out_word=lout_word;
                     Out_posting_list=lout_list;
                     lout_word=lout_list="";
                     ++rec_write;

             }
                
        }
					 
			bw_index.close();
			bw_sec_index.close();
			System.out.println("No of records merged: "+rec_read);
			System.out.print(" Records Written into merged file: "+rec_write+"\n");	 	
		        
        }
		catch(Exception e)
		{
			System.out.println("Exception in merge_funciotn");
			System.out.println(e.getMessage());
		}
	}

}
