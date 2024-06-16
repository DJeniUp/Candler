package games.rednblack.candler.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import games.rednblack.candler.Scenes.SceneOne;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;

@All(ViewPortComponent.class)
public class CameraSystem extends IteratingSystem {

    protected ComponentMapper<TransformComponent> transformMapper;
    protected ComponentMapper<ViewPortComponent> viewportMapper;

    private int focus = -1;
    private int zoomflag=0;
    private float neededZoom=1.0f;

    private Vector3 mVector3 = new Vector3();

    public CameraSystem() {
    }

    @Override
    protected void process(int entity) {
        ViewPortComponent viewPortComponent = viewportMapper.get(entity);
        OrthographicCamera camera = (OrthographicCamera) viewPortComponent.viewPort.getCamera();
        //System.out.println(camera.zoom);
        SceneOne sceneOne = SceneOne.getInstance();
        if(sceneOne.light==1 && zoomflag == 0){

            neededZoom+=1;
            zoomflag=1;
        }else if(zoomflag==1 && sceneOne.light==0){
            zoomflag=0;
            neededZoom-=1;
        }
        if(neededZoom-camera.zoom>0.01f){
            camera.zoom+=0.01f;
        }else if(neededZoom< camera.zoom){
            camera.zoom-=0.004f;
        }

        if (focus != -1) {
            TransformComponent transformComponent = transformMapper.get(focus);

            if (transformComponent != null) {

                float x = transformComponent.x+10;
                float y = transformComponent.y + 10;


                mVector3.set(x, y, 0);
                camera.position.lerp(mVector3, 0.1f);
            }
        }
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }
}
