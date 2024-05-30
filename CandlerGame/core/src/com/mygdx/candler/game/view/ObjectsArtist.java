package com.mygdx.candler.game.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;

import java.util.ArrayList;

public class ObjectsArtist {
    Stage stage;
    Player player;
    ArrayList<Object> objects;
    public ObjectsArtist(Stage stage, Player player){
        this.stage = stage;
        this.player= player;
        objects = new ArrayList<>();
        objects.add(new Object(new Vector2(1f,0.17f),stage,"Lighter2.png",player));
    }
    public void draw(){
        objects.get(0).draw(player.currentPosition.x);
    }
}
