package com.mygdx.candler.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
public interface Object {
    //position/size is float representing fraction of screen
    //only pos.x (width) can be >1 because objects can be placed anywhere on the map
    //be carefull that pos.y+size.y
    Vector2 pos = new Vector2(),size = new Vector2();
    ArrayList<Texture> textures = new ArrayList<>();
    int textureIndex=0;
//    Stage stage = null;
//    default void draw(){
//        stage.getBatch().draw(textures.get(textureIndex),pos.x*stage.getWidth(),pos.y*stage.getHeight(),
//                (pos.x+size.x)*stage.getWidth(),(pos.y+size.y)*stage.getHeight());
//    };
}
