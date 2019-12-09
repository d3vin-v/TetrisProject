
public class DevinBrain implements Brain {

	@Override
	public int bestMove(Board board, Piece piece, int pieceX, int pieceY, int limitHeight) {
		double bestScore = 1e20;
		Piece bestPiece = null;
		Piece current = piece;
		final int yBound = limitHeight - current.getHeight()+1;
		final int xBound = board.getWidth() - current.getWidth()+1;
		//test rotations
//		int tempWidth; 
//		for(int m = 0; m < 2; m++) {
//			
//		}
		
		//attempt to find width and make decision to place in lowest column based on that
		for(int i = 1; i < 10; i ++) {
			System.out.println("1");
			if(current.getWidth() > 2) {
				for(int l = 1; l < 5; l++) {
					System.out.println("4");
					int y = board.dropHeight(current, l) + 2;
					if(l >= 3) {
						if(y > board.dropHeight(current, l-3)) 
							return JTetris.LEFT;
					}else if(y > board.dropHeight(current, l+3)) {
						return JTetris.RIGHT;
					}
				}
			}
			if(current.getWidth() > 1) {
				for(int j = 1; j < 5; j++) {
					System.out.println("3");
					int y = board.dropHeight(current, j) + 1;
					if(j >= 2) {
						if(y > board.dropHeight(current, j-2)) 
							return JTetris.LEFT;
					}else if(y > board.dropHeight(current, j+2)) {
						return JTetris.RIGHT;
					}
				}
			}
			for(int k = 1; k < 5; k++) {
				System.out.println("2");
				int y = board.dropHeight(current, k);
				if(y > board.dropHeight(current, k-1)) {
					return JTetris.LEFT;
				}else if(y > board.dropHeight(current, k+1)) {
					return JTetris.RIGHT;
				}
			}
			
		}
		return JTetris.DROP;
//		while (true) {
//			final int yBound = limitHeight - current.getHeight()+1;
//			final int xBound = board.getWidth() - current.getWidth()+1;
//		
//			for(int i = 0; i < xBound; i++) {
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
