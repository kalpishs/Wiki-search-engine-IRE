import java.util.Comparator;


public class Compare2 implements Comparator<store>{

	@Override
	public int compare(store arg0, store arg1) {
		int result=arg1.ranking-arg0.ranking;
		return result;
	}

}
