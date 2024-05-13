package games.rednblack.candler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        font.getData().scaleX = 2;
        font.getData().scaleY = 2;
    }

    public void update(){
        batch.begin();
        font.setFixedWidthGlyphs(toTypeSentence.toString());
        float currentX = toTypeSentence.getPosition().x;

        for (int i = 0; i < toTypeSentence.getText().length(); i++) {
            GlyphLayout c = new GlyphLayout();

            //Set spacing between letters
            currentX += font.getSpaceXadvance() * 4.5f;

            //Highlight characters which have been typed
            if (i < toTypeSentence.getHighlightedCharacters()) {
                if(typedSentence.charAt(i)==toTypeSentence.getText().charAt(i)){
                    font.setColor(Color.WHITE);
                }else{
                    font.setColor(Color.RED);
                }
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

    public void dispose(){
        batch.dispose();
        font.dispose();
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
        if(i==Input.Keys.BACKSPACE && !typedSentence.isEmpty()){
            typedSentence = typedSentence.substring(0,typedSentence.length()-1);
            toTypeSentence.setHighlightedCharacters(toTypeSentence.getHighlightedCharacters()-1);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if(character=='\b' || typedSentence.length()==toTypeSentence.getText().length()){
            //hold backspace case
            return false;
        }
        typedSentence = typedSentence.concat(String.valueOf(character));
        if(typedSentence.endsWith(toTypeSentence.toString())){
            toTypeSentence = new Sentence(sentenceStorage.get(++sentenceStorageIndex), toTypeSentence.getColumn());
            typedSentence="";
        }else{
            highlightConsecutive(toTypeSentence);
        }
        return true;
    }

    private void highlightConsecutive(Sentence sentence) {
        if (StringUtils.isNotBlank(typedSentence)) {
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

