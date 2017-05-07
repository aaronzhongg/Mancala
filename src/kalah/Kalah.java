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
		Printer printer = Printer.getInstance();
		Rules rules = Rules.getInstance();
		// Replace what's below with your implementation
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("Player 1's turn - Specify house number or 'q' to quit: ");

		Board board = new Board();


		while(!rules.gameEnded(board)) {
			printer.printRound(board);

			String playerMove = printer.playerMove(board.getCurrentPlayerTurn());

			if (playerMove == "q") {

			}

		}
	}
}
