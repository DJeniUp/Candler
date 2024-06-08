package com.mygdx.candler.game.model.sentence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Locations;
import com.mygdx.candler.game.controller.Manager;

public class Timer{
    Stage stage;
    BitmapFont font;
    private int countdown;
    private float timeSeconds;
    public Timer(Stage stage, int countdown) {
        this.stage = stage;
        font=new BitmapFont();
        font.getData().setScale(Config.letterScale);
        font.setColor(Color.MAROON);
        this.countdown=countdown;
        timeSeconds=0;
    }
    public void draw() {
        timeSeconds+=Gdx.graphics.getDeltaTime();
        if(timeSeconds>1f){
            countdown--;
            timeSeconds=0;
        }
        if(countdown==0){
            //do smth TODO
        }
        font.draw(stage.getBatch(),""+countdown,0.05f*stage.getWidth(),0.95f* stage.getHeight());
    }
}
