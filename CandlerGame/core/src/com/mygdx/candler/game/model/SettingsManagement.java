package com.mygdx.candler.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.candler.game.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsManagement {
    public List<Texture> possibleColors;
    public List<Integer> possibleColorIndexes;
    public int position;
    public SettingsManagement(){
        possibleColors = new ArrayList<>();
        possibleColors.add(new Texture("Settings/green.png"));
        possibleColors.add(new Texture("Settings/blue.png"));
        possibleColors.add(new Texture("Settings/grey.png"));
        possibleColors.add(new Texture("Settings/red.png"));
        possibleColors.add(new Texture("Settings/magenta.png"));
        possibleColors.add(new Texture("Settings/orange.png"));
        possibleColors.add(new Texture("Settings/yellow.png"));
        possibleColors.add(new Texture("Settings/white.png"));
        possibleColorIndexes = new ArrayList<>();
        possibleColorIndexes.add(5);
        possibleColorIndexes.add(7);
        possibleColorIndexes.add(3);
        position=0;
    }
    public void keyDown(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
            music.play();
            if(position==2){
                return;
            }
            position++;
        }
    }
    public void keyUp(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
            music.play();
            if(position==0){
                return;
            }
            position--;
        }
    }
    public void keyLeft(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
            music.play();
            if(possibleColorIndexes.get(position)==0){
                possibleColorIndexes.set(position,possibleColors.size()-1);
            }else{
                possibleColorIndexes.set(position, possibleColorIndexes.get(position)-1);
            }
        }
    }
    public void keyRight(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
            music.play();
            if(possibleColorIndexes.get(position)==possibleColors.size()-1){
                possibleColorIndexes.set(position,0);
            }else{
                possibleColorIndexes.set(position, possibleColorIndexes.get(position)+1);
            }
        }
    }
    public Color getColor(int position){
        switch (position) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GRAY;
            case 3:
                return Color.RED;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.YELLOW;
            case 7:
                return Color.WHITE;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
    public void updateColor(){
        switch (position){
            case 0:
                Config.notTyped=getColor(possibleColorIndexes.get(position));
                break;
            case 1:
                Config.correctlyTyped=getColor(possibleColorIndexes.get(position));
                break;
            case 2:
                Config.incorrectlyTyped=getColor(possibleColorIndexes.get(position));
                break;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
    
    public boolean checkDistinctPosition(){
        if(Objects.equals(possibleColorIndexes.get(0), possibleColorIndexes.get(1))){
            return false;
        }
        if(Objects.equals(possibleColorIndexes.get(0), possibleColorIndexes.get(2))){
            return false;
        }
        if(Objects.equals(possibleColorIndexes.get(1), possibleColorIndexes.get(2))){
            return false;
        }
        return true;
    }
}
