package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.components.additional.ButtonComponent;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class PauseMenu extends ApplicationAdapter {
    private SceneLoader sceneLoader;
    private OrthographicCamera camera;
    private Viewport viewport;
    private com.artemis.World mEngine;
    public ItemWrapper root;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(60, 32, camera);

        SceneConfiguration config = new SceneConfiguration();
        sceneLoader = new SceneLoader(config);
        sceneLoader.loadScene("PauseMenu", viewport);
        mEngine = sceneLoader.getEngine();
        root = new ItemWrapper(sceneLoader.getRoot(), mEngine);
        ItemWrapper continueButton = root.getChild("ContinueButton");
        ComponentRetriever.create(continueButton.getEntity(), ButtonComponent.class, mEngine);
        ButtonComponent continueButtonComponent = continueButton.getComponent(ButtonComponent.class);
        continueButtonComponent.addListener(new ButtonComponent.ButtonListener(){

            @Override
            public void touchUp(int entity) {

            }

            @Override
            public void touchDown(int entity) {

            }

            @Override
            public void clicked(int entity) {
                //CandlerGame.startGame();
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        viewport.apply();
        sceneLoader.getEngine().process();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        sceneLoader.dispose();
    }

    public static void createWindow() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Secondary Window");
        config.setWindowedMode(800, 600);
        new Lwjgl3Application(new PauseMenu(), config);
    }
}
