package Skeleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuArtist implements Artist{
    Texture backgroundTexture;
    ImageButton playButton;
    Stage stage;
    Manager manager;
    MenuArtist(Manager manageR, Stage stageArg){
        manager=manageR;
        stage = stageArg;
        playButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Button.png"))));
        playButton.setPosition(stage.getWidth()/2-100,stage.getHeight()/2-50);
        backgroundTexture = new Texture(Gdx.files.internal("MenuBg.png"));
        stage.addActor(playButton);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                manager.Location="Game";
            }
        });
    }
    public void draw(){
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().end();
        stage.act();
        stage.draw();
    }
}
