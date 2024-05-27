package com.mygdx.candler.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.objects.Player;

public class GameArtist implements Artist{
    Stage stage;
    Manager manager;
    Player player;
    Texture backgroundTexture;
    private Music backgroundMusic;
    GameArtist(Stage stage,Manager manager){
        this.stage=stage;
        this.manager=manager;
        player = new Player(stage);
        backgroundTexture = new Texture("Game/back.png");
//        backgroundMusic= Gdx.audio.newMusic(Gdx.files.internal("music/background.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();
    }

    @Override
    public void draw() {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        player.draw();
        stage.getBatch().end();
    }
}
