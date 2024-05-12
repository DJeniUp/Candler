package games.rednblack.candler;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
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
import java.time.LocalTime;
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

    private static final List<String> possibleWords = new ArrayList<>();
    final Random random = new Random();
    SpriteBatch batch;
    private BitmapFont font;
    private List<Word> words;
    private String currentWord;

    private static ArrayList<String> loadWordsFromCSV() {
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/words.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String word = StringUtils.trim(columns[0]).toLowerCase();
                words.add(word);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return words;
    }

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        words = new ArrayList<>();
        words = Collections.synchronizedList(words);
        possibleWords.addAll(loadWordsFromCSV());
        currentWord = "";
        Gdx.input.setInputProcessor(this);

        font.getData().scaleX = 2;
        font.getData().scaleY = 2;

        //fill words on the screen
        for (int i = 0; i < 5; i++) {
            addWord(possibleWords.get(random.nextInt(possibleWords.size())), i);
        }
        for (Word word : words) {
            word.setText(word.getText().trim().toLowerCase());
        }

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

    private void addWord(String text,int column){
        words.add(new Word(text, column));
    }

    @Override
    public void render() {
//        mCamera.update();
        mViewport.apply();
        mEngine.process();


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int w = 0; w < words.size(); w++) {

            Word word = words.get(w);
            font.setFixedWidthGlyphs(word.toString());
            float currentX = word.getPosition().x;

            for (int i = 0; i < word.getText().length(); i++) {
                GlyphLayout c = new GlyphLayout();

                //Set spacing between letters
                currentX += font.getSpaceXadvance() * 4.5f;

                //Highlight characters which have been typed
                if (i < word.getHighlightedCharacters()) {
                    font.setColor(Color.WHITE);
                    c.setText(font, String.valueOf(word.getText().charAt(i)));
                    font.draw(batch, c, currentX, word.getPosition().y);
                    continue;
                }

                //Render rest characters as green
                font.setColor(Color.GREEN);
                c.setText(font, String.valueOf(word.getText().charAt(i)));
                font.draw(batch, c, currentX, word.getPosition().y);
            }
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
        currentWord = currentWord.concat(String.valueOf(character));
        ArrayList<Word> wordsToRemove = new ArrayList<>();
        ArrayList<Word> wordsToAdd = new ArrayList<>();

        for (Word word : words) {
            if (currentWord.endsWith(word.toString())) {
                wordsToRemove.add(word);
                wordsToAdd.add(new Word(possibleWords.get(random.nextInt(possibleWords.size())), word.getColumn()));
                currentWord = currentWord.substring(currentWord.length() - word.getText().length());
            } else {
                highlightConsecutive(word);
            }
        }
        words.removeAll(wordsToRemove);
        words.addAll(wordsToAdd);
        return true;
    }

    private void highlightConsecutive(Word word) {
        if (StringUtils.isNotBlank(currentWord)) {
            String text = word.getText();
            int matching = 0;

            int maxIndex = Math.min(text.length(), currentWord.length());

            for (int i = 1; i < maxIndex + 1; i++) {
                if (currentWord.endsWith(text.substring(0, i))) {
                    matching = i;
                }
            }
            word.setHighlightedCharacters(matching);
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
