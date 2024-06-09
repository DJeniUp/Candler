package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.sentence.TyperArtist;

import java.util.ArrayList;
import java.util.Arrays;

public class InstructorDialog {
    Manager manager;
    Stage stage;
    Instructor instructor;
    TyperArtist typerArtist;
    Player player;
    BitmapFont font;
    public InstructorDialog(Manager manager, Stage stage, Player player, Instructor instructor) {
        this.manager = manager;
        font = new BitmapFont();
        font.getData().setScale(1.7f);
        this.player=player;
        this.stage = stage;
        this.instructor = instructor;
        typerArtist=new TyperArtist(manager, stage,new ArrayList<>(Arrays.asList("Thank you")),1000,false);
        typerArtist.load(0,player.currentPosition);
    }
    public void draw(){
        font.draw(stage.getBatch(),"Enjoy the game",05f*stage.getWidth(),stage.getHeight()/2f);
    }
}