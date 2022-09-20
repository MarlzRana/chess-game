package chess;

public class Rook extends Piece {

	public Rook(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			super.setSymbol("♖");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			super.setSymbol("♜");
		}

	}

	public PieceColour getColour() {
		return colour;
	}

	// The rook can move up, down, left and right as many squares as you want
	// provided there isn't something in the way of it
	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// CHECKING THAT WE AREN'T MOVING FROM THE ORIGIN SQUARE TO DESTINATION SQUARE
		// (NO MOVEMENT)
		if (i0 == i1 && j0 == j1) {
			return false;
		}
		// CHECKING IF THE DESTINATION SQUARE DOESN'T CONTAIN A FRIENDLY PIECE
		// Try to get the pieces in the origin square and destination square
		Piece pieceInOrigin = Board.getPiece(i0, j0);
		Piece pieceInDestination = Board.getPiece(i1, j1);
		// Getting their respective colors
		PieceColour pieceInOriginColor = pieceInOrigin.getColour();
		PieceColour pieceInDestinationColor;
		// Make sure that if there is a piece in the destination square that it is the
		// opposite color of the piece in the origin piece
		if (pieceInDestination != null) {
			pieceInDestinationColor = pieceInDestination.getColour();
			// If they are the same color return false as this is an invalid move
			if (pieceInOriginColor == pieceInDestinationColor) {
				return false;
			}
		}
		// CHECK IF WE ARE MOVING IN A STRAIGHT DIRECTION
		if (!(i0 == i1 || j0 == j1)) {
			return false;
		}
		// CHECK THERE ARE NO PIECES IN BETWEEN THE THE ORIGIN AND DESTINATION PIECE
		// Check determining whether we are moving in the I direction or not
		boolean isMovingInTheIDirection = true;
		if (i0 == i1) {
			isMovingInTheIDirection = false;
		}
		// Exhibit a different behavior depending on whether we are moving in the i or j
		// direction
		if (isMovingInTheIDirection) {
			int lowerI = 0;
			int higherI = 0;
			if (i1 > i0) {
				lowerI = i0;
				higherI = i1;
			} else {
				lowerI = i1;
				higherI = i0;
			}
			for (int i = lowerI + 1; i < higherI; i++) {
				if (Board.hasPiece(i, j0)) {
					return false;
				}
			}
		} else {

			int lowerJ = 0;
			int higherJ = 0;

			if (j1 > j0) {
				lowerJ = j0;
				higherJ = j1;
			} else {
				lowerJ = j1;
				higherJ = j0;
			}

			for (int j = lowerJ + 1; j < higherJ; j++) {
				if (Board.hasPiece(i0, j)) {
					return false;
				}
			}
		}

		return true;
	}
}
