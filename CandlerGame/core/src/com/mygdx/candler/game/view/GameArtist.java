package com.mygdx.candler.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Locations;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Player;

public class GameArtist {
    Stage stage;
    Manager manager;
    public Player player;
    Texture backgroundTexture;
    MapArtist mapArtist;
    ObjectsArtist objectsArtist;
    public GameArtist(Stage stage, Manager manager){
        this.stage=stage;
        this.manager=manager;
        player = new Player(stage);
        backgroundTexture = new Texture("Game/back.png");
        mapArtist = new MapArtist(stage,player);
        objectsArtist = new ObjectsArtist(stage,player);
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/background.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();
    }
    public void draw() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            manager.setLocation(Locations.MainMenu);
            return;
        }
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        mapArtist.draw();
        objectsArtist.draw();
        player.draw(player.currentPosition.x);
        stage.getBatch().end();
    }
}
