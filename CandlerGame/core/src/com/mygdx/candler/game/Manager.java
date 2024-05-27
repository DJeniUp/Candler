package com.mygdx.candler.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Objects;

public class Manager extends ApplicationAdapter {
	public SpriteBatch batch;
	public Stage stage;
	public MainMenuArtist mainMenuArtist;
	public GameArtist gameArtist;
	public String location;
	@Override
	public void create () {
		location = "MainMenu";
		stage = new Stage();
		batch = (SpriteBatch) stage.getBatch();
		mainMenuArtist = new MainMenuArtist(stage,this);
		gameArtist = new GameArtist(stage,this);
	}

	@Override
	public void render () {
		if(Objects.equals(location, "MainMenu")){
			mainMenuArtist.draw();
		}
		if(Objects.equals(location, "Game")) {
			gameArtist.draw();
		}
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}

	public void setLocation(String location) {

		this.location = location;
	}
}
