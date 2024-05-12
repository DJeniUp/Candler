package games.rednblack.candler.scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.scripts.BasicScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class PlayerScript extends BasicScript {
    protected com.artemis.World mEngine;
    private final Vector2 impulse = new Vector2(0, 0);
    private final Vector2 speed = new Vector2(0, 0);

    private PhysicsBodyComponent mPhysicsBodyComponent;

    private int animEntity;
    @Override
    public void init(int item) {
        super.init(item);

    }
    @Override
    public void act(float v) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            Body body = mPhysicsBodyComponent.body;
            speed.set(body.getLinearVelocity());
            impulse.set(-5, 0);

            body.applyLinearImpulse(impulse.sub(speed), body.getWorldCenter(), true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

            Body body = mPhysicsBodyComponent.body;
            speed.set(body.getLinearVelocity());
            impulse.set(5, 0);

            body.applyLinearImpulse(impulse.sub(speed), body.getWorldCenter(), true);
        }
    }

    @Override
    public void dispose() {

    }

}
