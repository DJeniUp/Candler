package com.mygdx.candler.game.model.sentence;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.TypingProcessor;

import java.io.FileReader;
import java.util.ArrayList;

public class TyperArtist implements TypingProcessor {
    Manager manager;
    BitmapFont font;
    Stage stage;
    StringBuilder typedSentence = new StringBuilder();
    public ArrayList<SentenceDrawer> sentenceDrawers;
    ArrayList<String> possibleSentences;
    Timer timer;
    public TyperArtist(Manager manager, Stage stage, FileReader fileReader, int countdown) {
        this.manager = manager;
        timer=new Timer(manager, stage, countdown);
        this.stage=stage;
        font = new BitmapFont();
        sentenceDrawers=new ArrayList<>();
        possibleSentences=new ArrayList<>();
        possibleSentences.addAll(SentenceLoader.loadSentencesFromCSV(fileReader));
    }
    public TyperArtist(Manager manager, Stage stage, ArrayList<String>possibleSentences,int countdown) {
        this.manager=manager;
        timer=new Timer(manager, stage, countdown);
        this.stage=stage;
        font = new BitmapFont();
        sentenceDrawers=new ArrayList<>();
        this.possibleSentences=possibleSentences;
    }
    public void draw() {
        timer.draw();
        for(int i=sentenceDrawers.size()-1;i>=0;i--){
            SentenceDrawer sentenceDrawer = sentenceDrawers.get(i);
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
        sentenceDrawers.add(new SentenceDrawer(manager, possibleSentences.get(possibleSentenceIndex),stage,coords));
    }

    public StringBuilder getTypedSentence(){
        return typedSentence;
    }

    public void clearTyped(){
        typedSentence=new StringBuilder();
    }
}
