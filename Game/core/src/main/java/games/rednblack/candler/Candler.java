package games.rednblack.candler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import games.rednblack.candler.scripts.PlayerScript;
import games.rednblack.candler.system.CameraSystem;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import org.apache.commons.lang3.*;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Candler extends ApplicationAdapter implements InputProcessor {
    private AssetManager mAssetManager;

    private SceneLoader mSceneLoader;
    private AsyncResourceManager mAsyncResourceManager;

    private Viewport mViewport;
    private OrthographicCamera mCamera;

    private com.artemis.World mEngine;

    private ExtendViewport mHUDViewport;

    private static final List<String> sentenceStorage = new ArrayList<>();
    SpriteBatch batch;
    private BitmapFont font;
    private String typedSentence;
    public Sentence toTypeSentence;
    private int sentenceStorageIndex;
    CandlerCharacter candle=null;
    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        sentenceStorage.addAll(SentenceLoader.loadSentencesFromCSV());
        toTypeSentence =new Sentence(sentenceStorage.get(0),1);
        typedSentence = "";
        sentenceStorageIndex = 0;
        Gdx.input.setInputProcessor(this);

        font.getData().scaleX = 2;
        font.getData().scaleY = 2;

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
        player.addScript(playerScript);
        cameraSystem.setFocus(player.getEntity());
    }


    @Override
    public void render() {
//        mCamera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mViewport.apply();
        mEngine.process();



        batch.begin();
        font.setFixedWidthGlyphs(toTypeSentence.toString());
        float currentX = toTypeSentence.getPosition().x;

        for (int i = 0; i < toTypeSentence.getText().length(); i++) {
            GlyphLayout c = new GlyphLayout();

            //Set spacing between letters
            currentX += font.getSpaceXadvance() * 4.5f;

            //Highlight characters which have been typed
            if (i < toTypeSentence.getHighlightedCharacters()) {
                font.setColor(Color.WHITE);
                c.setText(font, String.valueOf(toTypeSentence.getText().charAt(i)));
                font.draw(batch, c, currentX, toTypeSentence.getPosition().y);
                continue;
            }

            //Render rest characters as green
            font.setColor(Color.ORANGE);
            c.setText(font, String.valueOf(toTypeSentence.getText().charAt(i)));
            font.draw(batch, c, currentX, toTypeSentence.getPosition().y);
        }
        batch.end();
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
        batch.dispose();
        font.dispose();
        mAssetManager.dispose();
        mSceneLoader.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        typedSentence = typedSentence.concat(String.valueOf(character));
        if(typedSentence.endsWith(toTypeSentence.toString())){
            toTypeSentence = new Sentence(sentenceStorage.get(++sentenceStorageIndex), toTypeSentence.getColumn());
        }else{
            highlightConsecutive(toTypeSentence);
        }
        return true;
    }

    private void highlightConsecutive(Sentence sentence) {
        if (StringUtils.isNotBlank(typedSentence)) {
            String text = sentence.getText();
            int matching = 0;
            int maxIndex = Math.min(text.length(), typedSentence.length());

            for (int i = 1; i < maxIndex + 1; i++) {
                if (typedSentence.endsWith(text.substring(0, i))) {
                    matching = i;
                }
            }
            sentence.setHighlightedCharacters(matching);
        }
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
