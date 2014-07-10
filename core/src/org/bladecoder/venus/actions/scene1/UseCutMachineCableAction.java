package org.bladecoder.venus.actions.scene1;

import java.util.HashMap;

import org.bladecoder.bladeengine.actions.Action;
import org.bladecoder.bladeengine.actions.Param;
import org.bladecoder.bladeengine.actions.Param.Type;
import org.bladecoder.bladeengine.model.Actor;
import org.bladecoder.bladeengine.model.SpriteActor;
import org.bladecoder.bladeengine.model.Text;
import org.bladecoder.bladeengine.model.TextManager;
import org.bladecoder.bladeengine.model.World;
import org.bladecoder.bladeengine.util.EngineLogger;

import com.badlogic.gdx.graphics.Color;

public class UseCutMachineCableAction implements Action {
	public static final String INFO = "Action for using the 'cut_machine' with the 'cable'";
	public static final Param[] PARAMS = {
		new Param("cut_cable_text", "Text to show when attach the cable to the cut_machine", Type.STRING),
		new Param("default_cable_text", "Text to show when the cable can not be attached to the cut_machine", Type.STRING)
		};
	
	private String cutCableText;
	private String defaultCableText;
	private String actorId;

	@Override
	public void run() {
		SpriteActor actor = (SpriteActor)World.getInstance().getCurrentScene().getActor(actorId);
		Actor target = World.getInstance().getCurrentScene().getActor("cable");

		EngineLogger.debug("USING " + actor.getDesc() + " IN " + target.getDesc());

		if (target.getState().equals("CUT") && actor.getState().equals("NO_BATTERY")) {
			World.getInstance().getTextManager().addSubtitle(cutCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
			actor.setState("WITH_CABLE");
			actor.startFrameAnimation("cutter.withcable", null);
			actor.playSound("click");
			World.getInstance().getInventory().removeItem((SpriteActor) target);
		} else {
			World.getInstance().getTextManager().addSubtitle(defaultCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
		}
	}

	@Override
	public void setParams(HashMap<String, String> params) {
		actorId = params.get("actor");

		cutCableText = params.get("cut_cable_text");
		defaultCableText = params.get("default_cable_text");
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
