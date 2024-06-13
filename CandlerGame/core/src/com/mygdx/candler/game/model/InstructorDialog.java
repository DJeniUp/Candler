package com.mygdx.candler.game.model;

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
        font.setColor(Config.Instructor.color);
        font.getData().setScale(Config.Instructor.fontScale);
        this.player=player;
        this.stage = stage;
        quote = Config.Instructor.quote;
        typedQuote=new StringBuilder();
        index = 0;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(abs(player.currentPosition.x-Config.Instructor.instructorPosition.x)>Config.Instructor.minDist){
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
        float distance=player.currentPosition.x-Config.Instructor.instructorPosition.x;
        if(distance<Config.Instructor.lower || distance>Config.Instructor.upper){
            clearTypedQuote();
            return;
        }
        font.draw(stage.getBatch(),typedQuote.toString(),Config.Instructor.posX*stage.getWidth(),
                                                        Config.Instructor.posY*stage.getHeight());
    }
    public void clearTypedQuote(){
        typedQuote.setLength(0);
        index=0;
    }
}
