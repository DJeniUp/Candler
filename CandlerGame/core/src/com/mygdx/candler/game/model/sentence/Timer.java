package com.mygdx.candler.game.model.sentence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;

public class Timer{
    Manager manager;
    Stage stage;
    BitmapFont font;
    private final int countdown;
    private int curCountdown;
    private float timeSeconds;
    private final boolean flag;
    public Timer(Manager manager, Stage stage, int countdown, boolean flag) {
        this.manager=manager;
        this.stage = stage;
        font=new BitmapFont();
        font.getData().setScale(Config.Typing.letterScale);
        font.setColor(Color.WHITE);
        this.countdown=countdown;
        timeSeconds=0;
        curCountdown=countdown;
        this.flag=flag;
    }
    public String transform(int x){
        if(x/10>0){
            return ""+x;
        }else{
            return "0"+x;
        }
    }
    public void draw() {
        timeSeconds+=Gdx.graphics.getDeltaTime();
        if(timeSeconds>1f){
            curCountdown--;
            timeSeconds=0;
        }
        if(curCountdown==0){
            Manager.mistakes++;
            curCountdown=countdown;
            return;
        }
        if(flag){
            font.draw(stage.getBatch(),transform(curCountdown),Config.Timer.posX*stage.getWidth(),
                                                            Config.Timer.posY* stage.getHeight());
        }
    }
}
