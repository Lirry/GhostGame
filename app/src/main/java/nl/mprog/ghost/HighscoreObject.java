// Lirry Pinter ID: 10565051

package nl.mprog.ghost;


public class HighscoreObject{

    // Used to save the highscores, putting them in a list
    String name;
    int score;

    public HighscoreObject(){
        super();
    }

    public HighscoreObject(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.score;
    }

}
