package games.rednblack.candler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import games.rednblack.candler.Managers.LabelManager;
import games.rednblack.candler.Scenes.SceneOne;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SentenceMechanic implements InputProcessor {

    private static final List<String> sentenceStorage = new ArrayList<>();
    SpriteBatch batch;
    private BitmapFont font;
    private String typedSentence;
    public Sentence toTypeSentence;
    private int sentenceStorageIndex;

    public void create(SpriteBatch batch){
        this.batch = batch;
        font = new BitmapFont();
        sentenceStorage.addAll(SentenceLoader.loadSentencesFromCSV());
        toTypeSentence =new Sentence(sentenceStorage.get(0),1);
        typedSentence = "";
        sentenceStorageIndex = 0;
        Gdx.input.setInputProcessor(this);
        font.getData().scaleX = 5;
        font.getData().scaleY = 5;
    }

    public void update(){
        SceneOne sceneOne = SceneOne.getInstance();
        if(sceneOne.light==1) {
            font.setFixedWidthGlyphs(toTypeSentence.toString());
            toTypeSentence.setX((Gdx.graphics.getWidth()-toTypeSentence.toString().length()*13f*font.getSpaceXadvance())/2);
            float currentX = toTypeSentence.getPosition().x;

            for (int i = 0; i < toTypeSentence.getText().length(); i++) {
                GlyphLayout c = new GlyphLayout();

                //Set spacing between letters
                currentX += font.getSpaceXadvance() * 12f;

                //Highlight characters which have been typed
                if (i < toTypeSentence.getHighlightedCharacters()) {
                    if (typedSentence.charAt(i) == toTypeSentence.getText().charAt(i)) {
                        font.setColor(Color.WHITE);
                    } else {
                        font.setColor(Color.RED);
                    }
                    c.setText(font, String.valueOf(toTypeSentence.getText().charAt(i)));
                    if (toTypeSentence.getText().charAt(i) == ' ') {
                        c.setText(font, "_");
                    }
                    font.draw(batch, c, currentX, toTypeSentence.getPosition().y);
                    continue;
                }

                //Render rest characters as green
                font.setColor(Color.ORANGE);
                c.setText(font, String.valueOf(toTypeSentence.getText().charAt(i)));
                font.draw(batch, c, currentX, toTypeSentence.getPosition().y);
            }
        }
    }



    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.ESCAPE) {
            //Gdx.app.exit();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        SceneOne sceneOne = SceneOne.getInstance();
        if(i==Input.Keys.BACKSPACE && !typedSentence.isEmpty() && sceneOne.light==1){
            typedSentence = typedSentence.substring(0,typedSentence.length()-1);
            toTypeSentence.setHighlightedCharacters(toTypeSentence.getHighlightedCharacters()-1);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        SceneOne sceneOne = SceneOne.getInstance();
        if(sceneOne.light==1) {
            if (character == '\b' || typedSentence.length() == toTypeSentence.getText().length()) {

                //hold backspace case
                return false;
            }
            typedSentence = typedSentence.concat(String.valueOf(character));
            if (typedSentence.endsWith(toTypeSentence.toString())) {
                toTypeSentence = new Sentence(sentenceStorage.get(++sentenceStorageIndex), toTypeSentence.getColumn());
                typedSentence = "";
                sceneOne.light = 0;
                sceneOne.scenes.set(sceneOne.scene, 1);
                String textLabel = "text" + String.valueOf(sceneOne.scene) + ".json";
                LabelManager labelManager = LabelManager.getInstance();
                labelManager.setLabelManager(textLabel);
                sceneOne.scene++;
            } else {
                highlightConsecutive(toTypeSentence);
            }
        }
        return true;
    }

    private void highlightConsecutive(Sentence sentence) {
        SceneOne sceneOne = SceneOne.getInstance();
        if (StringUtils.isNotBlank(typedSentence) && sceneOne.light==1) {
            sentence.setHighlightedCharacters(typedSentence.length());
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

