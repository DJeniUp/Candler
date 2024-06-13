package com.mygdx.candler.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.Config;
import com.mygdx.candler.game.controller.Manager;
import java.util.ArrayList;
import static com.mygdx.candler.game.controller.Locations.End;
import static java.lang.Math.abs;

public class Portal extends Object{
    Manager manager;
    Vector2 pos;
    Vector2 size;
    Stage stage;
    ArrayList<Texture> textures;
    Player player;
    boolean locked;
    public int ID;
    public Portal(Manager manager, Vector2 position, Stage stage, String[] filenames,Player player,int ID){
        super();
        this.manager = manager;
        this.pos = position;
        this.stage = stage;
        this.player=player;
        size = Config.Objects.defaultObjectSize;
        locked=false;
        textures = new ArrayList<>();
        this.ID=ID;
        for(String i:filenames){
            textures.add(new Texture("Game/Objects/"+i));
        }
        textureIndex=0;
    }

    public void changeTexture(){
        super.changeTexture();
    }
    public void draw(float x){
        if(abs(pos.x-x)<=Config.Objects.minDist){
            manager.location = End;
        }
        super.draw();
        if(abs(x-pos.x)<Config.Objects.displayDist) {
            stage.getBatch().draw(textures.get(textureIndex), (pos.x - x) * stage.getWidth(), pos.y * stage.getHeight(),
                    size.x * stage.getWidth(), size.y * stage.getHeight());
        }
    }
}
