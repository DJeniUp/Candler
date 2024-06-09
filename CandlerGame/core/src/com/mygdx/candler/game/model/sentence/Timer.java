package com.mygdx.candler.game.model.sentence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Locations;
import com.mygdx.candler.game.controller.Manager;

public class Timer{
    Manager manager;
    Stage stage;
    BitmapFont font;
    private final int countdown;
    private int curCountdown;
    private float timeSeconds;
    public Timer(Manager manager, Stage stage, int countdown) {
        this.manager=manager;
        this.stage = stage;
        font=new BitmapFont();
        font.getData().setScale(Config.letterScale);
        font.setColor(Color.MAROON);
        this.countdown=countdown;
        timeSeconds=0;
        curCountdown=countdown;
    }
    public void draw() {
        timeSeconds+=Gdx.graphics.getDeltaTime();
        if(timeSeconds>1f){
            curCountdown--;
            timeSeconds=0;
        }
        if(curCountdown==0){
            manager.setLocation(Locations.MainMenu);
            curCountdown=countdown;
            return;
        }
        font.draw(stage.getBatch(),""+curCountdown,0.05f*stage.getWidth(),0.95f* stage.getHeight());
    }
}
