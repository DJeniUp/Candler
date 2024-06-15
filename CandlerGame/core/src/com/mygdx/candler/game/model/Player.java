package com.mygdx.candler.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.sentence.TyperArtist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Player extends Object {
    Manager manager;
    public Vector2 currentPosition;
    ArrayList<Texture> textures;
    float textureIndex;
    Stage stage;
    boolean trapped;
    TyperArtist typerArtist;
    boolean headingRight = true;
    Object receiver;
    public Player(Manager manager, Stage stage){
        this.manager=manager;
        currentPosition = Config.Players.startingPosition;
        this.stage = stage;
        textures = new ArrayList<>();
        textures.add(new Texture("Game/candler_1.png"));
        textures.add(new Texture("Game/candler_2.png"));
        textureIndex = 0;
        trapped = false;
    }
    public void draw(float x){
        Gdx.input.setInputProcessor(typerArtist);
        if(!trapped&&Gdx.input.isKeyPressed(Input.Keys.LEFT)&&currentPosition.x>=Config.Players.leftMapBound){
            currentPosition.x -= Config.Players.moveSpeed;
            headingRight = false;
        }
        if(!trapped&&Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&currentPosition.x<=Config.Players.rightMapBound){
            currentPosition.x += Config.Players.moveSpeed;
            headingRight = true;
        }
        stage.getBatch().draw(textures.get((int)textureIndex),stage.getWidth()*(0.3f+(headingRight?0:Config.Players.playerAnimationDelta)),stage.getHeight()*0.2f,(headingRight?1:-1)* Config.Players.playerSize.x*stage.getWidth(),Config.Players.playerSize.y*stage.getHeight());
        textureIndex=(textureIndex+ Config.Players.animationSpeed)%textures.size();
        if(trapped){
            typerArtist.draw();
        }
    }
    public void unlock(){
        if(typerArtist.sentenceDrawers.isEmpty()){
            typerArtist.clearTyped();
            trapped = false;
            if(receiver!=null){
                System.out.println("texture should change");
                receiver.changeTexture();
                receiver = null;
            }
        }

    }
    public void lock(Object receiver){
        this.receiver = receiver;
        lock();
    }
    public void lock(){
        try {
            Random rn=new Random();
            FileReader temp=Config.Typing.getFileReader("Game/test.txt");
            typerArtist = new TyperArtist(manager, stage, temp, 10+abs(rn.nextInt())%10, true);
            Gdx.input.setInputProcessor(typerArtist);
            typerArtist.load(receiver.ID, Config.Typing.position);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        trapped=true;
    }
}
