package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.EaglerInputStream;
import net.lax1dude.eaglercraft.Random;
import net.lax1dude.eaglercraft.internal.EnumPlatformType;
import net.lax1dude.eaglercraft.internal.IAudioCacheLoader;
import net.lax1dude.eaglercraft.internal.IAudioHandle;
import net.lax1dude.eaglercraft.internal.IAudioResource;
import net.lax1dude.eaglercraft.internal.PlatformAudio;
import net.peyton.eagler.minecraft.AudioUtils;

public class SoundManager {
	private GameSettings options;
	private Random rand = new Random();
	private int ticksBeforeMusic = this.rand.nextInt(12000);

	private Map<String, IAudioResource> sounds = new HashMap<String, IAudioResource>();
	private Map<String, IAudioResource> music = new HashMap<String, IAudioResource>();

	private IAudioHandle musicHandle;

	private String[] newMusic = new String[]{"hal1.ogg", "hal2.ogg", "hal3.ogg", "hal4.ogg", "nuance1.ogg", "nuance2.ogg", "piano1.ogg", "piano2.ogg", "piano3.ogg"};

	public void loadSoundSettings(GameSettings var1) {
		this.options = var1;
	}

	public void onSoundOptionsChanged() {


	}

	public void closeMinecraft() {
	}

	public void playRandomMusicIfReady() {

	}

	public void setListener(EntityLiving var1, float var2) {

	}

	public void playSound(String var1, float var2, float var3, float var4, float var5, float var6) {

	}

	public void playSoundFX(String var1, float var2, float var3) {

	}


}
