package com.gamereadyy.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
/**@author
 * Aleksandra Krajna*/
/**Klasa odpowiedzialna za nadanie animacji żółwikowi*/

public class Animation {
   /**przechowujemy klatki*/
   Array<TextureRegion> frames;
   /**czas jaki zajmuje jedna klatka - jak długo ma pozostać klatka na widoku*/
   float maxFrameTime;
   /**czas bieżącej klatki*/
   float currentFrameTime;
   /**ilość klatek*/
   int frameCount;
   /**klatka bieżąca*/
   int frame;

   /**
    * Utworzenie obiektów i nadanie im wartości
    * @param region - tekstura jaką mamy podać
    * @param frameCount - ilość klatek w teksturze
    * @param cycleTime - całkowity czas potrzebny do przejść przez obraz
    */
   public Animation(TextureRegion region, int frameCount, float cycleTime){
      frames = new Array<TextureRegion>();
      TextureRegion temp;
      /**szerokość pojedynczej klatki*/
      int frameWidth = region.getRegionWidth() / frameCount;
      /**pętla odpowiedzialna za ciagłą zmiane tekstury*/
      for(int i = 0; i < frameCount; i++){
         temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
         frames.add(temp);
      }
      this.frameCount = frameCount;
      /**całkowity czas potrzebny do przejść przez obraz podzielony przez liczbe klatek*/
      maxFrameTime = cycleTime / frameCount;
      frame = 0;
   }

   /**aktualizacja animacji*/
   public void update(float dt){
      currentFrameTime += dt;
      if(currentFrameTime > maxFrameTime){
         frame++;
         currentFrameTime = 0;
      }
      if(frame >= frameCount)
         frame = 0;

   }
   /**klatka w której animacja jest aktualnie wyświetlana*/
   public void flip(){
      for(TextureRegion region : frames)
         region.flip(true, false);
   }

   public TextureRegion getFrame(){
      return frames.get(frame);
   }


}
