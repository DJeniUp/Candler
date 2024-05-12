package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.candler.scripts.PlayerScript;
import games.rednblack.candler.system.CameraSystem;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ItemWrapper;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class CandlerGame extends ApplicationAdapter {
    private AssetManager mAssetManager;

    private SceneLoader mSceneLoader;
    private AsyncResourceManager mAsyncResourceManager;

    private Viewport mViewport;
    private OrthographicCamera mCamera;

    private com.artemis.World mEngine;

    private ExtendViewport mHUDViewport;

    SentenceMechanic mSentenceMechanic=new SentenceMechanic();

    private TextureAtlas atlas;
    Candler candler=null;
    @Override
    public void create(){
        atlas=new TextureAtlas(Gdx.files.internal("Game/assets/orig/pack.atlas"));
        candler = new Candler(atlas);

        mSentenceMechanic.create();

        mAssetManager = new AssetManager();
        mAssetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(mAssetManager.getFileHandleResolver()));
        mAssetManager.load("project.dt", AsyncResourceManager.class);
        mAssetManager.finishLoading();

        mAsyncResourceManager = mAssetManager.get("project.dt", AsyncResourceManager.class);
        SceneConfiguration config = new SceneConfiguration();
        config.setResourceRetriever(mAsyncResourceManager);
        CameraSystem cameraSystem = new CameraSystem(5, 40, 5, 6);
        config.addSystem(cameraSystem);

        mSceneLoader = new SceneLoader(config);
        mEngine = mSceneLoader.getEngine();

        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(60, 32, mCamera);

        mSceneLoader.loadScene("MainScene", mViewport);

        ItemWrapper root = new ItemWrapper(mSceneLoader.getRoot(), mEngine);
        ItemWrapper player = root.getChild("Player");
        PlayerScript playerScript = new PlayerScript();
//        player.addScript(playerScript);
//        cameraSystem.setFocus(player.getEntity());
    }


    @Override
    public void render() {
//        mCamera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mViewport.apply();
        mEngine.process();


        mSentenceMechanic.update();
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width,height);
        if(width!=0 && height!=0){
            mSceneLoader.resize(width,height);
        }
    }

    @Override
    public void dispose() {
        mSentenceMechanic.dispose();
        mAssetManager.dispose();
        mSceneLoader.dispose();
    }

}
