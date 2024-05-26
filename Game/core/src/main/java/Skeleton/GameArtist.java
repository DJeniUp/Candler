package Skeleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.candler.GameObject;
import Typing.SentenceMechanic;
import games.rednblack.candler.components.PlayerComponent;
import games.rednblack.candler.scripts.PlayerScript;
import games.rednblack.candler.system.CameraSystem;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameArtist implements Artist{
    private Music backgroundMusic;
    SpriteBatch batch;
    private AssetManager mAssetManager;

    private SceneLoader mSceneLoader;
    private AsyncResourceManager mAsyncResourceManager;

    private Viewport mViewport;
    private OrthographicCamera mCamera;

    private com.artemis.World mEngine;


    SentenceMechanic mSentenceMechanic=new SentenceMechanic();

    private TextureAtlas atlas;
    GameObject.Candler candler=null;
    Stage stage;
    @Override
    public void create(){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        mAssetManager = new AssetManager();
        mAssetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(mAssetManager.getFileHandleResolver()));
        mAssetManager.load("project.dt", AsyncResourceManager.class);

        mAssetManager.finishLoading();

        mAsyncResourceManager = mAssetManager.get("project.dt", AsyncResourceManager.class);
        SceneConfiguration config = new SceneConfiguration();
        config.setResourceRetriever(mAsyncResourceManager);
        CameraSystem cameraSystem = new CameraSystem(35, 500, 20, 500);
        config.addSystem(cameraSystem);


        mSceneLoader = new SceneLoader(config);
        mEngine = mSceneLoader.getEngine();

        ComponentRetriever.addMapper(PlayerComponent.class);

        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(60, 32, mCamera);
        mSceneLoader.loadScene("MainScene", mViewport);

        ItemWrapper root = new ItemWrapper(mSceneLoader.getRoot(), mEngine);


        mSentenceMechanic.create(batch);

        ItemWrapper player = root.getChild("player");
        ComponentRetriever.create(player.getChild("player-anim").getEntity(), PlayerComponent.class, mEngine);
        PlayerScript playerScript = new PlayerScript();
        player.addScript(playerScript);
        cameraSystem.setFocus(player.getEntity());

    }


    @Override
    public void render() {
        batch.setProjectionMatrix(mCamera.combined);
        batch.begin();
        mCamera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mViewport.apply();
        mEngine.process();
        mSentenceMechanic.toTypeSentence.getPosition().y=Gdx.graphics.getHeight()*0.75f;
        mSentenceMechanic.update();
        stage.getBatch().begin();
        stage.getBatch().end();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width,height);
        batch.setProjectionMatrix(mViewport.getCamera().combined);
        if(width!=0 && height!=0){
            mSceneLoader.resize(width,height);

        }
    }

    @Override
    public void draw(){
    };
}
