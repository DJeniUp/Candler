package com.mygdx.candler.game.typer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SentenceDrawer{
    final static float LETTERWIDTHFRACTION = 0.04f;
    Vector2 position;
    String toTypeSentence;
    Stage stage;
    BitmapFont font;
    public boolean done=true; // true if correctly fully typed the word (false when exists red/orange color)
    public SentenceDrawer(String toTypeSentence, Stage stage, Vector2 position) {
        this.position = position;
        this.toTypeSentence = toTypeSentence;
        this.stage = stage;
        this.font = new BitmapFont();
        font.getData().setScale(3);
    }
    public void draw(String typedSentence) {
        font.setFixedWidthGlyphs(toTypeSentence);
        for (int i = 0; i < toTypeSentence.length(); i++) {
            GlyphLayout c = new GlyphLayout();
            if (i < typedSentence.length()) {
                if (typedSentence.charAt(i) == toTypeSentence.charAt(i)) {
                    font.setColor(Color.WHITE);
                } else {
                    font.setColor(Color.RED);
                    done = false;
                }
            } else {
                font.setColor(Color.ORANGE);
                done = false;
            }
            c.setText(font, String.valueOf(toTypeSentence.charAt(i)));
            font.draw(stage.getBatch(), c, (position.x+i*LETTERWIDTHFRACTION)*stage.getWidth(), position.y*stage.getHeight());
        }
    }
}
