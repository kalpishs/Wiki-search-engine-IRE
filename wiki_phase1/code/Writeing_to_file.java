import java.util.HashMap;
import java.util.Map.Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class Writeing_to_file {

	public static void write_db()throws Exception {
		// TODO Auto-generated method stub
		try
    	{
			File file = new File(Driver.args_1);
			if (file.exists()==false) {
				file.createNewFile();
			}
			BufferedWriter buff_write = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			for (Entry<String, HashMap<String, listing>> db_entry : Handller_pars.database.entrySet())
			{
				HashMap<String,listing> maping = new HashMap<String,listing>(db_entry.getValue());
				buff_write.write(db_entry.getKey()+":-");
				for(Entry<String,listing> location_entry:maping.entrySet())
				{
					String title="";
					String doc_id;
					doc_id=location_entry.getKey();
					String cat="";
					String info="";
					listing objct =location_entry.getValue();			
					String body="";
					if(objct.title>0)
	    				title="t:"+objct.title;
					if(objct.cat>0)
						cat="c:"+objct.cat;
					//String append;
					if(objct.infobox>0)
						info="i:"+objct.infobox;
					if(objct.body>0)
						body="b:"+objct.body;
					
					buff_write.write(doc_id+":"+title+cat+info+body+";");
				}
				buff_write.write("\n");
			}
			buff_write.close();
			Handller_pars.database.clear();
    	}catch (Exception e) {
    		
    	}
	}

}
