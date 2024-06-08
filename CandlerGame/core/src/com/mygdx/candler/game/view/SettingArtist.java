package com.mygdx.candler.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.candler.game.controller.Locations;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.SettingsManagement;

public class SettingArtist {
    Stage stage;
    Manager manager;
    SettingsManagement settingsManagement;
    BitmapFont font;
    public SettingArtist(Stage stage, Manager manager) {
        font = new BitmapFont();
        font.getData().setScale(1.7f);
        this.stage = stage;
        this.manager = manager;
        settingsManagement = new SettingsManagement();
    }
    public void draw(){
        ScreenUtils.clear(0, 0, 0, 0);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            if(settingsManagement.checkDistinctPosition()){
                manager.setLocation(Locations.MainMenu);
            }
            return;
        }
        settingsManagement.keyDown();
        settingsManagement.keyUp();
        settingsManagement.keyLeft();
        settingsManagement.keyRight();
        settingsManagement.updateColor();
        stage.getBatch().begin();
        if(settingsManagement.position == 0){
            font.setColor(Color.RED);
        }else{
            font.setColor(Color.WHITE);
        }
        font.draw(stage.getBatch(), "Sentence to Type", 0.34f*stage.getWidth(), 0.9f*stage.getHeight());
        stage.getBatch().draw(settingsManagement.possibleColors.get(settingsManagement.possibleColorIndexes.get(0)), 0.1f*stage.getWidth(), 0.7f*stage.getHeight(), 0.8f*stage.getWidth(), 0.15f*stage.getHeight());
        if(settingsManagement.position == 1){
            font.setColor(Color.RED);
        }else{
            font.setColor(Color.WHITE);
        }
        font.draw(stage.getBatch(), "Typed Sentence", 0.36f*stage.getWidth(), 0.65f*stage.getHeight());
        stage.getBatch().draw(settingsManagement.possibleColors.get(settingsManagement.possibleColorIndexes.get(1)), 0.1f*stage.getWidth(), 0.45f*stage.getHeight(), 0.8f*stage.getWidth(), 0.15f*stage.getHeight());
        if(settingsManagement.position == 2){
            font.setColor(Color.RED);
        }else{
            font.setColor(Color.WHITE);
        }
        font.draw(stage.getBatch(), "Mistakes", 0.43f*stage.getWidth(), 0.40f*stage.getHeight());
        stage.getBatch().draw(settingsManagement.possibleColors.get(settingsManagement.possibleColorIndexes.get(2)), 0.1f*stage.getWidth(), 0.20f*stage.getHeight(), 0.8f*stage.getWidth(), 0.15f*stage.getHeight());
        stage.getBatch().end();
    }
}
