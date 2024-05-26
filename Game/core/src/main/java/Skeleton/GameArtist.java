package Skeleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameArtist implements Artist{
    private Music backgroundMusic;
    GameArtist(){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }
    @Override
    public void draw(){

    };
}
