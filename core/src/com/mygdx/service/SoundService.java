package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.enumeration.SoundEnum;

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

	private MusicEnum lastMusicPlayed;

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

	public void playMusic(MusicEnum musicEnum) {
		stopMusic();
		switch (musicEnum) {
		case BOSS:
			boss.play();
			boss.setLooping(true);
			break;
		case HAMMERFEST:
			music.play();
			music.setLooping(true);
			break;
		case HURRY_UP:
			hurryUp.play();
			hurryUp.setLooping(true);
			break;
		case LOST_TRACK:
			lostTrack.play();
			lostTrack.setLooping(true);
			break;
		case BOSS2:
		default:
			boss2.play();
			boss2.setLooping(true);
			break;
		}
		lastMusicPlayed = musicEnum;
	}

	public void playLastMusic() {
		playMusic(lastMusicPlayed);
	}

	/*******************
	 * --- son ---
	 *******************/

	public void playSound(SoundEnum soundEnum) {
		switch (soundEnum) {
		case BLACKBOMBE:
			soundBlackBombe.play();
			break;
		case BLUEBOMBE:
			soundBlueBombe.play();
			break;
		case CHANGETYPE:
			soundChangeType.play();
			break;
		case CRISTAL:
			soundCristal.play();
			break;
		case DEAD:
			soundDead.play();
			break;
		case GREENBOMBE:
			soundGreenBombe.play();
			break;
		case HURRYUP:
			soundHurryUp.play();
			break;
		case IGORFALL:
			soundIgorFall.play();
			break;
		case IGORJUMP:
			soundIgorJump.play();
			break;
		case KICKBOMBE:
			soundKickBombe.play();
			break;
		case KILLENNEMIS:
			soundKillEnnemis.play();
			break;
		case LETTEREFFECTOBJECT:
			soundLetterEffectObject.play();
			break;
		case NEXTLEVEL:
			soundNextLevel.play();
			break;
		case NORMALBOMBE:
			soundNormalBombe.play();
			break;
		case POP:
			soundPop.play();
			break;
		case PUTBOMBE:
			soundPutBombe.play();
			break;
		case TAKE_OBJECT:
			soundTake_object.play();
			break;
		case TELEPORTER:
			soundTeleporter.play();
			break;
		case TUBERCULOZ:
			soundTuberculoz.play();
			break;
		default:
			soundBlackBombe.play();
		}
	}

	

}
