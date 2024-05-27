package com.mygdx.candler.game.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Artist;

public class MapArtist implements Artist {
    Stage stage;
    public MapArtist(Stage stage){
        this.stage = stage;
    }
    @Override
    public void draw() {
        stage.draw();
    }
}
