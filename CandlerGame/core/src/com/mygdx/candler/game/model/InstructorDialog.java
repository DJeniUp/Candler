package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;

import static java.lang.Math.abs;

public class InstructorDialog {
    Manager manager;
    Stage stage;
    Player player;
    BitmapFont font;
    public StringBuilder quote;
    public StringBuilder typedQuote;
    private int index;
    public InstructorDialog(Manager manager, Stage stage, Player player) {
        this.manager = manager;
        font = new BitmapFont();
        font.setColor(Color.PURPLE);
        font.getData().setScale(1.7f);
        this.player=player;
        this.stage = stage;
        quote = Config.quote;
        typedQuote=new StringBuilder();
        index = 0;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(abs(player.currentPosition.x-Config.instructorPosition.x)>0.3f){
                    return;
                }
                if(index < quote.length()) {
                    typedQuote.append(quote.charAt(index));
                    index++;
                }
            }
        },0,0.1f);
    }
    public void draw(){
        System.out.println("Distance = "+abs(player.currentPosition.x-Config.instructorPosition.x));
        float distance=abs(player.currentPosition.x-Config.instructorPosition.x);
        if(distance>0.3f||distance<0.15f){
            clearTypedQuote();
            return;
        }
        font.draw(stage.getBatch(),typedQuote.toString(),0.2f*stage.getWidth(),0.2f*stage.getHeight());
    }
    public void clearTypedQuote(){
        typedQuote.setLength(0);
        index=0;
    }
}
