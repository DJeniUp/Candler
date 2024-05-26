package Skeleton;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.*;
import java.util.Objects;

public class Manager extends ApplicationAdapter{
    Stage stage;
    GameArtist gameArtist;
    MenuArtist menuArtist;
    SpriteBatch batch;
    String Location;

    private Texture exitButtonActive;
    private Texture exitButtonInactive;



    private boolean inGame = false;


    public Manager() {
        stage = new Stage();
        batch = new SpriteBatch();
        gameArtist = new GameArtist();
        menuArtist = new MenuArtist(this,stage);
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
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
    }
    @Override
    public void resize(int i, int i1) {
        //stage.setViewport(i, i1, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }



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

