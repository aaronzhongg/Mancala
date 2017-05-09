package kalah.Game;

/**
 * Created by Aaron on 9/05/17.
 */
public class GameHelper {

    public GameHelper() {

    }

    /**
     * Check if the seeds remaining
     * @param seeds
     * @return true - there are seeds remaining. false - no seeds remaining
     */
    public static boolean checkIfAnySeedsRemaining(int seeds) {
        if (seeds == 0) {
            return false;
        }
        return true;
    }

    /**
     * Get the opposite house number of input house
     * @param house
     * @return opposite house number
     */
    public static int getOppositeHouse(int house) {
        switch (house) {
            case 0:
                return 5;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 1;
            case 5:
                return 0;
            default:
                return -1;
        }
    }
}
