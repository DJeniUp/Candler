package com.mygdx.candler.game.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Player;
import com.mygdx.candler.game.model.Tile;

import java.util.ArrayList;

public class MapArtist {
    Manager manager;
    Stage stage;
    ArrayList<Tile> tiles;
    Player player;
    public MapArtist(Manager manager, Stage stage, Player player){
        this.manager = manager;
        this.stage = stage;
        this.player = player;
        tiles = new ArrayList<>();
        tiles.add(new Tile(manager, "map.png",0,4.0f,stage));
    }
    public void draw() {
        System.out.println(player.currentPosition.x);
        tiles.get(0).draw(player.currentPosition.x); // Currently we only have one map. Later, we'll go through array by for-cycle
    }
}
