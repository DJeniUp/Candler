package games.rednblack.candler;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface GameObject {
    Texture[] animationFrames=null;
    int crdX=0,crdY=0;
    void moveLeft();
    void moveRight();
    void animate(Stage stage);

    class Candler extends InputAdapter implements GameObject {

        final int crdY = 50;
        int crdX = 0;
        final int movementSpeed = 0, leftMovementBound = 0, rightMovementBound = 1000;
        TextureAtlas.AtlasRegion[] animationFrames = new TextureAtlas.AtlasRegion[2];
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
}

