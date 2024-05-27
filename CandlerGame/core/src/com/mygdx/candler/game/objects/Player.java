package com.mygdx.candler.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Vector;

public class Player implements Object{
    final float ANIMATIONSPEED = 0.05f;
    final Vector2 size = new Vector2(0.2f,0.2f);
    Vector2 pos;
    ArrayList<Texture> textures;
    float textureIndex;
    Stage stage;
    public Player(Stage stage){
        pos = new Vector2(0.1f,0.5f);
        this.stage = stage;
        textures = new ArrayList<>();
        textures.add(new Texture("Game/candler_1.png"));
        textures.add(new Texture("Game/candler_2.png"));
        textureIndex = 0;
    }
    public void draw(){
        stage.getBatch().draw(textures.get((int)textureIndex),stage.getWidth()*0.4f,stage.getHeight()*0.5f,size.x*stage.getWidth(),size.y*stage.getHeight());
        textureIndex=(textureIndex+ANIMATIONSPEED)%textures.size();
    }
}
