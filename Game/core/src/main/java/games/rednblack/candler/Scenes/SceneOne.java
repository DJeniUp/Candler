package games.rednblack.candler.Scenes;

import com.artemis.PooledComponent;

import java.util.ArrayList;

public class SceneOne {
    private static SceneOne INSTANCE;
    public int light,scene;
    public ArrayList<Integer>scenes;
    public ArrayList<Float>time;

    private SceneOne(){
        light=0;
        scene=0;
        scenes=new ArrayList<Integer>();
        time= new ArrayList<Float>();
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
}
