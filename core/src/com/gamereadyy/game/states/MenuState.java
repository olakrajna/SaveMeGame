package com.gamereadyy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gamereadyy.game.SaveMe;

/**@author Aleksandra Krajna*/
 /** Klasa tworząca menu główne gry*/

public class MenuState extends State {

    /**Szerokość i wysokość przycisku*/
    private static final int BUTTON_WIDTH = 123;
    private static final int BUTTON_HEIGHT = 71;


    /**Tekstury przycisków*/
    private Texture background;
    private Texture playBtn;
    private Texture playBtn2;
    private Texture infoBtn;
    private Texture infoBtn2;
    private Texture exitBtn;
    private Texture exitBtn2;
    private Texture turtleBtn;
    private Texture turtleBtn2;

    /**
     * utworzenie obiektów, nadanie wartości
     * @param gsm - zmienna pomagająca zarządzać stanami gry
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        /*Utworzenie obiektów*/
        background = new Texture("bgstart2.png");
        playBtn = new Texture("playbtn.png");
        playBtn2 = new Texture("playBtnA.png");
        infoBtn = new Texture("infobtn.png");
        infoBtn2 = new Texture("infobtn2.png");
        exitBtn = new Texture("exitbtn.png");
        exitBtn2 = new Texture("exitbtn2.png");
        turtleBtn = new Texture("turtleBtn.png");
        turtleBtn2 = new Texture("turtleBtn2.png");

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
    }

    /**
     * metoda odpowiedzialna za rysowanie świata gry u aktualizowania go
     * @param sb - zmienna odpowiedzialna za wyświetlanie tekstur na ekranie
     */
    @Override
    public void render(SpriteBatch sb) {


        int x = SaveMe.width/2 - playBtn.getWidth()/2 ;
        int y = SaveMe.height/2 - playBtn.getHeight()/2 + 60;
        sb.begin();
        sb.draw(background, 0,0, SaveMe.width, SaveMe.height);
        /**przycisk rozpoczynający gre
        *Ustawienie pozycji guzika*/
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && SaveMe.height - Gdx.input.getY() < y + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y ) {
            sb.draw(playBtn2, x, y);
            if(Gdx.input.isTouched()) {
                gsm.set(new ChooseLevelState(gsm));
            }
        } else {
            sb.draw(playBtn, x, y);
        }
        /**przycisk przekierowywujący do informacji o grze, jej zasadach
        *Ustawienie pozycji guzika*/
        int x1 = SaveMe.width/2 - infoBtn.getWidth()/2;
        int y1 = SaveMe.width/2 - infoBtn.getHeight()/2 + 20;

        if(Gdx.input.getX() < x1 + BUTTON_WIDTH && Gdx.input.getX() > x1 && SaveMe.height - Gdx.input.getY() < y1 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y1 ) {
            sb.draw(infoBtn, x1, y1);
            if(Gdx.input.isTouched()) {
                gsm.set(new InfoGameState(gsm));
            }
        } else {
            sb.draw(infoBtn2, x1, y1 );
        }

        /**przycisk zamykający gre
        *Ustawienie pozycji guzika*/
        int x2 = SaveMe.width/2 - exitBtn.getWidth()/2;
        int y2 = SaveMe.width/2 - exitBtn.getHeight()/2 - 80 ;

        if(Gdx.input.getX() < x2 + BUTTON_WIDTH && Gdx.input.getX() > x2 && SaveMe.height - Gdx.input.getY() < y2 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y2 ) {
            sb.draw(exitBtn, x2, y2);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            sb.draw(exitBtn2, x2, y2 );
        }
        /**przycisk przekierowywujący do informacji o zółwiach wodnych
        *Ustawienie pozycji guzika*/
        int x3 = SaveMe.width/2 - turtleBtn.getWidth()/2;
        int y3 = SaveMe.width/2 - turtleBtn.getHeight()/2 + 120 ;

        if(Gdx.input.getX() < x3 + BUTTON_WIDTH && Gdx.input.getX() > x3 && SaveMe.height - Gdx.input.getY() < y3 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y3 ) {
            sb.draw(turtleBtn, x3, y3);
            if(Gdx.input.isTouched()) {
                gsm.set(new InfoState(gsm));
            }
        } else {
            sb.draw(turtleBtn2, x3, y3 );
        }
        sb.end();
    }
    /**Metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        playBtn2.dispose();
        infoBtn2.dispose();
        infoBtn.dispose();
        exitBtn.dispose();
        exitBtn2.dispose();
        turtleBtn.dispose();
        turtleBtn2.dispose();
    }
}
