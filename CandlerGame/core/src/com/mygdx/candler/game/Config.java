package com.mygdx.candler.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;

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
        objects.add(new Object(manager, new Vector2(1f,0.17f),stage, new String[]{"Lighter2.png"},player));
        objects.add(new Object(manager, new Vector2(0.8f,0.15f),stage,new String[]{"Lighter2.png"},player));
        objects.add(new Object(manager, new Vector2(1.2f,0.17f),stage,new String[]{"Lighter2.png"},player));
    }

    //Player
    public static float animationSpeed=0.05f;
    public static float moveSpeed=0.005f;
    public static Vector2 startingPosition=new Vector2(0.0f,0.3f);
    public static Vector2 playerSize=new Vector2(0.2f,0.2f);
    public static float leftMapBound = 0.1f;
    public static float playerAnimationDelta = playerSize.x/1.5f;

    //Instructor
    public static Vector2 instructorSize=new Vector2(0.3f,0.2f);
    public static Vector2 instructorPosition=new Vector2(0.5f,0.22f);

    //Tile
    public final static float tileHeight = 0.3f;
}
