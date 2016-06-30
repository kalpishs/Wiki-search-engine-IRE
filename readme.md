____________________________________________________________________________________________________________________________________________
Phase-2
Kalpish Singhal
____________________________________________________________________________________________________________________________________________
==============================================================================================================================
	           Indexing mechanism for search egine 
==============================================================================================================================

-> The code basically implement INDEX CREATION MODULE for seach engin 

-> It reads the content of XML File and SAX Parser passes it

-> Character by character it scans the text and extracts words for indexing

-> sorted posting list on term frequency

-> Amended every word with D.F in indexing 

==============================================================================================================================
	           Files LIST With One Line Disciption
==============================================================================================================================

(i)  Driver.java //contains main function takes Xml "wiki dump" as comandline input and calls Handller_pars for handling 

(ii) Handller_pars.java //contains parsing handling mechanism to remove scrap and send words for parsing into map

(iii)help_parsing.java //helps in parsing the content removing stop words stemming it using Stemmer.java

(iv) Stemmer.java //stemm the word to its root form and return it to the calling function 

(iv) Writeing_to_file.java //it writes o/p to the given comaand line input filr if file not exist it will create new file 

(v)  listing.java //Class defination for using ojects to keep count to be maped into the map 

vi) merge_index.java //this merges the complete index files created by writing the file

Vii) post_index_processing.java //This processes the merged index to built sorted index with DF of each term 

viii) Driver_title.java //This is main class to extract title corrsponding to the document id and save to file#this can be merged with 
driver.java but title document number mapping where not initially part of the statement project

ix) Map_title.java//this writes title to the file with wiki_doc_id

X) title_id_processor.java //processes the titel built above to make secondary and tertiary title_index

xi)Query_processor.java //used for proceesing user quries to give the  desired document 


==============================================================================================================================
	            Implemented characteristics
==============================================================================================================================


-> Used tree Hash map for SortedMap order of the keys will be sorted. so sorted according to word A-Z

-> Not storing zeros in index file. helps in reducing 

-> One go parssing :- data is parsed in one go to reduce time 

-> Stop words are removed to make index efficent except from title in the view for seacrch of titles like "to be not to be" type query 

-> infobox is parsed to get relevent data.

->Query time: <=1sec per query Query can be single word (Sachin) or multi-word(Sachin Tendulkar India) or fielded query(t:sachin b:india), 
where t=title,b=body,c=category,e:external links,i:info-box

==============================================================================================================================
	            Output Format
==============================================================================================================================

# index file


=> word-<df>?doc_id:<title count>,<category count>,<infobox count>,<body count>,<external link count>;..;




eg:-
ledzi-1?6256:,,,1,; # represnt ledzi is in document 6256 1 time in body 






#Query processor


a) enter the number of quarries you want to process:<eg:-2>


b) enter your query:<enter user query>


c) Time taken in Result Generation: ...milisec







#Assumption



	i) xml-wiki dump #present in parent directory of code directory


	
	ii) output folders should be present in parent directory

