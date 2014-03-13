import java.util.HashSet;
import java.util.Set;


public class coordinate {

	private int row;
	private int column;
	
	public coordinate( int r, int c) {
		row = r;
		column = c;
	}
	
	public coordinate( int r, Character c ) {
		row = r;
		column = ( c - 64 );
	}
	
	public coordinate() {
		row = -1;
		column = -1;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return column;
	}
	
	public void printCoordinate() {
		System.out.println( "Row: " + row + " Column: " + column );
	}
	
	@Override public boolean equals( Object o ) {
		if( o instanceof coordinate) {
			if( ((coordinate) o).getRow() == row && ((coordinate) o).getCol() == column ) {
				return true;
			}
		}
		return false;
	}
	
	@Override public int hashCode() {
		return ( 32 * row * (column+3));
	}
	
}
