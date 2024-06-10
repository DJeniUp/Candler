package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Boat extends Object {
    Manager manager;
    Vector2 pos;
    Vector2 size;
    Stage stage;
    ArrayList<Texture> textures;
    int textureIndex = 0;
    Player player;
    boolean locked;
    public Boat(Manager manager, Vector2 position, Stage stage, String[] filenames,Player player){
        super();
        this.manager = manager;
        this.pos = position;
        this.stage = stage;
        this.player=player;
        size = Config.defaultObjectSize;
        locked=false;
        textures = new ArrayList<>();
        for(String i:filenames)
            textures.add(new Texture("Game/Objects/"+i));
    }

    public void changeTexture(){ super.changeTexture();}
    public void draw(float x){
        super.draw();
        if(x>=2.2f) {
            pos.x = x+0.2f;
        }
        if(abs(x-pos.x)<1f) {
            stage.getBatch().draw(textures.get(textureIndex), (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
    }
}
