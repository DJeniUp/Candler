package games.rednblack.candler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface GameObject {
    Texture[] animationFrames=null;
    int crdX=0,crdY=0;
    void moveLeft();
    void moveRight();
    void animate(Stage stage);
}
