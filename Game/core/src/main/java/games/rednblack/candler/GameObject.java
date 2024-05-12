package games.rednblack.candler;

import com.badlogic.gdx.graphics.Texture;

public interface GameObject {
    Texture[] animationFrames=null;
    int crdX=0,crdY=0;
    void moveLeft();
    void moveRight();
    void animate();
}
