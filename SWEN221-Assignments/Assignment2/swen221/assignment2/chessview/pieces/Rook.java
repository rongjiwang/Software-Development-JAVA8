package swen221.assignment2.chessview.pieces;

import swen221.assignment2.chessview.*;

public class Rook extends PieceImpl implements Piece {
	public Rook(boolean isWhite) {
		super(isWhite);
	}

	/**
	 * rook's old position this is for castling use
	 */
	private Position oldPosition;

	/**
	 * check all the valid or invalid move for Rook
	 * 
	 * @param oldPosition
	 *            old location
	 * @param newPosition
	 *            new location
	 * @param isTaken
	 *            simple move or take a piece down
	 * @param board
	 * @return true meet all the condition
	 * 
	 * 
	 */
	public boolean isValidMove(Position oldPosition, Position newPosition, Piece isTaken, Board board) {
		this.oldPosition = oldPosition;

		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row()) - Math.min(oldPosition.row(), newPosition.row());

		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		if (this.equals(p)
				&& (((diffCol > 0 && diffRow == 0) && board.clearColumnExcept(oldPosition, newPosition, p, t))
						|| ((diffCol == 0 && diffRow > 0) && board.clearRowExcept(oldPosition, newPosition, p, t)))) {
			if (isTaken != null && t != null) { // taken codition
				if (isTaken.equals(t) && (this.isWhite != t.isWhite())) {
					return true;
				}
				return false;
			} else if (t == null && isTaken == null) { // move codition
				return true;
			}
			return false;
		}

		return false;

	}

	/**
	 * 
	 * @return position of rook
	 */
	public Position getOldPosition() {
		return oldPosition;
	}

	public String toString() {
		if (isWhite) {
			return "R";
		} else {
			return "r";
		}
	}
}
