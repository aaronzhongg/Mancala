package kalah.Models;

import kalah.Game.Rules;

import java.util.ArrayList;
import java.util.List;

import static kalah.Utility.GameConfig.NUMBER_OF_PLAYERS;

/**
 * Board.java represents the board used for a game of Mancala
 */
public class Board {
    private List<Player> _players;
    private int _currentPlayerTurn;

    public Board() {
        _players = new ArrayList<>();
        for (int i = 1; i < NUMBER_OF_PLAYERS + 1; i++) {
            _players.add(new Player(i));
        }
        _currentPlayerTurn = _players.get(0).getId();
    }

    public  List<Player> getPlayers() {
        return _players;
    }

    public Player getPlayer1(){
        return _players.get(0);
    }

    public Player getPlayer2(){
        return _players.get(1);
    }

    public void nextPlayerTurn() {
        if (_currentPlayerTurn + 1 > _players.size()) {
            _currentPlayerTurn = _players.get(0).getId();
        } else {
            _currentPlayerTurn = _players.get(1).getId();
        }

    }

    public int getCurrentPlayerTurn() {
        return _currentPlayerTurn;
    }
}