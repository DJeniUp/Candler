package games.rednblack.candler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import org.w3c.dom.Text;

public class Candler extends InputAdapter implements GameObject {

    final int crdY = 50;
    int crdX = 0;
    final int movementSpeed = 0, leftMovementBound = 0, rightMovementBound = 1000;
    Texture[] animationFrames = new Texture[2];
    Candler(TextureAtlas atlas){
        animationFrames[0] = atlas.findRegion("CandlerFrame1").getTexture();
        animationFrames[1] = atlas.findRegion("CandlerFrame").getTexture();
    }
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
