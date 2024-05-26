package com.mygdx.candler.game.typer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Artist;

import java.io.FileReader;
import java.util.ArrayList;

public class TyperArtist implements Artist, TypingProcessor {
    BitmapFont font;
    Stage stage;
    StringBuilder typedSentence = new StringBuilder();
    public ArrayList<SentenceDrawer> sentenceDrawers;
    ArrayList<String> possibleSentences;

    public TyperArtist(Stage stage, FileReader fileReader) {
        this.stage=stage;
        font = new BitmapFont();
        sentenceDrawers=new ArrayList<>();
        possibleSentences=new ArrayList<>();
        possibleSentences.addAll(SentenceLoader.loadSentencesFromCSV(fileReader));
    }
    @Override
    public void draw() {
        for(SentenceDrawer sentenceDrawer:sentenceDrawers){
            sentenceDrawer.draw(typedSentence.toString());
            if(sentenceDrawer.done){
                sentenceDrawers.remove(sentenceDrawer);
            }
        }
    }
    @Override
    public boolean keyTyped(char c) {
        if(c=='\b'){
            if(typedSentence.isEmpty()){
                return false;
            }
            typedSentence.deleteCharAt(typedSentence.length()-1);
            return true;
        }
        typedSentence.append(c);
        return true;
    }

    public void load(int possibleSentenceIndex, Vector2 coords){
        sentenceDrawers.add(new SentenceDrawer(possibleSentences.get(possibleSentenceIndex),stage,coords));
    }
}
