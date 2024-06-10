package games.rednblack.candler.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import games.rednblack.candler.Scenes.SceneOne;
import games.rednblack.candler.components.LighterComponent;
import games.rednblack.editor.renderer.components.MainItemComponent;
import games.rednblack.editor.renderer.components.ParentNodeComponent;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.components.sprite.SpriteAnimationComponent;
import games.rednblack.editor.renderer.components.sprite.SpriteAnimationStateComponent;

@All(LighterComponent.class)
public class Lighter1AnimSystem extends IteratingSystem {

    protected ComponentMapper<LighterComponent>lighter1Mapper;
    protected ComponentMapper<ParentNodeComponent> parentMapper;
    protected ComponentMapper<PhysicsBodyComponent> physicsMapper;
    protected ComponentMapper<SpriteAnimationComponent> spriteMapper;
    protected ComponentMapper<SpriteAnimationStateComponent> spriteStateMapper;
    protected ComponentMapper<TransformComponent> transformMapper;
    protected ComponentMapper<MainItemComponent> mainItemComponentMapper;
    @Override
    protected void process(int entity) {
        ParentNodeComponent nodeComponent = parentMapper.get(entity);

        LighterComponent lighterComponent =lighter1Mapper.get(entity);
        if(lighterComponent.sceneNumber==-1) {
            MainItemComponent mainItemComponent = mainItemComponentMapper.get(entity);
            String tag = mainItemComponent.tags.first();
            int sceneNumber = tag.charAt(tag.length()-1)-'0';
            lighterComponent.sceneNumber = sceneNumber;
        }
        SpriteAnimationComponent spriteAnimationComponent =spriteMapper.get(entity);
        SpriteAnimationStateComponent spriteAnimationStateComponent=spriteStateMapper.get(entity);
        SceneOne sceneOne=SceneOne.getInstance();
        if(spriteAnimationComponent!=null && sceneOne.scenes.get(lighterComponent.sceneNumber)==1){
            spriteAnimationComponent.currentAnimation="light";
            spriteAnimationStateComponent.set(spriteAnimationComponent);
        }
    }
}
