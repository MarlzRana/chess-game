package chess;

import java.util.Set;

public class Knight extends Piece {

	public Knight(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			super.setSymbol("♘");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			super.setSymbol("♞");
		}
	}

	public String getSymbol() {
		return super.getSymbol();
	}

	public PieceColour getColour() {
		return colour;
	}

	// Knight can move in a very sporadic way
	// Create a 3*3 square around the knight
	// It can move to the squares encapsulating the corners of this imaginary 3*3
	// square
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
		// GENERATE A LIST OF POTENTIAL I AND J COORDINATES THE NIGHT CAN MOVE TO
		Set<String> potentialIJCoordinates = Set.of("" + (i0 + 2) + (j0 + 1),
				"" + (i0 - 2) + (j0 + 1),
				"" + (i0 + 2) + (j0 - 1),
				"" + (i0 - 2) + (j0 - 1), "" + (i0 + 1) + (j0 + 2),
				"" + (i0 - 1) + (j0 - 2),
				"" + (i0 - 1) + (j0 + 2),
				"" + (i0 + 1) + (j0 - 2));
		if (!(potentialIJCoordinates.contains("" + i1 + j1))) {
			return false;
		}
		return true;
	}
}
