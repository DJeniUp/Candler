package com.mygdx.candler.game.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;
import com.mygdx.candler.game.model.Instructor;
import com.mygdx.candler.game.model.InstructorDialog;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class InstructorArtist {
    Manager manager;
    Stage stage;
    Player player;
    Instructor instructor;
    public InstructorArtist(Manager manager, Stage stage, Player player){
        this.manager = manager;
        this.stage = stage;
        this.player= player;
        instructor = new Instructor(manager, Config.Instructor.instructorPosition,stage,"instructor.png",player);
    }
    public void draw(){
        instructor.draw(player.currentPosition.x);
    }
}
