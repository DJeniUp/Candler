package games.rednblack.candler.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Body;
import games.rednblack.candler.Scenes.SceneOne;
import games.rednblack.candler.components.PlayerComponent;
import games.rednblack.editor.renderer.components.ParentNodeComponent;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.components.sprite.SpriteAnimationComponent;
import games.rednblack.editor.renderer.components.sprite.SpriteAnimationStateComponent;

@All(PlayerComponent.class)
public class PlayerAnimSystem extends IteratingSystem {

    protected ComponentMapper<ParentNodeComponent> parentMapper;
    protected ComponentMapper<PhysicsBodyComponent> physicsMapper;
    protected ComponentMapper<PlayerComponent> playerMapper;
    protected ComponentMapper<SpriteAnimationComponent> spriteMapper;
    protected ComponentMapper<SpriteAnimationStateComponent> spriteStateMapper;
    protected ComponentMapper<TransformComponent> transformMapper;
    @Override
    protected void process(int entity) {
        ParentNodeComponent nodeComponent = parentMapper.get(entity);
        Body body = physicsMapper.get(nodeComponent.parentEntity).body;
        SceneOne sceneOne = SceneOne.getInstance();

        if (body == null)
            return;

        PlayerComponent playerComponent = playerMapper.get(entity);
        SpriteAnimationComponent spriteAnimationComponent = spriteMapper.get(entity);
        SpriteAnimationStateComponent spriteAnimationStateComponent = spriteStateMapper.get(entity);
        TransformComponent transformComponent = transformMapper.get(entity);
        if(sceneOne.light==1){
            spriteAnimationComponent.currentAnimation="magic";
        }else{
            spriteAnimationComponent.currentAnimation="standart";
        }

        if (Math.abs(body.getLinearVelocity().x) > 0.1f) {

            transformComponent.flipX = body.getLinearVelocity().x < 0;
        }
        spriteAnimationStateComponent.set(spriteAnimationComponent);
        TransformComponent playerTr = transformMapper.get(nodeComponent.parentEntity);
        if(sceneOne.pause==0) {
            sceneOne.x = playerTr.x;
            sceneOne.y = playerTr.y;
        }
        //System.out.println(sceneOne.x+ " "+ sceneOne.y + " " + transformComponent.x + " " + transformComponent.y);

    }
}
