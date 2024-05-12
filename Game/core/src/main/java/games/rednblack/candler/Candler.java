package games.rednblack.candler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Candler extends InputAdapter implements GameObject {

    final int crdY = 50;
    int crdX = 0;

    final int movementSpeed = 0, leftMovementBound = 0, rightMovementBound = 1000;
    @Override
    public void moveLeft(){
        if(crdX-movementSpeed>leftMovementBound) crdX-=movementSpeed;
    }

    @Override
    public void moveRight() {
        if(crdX+movementSpeed<rightMovementBound) crdX+=movementSpeed;
    }

    @Override
    public void animate() { //TODO

    }

}
