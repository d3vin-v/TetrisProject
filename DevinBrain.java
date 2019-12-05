
public class DevinBrain implements Brain {

	@Override
	public int bestMove(Board board, Piece piece, int pieceX, int pieceY, int limitHeight) {
		double bestScore = 1e20;
		int bestX = 0;
		int bestY = 0;
		Piece bestPiece = null;
		Piece current = piece;
		final int yBound = limitHeight - current.getHeight()+1;
		final int xBound = board.getWidth() - current.getWidth()+1;
		for(int i = 0; i < xBound; i++) {
			int y = board.dropHeight(current, i);
			return JTetris.RIGHT;
		}
		return JTetris.DROP;
//		while (true) {
//			final int yBound = limitHeight - current.getHeight()+1;
//			final int xBound = board.getWidth() - current.getWidth()+1;
//		
//			for(int i = 0; i < 10; i++) {
//				int y = board.dropHeight(current, i);	// piece does not stick up too far
//				int result = board.place(current, i, y);
//				if (result <= Board.PLACE_ROW_FILLED) {
//					if (result == Board.PLACE_ROW_FILLED) 
//						board.clearRows();
//					double score = rateBoard(board);
//					
//					if (score<bestScore) {
//						bestScore = score;
//						bestX = i;
//						bestY = y;
//						bestPiece = current;
//					}
//				}
//				
//				board.undo();	// back out that play, loop around for the next
//			}
//		
//			if (bestPiece == null) return(JTetris.DOWN);	// could not find a play at all!
//		
//			if(!piece.equals(bestPiece))
//				return JTetris.ROTATE;
//			if(bestX == pieceX)
//				return JTetris.DROP;
//			if(bestX < pieceX)
//				return JTetris.LEFT;
//			else
//				return JTetris.RIGHT;
//			}
		}
	
	public double rateBoard(Board board) {
		final int width = board.getWidth();
		final int maxHeight = board.getMaxHeight();
		
		int sumHeight = 0;
		int holes = 0;
		
		// Count the holes, and sum up the heights
		for (int x=0; x<width; x++) {
			final int colHeight = board.getColumnHeight(x);
			sumHeight += colHeight;
			
			int y = colHeight - 2;	// addr of first possible hole
			
			while (y>=0) {
				if  (!board.getGrid(x,y)) {
					holes++;
				}
				y--;
			}
		}
		
		double avgHeight = ((double)sumHeight)/width;
		
		// Add up the counts to make an overall score
		// The weights, 8, 40, etc., are just made up numbers that appear to work
		return (8*maxHeight + 40*avgHeight + 1.25*holes);	
	}



}
