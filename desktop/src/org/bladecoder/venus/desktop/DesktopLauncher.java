package org.bladecoder.venus.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.bladecoder.bladeengine.BladeEngine;
import org.bladecoder.bladeengine.model.World;
import org.bladecoder.bladeengine.util.Config;
import org.bladecoder.venus.Venus;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Properties;

public class DesktopLauncher extends BladeEngine {

  private boolean fullscreen = false;
  private LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

  DesktopLauncher() {
    Properties p = new Properties();

    try {
      InputStream s = DesktopLauncher.class.getResourceAsStream("/" + Config.PROPERTIES_FILENAME);
      if(s!=null)
        p.load(s);
    } catch (IOException e) {
    }

    cfg.title = p.getProperty(Config.TITLE_PROP, "Venus");
//		cfg.useGL30 = true;

    //cfg.width = World.getInstance().getWidth();
    //cfg.height = World.getInstance().getHeight();

    cfg.width = 1920 / 2;
    cfg.height = 1080 / 2;

    cfg.resizable = true;
    cfg.samples = 2;
  }

  public void run() {
    if(DesktopLauncher.class.getResource("/icons/icon128.png")!=null)
      cfg.addIcon("icons/icon128.png", Files.FileType.Internal);

    if(DesktopLauncher.class.getResource("/icons/icon32.png")!=null)
      cfg.addIcon("icons/icon32.png", Files.FileType.Internal);

    if(DesktopLauncher.class.getResource("/icons/icon16.png")!=null)
      cfg.addIcon("icons/icon16.png", Files.FileType.Internal);

    new LwjglApplication(this, cfg);
  }

  @Override
  public void create() {
    // Gdx.input.setCursorCatched(false);
    if (fullscreen)
      Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode());

    hideCursor();

    super.create();
  }

  private void hideCursor() {
    Cursor emptyCursor;

    int min = Cursor.getMinCursorSize();
    IntBuffer tmp = BufferUtils.createIntBuffer(min * min);
    try {
      emptyCursor = new Cursor(min, min, min / 2,
        min / 2, 1, tmp, null);

      Mouse.setNativeCursor(emptyCursor);
    } catch (LWJGLException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    DesktopLauncher game = new DesktopLauncher();
    game.run();
  }
}

