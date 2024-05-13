package games.rednblack.candler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lantern implements GameObject {
    private int crdX=0;
    private int crdY=0;
    Texture[] unlightedTexture;
    Texture[] lightedTexture;
    private boolean lighted;
    Lantern( ){  //TODO
    }

    @Override
    public void moveLeft() {}

    @Override
    public void moveRight() {}

    @Override
    public void animate(Stage stage) {     //TODO

    }
}
