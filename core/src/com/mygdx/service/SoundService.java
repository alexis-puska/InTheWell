package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Play musique or a sound in game.
 * 
 * @author alexispuskarczyk
 */
public class SoundService {

	/*******************
	 * --- musique ---
	 *******************/
	private Music boss2;
	private Music boss;
	private Music hurryUp;
	private Music lostTrack;
	private Music music;

	/*******************
	 * --- son ---
	 *******************/
	private Sound soundBlackBombe;
	private Sound soundBlueBombe;
	private Sound soundChangeType;
	private Sound soundCristal;
	private Sound soundDead;
	private Sound soundGreenBombe;
	private Sound soundHurryUp;
	private Sound soundIgorFall;
	private Sound soundIgorJump;
	private Sound soundKickBombe;
	private Sound soundKillEnnemis;
	private Sound soundLetterEffectObject;
	private Sound soundNextLevel;
	private Sound soundNormalBombe;
	private Sound soundPop;
	private Sound soundPutBombe;
	private Sound soundTake_object;
	private Sound soundTeleporter;
	private Sound soundTuberculoz;

	public SoundService() {
		Gdx.app.log("SoundService", "Init");
		/*******************
		 * --- musique ---
		 *******************/
		boss2 = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss_v2.3.mp3"));
		boss = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss.mp3"));
		hurryUp = Gdx.audio.newMusic(Gdx.files.internal("music/music_hurry_up.mp3"));
		lostTrack = Gdx.audio.newMusic(Gdx.files.internal("music/music_lost_track.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music/music_music.mp3"));
		/*******************
		 * --- son ---
		 *******************/
		soundBlackBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_black_bombe.wav"));
		soundBlueBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_blue_bombe.wav"));
		soundChangeType = Gdx.audio.newSound(Gdx.files.internal("sound/sound_change_type.wav"));
		soundCristal = Gdx.audio.newSound(Gdx.files.internal("sound/sound_cristal.wav"));
		soundDead = Gdx.audio.newSound(Gdx.files.internal("sound/sound_dead.wav"));
		soundGreenBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_green_bombe.wav"));
		soundHurryUp = Gdx.audio.newSound(Gdx.files.internal("sound/sound_hurry_up.wav"));
		soundIgorFall = Gdx.audio.newSound(Gdx.files.internal("sound/sound_igor_fall.wav"));
		soundIgorJump = Gdx.audio.newSound(Gdx.files.internal("sound/sound_igor_jump.wav"));
		soundKickBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_kick_bombe.wav"));
		soundKillEnnemis = Gdx.audio.newSound(Gdx.files.internal("sound/sound_kill_ennemis.wav"));
		soundLetterEffectObject = Gdx.audio.newSound(Gdx.files.internal("sound/sound_letter_effect_object.wav"));
		soundNextLevel = Gdx.audio.newSound(Gdx.files.internal("sound/sound_next_level.wav"));
		soundNormalBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_normal_bombe.wav"));
		soundPop = Gdx.audio.newSound(Gdx.files.internal("sound/sound_pop.wav"));
		soundPutBombe = Gdx.audio.newSound(Gdx.files.internal("sound/sound_put_bombe.wav"));
		soundTake_object = Gdx.audio.newSound(Gdx.files.internal("sound/sound_take_object.wav"));
		soundTeleporter = Gdx.audio.newSound(Gdx.files.internal("sound/sound_teleporter.wav"));
		soundTuberculoz = Gdx.audio.newSound(Gdx.files.internal("sound/sound_tuberculoz.wav"));
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
