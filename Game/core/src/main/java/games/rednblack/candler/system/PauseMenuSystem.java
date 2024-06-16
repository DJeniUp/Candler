package games.rednblack.candler.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import games.rednblack.candler.components.PauseMenuComponent;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.physics.PhysicsContact;

@All(PauseMenuComponent.class)
public class PauseMenuSystem extends IteratingSystem{

    private int focus=-1;
    protected ComponentMapper<PhysicsBodyComponent> physicsMapper;
    protected ComponentMapper<TransformComponent> transformMapper;
    private PhysicsBodyComponent mPhysicsBodyComponent;
    @Override
    protected void process(int entity) {
        TransformComponent pauseMenuTC = transformMapper.get(entity);
        if(focus!=-1){
            TransformComponent transformComponent = transformMapper.get(focus);

            if (transformComponent != null) {

                float x = transformComponent.x+10;
                float y = transformComponent.y + 10;
                pauseMenuTC.x=x;
                pauseMenuTC.y=y;

            }
        }
    }

    public void setFocus(int i){
        focus=i;
    }


}
