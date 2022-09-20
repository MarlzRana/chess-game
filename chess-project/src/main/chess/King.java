package chess;

import java.util.Set;

public class King extends Piece {

	public King(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			super.setSymbol("♔");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			super.setSymbol("♚");
		}
	}

	public String getSymbol() {
		return super.getSymbol();
	}

	public PieceColour getColour() {
		return colour;
	}

	// The king can move in a "box" around it and one square at a time
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
		Set<Integer> setOfPotentialPossibleICoordinates = Set.of(i0 - 1, i0, i0 + 1);
		Set<Integer> setOfPotentialPossibleJCoordinates = Set.of(j0 - 1, j0, j0 + 1);
		if (!(setOfPotentialPossibleICoordinates.contains(i1) && setOfPotentialPossibleJCoordinates.contains(j1))) {
			return false;
		}
		return true;
	}
}
