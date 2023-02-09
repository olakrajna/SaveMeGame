package com.gamereadyy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamereadyy.game.SaveMe;

/** @author Aleksandra Krajna*/
/** Klasa wyświetlająca informacje o grze*/
public class InfoGameState extends State{

    /**Szerokość i wysokść przycisku*/
    private static final int BUTTON_W_H = 34;

    /**tekstura przycisku i tła*/
    private Texture bg;
    private Texture xBtn;
    private Texture xBtn2;

    /**
     * utworzenie obiektów, nadanie wartości
     * @param gsm - zmienna pomagająca zarządzać stanami gry
     */
    protected InfoGameState(GameStateManager gsm) {
        super(gsm);
        /**Utworzenie obiektów*/
        bg = new Texture("bginfo.png");
        xBtn = new Texture("X.png");
        xBtn2 = new Texture("X2.png");
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
        sb.draw(bg, 0,0, SaveMe.width, SaveMe.height);
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
        sb.end();
    }
    /**Metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    @Override
    public void dispose() {
        bg.dispose();
        xBtn.dispose();
        xBtn2.dispose();
    }
}
