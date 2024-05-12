package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen extends ApplicationAdapter implements Screen {

    CandlerGame game;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;


    public MainMenuScreen(CandlerGame game) {
        this.game = game;
        playButtonActive = new Texture("libgdx.png");
        playButtonInactive = new Texture("assets/libgdx.png");
        exitButtonActive = new Texture("assets/libgdx.png");
        exitButtonInactive = new Texture("assets/libgdx.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(playButtonActive, 100, 100);
        game.batch.end();
    }

    @Override
    public void hide() {

    }
}
