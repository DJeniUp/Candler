package games.rednblack.candler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

//temp
import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.PixmapIO;

public class Candler extends InputAdapter implements GameObject {

    final int crdY = 50;
    int crdX = 0;
    final int movementSpeed = 0, leftMovementBound = 0, rightMovementBound = 1000;
    AtlasRegion[] animationFrames = new AtlasRegion[2];
    int frame = 0;
    Candler(TextureAtlas atlas){
        animationFrames[0] = atlas.findRegion("CandlerFrames",0);
        animationFrames[1] = atlas.findRegion("CandlerFrames",1);
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
    public void animate(Stage stage) { //TODO
        stage.getBatch().draw(animationFrames[frame],crdX,(stage.getHeight())/2);
        frame=(frame+1)%animationFrames.length;
    }

}
