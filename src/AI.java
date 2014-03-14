import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class AI extends player {
	
	private int moveCount;
	private static int earlyGame = 4;
	private static int eMidGame = 10;
	private static int midGame = 20;
	private ArrayList<move> small;
	private ArrayList<move> med;
	private ArrayList<move> large;
	
	private Character color;
	
	private Set<coordinate> base;

	public AI( Character c ) {
		super(c);
		color = c;
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
		
		board placeHolder = b;
		
		coordinate selected = new coordinate();
		coordinate check = new coordinate();
		
		if( moveCount <= earlyGame ) {
			/// find an early game move
			selected = createTerr( placeHolder );
			if( !selected.equals(check)) {
				base.add(selected);
				return selected;
			}
			else {
				selected = search(placeHolder);
			}
		}
		else {
			selected = search(placeHolder);
		}
		return selected;
	}

	public int getPlayerType() {
		return 2;
	}
	
	public Character getOpColor() {
		if( color.equals('B')) {
			return 'W';
		}
		else {
			 return 'B';
		}
	}
	
	public coordinate search( board b ) {
		//System.out.println(true);
		minimaxNode m = maxMove(b, 0, new coordinate());
		return m.getMove();
	}
	
	public minimaxNode maxMove( board b, int depth, coordinate lstMv ) {
		// if game is over return game state evaluation
		if( noMoreMoves(b) || DepthReached( depth ) ){
			/// pass
			int max = EvalState(b);
			return new minimaxNode(b, lstMv, max);
		}
		else {
			coordinate bestMove = new coordinate();
			Set<coordinate> posMoves = genMoves( b );
			for( coordinate c: posMoves ) {
				if( b.isMoveValid(c, getOpColor())) {
					board minBoard = b;
					minBoard.move(c, color);
					minimaxNode minState = minMove( minBoard, depth+1, c );
				
					if( minState.getState().move(c, color) > minState.getState().move(bestMove, color) ) {
						bestMove =  c;
					}
				}
			}
			//return bestMove;
			return new minimaxNode(b, bestMove, EvalState(b));
		}
	}
	
	public minimaxNode minMove( board b, int depth, coordinate lstMv ) {
		if( noMoreMoves(b) || DepthReached( depth ) ) {
			int min = EvalState(b);
			return new minimaxNode(b, lstMv, min);
		}
		else {
			coordinate bestMove = new coordinate();
			Set<coordinate> posMoves = genMoves( b );
			for( coordinate c: posMoves ) {
				if( b.isMoveValid(c, getOpColor())) {
					board maxBoard = b;
					maxBoard.move(c, color);
					minimaxNode maxState = maxMove( maxBoard, depth+1, c );
					if( maxState.getState().move(c, color) > maxState.getState().move(c, color)) {
						bestMove = c;
					}
				}
			}
			//return bestMove;
			return new minimaxNode( b, bestMove, EvalState(b));
		}
	}
	
	private boolean DepthReached(int depth) {
		int depthLimit = 5;
		if( depth == depthLimit) {
			return true;
		}
		return false;
	}
	
	private int EvalState( board b ) {
		int val = b.evalInf();
		return val;
	}

	private Set<coordinate> genMoves(board b) {
		//System.out.println( true );
		Set<coordinate> moves = new HashSet<coordinate>();
		if( moveCount <= eMidGame ){
			/// find an early mid game move
			for( coordinate c: base) {
				for( int i = 0; i < large.size(); i++) {
					move temp = large.get(i);
					moves.addAll(temp.getMoveFrom(c));
				}
			}
			return moves;
		}
		else if( moveCount <= midGame ){
			/// find a mid game move
			
			///run search to find a mid game move
			/// search()
			/// find move with most influence on board from a set of example extensions
			
			
		}
		else {
			/// find a late game move
			
		}
		
		return null;
	}
	
	private boolean noMoreMoves(board b) {
		/// if all intersections left on the board are territory, satisfying territory ripple, and no space large enough to make two eyes
		
		return false;
	}
	
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
	
	private coordinate extend(board b) {
		
		
		
		return null;
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
