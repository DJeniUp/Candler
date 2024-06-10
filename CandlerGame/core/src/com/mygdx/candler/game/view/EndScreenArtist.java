package com.mygdx.candler.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Locations;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Player;

public class EndScreenArtist {
    Manager manager;
    Stage stage;
    public Player player;
    Texture backgroundTexture;
    public EndScreenArtist(Stage stage, Manager manager){
        manager.gameArtist.backgroundMusic.stop();
        this.stage=stage;
        this.manager=manager;
        player = new Player(manager, stage);
        backgroundTexture = new Texture("Game/end.png");
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/jjk_end.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }
    public void draw() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.exit(0);
            return;
        }
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        stage.getBatch().end();
    }
}
