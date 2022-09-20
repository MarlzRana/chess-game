package chess;

public class Bishop extends Piece {

	public Bishop(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			super.setSymbol("♗");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			super.setSymbol("♝");
		}
	}

	public String getSymbol() {
		return super.getSymbol();
	}

	public PieceColour getColour() {
		return colour;
	}

	// The bishop can only move diagonally
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
		// CHECKING TO MAKE SURE WE ARE NOT MOVING IN A STRAIGHT DIRECTION
		if (i0 == i1 || j0 == j1) {
			return false;
		}
		// CHECKING IF WE ARE MOVING DIAGONALLY
		int differenceInI = Math.abs(i1 - i0);
		int differenceInJ = Math.abs(j1 - j0);
		if (differenceInI != differenceInJ) {
			return false;
		}
		// MAKING SURE THERE ARE NO PIECE IN BETWEEN THE PATH
		int iDirectionMultiplier = -1;
		int jDirectionMultiplier = -1;
		if (i1 > i0) {
			iDirectionMultiplier = 1;
		}
		if (j1 > j0) {
			jDirectionMultiplier = 1;
		}
		for (int k = 1; k < differenceInI; k++) {
			if (Board.hasPiece(i0 + k * iDirectionMultiplier, j0 + k * jDirectionMultiplier)) {
				return false;
			}
		}
		return true;
	}

}
