
public class minimaxNode {

	
	private board curState;
	private coordinate move;
	private int moveVal;
	
	public minimaxNode( board b, coordinate c, int val) {
		curState = b;
		move = c;
		moveVal = val;
	}
	
	public board getState() {
		return curState;
	}
	
	public coordinate getMove() {
		return move;
	}
	
	public int getMoveVal() {
		return moveVal;
	}
	
	public void setVal( int val ) {
		moveVal = val;
	}
}
