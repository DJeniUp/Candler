package games.rednblack.candler.scripts;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import games.rednblack.candler.Scenes.SceneOne;
import games.rednblack.candler.components.LighterComponent;
import games.rednblack.candler.components.PlayerComponent;
import games.rednblack.editor.renderer.components.DimensionsComponent;
import games.rednblack.editor.renderer.components.MainItemComponent;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.components.sprite.SpriteAnimationComponent;
import games.rednblack.editor.renderer.physics.PhysicsContact;
import games.rednblack.editor.renderer.scripts.BasicScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class PlayerScript extends BasicScript implements PhysicsContact {

    protected com.artemis.World mEngine;
    protected ComponentMapper<PhysicsBodyComponent> physicsMapper;
    protected ComponentMapper<TransformComponent> transformMapper;
    protected ComponentMapper<PlayerComponent> playerMapper;
    protected ComponentMapper<MainItemComponent> mainItemMapper;
    protected ComponentMapper<DimensionsComponent> dimensionsMapper;
    protected ComponentMapper<LighterComponent> lighter1Mapper;

    public static final int LEFT = 1;
    public static final int RIGHT = -1;
    public static final int JUMP = 0;

    private int animEntity;
    private PhysicsBodyComponent mPhysicsBodyComponent;

    private final Vector2 impulse = new Vector2(0, 0);
    private final Vector2 speed = new Vector2(0, 0);

    @Override
    public void init(int item) {
        super.init(item);

        ItemWrapper itemWrapper = new ItemWrapper(item, mEngine);
        animEntity = itemWrapper.getChild("player-anim").getEntity();

        mPhysicsBodyComponent = physicsMapper.get(item);
        TransformComponent transformComponent = transformMapper.get(item);
        SceneOne sceneOne = SceneOne.getInstance();
        Body body = mPhysicsBodyComponent.body;
        body.setTransform(sceneOne.x,sceneOne.y+1f,0);
        sceneOne.pause=0;
    }

    @Override
    public void act(float delta) {
        SceneOne sceneOne = SceneOne.getInstance();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && sceneOne.light==0) {
            movePlayer(LEFT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && sceneOne.light==0) {
            movePlayer(RIGHT);
        }

    }

    public void movePlayer(int direction) {
        Body body = mPhysicsBodyComponent.body;

        speed.set(body.getLinearVelocity());

        switch (direction) {
            case LEFT:
                impulse.set(-60, speed.y);
                break;
            case RIGHT:
                impulse.set(60, speed.y);
                break;
        }
        body.applyLinearImpulse(impulse.sub(speed), body.getWorldCenter(), true);

    }


    @Override
    public void dispose() {

    }
    protected ComponentMapper<SpriteAnimationComponent> spriteMapper;

    @Override
    public void beginContact(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {
        //MainItemComponent mainItemComponent = mainItemMapper.get(contactEntity);
        //PlayerComponent playerComponent = playerMapper.get(animEntity);
        //System.out.println(playerComponent.x);
        //SpriteAnimationComponent spriteAnimationComponent = spriteMapper.get(contactEntity);
        LighterComponent lighterComponent = lighter1Mapper.get(contactEntity);
        SceneOne sceneOne = SceneOne.getInstance();
        if(lighterComponent !=null &&  lighterComponent.sceneNumber== sceneOne.scene){

            sceneOne.light=1;
            //ItemWrapper
            //Entity lightEnt= CandlerGame.root.getChild("light1").getEntity();
            //mEngine.delete(contactEntity);
            //System.out.println("YES\n");
        }
    }

    @Override
    public void endContact(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void preSolve(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }

    @Override
    public void postSolve(int contactEntity, Fixture contactFixture, Fixture ownFixture, Contact contact) {

    }
}


