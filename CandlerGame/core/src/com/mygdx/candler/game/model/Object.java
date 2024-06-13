package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Object {
    Manager manager;
    Vector2 pos;
    Vector2 size;
    Stage stage;
    ArrayList<Texture> textures;
    int textureIndex = 0;
    Player player;
    boolean locked;
    public int ID;
    public Object(Manager manager, Vector2 position, Stage stage, String[] filenames,Player player, int ID){
        this.manager = manager;
        this.pos = position;
        this.stage = stage;
        this.player=player;
        size = Config.Objects.defaultObjectSize;
        locked=false;
        textures = new ArrayList<>();
        for(String i:filenames){
            textures.add(new Texture("Game/Objects/"+i));
        }
        this.ID=ID;
    }
    public Object(Manager manager, Vector2 position, Stage stage, String[] filenames,Player player,Vector2 size, int ID){
        this(manager,position,stage,filenames,player,ID);
        this.size = size;
    }

    public Object(){}
    public void changeTexture(){ textureIndex = (textureIndex+1)%textures.size();}
    public void draw(){}
    public void draw(float x){
        if(x-pos.x<1.2) {
            stage.getBatch().draw(textures.get(textureIndex), (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
        if(locked){
            player.unlock();
        }
        if(!locked&&abs(pos.x-x)<Config.Objects.defaultObjectSize.x){
            locked=true;
            player.lock(this);
        }
    }
}
