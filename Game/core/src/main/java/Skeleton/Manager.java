package Skeleton;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Objects;

public class Manager extends ApplicationAdapter{
    Stage stage;
    GameArtist gameArtist;
    MenuArtist menuArtist;
    SpriteBatch batch;
    String Location;
    public Manager() {
        stage = new Stage();
        batch = new SpriteBatch();
        gameArtist = new GameArtist();
        menuArtist = new MenuArtist(this,stage);
        Location = "MainMenu";
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(stage) ;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0.6f, 0.01f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (Objects.equals(Location, "MainMenu")) {
                Location = "Game";
            }
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if(Objects.equals(Location,"Game")){
                //Maybe save progress
                Location = "MainMenu";
            }else if(Objects.equals(Location,"MainMenu")){
                System.exit(0);
            }
        }
        if(Objects.equals(Location,"Game")){
            gameArtist.draw();
        }else if(Objects.equals(Location, "MainMenu")) {
            menuArtist.draw();
        }
    }
    @Override
    public void resize(int i, int i1) {
        //stage.setViewport(i, i1, false);
        //WTF

        //        mViewport.update(width,height);
        //        batch.setProjectionMatrix(mViewport.getCamera().combined);
        //        if(width!=0 && height!=0){
        //            mSceneLoader.resize(width,height);
        //
        //        }
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        sentenceMechanic.dispose();
        assetManager.dispose();
        sceneLoader.dispose();
        //super.dispose();
    }
}

