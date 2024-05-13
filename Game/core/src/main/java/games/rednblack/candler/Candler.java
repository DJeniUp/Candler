package games.rednblack.candler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.w3c.dom.Text;

//temp
import java.io.File;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.PixmapIO;

public class Candler extends InputAdapter implements GameObject {

    final int crdY = 50;
    int crdX = 0;
    final int movementSpeed = 0, leftMovementBound = 0, rightMovementBound = 1000;
    Texture[] animationFrames = new Texture[2];
    int frame = 0;
    Candler(TextureAtlas atlas){
        animationFrames[0] = atlas.findRegion("CandlerFrames",0).getTexture();
        animationFrames[1] = atlas.findRegion("CandlerFrames",1).getTexture();
        Texture q = animationFrames[0];
        TextureData w = q.getTextureData();
        if(!w.isPrepared())w.prepare();
        Pixmap pixmap = w.consumePixmap();
        PixmapIO.writePNG(new FileHandle("frame1.png"),pixmap);
        System
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
        stage.getBatch().draw(animationFrames[frame],crdX,(stage.getHeight()-animationFrames[frame].getHeight())/2);
        frame=(frame+1)%animationFrames.length;
    }

}
