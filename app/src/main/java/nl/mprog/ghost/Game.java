// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

public class Game {

    private final Lexicon lexicon;
    boolean player_turn = true;
    boolean winner;
    boolean language_switch = true;


    // Constructor
    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    // Use the lexicon to filter the word
    public void guess(String letter)  {
        lexicon.filter(letter);
    }

    // Returns the turn
    public boolean turn(){
        return player_turn;
    }

    // Returns the language switch
    public boolean language_switch(){
        return language_switch;
    }

    // Check if the game is ended
    public boolean ended() {
        return lexicon.game_word != null && lexicon.game_word.equals(lexicon.result())
                || lexicon.count() == 0;
    }

    public boolean winner() {
        return winner;
    }

}

