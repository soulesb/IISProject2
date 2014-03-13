import java.util.Scanner;


public class projectMain {

	
	private static int size;
	private static player p1;
	private static player p2;
	
	public static void main(String[] args) {
			
		
		/// set size
		size = 9;
		
		/// create board
		board goBoard = new board( size );
		
		
		
		Scanner in = new Scanner(System.in);
		
		getPlayers( in );
		
		/// intialize players, at the moment we can assume that the program only needs to support two humnan players, but an input will be
		/// good for when we need to check what combination of players are going to be playing
		
		//player p1 = new human( 'B' );
		//player p2 = new human( 'W' );
		
		boardPrint(goBoard);
		
		coordinate p1Move = new coordinate(-2, -2);
		coordinate p2Move = new coordinate(-2, -2);
		coordinate passTest = new coordinate();
		
		boolean lastWasPass = false;
		
		while( true ) {
			p1Move = getPlayerMove( p1, in, goBoard );
			
			/// make move
			if( !p1Move.equals(passTest)) {
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
			p2Move = getPlayerMove( p2, in, goBoard );
			//p2Move = getMove(p2, in, goBoard);
			
			/// make move			
			if( !p2Move.equals(passTest)) {
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
	
	private static coordinate getPlayerMove(player p, Scanner in, board goBoard) {
		if( p.getPlayerType() == 1 ) {
			/// player is human ask them for moves
			return getMove(p1, in, goBoard);
		}
		else if( p.getPlayerType() == 2 ) {
			/// player is an ai tell it to give a coordinate
			getMove( p, goBoard);
		}
		return new coordinate();
	}

	private static void getPlayers(Scanner in) {
		System.out.println( "For a human vs human game enter 1, for a computer vs human game enter 2, for a computer vs computer game enter 3.");
		while( true ) {
			try {
				int input = in.nextInt();
				if( input == 1 ) {
					p1 = new human( 'B' );
					p2 = new human( 'W' );
					return;
				}
				else if( input == 2 ) {
					p1 = new AI( 'B' );
					p2 = new human( 'W' );
					return;
				}
				else if( input == 3 ) {
					p1 = new AI( 'B' );
					p2 = new AI( 'W' );
					return;
				}
				else {
					System.out.println( "For a human vs human game enter 1, for a computer vs human game enter 2, for a computer vs computer game enter 3.");
				}
			
			} catch (Exception e ) {
				
			}
			
		}
	}

	private static coordinate getMove(player p, Scanner in, board b) {
		boolean validMove = false;
		coordinate move;
		while( !validMove ) {
			System.out.print( p.getColorString() + ", please enter a move (ex. A5), or enter 'pass' to pass your move: ");
			String input = in.next();
			
			if( input.equals("pass")) {
				move = new coordinate();
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
	
	private static coordinate getMove( player p, board b ) {
		/// for getting ai moves, no neef for this silly scanning
		return p.getMove(b);
	}

	public static void boardPrint( board goBoard) {
		System.out.println();
		goBoard.printBoard();
		System.out.println();
		goBoard.printIMap();
		System.out.println();
	}
}
