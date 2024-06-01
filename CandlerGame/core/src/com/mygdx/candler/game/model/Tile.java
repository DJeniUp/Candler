package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;

public class Tile {
    Stage stage;
    Texture texture;
    float x, width;
    public Tile(String filename, float x, float width,Stage stage) {
        texture = new Texture("Game/Tiles/"+filename);
        this.x=x;
        this.width=width;
        this.stage=stage;
    }
    public void draw(float playerX){
        stage.getBatch().draw(texture,(x-playerX)*stage.getWidth(),0,width*stage.getWidth(), Config.tileHeight *stage.getHeight());
    }
}
