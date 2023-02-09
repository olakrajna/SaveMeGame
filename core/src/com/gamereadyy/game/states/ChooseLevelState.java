package com.gamereadyy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamereadyy.game.SaveMe;

/** @author Aleksandra Krajna */
/**Klasa odpowiedzialna za wybór poziomów gry*/

public class ChooseLevelState extends State{

    /**Szerokość i wysokość przycisków poziomów*/
    private static final int BUTTON_WIDTH = 123;
    private static final int BUTTON_HEIGHT = 71;

    /**Szerokość i wysokść przycisku X*/
    private static final int BUTTON_W_H = 34;

    /**tekstura przycisków i tła*/
    private Texture bg;
    private Texture xBtn;
    private Texture xBtn2;
    private Texture level;
    private Texture level1Btn;
    private Texture level2Btn;

    /**
     * utworzenie obiektów, nadanie wartości
     * @param gsm - zmienna pomagająca zarządzać stanami gry
     */
    protected ChooseLevelState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg.png");
        xBtn = new Texture("X.png");
        xBtn2 = new Texture("X2.png");
        level1Btn = new Texture("level1.png");
        level2Btn = new Texture("level2.png");
        level = new Texture("level.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    /**
     * metoda odpowiedzialna za rysowanie świata gry u aktualizowania go
     * @param sb - zmienna odpowiedzialna za wyświetlanie tekstur na ekranie*/
    @Override
    public void render(SpriteBatch sb) {
        /**rozpoczynamy rysowanie*/
        sb.begin();
        /**przekazanie tekstury do wyświetlenia i nadanie lokalizacji w oknie gry*/
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(level,  cam.position.x - (cam.viewportWidth/2) + 130 , 700);
        /**ustawienie pozycji guzika*/
        int x = SaveMe.width/2 - xBtn.getWidth()/2 + 180;
        int y = SaveMe.height/2 - xBtn.getHeight()/2 + 350 ;

        /**wyrysowanie przycisku "X"*/
        sb.draw(xBtn, x, y);
        if(Gdx.input.getX() < x + BUTTON_W_H && Gdx.input.getX() > x && SaveMe.height - Gdx.input.getY() < y + BUTTON_W_H && SaveMe.height - Gdx.input.getY() > y ) {
             /**nadanie mu akcji, jeżeli zostanie naciśnięty - powrócimy do menu,
            jeżeli tylko najedziemy na niego, to wyświetli się inna tekstura*/
            if(Gdx.input.isTouched()) {
                gsm.set(new MenuState(gsm));
            } else {
                sb.draw(xBtn2, x, y);
            }
        }
        /**przycisk poziomu 1
        *Ustawienie pozycji guzika*/
        int x1 = SaveMe.width/2 - level1Btn.getWidth()/2;
        int y1 = SaveMe.width/2 - level1Btn.getHeight()/2 + 400;
            /**nadanie mu akcji, jeżeli zostanie naciśnięty - znajdziemy się na 1 poziomie,
            jeżeli tylko najedziemy na niego, to wyświetli się inna tekstura*/
        if(Gdx.input.getX() < x1 + BUTTON_WIDTH && Gdx.input.getX() > x1 && SaveMe.height - Gdx.input.getY() < y1 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y1 ) {
            sb.draw(level1Btn, x1, y1);
            if(Gdx.input.isTouched()) {
                gsm.set(new PlayState(gsm));
            }
        } else {
            sb.draw(level1Btn, x1, y1 );
        }
        /**przycisk poziomu 2
        *stawienie pozycji guzika*/
        int x2 = SaveMe.width/2 - level2Btn.getWidth()/2;
        int y2 = SaveMe.width/2 - level2Btn.getHeight()/2 + 300;
            /**nadanie mu akcji, jeżeli zostanie naciśnięty - znajdziemy się na 2 poziomie,
            jeżeli tylko najedziemy na niego, to wyświetli się inna tekstura*/
        if(Gdx.input.getX() < x2 + BUTTON_WIDTH && Gdx.input.getX() > x2 && SaveMe.height - Gdx.input.getY() < y2 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y2 ) {
            sb.draw(level2Btn, x2, y2);
            if(Gdx.input.isTouched()) {
                gsm.set(new AnotherPlayState(gsm));
            }
        } else {
            sb.draw(level2Btn, x2, y2 );
        }

        sb.end();
    }

    /**metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    @Override
    public void dispose() {
        bg.dispose();
        xBtn.dispose();
        xBtn2.dispose();
        level1Btn.dispose();
        level2Btn.dispose();
    }
}
