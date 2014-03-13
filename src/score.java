
public class score {

	
	private int bScore;
	private int wScore;
	
	public score( ) {
		bScore = -1;
		wScore = -1;
	}
	
	public score( int blackScore, int whiteScore ) {
		bScore = blackScore;
		wScore = whiteScore;
	}
	
	public int getBScore() {
		return bScore;
	}
	
	public int getWScore() {
		return wScore;
	}
	
	public void setBScore( int blackScore ) {
		bScore = blackScore;
	}
	
	public void modBScore( int modVal ) {
		bScore = bScore + modVal;
	}
	
	public void setWScore( int whiteScore ) {
		wScore = whiteScore;
	}
	
	public void modWScore( int modVal ) {
		wScore = wScore + modVal;
	}
}
