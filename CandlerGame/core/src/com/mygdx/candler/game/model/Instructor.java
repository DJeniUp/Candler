package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.candler.game.Config;

public class Instructor {
    Vector2 pos;
    Vector2 size;
    Stage stage;
    Texture texture;
    Player player;
    public Instructor(Vector2 pos, Stage stage, String filename, Player player) {
        this.pos = pos;
        this.stage = stage;
        this.player = player;
        size= Config.instructorSize;
        texture = new Texture("Game/"+filename);
    }
    public void draw(float x){
        if(x-pos.x<1.2) {
            stage.getBatch().draw(texture, (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
    }
}
