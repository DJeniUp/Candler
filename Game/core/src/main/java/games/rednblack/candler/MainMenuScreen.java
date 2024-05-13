package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;

public class MainMenuScreen extends ApplicationAdapter implements Screen {

    private CandlerGame game;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;


    public MainMenuScreen(CandlerGame game) {
        this.game = game;
        playButtonActive = new Texture(Gdx.files.internal("Button.png"));
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
