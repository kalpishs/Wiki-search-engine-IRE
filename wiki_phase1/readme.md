____________________________________________________________________________________________________________________________________________
Phase-1
Kalpish Singhal
201505513
M.tech CSE(PG1)
____________________________________________________________________________________________________________________________________________
==============================================================================================================================
	           Indexing mechanism for search egine 
==============================================================================================================================
-> The code basically implement INDEX CREATION MODULE for seach engin 
-> It reads the content of XML File and SAX Parser passes it
-> Character by character it scans the text and extracts words for indexing
==============================================================================================================================
	           Files LIST With One Line Disciption
==============================================================================================================================
(i)  Driver.java //contains main function takes Xml "wiki dump" as comandline input and calls Handller_pars for handling 
(ii) Handller_pars.java //contains parsing handling mechanism to remove scrap and send words for parsing into map
(iii)help_parsing.java //helps in parsing the content removing stop words stemming it using Stemmer.java
(iv) Stemmer.java //stemm the word to its root form and return it to the calling function 
(iv) Writeing_to_file.java //it writes o/p to the given comaand line input filr if file not exist it will create new file 
(v)  listing.java //Class defination for using ojects to keep count to be maped into the map 
==============================================================================================================================
	            Implemented characteristics
==============================================================================================================================

-> Used tree Hash map for SortedMap order of the keys will be sorted. so sorted according to word A-Z
-> Not storing zeros in index file. helps in reducing 
-> One go parssing :- data is parsed in one go to reduce time 
-> Stop words are removed to make index efficent except from title in the view for seacrch of titles like "to be not to be" type query 
-> infobox is parsed to get relevent data.
==============================================================================================================================
	            Output Format
==============================================================================================================================
->word:-doc_id:title:<no>category:<no>infobox:<no>body:<no>;
eg:-
ledzi:-6256:b:1; # represnt ledzi is in document 6256 1 time in body 

#Assumption
->for scipt file 2 comand line inputs 
	i) xml-wiki dump #present in parent directory of code directory  
	ii) output file name #if not present output file in parent folder new file is ceated 


