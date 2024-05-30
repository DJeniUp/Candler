package com.mygdx.candler.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainMenuArtist {
    Stage stage;
    Manager manager;
    public Texture backgroundTexture;
    TyperArtist typerArtist;
    Texture buttonBg;

    public MainMenuArtist(Stage stage, Manager manager){
        this.stage=stage;
        this.manager=manager;
        try {
            FileReader temp= new FileReader("assets/MainMenu/sentences.txt");
            typerArtist=new TyperArtist(stage,temp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backgroundTexture=new Texture("MainMenu/background.png");
        buttonBg = new Texture("MainMenu/MenuButtonBg.png");
        update();
    }
    public void update(){
        Gdx.input.setInputProcessor(typerArtist);
        typerArtist.sentenceDrawers.clear();
        typerArtist.load(0,new Vector2(0.42f,0.58f));
        typerArtist.load(1 ,new Vector2(0.42f,0.3f));
    }
    public void draw() {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        stage.getBatch().draw(buttonBg,0.41f*stage.getWidth(),0.47f*stage.getHeight(),0.18f*stage.getWidth(),0.13f*stage.getHeight());
        stage.getBatch().draw(buttonBg,0.41f*stage.getWidth(),0.19f*stage.getHeight(),0.18f*stage.getWidth(),0.13f*stage.getHeight());
        typerArtist.draw();
        System.out.println(typerArtist.getTypedSentence());
        if(typerArtist.getTypedSentence().toString().equals("play")){
            manager.setLocation("Game");
        }
        if(typerArtist.getTypedSentence().toString().equals("exit")){
            Gdx.app.exit();
        }
        stage.getBatch().end();
    }
}
