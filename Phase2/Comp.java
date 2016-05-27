import java.util.Comparator;
public class Comp implements Comparator<term>
{
	
	 public int compare(term a, term b) 
	    { 
		 	int result;
	        result= a.word.compareTo(b.word);
	        return result;
	    } 
}
