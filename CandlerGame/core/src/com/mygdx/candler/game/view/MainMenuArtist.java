package com.mygdx.candler.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Locations;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.sentence.TyperArtist;

import java.io.*;

public class MainMenuArtist {
    Manager manager;
    Stage stage;
    public Texture backgroundTexture;
    TyperArtist typerArtist;
    Texture buttonBg;
    public MainMenuArtist(Stage stage, Manager manager){
        this.stage=stage;
        this.manager=manager;
        try {
            FileReader temp = Config.Typing.getFileReader("MainMenu/sentences.txt");
            typerArtist=new TyperArtist(manager, stage,temp, 60, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        backgroundTexture=new Texture("MainMenu/background.png");
        buttonBg = new Texture("MainMenu/MenuButtonBg.png");
        update();
    }
    public void update(){
        typerArtist.clearTyped();
        typerArtist.sentenceDrawers.clear();
        typerArtist.load(0,new Vector2(0.44f,0.89f));//play
        typerArtist.load(1 ,new Vector2(0.44f,0.29f));//exit
        typerArtist.load(2,new Vector2(0.40f,0.6f));//settings
    }
    public void draw() {
        Gdx.input.setInputProcessor(typerArtist);
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        stage.getBatch().draw(buttonBg,0.41f*stage.getWidth(),0.79f*stage.getHeight(),0.18f*stage.getWidth(),0.13f*stage.getHeight());
        stage.getBatch().draw(buttonBg,0.41f*stage.getWidth(),0.19f*stage.getHeight(),0.18f*stage.getWidth(),0.13f*stage.getHeight());
        typerArtist.draw();
        if(typerArtist.getTypedSentence().toString().equals("play")){
            update();
            manager.setLocation(Locations.Game);
        }
        if(typerArtist.getTypedSentence().toString().equals("exit")){
            typerArtist.clearTyped();
            Gdx.app.exit();
        }
        if(typerArtist.getTypedSentence().toString().equals("settings")){
            update();
            manager.setLocation(Locations.Settings);
        }
        stage.getBatch().end();
    }
}
