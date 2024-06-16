package games.rednblack.candler.Scenes;

import com.artemis.PooledComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

public class SceneOne {
    private static SceneOne INSTANCE;
    public float x=-131.40f,y=8.10f;
    public int pause;
    public int light,scene;
    public ArrayList<Integer>scenes;
    public ArrayList<Float>time;

    private SceneOne(){
        light=0;
        scene=0;
        scenes=new ArrayList<Integer>();
        time= new ArrayList<Float>();
        pause=0;
        for(int i=0;i<9;i++){
            scenes.add(0);
            time.add(0f);
        }
    }

    public static SceneOne getInstance(){
        if(INSTANCE==null){
            INSTANCE = new SceneOne();
        }
        return INSTANCE;
    }
    public void save() {
        Preferences prefs = Gdx.app.getPreferences("SceneOnePreferences");
        prefs.putFloat("x", x);
        prefs.putFloat("y", y);
        prefs.putInteger("pause", pause);
        prefs.putInteger("light", light);
        prefs.putInteger("scene", scene);

        for (int i = 0; i < scenes.size(); i++) {
            prefs.putInteger("scene_" + i, scenes.get(i));
            prefs.putFloat("time_" + i, time.get(i));
        }

        prefs.flush();
        System.out.println("Гру збережено!");
    }

    public void load() {
        Preferences prefs = Gdx.app.getPreferences("SceneOnePreferences");
        x = prefs.getFloat("x", -131.40f);
        y = prefs.getFloat("y", 8.10f);
        pause = prefs.getInteger("pause", 0);
        light = prefs.getInteger("light", 0);
        scene = prefs.getInteger("scene", 0);

        for (int i = 0; i < scenes.size(); i++) {
            scenes.set(i, prefs.getInteger("scene_" + i, 0));
            time.set(i, prefs.getFloat("time_" + i, 0f));
        }

        System.out.println("Гру завантажено!");
    }
}
