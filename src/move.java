import java.util.HashSet;
import java.util.Set;


public class move {

	
	private int val1;
	private int val2;
	
	public move( int one, int two ) {
		val1 = one;
		val2 = two;
	}
	
	public Set<coordinate> getMoveFrom( coordinate cor ) {
		Set<coordinate> moveSet = new HashSet<coordinate>();
		
		int row = cor.getRow();
		int col = cor.getCol();
		
		coordinate a = new coordinate( ( row + val1), ( col + val2) );
		coordinate b = new coordinate( ( row + val1), ( col - val2) );
		coordinate c = new coordinate( ( row - val1), ( col + val2) );
		coordinate d = new coordinate( ( row - val1), ( col - val2) );
		
		coordinate e = new coordinate( ( row + val2), ( col + val1) );
		coordinate f = new coordinate( ( row + val2), ( col - val1) );
		coordinate g = new coordinate( ( row - val2), ( col + val1) );
		coordinate h = new coordinate( ( row - val2), ( col - val1) );
		
		moveSet.add(a);
		moveSet.add(b);
		moveSet.add(c);
		moveSet.add(d);
		moveSet.add(e);
		moveSet.add(f);
		moveSet.add(g);
		moveSet.add(h);
		
		return moveSet;
	}
}
