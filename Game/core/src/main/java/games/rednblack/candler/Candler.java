package games.rednblack.candler;

import com.badlogic.gdx.graphics.Texture;

public class Candler implements GameObject {
    Texture[] animationFrames;
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
    public void animate() {
        
    }

}
