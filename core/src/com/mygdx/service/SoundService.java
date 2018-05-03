package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundService {

	private static SoundService INSTANCE = new SoundService();

	/*******************
	 * --- musique ---
	 *******************/
	private Music boss2 = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss_v2.3.mp3"));
	private Music boss = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss.mp3"));
	private Music hurryUp = Gdx.audio.newMusic(Gdx.files.internal("music/music_hurry_up.mp3"));
	private Music lostTrack = Gdx.audio.newMusic(Gdx.files.internal("music/music_lost_track.mp3"));
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("music/music_music.mp3"));

	/*******************
	 * --- son ---
	 *******************/
	private Sound soundBlackBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_black_bombe.wav"));
	private Sound soundBlueBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_blue_bombe.wav"));
	private Sound soundChangeType = Gdx.audio.newSound(Gdx.files.internal("sound/sound_change_type.wav"));
	private Sound soundCristal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_cristal.wav"));
	private Sound soundDead = Gdx.audio.newSound(Gdx.files.internal("sound/sound_dead.wav"));
	private Sound soundGreenBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_green_bombe.wav"));
	private Sound soundHurryUp = Gdx.audio.newSound(Gdx.files.internal("sound/sound_hurry_up.wav"));
	private Sound soundIgorFall = Gdx.audio.newSound(Gdx.files.internal("sound/sound_igor_fall.wav"));
	private Sound soundIgorJump = Gdx.audio.newSound(Gdx.files.internal("sound/sound_igor_jump.wav"));
	private Sound soundKickBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_kick_bombe.wav"));
	private Sound soundKillEnnemis = Gdx.audio.newSound(Gdx.files.internal("sound/sound_kill_ennemis.wav"));
	private Sound soundLetterEffectObject = Gdx.audio
			.newSound(Gdx.files.internal("sound/sound_letter_effect_object.wav"));
	private Sound soundNextLevel = Gdx.audio.newSound(Gdx.files.internal("sound/sound_next_level.wav"));
	private Sound soundNormalBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_normal_bombe.wav"));
	private Sound soundPop = Gdx.audio.newSound(Gdx.files.internal("sound/sound_pop.wav"));
	private Sound soundPutBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_put_bombe.wav"));
	private Sound soundTake_object = Gdx.audio.newSound(Gdx.files.internal("sound/sound_take_object.wav"));
	private Sound soundTeleporter = Gdx.audio.newSound(Gdx.files.internal("sound/sound_teleporter.wav"));
	private Sound soundTuberculoz = Gdx.audio.newSound(Gdx.files.internal("sound/sound_tuberculoz.wav"));

	public static SoundService getInstance() {
		return INSTANCE;
	}

	/*******************
	 * --- musique ---
	 *******************/
	public void stopMusic() {
		if (boss.isPlaying()) {
			boss.stop();
		} else if (boss2.isPlaying()) {
			boss2.stop();
		} else if (hurryUp.isPlaying()) {
			hurryUp.stop();
		} else if (lostTrack.isPlaying()) {
			lostTrack.stop();
		} else if (music.isPlaying()) {
			music.stop();
		}
	}

	public void playMusicBoss2() {
		stopMusic();
		boss2.play();
		boss2.setLooping(true);
	}

	public void playMusicBoss() {
		stopMusic();
		boss.play();
		boss.setLooping(true);
	}

	public void playMusicHurryUp() {
		stopMusic();
		hurryUp.play();
		hurryUp.setLooping(true);
	}

	public void playMusicLostTrack() {
		stopMusic();
		lostTrack.play();
		lostTrack.setLooping(true);
	}

	public void playMusic() {
		stopMusic();
		music.play();
		music.setLooping(true);
	}

	/*******************
	 * --- son ---
	 *******************/
	public void playSoundBlackBombe() {
		soundBlackBombe.play();
	}

	public void playSoundBlueBombe() {
		soundBlueBombe.play();
	}

	public void playSoundChangeType() {
		soundChangeType.play();
	}

	public void playSoundCristal() {
		soundCristal.play();
	}

	public void playSoundDead() {
		soundDead.play();
	}

	public void playSoundGreenBombe() {
		soundGreenBombe.play();
	}

	public void playSoundHurryUp() {
		soundHurryUp.play();
	}

	public void playSoundIgorFall() {
		soundIgorFall.play();
	}

	public void playSoundIgorJump() {
		soundIgorJump.play();
	}

	public void playSoundKickBombe() {
		soundKickBombe.play();
	}

	public void playSoundKillEnnemis() {
		soundKillEnnemis.play();
	}

	public void playSoundLetterEffectObject() {
		soundLetterEffectObject.play();
	}

	public void playSoundNextLevel() {
		soundNextLevel.play();
	}

	public void playSoundNormalBombe() {
		soundNormalBombe.play();
	}

	public void playSoundPop() {
		soundPop.play();
	}

	public void playSoundPutBombe() {
		soundPutBombe.play();
	}

	public void playSoundTake_object() {
		soundTake_object.play();
	}

	public void playSoundTeleporter() {
		soundTeleporter.play();
	}

	public void playSoundTuberculoz() {
		soundTuberculoz.play();
	}

	@Override
	public void finalize() {
		music.dispose();
		boss2.dispose();
		boss.dispose();
		hurryUp.dispose();
		lostTrack.dispose();
		soundBlackBombe.dispose();
		soundBlueBombe.dispose();
		soundChangeType.dispose();
		soundCristal.dispose();
		soundDead.dispose();
		soundGreenBombe.dispose();
		soundHurryUp.dispose();
		soundIgorFall.dispose();
		soundIgorJump.dispose();
		soundKickBombe.dispose();
		soundKillEnnemis.dispose();
		soundLetterEffectObject.dispose();
		soundNextLevel.dispose();
		soundNormalBombe.dispose();
		soundPop.dispose();
		soundPutBombe.dispose();
		soundTake_object.dispose();
		soundTeleporter.dispose();
		soundTuberculoz.dispose();
	}

}
