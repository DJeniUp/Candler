package games.rednblack.candler;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Sentence {
    private final Vector2 position;
    private final int column;
    private String text;
    private int highlightedCharacters = 0;


    public Sentence(String text, int column) {
        this.text = text;
        this.column = column;
        this.position = new Vector2(getSpaceWithinColumn(column), 400);
    }

    private float getSpaceWithinColumn(int column) {
        return column * 256;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getHighlightedCharacters() {
        return highlightedCharacters;
    }

    public void setHighlightedCharacters(int i) {
        this.highlightedCharacters = i;
    }

    @Override
    public String toString() {
        return text;
    }

    public int getColumn() {
        return column;
    }
}
