package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Game.Rules;
import kalah.Models.Board;
import kalah.Utility.Printer;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		Printer printer = new Printer(io);
		Rules rules = new Rules();
		boolean validMove;
		Board board = new Board();
		boolean fullGame = true;

		// While loop to continue as long as each player has a move
		while(!rules.gameEnded(board)) {
			printer.printRound(board);

			String playerMove = printer.playerMove(board.getCurrentPlayerTurn());

			// Handle input (valid inputs only)
			if (playerMove.equals("q")) {
				fullGame = false;
				break;
			} else {
				int chosenHouse = Integer.parseInt(playerMove);
				validMove = rules.playerMove(board, chosenHouse);
			}

			if (!validMove) {
				printer.printEmptyHouse();
			}

		}

		if (!fullGame) {
			printer.printQuit(board);
		} else {
			printer.printRound(board);
			printer.printGameOver();
			printer.printRound(board);
			rules.scoreFullGame(board);
			printer.printFullGame(board);
		}
	}
}
