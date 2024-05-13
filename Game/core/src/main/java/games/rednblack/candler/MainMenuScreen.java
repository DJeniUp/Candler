package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ApplicationAdapter{
    private Stage stage;
    private Game game;
    SpriteBatch batch;
    private Texture playButtonActive;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    private Texture bg;


    public MainMenuScreen(Game game) {

        //create();
        this.game=game;
    }



    @Override
    public void create() {
        batch = new SpriteBatch();
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        playButtonActive = new Texture(Gdx.files.internal("Button.png"));
        bg = new Texture(Gdx.files.internal("MenuBg.png"));
        myTextureRegion = new TextureRegion(playButtonActive);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button.setPosition(stage.getWidth()/2-100,stage.getHeight()/2-50);
        stage.addActor(button);
        button.addListener(new ChangeListener()
        {

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Button Pressed");

            }
        });
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0,stage.getWidth(),stage.getHeight());
        stage.getBatch().end();
        stage.act();
        stage.draw();
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
        stage.dispose();
        //super.dispose();
    }
}
