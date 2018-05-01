package com.mygdx.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;

public class Sound {

	private Music boss2 = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss_v2.3.mp3"));
	private Music boss = Gdx.audio.newMusic(Gdx.files.internal("music/music_boss.mp3"));
	private Music hurryUp = Gdx.audio.newMusic(Gdx.files.internal("music/music_hurry_up.mp3"));
	private Music lostTrack = Gdx.audio.newMusic(Gdx.files.internal("music/music_lost_track.mp3"));
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("music/music_music.mp3"));

	private static Sound INSTANCE = new Sound();

	public static Sound getINSTANCE() {
		return INSTANCE;
	}

	public void playMusic() {
		music.play();
		music.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(Music music) {
				music.play();
			}
		});
	}

	public void stopMusic() {
		music.stop();
	}

	@Override
	public void finalize() {
		music.dispose();
	}

}
