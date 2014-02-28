import java.util.Scanner;


public class projectMain {

	
	private static int size;
	
	public static void main(String[] args) {
			
		
		/// set size
		size = 5;
		
		/// create board
		board goBoard = new board( size );
		
		boardPrint(goBoard);
		
		/// intialize players, at the moment we can assume that the program only needs to support two humnan players, but an input will be
		/// good for when we need to check what combination of players are going to be playing
		
		player p1 = new player( 'B' );
		player p2 = new player( 'W' );
		
		Scanner in = new Scanner(System.in);
		coordinate p1Move = new coordinate(-2, 'z');
		coordinate p2Move = new coordinate(-2, 'z');
		
		boolean lastWasPass = false;
		
		while( true ) {
			p1Move = getMove(p1, in, goBoard);
			
			/// make move
			if( p1Move.getRow() != -1 ) {
				goBoard.move(p1Move, p1.getColor());
				lastWasPass = false;
			}
			else if( lastWasPass ) {
				break;
			}
			else {
				lastWasPass = true;
			}
			
			boardPrint(goBoard);
			
			p2Move = getMove(p2, in, goBoard);
			
			/// make move			
			if( p2Move.getRow() != -1 ) {
				goBoard.move(p2Move, p2.getColor());
				lastWasPass = false;
			}
			else if( lastWasPass ) {
				break;
			}
			else {
				lastWasPass = true;
			}
			
			boardPrint(goBoard);
		}
		
		boardPrint(goBoard);
		
		/// calculate player score
		/// declare the winner
	}
	
	private static coordinate getMove(player p, Scanner in, board b) {
		boolean validMove = false;
		coordinate move;
		while( !validMove ) {
			System.out.print( p.getColorString() + ", please enter a move (ex. A5), or enter 'pass' to pass your move: ");
			String input = in.next();
			
			if( input.equals("pass")) {
				move = new coordinate( -1, 'z');
				return move;
			}
			
			try {
				Character c = input.substring(0,1).charAt(0);
				String rest = input.substring(1);
				int i = Integer.parseInt(rest);
			
				move = new coordinate( i, c );
				if( b.isMoveValid(move, p.getColor())) {
					return move;
				}
			} catch ( Exception e ) {
				/// make sure improper formatting doesn't blow up the process
			}
			
		}
		
		return null;
	}

	public static void boardPrint( board goBoard) {
		System.out.println();
		goBoard.printBoard();
		System.out.println();
		System.out.println();
	}
}
