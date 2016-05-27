import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class title_id_processor {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long char_count=0;
		long i=0;
		long char_sec=0;
		FileReader file_title= new FileReader("Title/title_id");
		BufferedReader br = new BufferedReader(file_title);
		String next = br.readLine();
		File file = new File("Title/Index_Title_Mapping");
		File file1 = new File("Title/Index_Ter");
		if (!file.exists()) {
			file.createNewFile();
		}
		if (!file1.exists()) {
			file1.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter bw1 = new BufferedWriter(fw1);
		 while(next!=null)
	        {
			 	if(next.indexOf("=")>0)
			 	{
			 		 String doc_id = next.substring(0,next.indexOf("="));
			 		char_count=char_count+next.getBytes().length+1;
				 	bw.write(doc_id+"="+char_count+"\n");
				 	char_sec=char_sec+doc_id.getBytes().length+(""+char_count).getBytes().length+2;
				 	if(i%100==0)
		                bw1.write(doc_id+"="+char_sec+"\n");
				 	++i;
				 	
			 	}
			 	else
			 		{next = br.readLine();}
			 	
			 	next = br.readLine();
	        }
		 bw.close();
		 br.close();
		 bw1.close();
	}

}
