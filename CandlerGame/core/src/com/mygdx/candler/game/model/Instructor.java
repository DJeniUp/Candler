package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;

import static java.lang.Math.abs;

public class Instructor {
    Manager manager;
    public Vector2 pos;
    Vector2 size;
    Stage stage;
    Texture texture;
    Player player;
    InstructorDialog instructorDialog;
    public Instructor(Manager manager, Vector2 pos, Stage stage, String filename, Player player) {
        this.manager=manager;
        this.pos = pos;
        this.stage = stage;
        this.player = player;
        size= Config.instructorSize;
        texture = new Texture("Game/"+filename);
        instructorDialog=new InstructorDialog(manager,stage,player);
    }
    public void draw(float x){
        if(abs(x-pos.x)<1.2f) {
            stage.getBatch().draw(texture, (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
        instructorDialog.draw();
    }
}
