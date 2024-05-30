package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Tile {
    final float TILEHEIGHT = 0.3f;
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
        stage.getBatch().draw(texture,(x-playerX)*stage.getWidth(),0,width*stage.getWidth(),TILEHEIGHT*stage.getHeight());
    }
}
