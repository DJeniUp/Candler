package games.rednblack.candler;

import com.artemis.World;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.badlogic.gdx.video.VideoPlayer;
//import com.badlogic.gdx.video.VideoPlayerCreator;
import games.rednblack.candler.Managers.LabelManager;
import games.rednblack.candler.components.*;
import games.rednblack.candler.system.*;
import games.rednblack.candler.scripts.PlayerScript;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.components.additional.ButtonComponent;
import games.rednblack.editor.renderer.components.label.LabelComponent;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.scene2d.ButtonClickListener;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;

import javax.swing.*;
import java.io.FileNotFoundException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class CandlerGame extends ApplicationAdapter {
    private Music backgroundMusic;
    private AssetManager mAssetManager;

    private SceneLoader mSceneLoader;
    private AsyncResourceManager mAsyncResourceManager;

    private Viewport mViewport;
    private OrthographicCamera mCamera;

    private com.artemis.World mEngine;
    public ItemWrapper root;
    private int gameFlag=0;


    SentenceMechanic mSentenceMechanic=new SentenceMechanic();

    private TextureAtlas atlas;
    Candler candler=null;
    Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private LabelManager labelManager;
    private World world;
    private Skin skin;
    private Window pauseMenu;
    //private VideoPlayer videoPlayer;

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
        ItemWrapper exitGameButton = root.getChild("ExitButton");

        ComponentRetriever.create(exitGameButton.getEntity(), ButtonComponent.class, mEngine);
        ComponentRetriever.create(playButton.getEntity(), ButtonComponent.class, mEngine);
        ButtonComponent exitButtonComponent = exitGameButton.getComponent(ButtonComponent.class);
        ButtonComponent playButtonComponent = playButton.getComponent(ButtonComponent.class);

        exitButtonComponent.addListener(new ButtonComponent.ButtonListener() {
            @Override
            public void touchUp(int i) {

            }

            @Override
            public void touchDown(int i) {

            }

            @Override
            public void clicked(int i) {
                Gdx.app.exit();
            }
        });
        playButtonComponent.addListener(new ButtonComponent.ButtonListener(){
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
                startGame();
            }
        });


    }
    /*public void playVideo(){
        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(800,600,mCamera);
        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        try {
            videoPlayer.play(Gdx.files.internal("StarAnim.mp4"));
        }catch (FileNotFoundException e){
            System.out.println("VideoFileNotFind");
        }
        //startGame();
    }*/
    public void startGame(){

        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setFullscreenMode(displayMode);

        mAssetManager = new AssetManager();
        mAssetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(mAssetManager.getFileHandleResolver()));
        mAssetManager.load("project.dt", AsyncResourceManager.class);

        mAssetManager.finishLoading();

        mAsyncResourceManager = mAssetManager.get("project.dt", AsyncResourceManager.class);
        SceneConfiguration config = new SceneConfiguration();

        batch = new SpriteBatch();
        mSentenceMechanic.create(batch);
        stage=new Stage(mViewport);
        //Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        labelManager =LabelManager.getInstance();
        LabelSystem labelSystem = new LabelSystem(batch, font, labelManager);
        config.addSystem(labelSystem);

        config.setResourceRetriever(mAsyncResourceManager);
        CameraSystem cameraSystem = new CameraSystem();
        config.addSystem(cameraSystem);
        PauseMenuSystem pauseMenuSystem = new PauseMenuSystem();
        config.addSystem(pauseMenuSystem);


        config.addSystem(new PlayerAnimSystem());
        config.addSystem(new Lighter1AnimSystem());
        config.addSystem(new LightAnimSystem());
        //config.addSystem(new LabelSystem());


        mSceneLoader = new SceneLoader(config);
        mEngine = mSceneLoader.getEngine();

        ComponentRetriever.addMapper(PlayerComponent.class);
        ComponentRetriever.addMapper(LighterComponent.class);
        ComponentRetriever.addMapper(PauseMenuComponent.class);

        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(60, 32, mCamera);
        mSceneLoader.loadScene("MainScene", mViewport);
        //mSceneLoader.loadScene("MainMenu", mViewport);

        root = new ItemWrapper(mSceneLoader.getRoot(), mEngine);

        //world = mSceneLoader.getWorld();
        //mSentenceMechanic.create(batch);

        ItemWrapper player = root.getChild("player");
        ItemWrapper pauseMenu = root.getChild("PauseMenu");
        ComponentRetriever.create(player.getChild("player-anim").getEntity(), PlayerComponent.class, mEngine);
        ComponentRetriever.create(pauseMenu.getEntity(), PauseMenuComponent.class, mEngine);
        //ItemWrapper lighter1=root.getChild("lighter1");
        //ComponentRetriever.create(lighter1.getChild("lighter1-anim").getEntity(), Lighter1Component.class, mEngine);
        PlayerScript playerScript = new PlayerScript();
        player.addScript(playerScript);
        pauseMenuSystem.setFocus(player.getEntity());
        cameraSystem.setFocus(player.getEntity());
        //labelSystem.setFocus(mCamera.);
        //cameraSystem.setFocus(root.getChild("Text1").getEntity());


        //ItemWrapper lighter1 = root.getChild("lighter1");
        mSceneLoader.addComponentByTagName("lighter0", LighterComponent.class);
        mSceneLoader.addComponentByTagName("light0", LighterComponent.class);
        mSceneLoader.addComponentByTagName("text0", LabelComponent.class);
        mSceneLoader.addComponentByTagName("lighter1", LighterComponent.class);
        mSceneLoader.addComponentByTagName("light1", LighterComponent.class);
        mSceneLoader.addComponentByTagName("lighter2", LighterComponent.class);
        mSceneLoader.addComponentByTagName("light2", LighterComponent.class);
        //lightManager = new LightAnimSystem(mSceneLoader);
        //lightManager.activateLight("light1");
        gameFlag=1;


        //initializePauseMenu();

    }





    @Override
    public void render() {

        super.render();
        if(gameFlag==1) {
            batch.setProjectionMatrix(mCamera.combined);
            batch.begin();

            mCamera.update();
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            mViewport.apply();
            mEngine.process();
            mSentenceMechanic.toTypeSentence.getPosition().y = Gdx.graphics.getHeight() * 0.1f;
            mSentenceMechanic.update();
            stage.getBatch().begin();
            stage.getBatch().end();
            batch.end();
        }else {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            mViewport.apply();
            mEngine.process();
        }

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
        batch.dispose();
        mSentenceMechanic.dispose();
        mAssetManager.dispose();
        mSceneLoader.dispose();
    }

}
