import java.util.Set;


public class AI extends player {
	
	private int moveCount;
	private static int earlyGame = 6;
	private static int midGame = 15;

	public AI( Character c ) {
		super(c);
		
		if( c.equals("B")) {
			moveCount = 0;
		}
		else {
			moveCount = 1;
		}
	}
	
	public coordinate getMove( board b ) {
		
		coordinate selected = new coordinate(-1,'Z');
		if( moveCount <= earlyGame ) {
			/// find an early game move
			selected = createTerr( b );
		}
		else if( moveCount <= midGame ){
			/// find a mid game move
			
			///run search to find a mid game move
			/// search()
			/// find move with most influence on board from a set of example extensions
			
			
		}
		else {
			/// find an end game move
			
		}
		
		return selected;
	}
	
	public coordinate search( board b ) {
		return maxMove(b);
	}
	
	public coordinate maxMove( board b ) {
		// if game is over return game state evaluation
		if( false ){
			
		}
		else {
			coordinate bestMove = new coordinate();
			Set<coordinate> posMoves = genMoves( b );
			for( coordinate c: posMoves ) {
				board minState = b.move(c, color); 
			}
			return bestMove;
		}
	}
	
	public coordinate minMove( board b ) {
		coordinate bestMove = new coordinate();
		Set<coordinate> posMoves = genMoves( b );
		for( coordinate c: posMoves ) {
			board maxState = maxMove()
		}
	}
	
}
