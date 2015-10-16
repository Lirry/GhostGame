// Lirry Pinter ID: 10565051

package nl.mprog.ghost;


public class PlayerLives {
    String ghost_text_player_one = "";
    String ghost_text_player_two = "";

    public void player_one_lives(int lives) {


        // Initializing switch for lives
        switch (lives) {
            case 1:
                ghost_text_player_one = "g";
                break;
            case 2:
                ghost_text_player_one = "gh";
                break;
            case 3:
                ghost_text_player_one = "gho";
                break;
            case 4:
                ghost_text_player_one = "ghos";
                break;
            case 5:
                ghost_text_player_one = "ghost!";
        }
    }

    public void player_two_lives(int lives) {

        // Initializing switch for lives
        switch (lives) {
            case 1:
                ghost_text_player_two = "g";
                break;
            case 2:
                ghost_text_player_two = "gh";
                break;
            case 3:
                ghost_text_player_two = "gho";
                break;
            case 4:
                ghost_text_player_two = "ghos";
                break;
            case 5:
                ghost_text_player_two = "ghost!";

        }
    }
}
