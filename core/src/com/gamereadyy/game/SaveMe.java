package com.gamereadyy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamereadyy.game.states.GameStateManager;
import com.gamereadyy.game.states.MenuState;
/** @author Aleksandra Krajna*/
/**Klasa główna gry*/

public class SaveMe extends ApplicationAdapter {
	/**wielkość okna gry*/
	public static final int width = 480;
	public static final int height = 800;

	/**tytuł okna - nazwa gry*/
	public static final String TITLE = "Save Me";
	/**zarządzanie stanami gry*/
	private GameStateManager gsm;
	/**renderowanie gry*/
	private SpriteBatch batch;
	Texture img;
	/**dźwięk gry*/
	private Music music;

	@Override
	public void create () {
		/**utworzenie obiektów*/
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		/**dodanie muzyki, nadanie głośności*/
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(0,0,0,0);
		/**otwieranie gry z poziomu klasy MenuState*/
		gsm.push(new MenuState(gsm));

	}
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/**aktualizacja*/
		gsm.update(Gdx.graphics.getDeltaTime());
		/**przekazanie pliku*/
		gsm.render(batch);
	}
	/**zwolnienie zasobów aplikacji*/
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
	}
}
