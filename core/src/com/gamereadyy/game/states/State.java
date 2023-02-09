package com.gamereadyy.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
/**
 * @author Aleksandra Krajna
 */

public abstract class State {

    /**utorzenie kamery pomagającej zlokalizować pozycje na świecie gry*/
    protected OrthographicCamera cam;
    /**wektor odpowiedzialny za obsługe myszy*/
    protected Vector3 mouse;
    /**menedżer stanu gry pomagający zarządzać stanami*/
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        /**utworzenie obiektów*/
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    /**metoda odpowiedzialna za obsługe danych wejściowych*/
    protected abstract void handleInput();
    /**
     * aktualizowanie stanu gru
     * @param dt - czas określający zależność między upływem czasu rzeczywistego a stanem gry
     */
    public abstract void update(float dt);
    /** metoda odpowiedzialna za rysowanie świata gry u aktualizowania go
     * @param sb - zmienna odpowiedzialna za wyświetlanie tekstur na ekranie*/
    public abstract void render(SpriteBatch sb);
    /**metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    public abstract void dispose();


}
