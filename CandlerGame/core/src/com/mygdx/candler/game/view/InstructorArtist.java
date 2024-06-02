package com.mygdx.candler.game.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.model.Instructor;
import com.mygdx.candler.game.model.Object;
import com.mygdx.candler.game.model.Player;

import java.util.ArrayList;

public class InstructorArtist {
    Stage stage;
    Player player;
    ArrayList<Instructor> instructors;
    public InstructorArtist(Stage stage, Player player){
        this.stage = stage;
        this.player= player;
        instructors = new ArrayList<>();
        instructors.add(new Instructor(Config.instructorPosition,stage,"instructor.png",player));
    }
    public void draw(){
        instructors.get(0).draw(player.currentPosition.x);
    }
}
