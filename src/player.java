
public abstract class player {

	Character color;
	String cString;
	int score;
	int prisoners;
	String lastMove;
	
	public abstract coordinate getMove( board b );
	
	public player( Character c ) {
		color = c;
		score = 0;
		prisoners = 0;
		
		if( c.equals('B')) {
			cString = "Black";
		}
		else {
			cString = "White";
		}
	}
	
	public Character getColor() {
		return color;
	}
	
	public String getColorString() {
		return cString;
	}
	
	public void addPrisoners( int i ) {
		prisoners += i;
	}
	
	public void setLastMove( String s ) {
		lastMove = s;
	}
	
	public String getLastMove( ) {
		return lastMove;
	}
}
