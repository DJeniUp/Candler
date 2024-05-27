package com.mygdx.candler.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.game.MapArtist;
import com.mygdx.candler.game.game.ObjectsArtist;
import com.mygdx.candler.game.game.objects.Player;

public class GameArtist implements Artist{
    Stage stage;
    Manager manager;
    public Player player;
    Texture backgroundTexture;
    MapArtist mapArtist;
    ObjectsArtist objectsArtist;
    private Music backgroundMusic;
    GameArtist(Stage stage,Manager manager){
        this.stage=stage;
        this.manager=manager;
        player = new Player(stage);
        backgroundTexture = new Texture("Game/back.png");
        mapArtist = new MapArtist(stage,player);
        objectsArtist = new ObjectsArtist(stage,player);
//        backgroundMusic= Gdx.audio.newMusic(Gdx.files.internal("music/background.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();
    }

    @Override
    public void draw() {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture,0,0,stage.getWidth(),stage.getHeight());
        mapArtist.draw();
        objectsArtist.draw();
        player.draw(player.currentPosition.x);
        stage.getBatch().end();
    }
}
