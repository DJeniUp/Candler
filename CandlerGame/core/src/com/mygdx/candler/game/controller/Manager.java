package com.mygdx.candler.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.candler.game.view.GameArtist;
import com.mygdx.candler.game.view.MainMenuArtist;
import com.mygdx.candler.game.view.SettingArtist;

public class Manager extends ApplicationAdapter {
	public SpriteBatch batch;
	public Stage stage;
	public MainMenuArtist mainMenuArtist;
	public GameArtist gameArtist;
	public SettingArtist settingArtist;
	public Locations location;
	@Override
	public void create () {
		location = Locations.MainMenu;
		stage = new Stage();
		batch = (SpriteBatch) stage.getBatch();
		mainMenuArtist = new MainMenuArtist(stage,this);
		gameArtist = new GameArtist(stage,this);
		settingArtist = new SettingArtist(stage,this);
	}

	@Override
	public void render () {
		System.out.println(location);
		if(location==Locations.MainMenu){
			mainMenuArtist.draw();
		}
		if(location==Locations.Game) {
			gameArtist.draw();
		}
		if(location==Locations.Settings){
			settingArtist.draw();
		}
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}

	public void setLocation(Locations location) {
		this.location = location;
	}
}
