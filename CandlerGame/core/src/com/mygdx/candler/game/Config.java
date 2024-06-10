package com.mygdx.candler.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;
import com.mygdx.candler.game.model.Tile;

import java.util.ArrayList;

public class Config {
    //Typing
    public static float letterWidth=0.04f;
    public static float letterScale=3;
    public static Color notTyped=Color.ORANGE;
    public static Color correctlyTyped=Color.WHITE;
    public static Color incorrectlyTyped=Color.RED;

    //Objects
    public static Vector2 defaultObjectSize=new Vector2(0.3f, 0.3f);
    public static void loadObjects(ArrayList<Object> objects, Manager manager, Stage stage, Player player){
        objects.add(new Object(manager, new Vector2(0.8f,0.14f),stage,new String[]{"Light1_0.png","Light1_1.png"},
                player, new Vector2(0.2f,0.2f)));
        objects.add(new Object(manager, new Vector2(1f,0.16f),stage, new String[]{"Lighter2_0.png","Lighter2_1.png"},player));
        objects.add(new Object(manager, new Vector2(1.2f,0.175f),stage,new String[]{"Lighter2_0.png","Lighter2_1_fire.png"},player));
        objects.add(new Object(manager, new Vector2(1.5f,0.10f),stage,new String[]{"Light1_0.png","Light1_1_orange.png"},
                        player,new Vector2(0.23f,0.23f)));
        objects.add(new Object(manager, new Vector2(1.8f,0.15f),stage,new String[]{"Lighter2_0.png","Lighter2_1_orange.png"},player));
        objects.add(new Object(manager, new Vector2(2.0f,0.17f),stage,new String[]{"Lighter2_0.png","Lighter2_1_blue.png"},player));
        objects.add(new Object(manager, new Vector2(2.4f,0.12f),stage,new String[]{"Light1_0_ocean.png","Light1_1_ocean.png"},
                player, new Vector2(0.16f,0.16f)));
        objects.add(new Object(manager, new Vector2(2.6f,0.15f),stage,new String[]{"Lighter2_0_ocean.png","Lighter2_1_ocean_1.png"},player));
        objects.add(new Object(manager, new Vector2(2.8f,0.11f),stage,new String[]{"Light1_0_ocean_2.png","Light1_1_blue.png"},
                player, new Vector2(0.18f,0.18f)));
        // THE LAST OBJECT SHOULD BE BOAT
    }

    //Player
    public static float animationSpeed=0.05f;
    public static float moveSpeed=0.005f;
    public static Vector2 startingPosition=new Vector2(0.0f,0.3f);
    public static Vector2 playerSize=new Vector2(0.2f,0.2f);
    public static float leftMapBound = 0.1f;
    public static float rightMapBound = 3f;
    public static float playerAnimationDelta = playerSize.x/1.5f;

    //Instructor
    public static Vector2 instructorSize=new Vector2(0.3f,0.2f);
    public static Vector2 instructorPosition=new Vector2(0.5f,0.22f);

    //Tile
    public final static float tileHeight = 0.3f;
    public static void loadTiles(ArrayList<Tile> tiles,Manager manager, Stage stage, Player player){
        tiles.add(new Tile(manager, "map.png",0,2.0f,stage));
        tiles.add(new Tile(manager, "map_ocean.png",2,2.0f,stage));

    }
}
