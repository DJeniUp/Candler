package com.mygdx.candler.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.typer.TyperArtist;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainMenuArtist implements Artist{
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
        typerArtist.load(0,new Vector2(0.43f,0.48f));
        typerArtist.load(1 ,new Vector2(0.43f,0.3f));
    }
    @Override
    public void draw() {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        stage.getBatch().draw(buttonBg,0.42f,0.46f,0.58f,0.54f);
        stage.getBatch().draw(buttonBg,0.42f,0.27f,0.58f,0.33f);
        typerArtist.draw();
        stage.getBatch().end();
    }
}
