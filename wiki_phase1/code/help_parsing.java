import java.util.HashMap;


public class help_parsing {
	public static String stop_words[]= {"coord","gr","com","tr","td","nbsp","http","https","www","a","about","above","across","after","again","against","all","almost","alone","along","already","also","although","always","among","an","and","another","any","anybody","anyone","anything","anywhere","are","area","areas","around","as","ask","asked","asking","asks","at","away","b","back","backed","backing","backs","be","became","because","become","becomes","been","before","began","behind","being","beings","best","better","between","big","both","but","by","c","came","can","cannot","case","cases","certain","certainly","clear","clearly","come","could","d","did","differ","different","differently","do","does","done","down","down","downed","downing","downs","during","e","each","early","either","end","ended","ending","ends","enough","even","evenly","ever","every","everybody","everyone","everything","everywhere","f","face","faces","fact","facts","far","felt","few","find","finds","first","for","four","from","full","fully","further","furthered","furthering","furthers","g","gave","general","generally","get","gets","give","given","gives","go","going","good","goods","got","great","greater","greatest","group","grouped","grouping","groups","h","had","has","have","having","he","her","here","herself","high","high","high","higher","highest","him","himself","his","how","however","i","if","important","in","interest","interested","interesting","interests","into","is","it","its","itself","j","just","k","keep","keeps","kind","knew","know","known","knows","l","large","largely","last","later","latest","least","less","let","lets","like","likely","long","longer","longest","m","made","make","making","man","many","may","me","member","members","men","might","more","most","mostly","mr","mrs","much","must","my","myself","n","necessary","need","needed","needing","needs","never","new","new","newer","newest","next","no","nobody","non","noone","not","nothing","now","nowhere","number","numbers","o","of","off","often","old","older","oldest","on","once","one","only","open","opened","opening","opens","or","order","ordered","ordering","orders","other","others","our","out","over","p","part","parted","parting","parts","per","perhaps","place","places","point","pointed","pointing","points","possible","present","presented","presenting","presents","problem","problems","put","puts","q","quite","r","rather","really","right","right","room","rooms","s","said","same","saw","say","says","second","seconds","see","seem","seemed","seeming","seems","sees","several","shall","she","should","show","showed","showing","shows","side","sides","since","small","smaller","smallest","so","some","somebody","someone","something","somewhere","state","states","still","still","such","sure","t","take","taken","than","that","the","their","them","then","there","therefore","these","they","thing","things","think","thinks","this","those","though","thought","thoughts","three","through","thus","to","today","together","too","took","toward","turn","turned","turning","turns","two","u","under","until","up","upon","us","use","used","uses","v","very","w","want","wanted","wanting","wants","was","way","ways","we","well","wells","went","were","what","when","where","whether","which","while","who","whole","whose","why","will","with","within","without","work","worked","working","works","would","x","y","year","years","yet","you","young","younger","youngest","your","yours","z"};

	public static void handle_title(String str_title)
	{
		try{
		String str;
		str=str_title.trim();
		str=str.toLowerCase();
		int len_str_title=str.length();
		if(len_str_title==0)
			return;
		else if (str.isEmpty())
			return;
		//stemmer for title
		Stemmer stemmer_title = new Stemmer();
		stemmer_title.add(str.toCharArray(),len_str_title);
		str=stemmer_title.stem();
		//System.out.println(str);
		if(Handller_pars.database.get(str)!=null)
     	{
     		len_str_title=str.length();
     	}
     	else{
			HashMap<String,listing> loc_map=new HashMap<String,listing>();
			Handller_pars.database.put(str, loc_map);
     	}
		if(!Handller_pars.database.get(str).containsKey(Handller_pars.curid))
		{
			Handller_pars.database.get(str).put(Handller_pars.curid, new listing());
		}
		else if(Handller_pars.database.get(str)==null) 
		{
			Handller_pars.database.get(str).put(Handller_pars.curid, new listing());			
		}
     	++Handller_pars.database.get(str).get(Handller_pars.curid).title;
     	
		
		}catch(Exception e){}
	}
	public static void infobox_handle(String string) {
		try{String str;
		str=string.trim();
		str=str.toLowerCase();
		int len_str_title=str.length();
		if(len_str_title<=2)
		{
			return;
		}
		if(str.isEmpty())
			return;
		if(Driver.stop_words_hash.contains(str)==false)
		{
			Stemmer s = new Stemmer();
	    	s.add(str.toCharArray(),len_str_title);
	    	str=s.stem();
	    	len_str_title=str.length();
	    	if(str.isEmpty()||len_str_title<3)
	    	{
	    		return;
	    	}
		if(Handller_pars.database.get(str)!=null)
     	{
     		len_str_title=str.length();
     	}
     	else
     	{
     		HashMap<String,listing> loc_map=new HashMap<String,listing>();
			Handller_pars.database.put(str, loc_map);
     	}
		if(!Handller_pars.database.get(str).containsKey(Handller_pars.curid))
		{
			Handller_pars.database.get(str).put(Handller_pars.curid, new listing());			
		}
     	++Handller_pars.database.get(str).get(Handller_pars.curid).infobox;
		}
	}catch(Exception e){}	
		
		
	}
	public static void handle_category(String string) {
		try{
			String str;
			str=string.toLowerCase();
			str.trim();
			int len_string=str.length();
			if (len_string<3)
				return;
			if(Driver.stop_words_hash.contains(str)==false) 
			{
				Stemmer s = new Stemmer();
				s.add(str.toCharArray(),str.length());
				str=s.stem();
				len_string=str.length();
				//skip
				if(len_string<3)
					return;
				if(Handller_pars.database.get(str)!=null)
				{
						//check len
			       		len_string=str.length();

			    }
			    else{
			    HashMap<String,listing> loc_map=new HashMap<String,listing>();
			       		Handller_pars.database.put(str, loc_map);
			       }	
				if (!Handller_pars.database.get(str).containsKey(Handller_pars.curid))
				{
					Handller_pars.database.get(str).put(Handller_pars.curid, new listing());					
				}
				else if(Handller_pars.database.get(str)==null)
		       	{
					Handller_pars.database.get(str).put(Handller_pars.curid, new listing());
		       	}
				++Handller_pars.database.get(str).get(Handller_pars.curid).cat;
				
			}
			
		}catch (Exception e)
		{
			
		}
	}
	public static void body_handler(String string) throws Exception{
		
		try{
			String str;
			str=string.toLowerCase();
			str.trim();
			int len_string=str.length();
			if(len_string<=2)
			{
				return;
			}
			if(str.isEmpty())
				return;
			if(Driver.stop_words_hash.contains(str)==false) //no Stop Word
			{
			Stemmer s = new Stemmer();
    		s.add(str.toCharArray(),len_string);
    		str=s.stem();
    		len_string=str.length();
    		if (len_string<3)
    			return;
    		if(Handller_pars.database.get(str)!=null)
			{
				len_string=str.length();

			}
			else{
    			HashMap<String,listing> loc_map=new HashMap<String,listing>();
	        	Handller_pars.database.put(str, loc_map);
			}
			if(!Handller_pars.database.get(str).containsKey(Handller_pars.curid))
			{
				Handller_pars.database.get(str).put(Handller_pars.curid, new listing());
			}
    		else if(Handller_pars.database.get(str)==null)
	        {
    			Handller_pars.database.get(str).put(Handller_pars.curid, new listing());
	        }
	         	
    		++Handller_pars.database.get(str).get(Handller_pars.curid).body;
			
    		
			}
			
			
		}catch (Exception e)
		{
			
		}
	}
	}

