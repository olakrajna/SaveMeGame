package com.gamereadyy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gamereadyy.game.SaveMe;
import com.gamereadyy.game.sprites.Tube;
import com.gamereadyy.game.sprites.Turtle;

/**  @author ALeksandra Kranj*/
 /** Klasa odpowiedzialna za 2 poziom gry */

public class AnotherPlayState extends State{
    /**odłegłość pomiędzy rurami*/
    private static final int TUBE_SPACING = 160;
    /**ilość rur*/
    private static final int TUBE_COUNT = 4;
    /**wysokość na osi OY*/
    private static final int GROUND_Y_OFFSET = -10;
    private static final int COL_GROUND_Y_OFFSET = -25;
    /**szerokość i wysokość gry*/
    private static final int BUTTON_WIDTH = 123;
    private static final int BUTTON_HEIGHT = 71;
    /**Utowrzenie obiektów klasy Turtle*/
    private Turtle turtle;
    /**tekstury zółwika, gry, gruntu, przycisków*/
    private Texture bg,bg2, gameOver;
    private Texture ground;
    private Texture collisionGround;
    private Texture menuBtn, menuBtn2;
    private Texture retryBtn, retryBtn2;
    private Texture exitBtn, exitBtn2;
    /**bitmapa odpowiedzialna za czcionke*/
    private BitmapFont font;
    private BitmapFont font1;
    /**wektory odpowiedzialne za pozycje*/
    private Vector2 groundPos1, groundPos2;
    private Vector2 colGroundPos1, colGroundPos2;

    /**rury*/
    private Array<Tube> tubes;
    /**punkty*/
    int score = 0;
    /**stan gry*/
    public int state = 1;
    /**przegrywanie gry*/
    private Boolean rep=true;

    /**
     * utworzenie obiektów, nadanie wartości
     * @param gsm zmienna pozwalająca zarządzać stanami gry
     */
    protected AnotherPlayState(GameStateManager gsm) {
        super(gsm);
        turtle = new Turtle(50, 300);
        cam.setToOrtho(false, SaveMe.width, SaveMe.height);
        bg = new Texture("bg.png");
        bg2 = new Texture("tło3.png");
        ground = new Texture("groundgarbage.png");
        gameOver = new Texture("gameov.png");
        collisionGround = new Texture("groundXX.png");
        exitBtn = new Texture("exitbtn.png");
        exitBtn2 = new Texture("exitbtn2.png");
        menuBtn = new Texture("menuBtn.png");
        menuBtn2 = new Texture("menuBtn2.png");
        retryBtn = new Texture("retryBtn.png");
        retryBtn2 = new Texture("retryBtn2.png");

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+ground.getWidth(), GROUND_Y_OFFSET);

        colGroundPos1 = new Vector2(-100+ cam.position.x - cam.viewportWidth/2 , COL_GROUND_Y_OFFSET);
        colGroundPos2 =  new Vector2((-100+ cam.position.x - cam.viewportWidth/2)+ground.getWidth() , COL_GROUND_Y_OFFSET);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        font1 = new BitmapFont();
        font1.setColor(Color.WHITE);
        font1.getData().setScale(2);

        tubes = new Array<Tube>();

        addTube();
    }
    /**metoda odpowiedzialna za dodawanie kolejnych rur do gry*/
    private void addTube() {
        if(state == 1) {
            for(int i = 1; i <= TUBE_COUNT; i++) {
                tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH )));
            }
        }
    }
    /**metoda wywołująca metode jump, która jest odpowiedzialna za zmiane pozycji zółwika po kliknięciu w ekran*/
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            turtle.jump();
        }
    }

    /**
     * aktualizowanie stanu gru
     * @param dt - czas określający zależność między upływem czasu rzeczywistego a stanem gry
     */
    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        turtle.update(dt*3/2);
        /**nadanie pozyji kamery*/
        cam.position.x = (turtle.getPosition().x + 80);

        /**pętla odpowiedzialna za zmiane pozycji rur*/
        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if(state==1) {
                if(cam.position.x - (cam.viewportWidth/2)> tube.getPosTopTube().x + tube.getTopTube().getWidth() ) {
                    rep = true;
                    tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING ) * TUBE_COUNT));
                }
            }
            /**jeśli żółwik nakłada się z plastikową torbą, gracz zyskuje punkt, a torba znika*/
            if(tube.collect2(turtle.getBounds()) && rep ) {
                tube.getPosBag().y = tube.getPosBag().y + 1000;
                score++;
                rep = false;
            }
            if(tube.collides(turtle.getBounds())){
                state = 2;
            }
        }
        if(turtle.getPosition().y <= collisionGround.getHeight() + COL_GROUND_Y_OFFSET) {
            state = 2;
        }

        cam.update();
    }
    /**
     * metoda odpowiedzialna za rysowanie świata gry u aktualizowania go
     * @param sb - zmienna odpowiedzialna za wyświetlanie tekstur na ekranie*/
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        /**jeśli gra jest cały czas wygrywwana, czyli state==1, to cały czas świat gry jest rysowany*/
        if (state == 1) {
            sb.draw(bg2, cam.position.x - (cam.viewportWidth/2), 0);
            sb.draw(turtle.getTexture(), (turtle.getPosition().x), turtle.getPosition().y);
            for(Tube tube: tubes) {
                sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
                sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
                sb.draw(tube.getPlasticBag(), tube.getPosBag().x, tube.getPosBag().y);
            }
            sb.draw(ground, groundPos1.x, groundPos1.y);
            sb.draw(ground,groundPos2.x, groundPos2.y);
            sb.draw(collisionGround, colGroundPos1.x, colGroundPos1.y);
            sb.draw( collisionGround,colGroundPos2.x, colGroundPos2.y );
            font.draw(sb , String.valueOf(score) , cam.position.x - 40, 750);
        } else if(state==2) /**jeśli gra jest przegrana*/
        {
            /**tło i napis game over*/
            sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0, SaveMe.width, SaveMe.height);
            sb.draw(gameOver,  cam.position.x - (cam.viewportWidth/2) + 20 , 700);
            /**podsumowanie przegranej gry*/
            font1.draw(sb, "Your collected plastic bags:  " + String.valueOf(score),cam.position.x - (cam.viewportWidth/2) + 30, 650 );
            font1.draw(sb, "This is about " + String.valueOf(score*0.015) + " kg of plastic.",cam.position.x - (cam.viewportWidth/2) + 30, 600 );
            font1.draw(sb, "And that's only " + String.valueOf((score*0.015f/80000000)) + "% of\nwhat into the oceans each year. ",cam.position.x - (cam.viewportWidth/2) + 30, 550 );
            font1.draw(sb, "Take care of the environment." + "\nDispose of plastics in the yellow " + "\ngarbage can, not in the water!  ",cam.position.x - (cam.viewportWidth/2) + 30, 150 );

            /**wyrysowanie przycisków i ustawienie ich pozycji*/
            int x2 = 240 - exitBtn.getWidth()/2;
            int y2 = 200 - exitBtn.getHeight()/2;
            int y3 = 300 - menuBtn.getHeight()/2;
            int y4 = 400 - retryBtn.getHeight()/2;


            /**przycisk powrotu do menu głównego*/
            if(Gdx.input.getX() < x2 + BUTTON_WIDTH && Gdx.input.getX() > x2 && SaveMe.height - Gdx.input.getY() < y3 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y3 ) {
                sb.draw(menuBtn, cam.position.x - (cam.viewportWidth/2)+ x2, y3);
                if(Gdx.input.isTouched()) {
                    gsm.set(new MenuState(gsm));
                }
            } else {
                sb.draw(menuBtn2, cam.position.x - (cam.viewportWidth/2) + x2, y3 );
            }

            /**przycisk opuszczenia gry*/
            if(Gdx.input.getX() < x2 + BUTTON_WIDTH && Gdx.input.getX() > x2 && SaveMe.height - Gdx.input.getY() < y2 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y2 ) {
                sb.draw(exitBtn2, cam.position.x - (cam.viewportWidth/2)+ x2, y2);
                if(Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
            } else {
                sb.draw(exitBtn, cam.position.x - (cam.viewportWidth/2) + x2, y2 );
            }

            /**przycisk rozpoczęcia kolnej gry*/
            if(Gdx.input.getX() < x2 + BUTTON_WIDTH && Gdx.input.getX() > x2 && SaveMe.height - Gdx.input.getY() < y4 + BUTTON_HEIGHT && SaveMe.height - Gdx.input.getY() > y4 ) {
                sb.draw(retryBtn, cam.position.x - (cam.viewportWidth/2)+ x2, y4);
                if(Gdx.input.isTouched()) {
                    gsm.set(new AnotherPlayState(gsm));
                }
            } else {
                sb.draw(retryBtn2, cam.position.x - (cam.viewportWidth/2) + x2, y4);
            }
        }
        sb.end();

    }
    /**metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    @Override
    public void dispose() {
        bg.dispose();
        turtle.dispose();
        exitBtn.dispose();
        exitBtn2.dispose();
        menuBtn.dispose();
        menuBtn2.dispose();
        retryBtn.dispose();
        retryBtn2.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }

    }
    /**metoda odpowiedzialna za dodawanie kolejnego gruntu*/
    private void updateGround(){
        if(state==1) {
            if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth()) {
                groundPos1.add(ground.getWidth() * 2, 0);
                colGroundPos1.add(ground.getWidth() * 2, 0);
            }
            if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth()) {
                groundPos2.add(ground.getWidth() * 2, 0);
                colGroundPos2.add(ground.getWidth() * 2, 0);

            }
        }

    }
}
