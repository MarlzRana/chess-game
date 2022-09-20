package chess;

import java.util.Set;

public class Pawn extends Piece {

	public Pawn(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			super.setSymbol("♙");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			super.setSymbol("♟");
		}
	}

	public String getSymbol() {
		return super.getSymbol();
	}

	public PieceColour getColour() {
		return colour;
	}

	// The pawn can move initially up two places, and then it can move up by one
	// after that
	// It can only move it in "forwards direction"
	// It can move diagonally provided there is a an enemy piece there and
	// capture/destroy it
	// Once a pawn gets to the other side of the board you can promote it to either
	// a Rook, Bishop, Queen or Knight
	// We can assume:
	// That we are given the coordinates of a square with a piece of the correct
	// color
	// We are not given the coordinates of a square with no piece
	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// CHECKING THAT WE AREN'T MOVING FROM THE ORIGIN SQUARE TO DESTINATION SQUARE
		// (NO MOVEMENT)
		if (i0 == i1 & j0 == j1) {
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
		// GENERATE DIRECTION OF MOVEMENT
		// Set the default direction "down the board"
		int direction = 1;
		// If the origin piece is white set the direction to "up the board"
		if (pieceInOriginColor == PieceColour.WHITE) {
			direction = -1;
		}
		// CHECK WE ARE MOVING CORRECTLY IN THE FORWARDS DIRECTION (UPWARDS) AND ONLY BY
		// ONE MOVE OR 2 IF IT IS THE PAWN'S FIRST MOVE
		Set<Integer> expectedI;
		if (pieceInOriginColor == PieceColour.WHITE && i0 >= 6) {
			expectedI = Set.of(i0 + 1 * direction, i0 + 2 * 1 * direction);
		} else if (pieceInOriginColor == PieceColour.BLACK && i0 <= 1) {
			expectedI = Set.of(i0 + 1 * direction, i0 + 2 * 1 * direction);
		} else {
			expectedI = Set.of(i0 + 1 * direction);
		}
		if (!expectedI.contains(i1)) {
			return false;
		}
		// CHECK IF WE ARE MOVING DIRECTLY FORWARDS AND IF SO MAKE SURE THAT WE DO NOT
		// RUN INTO ANY PIECES
		if (i0 + 1 * direction == i1 && j0 == j1) {
			if (!(pieceInDestination == null)) {
				return false;
			}
		}
		// CHECK IF WE ARE MOVING CORRECTLY IN THE DIAGONAL DIRECTION (UPWARDS DIAGONAL)
		// AND ONLY BY ONE MOVE
		// Checking whether we are moving in a forward j 3 * 1 rectangle
		Set<Integer> arrOfExpectedJ = Set.of(j0 - 1, j0, j0 + 1);
		if (!arrOfExpectedJ.contains(j1)) {
			return false;
		}
		// Checking whether if we are moving diagonal with a pawn that there is a piece
		// there
		// Remember from prior checks we have already established that the origin piece
		// and enemy piece are not friendly
		if (((j0 - 1 == j1) || (j0 + 1 == j1)) && pieceInDestination == null) {
			return false;
		}
		return true;
	}
}
