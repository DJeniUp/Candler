package games.rednblack.candler;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Word {
    private final Vector2 position;
    private final int column;
    private String text;
    private int highlightedCharacters = 0;
    public Word(String text, int column) {
        this.text=text;
        this.column=column;
        this.position = new Vector2(getSpaceWithinColumn(column), 768);
    }

    private float getSpaceWithinColumn(final int column) {
        return column*248;
    }

    public String getText() {
        return text;
    }

    public int getColumn() {
        return column;
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setText(String text) {
        this.text=text;
    }

    public int getHighlightedCharacters(){
        return highlightedCharacters;
    }

    public void setHighlightedCharacters(int x){
        this.highlightedCharacters=x;
    }

    @Override
    public String toString(){
        return text;
    }
}
