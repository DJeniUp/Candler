package com.mygdx.candler.game.model.sentence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;

public class SentenceDrawer {
    Vector2 position;
    String toTypeSentence;
    Stage stage;
    BitmapFont font;
    public boolean done; // true if correctly fully typed the word (false when exists red/orange color)
    public SentenceDrawer(String toTypeSentence, Stage stage, Vector2 position) {
        this.position = position;
        this.toTypeSentence = toTypeSentence;
        this.stage = stage;
        this.font = new BitmapFont();
        font.getData().setScale(Config.letterScale);
    }
    public void draw(String typedSentence) {
        font.setFixedWidthGlyphs(toTypeSentence);
        done = true;
        for (int i = 0; i < toTypeSentence.length(); i++) {
            GlyphLayout c = new GlyphLayout();
            if (i < typedSentence.length()) {
                if (typedSentence.charAt(i) == toTypeSentence.charAt(i)) {
                    font.setColor(Config.correctlyTyped);
                } else {
                    font.setColor(Config.incorrectlyTyped);
                    done = false;
                }
            } else {
                font.setColor(Config.notTyped);
                done = false;
            }
            c.setText(font, String.valueOf(toTypeSentence.charAt(i)));
            if(i<typedSentence.length() && toTypeSentence.charAt(i) == ' ') {
                c.setText(font,"_");
            }
            float x= position.x + Config.letterWidth * i;
            float y= position.y;
            font.draw(stage.getBatch(), c, x*stage.getWidth(), y*stage.getHeight());
        }
    }
}
