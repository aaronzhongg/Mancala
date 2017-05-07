package kalah.Models;

public class Player{

    private int _id;
    private Pit _pit;

    public Player() {}

    public Player(int id) {
        _pit = new Pit();
        _id = id;
    }

    public int getId(){
        return _id;
    }

    public Pit getPit() {
        return _pit;
    }
}