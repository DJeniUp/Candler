package com.mygdx.candler.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Config {
    //Typing
    public static float letterWidth=0.04f;
    public static float letterScale=3;
    public static Color notTyped=Color.ORANGE;
    public static Color correctlyTyped=Color.WHITE;
    public static Color incorrectlyTyped=Color.RED;

    //Objects
    public static Vector2 defaultObjectSize=new Vector2(0.5f, 0.5f);

    //Player
    public static float animationSpeed=0.05f;
    public static float moveSpeed=0.005f;
    public static Vector2 startingPosition=new Vector2(0.5f,0.3f);

    //Tile
    public final static float tileHeight = 0.3f;
}
