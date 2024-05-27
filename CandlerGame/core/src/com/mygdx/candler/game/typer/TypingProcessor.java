package com.mygdx.candler.game.typer;

import com.badlogic.gdx.InputProcessor;

public interface TypingProcessor extends InputProcessor {
    @Override
    default public boolean keyDown(int i) {
        return false;
    }

    @Override
    default public boolean keyUp(int i) {
        return false;
    }

    @Override
    default public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    default public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    default public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    default public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    default public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    default boolean scrolled(float v, float v1){
        return false;
    };

    @Override
    boolean keyTyped(char c);
}
