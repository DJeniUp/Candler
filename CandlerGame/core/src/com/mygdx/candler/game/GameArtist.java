package com.mygdx.candler.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameArtist implements Artist{
    Stage stage;
    Manager manager;
    private Music backgroundMusic;
    GameArtist(Stage stage,Manager manager){
        this.stage=stage;
        this.manager=manager;

//        backgroundMusic= Gdx.audio.newMusic(Gdx.files.internal("music/background.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();
    }

    @Override
    public void draw() {

    }
}
