package com.mygdx.candler.game.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Artist;
import com.mygdx.candler.game.game.objects.Player;
import com.mygdx.candler.game.game.objects.Tile;

import java.util.ArrayList;

public class MapArtist implements Artist {
    Stage stage;
    ArrayList<Tile> tiles;
    Player player;
    public MapArtist(Stage stage, Player player){
        this.stage = stage;
        this.player = player;
        tiles = new ArrayList<>();
        tiles.add(new Tile("map.png",0,4.0f,stage));
    }
    @Override
    public void draw() {
        System.out.println(player.currentPosition.x);
        tiles.get(0).draw(player.currentPosition.x); // Currently we only have one map. Later, we'll go through array by for-cycle
    }
}
