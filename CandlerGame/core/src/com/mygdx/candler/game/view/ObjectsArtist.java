package com.mygdx.candler.game.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;

import java.util.ArrayList;

public class ObjectsArtist {
    Manager manager;
    Stage stage;
    Player player;
    ArrayList<Object> objects;
    public ObjectsArtist(Manager manager, Stage stage, Player player){
        this.manager = manager;
        this.stage = stage;
        this.player= player;
        objects = new ArrayList<>();
        Config.Objects.loadObjects(objects, manager, stage, player);
        System.out.println("loaded");
    }
    public void draw(){
        for(Object i:objects)
            i.draw(player.currentPosition.x);
    }
}
