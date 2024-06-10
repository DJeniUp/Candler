package games.rednblack.candler.system;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import games.rednblack.candler.Managers.LabelManager;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.label.LabelComponent;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import com.badlogic.ashley.core.EntitySystem;

public class LabelSystem extends BaseSystem {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final LabelManager labelManager;
    public LabelSystem(SpriteBatch batch, BitmapFont font, LabelManager labelManager){
        this.batch = batch;
        this.font = font;
        this.labelManager = labelManager;
    }


    //@Override


    @Override
    protected void processSystem() {
        labelManager.update(world.getDelta());
        String labelText = labelManager.getCurrentSubtitle();
        if(labelText!=null){
            //System.out.println("TEXT");
            System.out.println(labelText);
           // batch.begin();
            float screenWidth = Gdx.graphics.getWidth();
            float screenHeight = Gdx.graphics.getHeight();
            float textWidth = font.getRegion().getRegionWidth();
            float textHeight = font.getCapHeight();
            float x = (screenWidth - textWidth)/2;
            float y = screenHeight/2;
            font.draw(batch, labelText, x, y);
           // batch.end();
        }
    }
}
