package com.example.netbook.ghost;

import android.util.Log;

import java.util.ArrayList;

public class Game {

    Lexicon lexicon;
    boolean player_turn = true;
    boolean winner;



    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    public void guess(String letter)  {
        lexicon.filter(letter);
    }

    public boolean turn(){
        return player_turn;
    }

    public boolean ended() {
        if (lexicon.game_word != null && lexicon.game_word.equals(lexicon.result())
                || lexicon.count() == 0) {
            return true;
        }
        return false;
    }
    public boolean winner() {
        return winner;
    }

    }
