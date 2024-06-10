package games.rednblack.candler;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.components.additional.ButtonComponent;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class MainMenu extends ApplicationAdapter {
    private AssetManager mAssetManager;

    private SceneLoader mSceneLoader;
    private AsyncResourceManager mAsyncResourceManager;

    private Viewport mViewport;
    private OrthographicCamera mCamera;

    private World mEngine;
    public ItemWrapper root;

    @Override
    public void create() {
        mAssetManager = new AssetManager();
        mAssetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(mAssetManager.getFileHandleResolver()));
        mAssetManager.load("project.dt", AsyncResourceManager.class);

        mAssetManager.finishLoading();

        mAsyncResourceManager = mAssetManager.get("project.dt", AsyncResourceManager.class);
        SceneConfiguration config = new SceneConfiguration();
        config.setResourceRetriever(mAsyncResourceManager);
        mSceneLoader = new SceneLoader(config);
        mEngine = mSceneLoader.getEngine();
        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(60, 32, mCamera);
        mSceneLoader.loadScene("MainMenu", mViewport);
        //mSceneLoader.loadScene("MainMenu", mViewport);

        root = new ItemWrapper(mSceneLoader.getRoot(), mEngine);
        ItemWrapper playButton = root.getChild("PlayButton");
        ComponentRetriever.create(playButton.getEntity(), ButtonComponent.class, mEngine);
        ButtonComponent buttonComponent = playButton.getComponent(ButtonComponent.class);
        buttonComponent.addListener(new ButtonComponent.ButtonListener(){
            @Override
            public void touchUp(int i) {
                System.out.println("Up");
            }

            @Override
            public void touchDown(int i) {
                System.out.println("Down");
            }

            @Override
            public void clicked(int i) {
                System.out.println("Start");
                CandlerGame candlerGame = new CandlerGame();
                //candlerGame.startGame(mAssetManager, mAsyncResourceManager, mSceneLoader);
            }
        });

    }
    private void startGame(){
        System.out.println("Start");
    }

    @Override
    public void render() {
        super.render();
        mCamera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mViewport.apply();
        mEngine.process();
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width,height);
        //batch.setProjectionMatrix(mViewport.getCamera().combined);
        if(width!=0 && height!=0){
            mSceneLoader.resize(width,height);

        }
    }
    @Override
    public void dispose() {
        mAssetManager.dispose();
        mSceneLoader.dispose();
    }
}
