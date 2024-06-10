package games.rednblack.candler.system;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import games.rednblack.candler.Scenes.SceneOne;
import games.rednblack.candler.components.LighterComponent;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.box2dLight.Light;
import games.rednblack.editor.renderer.components.MainItemComponent;
import games.rednblack.editor.renderer.components.light.LightBodyComponent;
import games.rednblack.editor.renderer.components.light.LightObjectComponent;

@All({LighterComponent.class, LightObjectComponent.class})
public class LightAnimSystem extends IteratingSystem {
    protected ComponentMapper<LightObjectComponent> lightObjectComponentMapper;
    protected ComponentMapper<LighterComponent>lighter1Mapper;
    protected ComponentMapper<LightBodyComponent> lightBodyComponentMapper;
    protected ComponentMapper<MainItemComponent> mainItemComponentMapper;
    private World world;
    private SceneLoader sceneLoader;
    private com.artemis.World mEngine;


    @Override
    protected void process(int entity) {
        LightObjectComponent lightObjectComponent=lightObjectComponentMapper.get(entity);
        Light light = lightObjectComponent.lightObject;
        LighterComponent lighterComponent =lighter1Mapper.get(entity);
        if(lighterComponent.sceneNumber==-1) {
            MainItemComponent mainItemComponent = mainItemComponentMapper.get(entity);
            String tag = mainItemComponent.tags.first();
            int sceneNumber = tag.charAt(tag.length()-1)-'0';
            lighterComponent.sceneNumber = sceneNumber;
        }
        SceneOne sceneOne=SceneOne.getInstance();
        if(light!=null && sceneOne.scenes.get(lighterComponent.sceneNumber)==1){

            light.setActive(true);
        }
    }
}
