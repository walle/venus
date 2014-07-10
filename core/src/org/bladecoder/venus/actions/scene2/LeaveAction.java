package org.bladecoder.venus.actions.scene2;

import java.util.HashMap;

import org.bladecoder.bladeengine.BladeEngine;
import org.bladecoder.bladeengine.actions.Action;
import org.bladecoder.bladeengine.actions.Param;
import org.bladecoder.bladeengine.model.World;
import org.bladecoder.bladeengine.ui.UI;
import org.bladecoder.bladeengine.util.EngineLogger;

import com.badlogic.gdx.Gdx;
import org.bladecoder.venus.Venus;

public class LeaveAction implements Action {
	public static final String INFO = "Restarts the game";
	public static final Param[] PARAMS = {};

	@Override
	public void setParams(HashMap<String, String> params) {
	}

	@Override
	public void run() {
		try {
			World.restart();
			
			UI ui = BladeEngine.getAppUI();
			
			World.getInstance().pause();
			
			ui.setScreen(UI.State.CREDIT_SCREEN);
		} catch (Exception e) {
			EngineLogger.error("ERROR LOADING GAME", e);

			World.getInstance().dispose();
			Gdx.app.exit();
		}
	}

	@Override
	public String getInfo() {
		return INFO;
	}

	@Override
	public Param[] getParams() {
		return PARAMS;
	}
}
