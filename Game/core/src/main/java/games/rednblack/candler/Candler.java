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
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import org.apache.commons.lang3.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private static final List<String> possibleSentences = new ArrayList<>();
    SpriteBatch batch;
    private BitmapFont font;
    private String currentSentence;
    public Sentence sentence;
    private int position;

    private static ArrayList<String> loadSentencesFromCSV() {
        ArrayList<String> sentences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/sentences.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String sentence = StringUtils.trim(line).toLowerCase();
                sentences.add(sentence);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sentences;
    }

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        possibleSentences.addAll(loadSentencesFromCSV());
        sentence=new Sentence(possibleSentences.get(0),1);
        currentSentence = "";
        position = 0;
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

        mSceneLoader = new SceneLoader(config);
        mEngine = mSceneLoader.getEngine();

        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(60, 32, mCamera);

        mSceneLoader.loadScene("MainScene", mViewport);

        ItemWrapper root = new ItemWrapper(mSceneLoader.getRoot(), mEngine);
    }


    @Override
    public void render() {
//        mCamera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mViewport.apply();
        mEngine.process();



        batch.begin();
        font.setFixedWidthGlyphs(sentence.toString());
        float currentX = sentence.getPosition().x;

        for (int i = 0; i < sentence.getText().length(); i++) {
            GlyphLayout c = new GlyphLayout();

            //Set spacing between letters
            currentX += font.getSpaceXadvance() * 4.5f;

            //Highlight characters which have been typed
            if (i < sentence.getHighlightedCharacters()) {
                font.setColor(Color.WHITE);
                c.setText(font, String.valueOf(sentence.getText().charAt(i)));
                font.draw(batch, c, currentX, sentence.getPosition().y);
                continue;
            }

            //Render rest characters as green
            font.setColor(Color.ORANGE);
            c.setText(font, String.valueOf(sentence.getText().charAt(i)));
            font.draw(batch, c, currentX, sentence.getPosition().y);
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
        currentSentence = currentSentence.concat(String.valueOf(character));
        ArrayList<Sentence> wordsToRemove = new ArrayList<>();
        ArrayList<Sentence> wordsToAdd = new ArrayList<>();
        if(currentSentence.endsWith(sentence.toString())){
            Sentence newSentence=new Sentence(possibleSentences.get(++position),sentence.getColumn());
            sentence=newSentence;
        }else{
            highlightConsecutive(sentence);
        }
        return true;
    }

    private void highlightConsecutive(Sentence sentence) {
        if (StringUtils.isNotBlank(currentSentence)) {
            String text = sentence.getText();
            int matching = 0;
            int maxIndex = Math.min(text.length(), currentSentence.length());

            for (int i = 1; i < maxIndex + 1; i++) {
                if (currentSentence.endsWith(text.substring(0, i))) {
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
