package games.rednblack.candler.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import games.rednblack.candler.Label;
import com.badlogic.gdx.files.FileHandle;
import games.rednblack.candler.Scenes.SceneOne;

public class LabelManager {
    private Array<Label> subtitles;
    private float elapsedTime;
    private static LabelManager INSTANCE;
    public static LabelManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new LabelManager();
        }return INSTANCE;
    }
    public LabelManager(){

    }
    public void setLabelManager(String subtitleFile){
        FileHandle fileHandle = Gdx.files.internal(subtitleFile);
        Json json = new Json();
        subtitles = json.fromJson(Array.class, Label.class,fileHandle);
        elapsedTime=0f;
    }
    public void update(float deltaTime){
        elapsedTime+=deltaTime;
    }

    public String getCurrentSubtitle(){
        SceneOne sceneOne = SceneOne.getInstance();
        String returnedText=null;
        if(subtitles!=null) {
            for (Label label : subtitles) {
                if (elapsedTime >= label.time) {
                    returnedText = label.text;
                }
            }
        }
        return returnedText;
    }
    public void reset(){
        elapsedTime=0f;
    }
}
