import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class AI extends player {
	
	private int moveCount;
	private static int earlyGame = 6;
	private static int eMGame = 10;
	private static int midGame = 15;
	private ArrayList<move> small;
	private ArrayList<move> med;
	private ArrayList<move> large;
	
	private Set<coordinate> base;

	public AI( Character c ) {
		super(c);
		
		if( c.equals("B")) {
			moveCount = 0;
		}
		else {
			moveCount = 1;
		}
		small = new ArrayList<move>();
		med = new ArrayList<move>();
		large = new ArrayList<move>();
		
		
		populateMoveList();
		base = new HashSet<coordinate>();
		
	}
	
	

	public coordinate getMove( board b ) {
		
		coordinate selected = new coordinate();
		coordinate check = new coordinate();
		boolean passThrough = false;
		
		if( moveCount <= earlyGame ) {
			/// find an early game move
			selected = createTerr( b );
			if( !selected.equals(check)) {
				base.add(selected);
				return selected;
			}
			else {
				passThrough = true;
			}
			
		}
		else if( moveCount <= eMGame || passThrough == true ){
			passThrough = false;
			
			return check;
			
		}
		else if( moveCount <= midGame || passThrough == true ){
			/// find a mid game move
			
			///run search to find a mid game move
			/// search()
			/// find move with most influence on board from a set of example extensions
			
			return check;
		}
		else {
			/// find an end game move
			return check;
		}
		
		return selected;
	}
	
	public int getPlayerType() {
		return 2;
	}
	
	
	/*
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
				board minState = b;
				minState.move(c, color);
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
	*/
	
	private coordinate createTerr(board brd) {
		coordinate selected = new coordinate(-1,-1);
		int bestScore = 0;
		coordinate a = new coordinate(3,3);
		coordinate b = new coordinate(3,7);
		coordinate c = new coordinate(7,3);
		coordinate d = new coordinate(7,7);
		int aScore = -1;
		int bScore = -1;
		int cScore = -1;
		int dScore = -1;
		
		if( brd.isMoveValid(a, color) ) {
			aScore = brd.move(a, color);
			if( aScore > bestScore ) {
				bestScore = aScore;
				selected = a;
			}
		}
		else if( brd.isMoveValid(b, color) ) {
			bScore = brd.move(b, color);
			if( bScore > bestScore ) {
				bestScore = bScore;
				selected = b;
			}
		}
		else if( brd.isMoveValid(c, color) ) {
			cScore = brd.move(c, color);
			if( cScore > bestScore ) {
				bestScore = cScore;
				selected = c;
			}
		}
		else if( brd.isMoveValid(d, color) ) {
			dScore = brd.move(d, color);
			if( dScore > bestScore ) {
				bestScore = dScore;
				selected = d;
			}
		}
		
		
		return selected;
	}
	
	private void populateMoveList() {
		// populate the move lists with haengma
		
		move strech = new move(1,0);
		move diagSmall = new move(1,1);
		small.add(strech);
		small.add(diagSmall);
		
		move oneJump = new move(2,0);
		move smallKnight = new move(2,1);
		med.add(oneJump);
		med.add(smallKnight);
		
		move twoJump = new move(3,0);
		move largeKnight = new move(3,1);
		move diagLarge = new move(2,2);
		large.add(twoJump);
		large.add(largeKnight);
		large.add(diagLarge);
		
		
		
		return;
	}
	
}
