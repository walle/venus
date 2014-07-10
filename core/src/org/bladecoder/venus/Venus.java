package org.bladecoder.venus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.bladecoder.bladeengine.model.World;
import org.bladecoder.bladeengine.ui.UI;

public class Venus extends ApplicationAdapter {
  private UI ui;

	@Override
	public void create () {
    ui = new UI();
	}

	@Override
	public void render () {
    ui.render();
	}

  @Override
  public void resize(int width, int height) {
    ui.resize(width, height);
  }

  @Override
  public void pause() {
    ui.pause();
    World.getInstance().saveGameState();
  }

  @Override
  public void resume() {
    ui.resume();
  }

  @Override
  public void dispose() {
    World.getInstance().dispose();
    ui.dispose();
  }

}
