package com.mygdx.candler.game.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
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
        Config.Tiles.loadTiles(tiles,manager,stage,player);
    }
    public void draw() {
        for(Tile tile:tiles) tile.draw(player.currentPosition.x); // Currently we only have one map. Later, we'll go through array by for-cycle
    }
}
