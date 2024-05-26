package com.mygdx.candler.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
public class MainMenuArtist implements Artist{
    Stage stage;
    Manager manager;
    public MainMenuArtist(Stage stage, Manager manager){
        this.stage=stage;
        this.manager=manager;
    }

    @Override
    public void draw() {
    }
}
