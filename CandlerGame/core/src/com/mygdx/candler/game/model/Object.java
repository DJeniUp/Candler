package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static java.lang.Math.abs;

public class Object {
    final Vector2 STANDARTSIZE = new Vector2(0.5f, 0.5f);
    Vector2 pos;
    Vector2 size;
    Stage stage;
    Texture texture;
    Player player;
    boolean trappedPlayer;
    public Object(Vector2 position, Stage stage, String filename,Player player){
        this.pos = position;
        this.stage = stage;
        this.player=player;
        size = STANDARTSIZE;
        texture = new Texture("Game/Objects/"+filename);
    }

    public Object() {
    }

    public void draw(float x){
        if(x-pos.x<1.2) {
            stage.getBatch().draw(texture, (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
        if(trappedPlayer){
            player.untrap();
        }
        if(!trappedPlayer&&abs(x-pos.x)<0.150){ //TODO make formula
            trappedPlayer=true;
            player.trap();
        }
    }
}
