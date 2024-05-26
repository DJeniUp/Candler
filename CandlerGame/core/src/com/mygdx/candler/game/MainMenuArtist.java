package com.mygdx.candler.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.typer.TyperArtist;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainMenuArtist implements Artist{
    Stage stage;
    Manager manager;
    public Texture backgroundTexture;
    TyperArtist typerArtist;

    public MainMenuArtist(Stage stage, Manager manager){
        this.stage=stage;
        this.manager=manager;
        try {
            FileReader temp= new FileReader("assets/MainMenu/sentences.csv");
            typerArtist=new TyperArtist(stage,temp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backgroundTexture=new Texture("MainMenu/background.png");
    }
    public void update(){

    }

    @Override
    public void draw() {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        typerArtist.draw();
        stage.getBatch().end();
    }
}
