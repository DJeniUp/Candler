package com.mygdx.candler.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.*;
import com.mygdx.candler.game.model.Object;

import java.util.ArrayList;

public class Config {
    //Typing
    public static float letterWidth=0.03f;
    public static float letterScale=2;
    public static Color notTyped=Color.ORANGE;
    public static Color correctlyTyped=Color.WHITE;
    public static Color incorrectlyTyped=Color.RED;

    //Objects
    public static Vector2 defaultObjectSize=new Vector2(0.3f, 0.3f);
    public static void loadObjects(ArrayList<Object> objects, Manager manager, Stage stage, Player player){
        objects.add(new Object(manager, new Vector2(0.8f,0.14f),stage,new String[]{"Light1_0.png","Light1_1.png"},
                player, new Vector2(0.2f,0.2f),0));
        objects.add(new Object(manager, new Vector2(1f,0.16f),stage, new String[]{"Lighter2_0.png","Lighter2_1.png"},player,1));
        objects.add(new Object(manager, new Vector2(1.2f,0.175f),stage,new String[]{"Lighter2_0.png","Lighter2_1_fire.png"},player,2));
        objects.add(new Object(manager, new Vector2(1.5f,0.10f),stage,new String[]{"Light1_0.png","Light1_1_orange.png"},
                        player,new Vector2(0.23f,0.23f),3));
        objects.add(new Object(manager, new Vector2(1.8f,0.15f),stage,new String[]{"Lighter2_0.png","Lighter2_1_orange.png"},player,4));
        objects.add(new Object(manager, new Vector2(2.0f,0.17f),stage,new String[]{"Lighter2_0.png","Lighter2_1_blue.png"},player,5));
        objects.add(new Boat(manager, new Vector2(2.4f,0.06f),stage,new String[]{"boat.png"},player));

        objects.add(new Object(manager, new Vector2(2.4f,0.12f),stage,new String[]{"Light1_0_ocean.png","Light1_1_ocean.png"},
                player, new Vector2(0.16f,0.16f),6));
        objects.add(new Object(manager, new Vector2(2.6f,0.15f),stage,new String[]{"Lighter2_0_ocean.png","Lighter2_1_ocean_1.png"},player,7));
        objects.add(new Object(manager, new Vector2(2.8f,0.11f),stage,new String[]{"Light1_0_ocean_2.png","Light1_1_blue.png"},
                player, new Vector2(0.18f,0.18f),8));
        objects.add(new Portal(manager, new Vector2(3.19f,0.05f),stage,new String[]{"portal.png"}, player,9));
    }
    //Boat
    public static Vector2 boatSize=new Vector2(0.3f,0.4f);
    //Player
    public static float animationSpeed=0.05f;
    public static float moveSpeed=0.025f;
    public static Vector2 startingPosition=new Vector2(0.0f,0.3f);
    public static Vector2 playerSize=new Vector2(0.2f,0.2f);
    public static float leftMapBound = 0.1f;
    public static float rightMapBound = 3f;
    public static float playerAnimationDelta = playerSize.x/1.5f;

    //Instructor
    public static Vector2 instructorSize=new Vector2(0.3f,0.2f);
    public static Vector2 instructorPosition=new Vector2(0.5f,0.22f);
    public static StringBuilder quote = new StringBuilder(
            "Hello. This is a Candler Game.\n" +
            "Your task is to lighten the lanterns.\n" +
            "If you fail, the world gets darker.");

    //Tile
    public final static float tileHeight = 0.3f;
    public static void loadTiles(ArrayList<Tile> tiles,Manager manager, Stage stage, Player player){
        tiles.add(new Tile(manager, "map.png",0,2.0f,stage));
        tiles.add(new Tile(manager, "map_ocean.png",2,2.0f,stage));

    }
}
