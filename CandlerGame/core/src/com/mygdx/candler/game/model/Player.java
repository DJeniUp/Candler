package com.mygdx.candler.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.model.sentence.TyperArtist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Player extends Object {
    final float ANIMATIONSPEED = 0.05f;
    final static float MOVESPEED = 0.005f;
    final static Vector2 INITIALPOS = new Vector2(0.5f,0.3f);
    final Vector2 size = new Vector2(0.2f,0.2f);
    public Vector2 currentPosition;
    ArrayList<Texture> textures;
    float textureIndex;
    Stage stage;
    boolean trapped;
    TyperArtist typerArtist;
    public Player(Stage stage){
        currentPosition = new Vector2(INITIALPOS.x,INITIALPOS.y);
        this.stage = stage;
        textures = new ArrayList<>();
        textures.add(new Texture("Game/candler_1.png"));
        textures.add(new Texture("Game/candler_2.png"));
        textureIndex = 0;
        trapped = false;
    }
    public void draw(float x){
        Gdx.input.setInputProcessor(typerArtist);
        if(!trapped&&Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            currentPosition.x -= MOVESPEED;
        }
        if(!trapped&&Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            currentPosition.x += MOVESPEED;
        }
        stage.getBatch().draw(textures.get((int)textureIndex),stage.getWidth()*0.3f,stage.getHeight()*0.2f,size.x*stage.getWidth(),size.y*stage.getHeight());
        textureIndex=(textureIndex+ANIMATIONSPEED)%textures.size();
        if(trapped){
            System.out.println("typer artist is supposed to draw ");
            typerArtist.draw();
        }
    }
    public void unlock(){
        if(typerArtist.sentenceDrawers.isEmpty()){
            typerArtist.clearTyped();
            trapped = false;
        }
    }
    public void lock(){
        try {
            typerArtist = new TyperArtist(stage,new FileReader("assets/Game/test.txt"));
            Gdx.input.setInputProcessor(typerArtist);
            typerArtist.load(0,new Vector2(0.3f,0.7f));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        trapped=true;
    }
}