package com.example.netbook.ghost;

import android.util.Log;

public class Game {

    Lexicon lexicon;
    public String game_word;
    boolean player_turn = true;
    boolean winner;




    Game(){
        //empty
    }

    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    public void guess(String letter) {
        game_word += letter;
        lexicon.filter(game_word);
    }

    public boolean turn(){
        return player_turn;
    }

    public boolean ended() {
        if (lexicon.game_word != null && lexicon.game_word.equals(game_word)
                || lexicon.count() == 0) {
            winner = !turn();
            return true;
        }
        return false;
    }
    public boolean winner() {
        return winner;
    }

    }
