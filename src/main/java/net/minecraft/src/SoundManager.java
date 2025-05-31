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
	private static final float DEFAULT_MUSIC_VOLUME = 0.7F;
	private static final float DEFAULT_SOUND_VOLUME = 0.8F;

	private Random rand = new Random();
	private int ticksBeforeMusic = this.rand.nextInt(12000);

	private Map<String, IAudioResource> sounds = new HashMap<String, IAudioResource>();
	private Map<String, IAudioResource> music = new HashMap<String, IAudioResource>();

	private IAudioHandle musicHandle;

	private String[] newMusic = new String[]{"hal1.ogg", "hal2.ogg", "hal3.ogg", "hal4.ogg", "nuance1.ogg", "nuance2.ogg", "piano1.ogg", "piano2.ogg", "piano3.ogg"};

	public void loadSoundSettings() {
	}

	public void onSoundOptionsChanged() {
		if(DEFAULT_MUSIC_VOLUME == 0.0F) {
			if(this.musicHandle != null && !this.musicHandle.shouldFree()) {
				musicHandle.end();
			}
		} else {
			if(this.musicHandle != null && !this.musicHandle.shouldFree()) {
				musicHandle.gain(DEFAULT_MUSIC_VOLUME);
			}
		}
	}

	public void closeMinecraft() {
	}

	public void playRandomMusicIfReady() {
		if(DEFAULT_MUSIC_VOLUME != 0.0F) {
			if(this.musicHandle == null || this.musicHandle.shouldFree()) {
				if(this.ticksBeforeMusic > 0) {
					--this.ticksBeforeMusic;
					return;
				}

				int var1 = rand.nextInt(newMusic.length);
				this.ticksBeforeMusic = this.rand.nextInt(12000) + 12000;
				String name = "/music/" + newMusic[var1];

				IAudioResource trk;
				if (!music.containsKey(name)) {
					if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
						trk = PlatformAudio.loadAudioDataNew(name, false, browserResourceLoader);
					} else {
						trk = PlatformAudio.loadAudioData(name, false);
					}
					if (trk != null) {
						music.put(name, trk);
					}
				} else {
					trk = music.get(name);
				}

				musicHandle = PlatformAudio.beginPlaybackStatic(trk, DEFAULT_MUSIC_VOLUME, 1.0f, false);
			}
		}
	}

	public void setListener(EntityLiving var1, float var2) {
		if (var1 != null && DEFAULT_SOUND_VOLUME != 0.0F) {
			try {
				float var9 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
				float var3 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
				double var4 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double) var2;
				double var6 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double) var2;
				double var8 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double) var2;
				PlatformAudio.setListener((float) var4, (float) var6, (float) var8, (float) var9, (float) var3);
			} catch (Exception e) {
			}
		}
	}

	public void playSound(String var1, float var2, float var3, float var4, float var5, float var6) {
		if(DEFAULT_SOUND_VOLUME != 0.0F) {
			if(var5 > 0.0F) {
				IAudioResource trk;
				if(var1 == null) return;

				var1 = var1.replace(".", "/");

				int randNum = AudioUtils.getRandomSound(var1);
				if(randNum == 0) {
					return;
				}
				String soundName = "/sound/" + var1 + (randNum != -1 ? randNum : "") + ".ogg";
				if (!sounds.containsKey(soundName)) {
					if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
						trk = PlatformAudio.loadAudioDataNew(soundName, true, browserResourceLoader);
					} else {
						trk = PlatformAudio.loadAudioData(soundName, true);
					}
					if (trk != null) {
						sounds.put(soundName, trk);
					}
				} else {
					trk = sounds.get(soundName);
				}

				if(trk != null) {
					PlatformAudio.beginPlayback(trk, var2, var3, var4, var5 * DEFAULT_SOUND_VOLUME, var6, false);
				}
			}
		}
	}

	public void playSoundFX(String var1, float var2, float var3) {
		if(DEFAULT_SOUND_VOLUME != 0.0F) {
			if(var2 > 1.0F) {
				var2 = 1.0F;
			}
			var2 *= 0.25F;

			IAudioResource trk;
			if(var1 == null) return;

			var1 = var1.replace(".", "/");
			int randNum = AudioUtils.getRandomSound(var1 + ".ogg");
			if(randNum == 0) {
				return;
			}
			String soundName = "/sound/" + var1 + (randNum != -1 ? randNum : "") + ".ogg";
			if (!sounds.containsKey(soundName)) {
				if (EagRuntime.getPlatformType() != EnumPlatformType.DESKTOP) {
					trk = PlatformAudio.loadAudioDataNew(soundName, true, browserResourceLoader);
				} else {
					trk = PlatformAudio.loadAudioData(soundName, true);
				}
				if (trk != null) {
					sounds.put(soundName, trk);
				}
			} else {
				trk = sounds.get(soundName);
			}
			if(trk != null) {
				PlatformAudio.beginPlaybackStatic(trk, var2 * DEFAULT_SOUND_VOLUME, var3, false);
			}
		}
	}

	public void playButtonClick() {
		playSoundFX("random.click", 1.0f, 1.0f);
	}

	private final IAudioCacheLoader browserResourceLoader = filename -> {
		try {
			return EaglerInputStream.inputStreamToBytesQuiet(EagRuntime.getRequiredResourceStream(filename));
		} catch (Throwable t) {
			return null;
		}
	};
}