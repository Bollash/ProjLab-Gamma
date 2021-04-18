package modell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public class Space implements java.io.Serializable{

    /**
     * Aktív telepesek száma
     */
    private int aliveSettlerCnt;

    /**
     * Napszél visszaszámláló
     */
    private int turnsTillSunStorm;

    /**
     * Játék állapotát jelző flag.
     * Ha True és több mint aktív telepes van akkor a játékosok nyertek
     * Ellenkező esetben vesztettek.
     */
    private boolean gameOver;

    /**
     * Hány körönként jön a napszél
     */
    private int sunStormFreq;

    /**
     * Az aszteroidák gyűjteménye
     */
    private List<Asteroid> asteroids;

    /**
     * Aktív karakterek gyűjteménye
     */
    private List<Actor> actors;

    /**
     * kapott paraméterek alapján legenerál egy játékteret
     * @param settlerCnt Játékosok száma
     * @param sameCnt Egy bizonyos nyersanyag magú aszteroidák száma. Összesen 4x ennyi aszteroida lesz a játékban.
     * @param freq Napvihar frekvencia.
     */
    public Space(int settlerCnt, int sameCnt, int freq){
        gameOver = false;
        this.aliveSettlerCnt = settlerCnt;
        sunStormFreq = freq;
        turnsTillSunStorm = sunStormFreq;
        List<Asteroid> asts = new ArrayList<>();
        Random rnd = new Random(System.currentTimeMillis());
        for(int i = 0; i < sameCnt; i++){
            asts.add(new Asteroid(rnd.nextInt(6), 10, rnd.nextInt(10), new Iron()));
            asts.add(new Asteroid(rnd.nextInt(6), 10, rnd.nextInt(10), new Ice()));
            asts.add(new Asteroid(rnd.nextInt(6), 10, rnd.nextInt(10), new Coal()));
            asts.add(new Asteroid(rnd.nextInt(6), 10, rnd.nextInt(10), new Uran()));
        }
        asteroids = new ArrayList<>();
        while(asts.size() != 0){
            int idx = rnd.nextInt(asts.size());
            if(asteroids.size() != 0){
                asteroids.get(rnd.nextInt(asteroids.size())).addNeighbour(asts.get(idx));
            }
            asteroids.add(asts.get(idx));
            asts.remove(idx);
        }
        actors = new ArrayList<>();
        for(int i = 0; i < settlerCnt; i++){
            actors.add(new Settler());
        }

        for (Actor c: actors) {
            asteroids.get(0).addActor(c);
        }
    }

    public Space(int settlerCnt, int turnsTillSunStorm, int sunStormFreq, List<Asteroid> asteroids, List<Actor> actors){
        this.aliveSettlerCnt = settlerCnt;
        this.turnsTillSunStorm = turnsTillSunStorm;
        this.sunStormFreq = sunStormFreq;
        this.asteroids = asteroids;
        this.actors = actors;
    }

    /**
     * Karakter kivevése az aktív karakterek közül
     * @param actor Ez a karakter halt meg
     */
    public void removeActor(Actor actor){
        actors.remove(actor);
    }

    /**
     * Csökkenti a napviharszámlálót. Ha az elérte a nullát, akkor napvihar söpör végig a pályán, és a számláló a sunStormFreq értékre áll vissza.
     */
    public void handleCountDown(){
        turnsTillSunStorm--;

        if(turnsTillSunStorm == 0){
            // megkeverjük
            Collections.shuffle(asteroids);
            // Ha páratlan hosszó akkor 1 ha nem akkor 0. Ezzel lehet biztosítani hogy mindig a fele + 0.5 aszteroidán van vihar
            int correction = asteroids.size() % 2 == 0?0:1;
            for(int i = 0; i < asteroids.size() / 2 + correction; i++){
                asteroids.get(i).sunStorm();
            }
            turnsTillSunStorm = sunStormFreq;
            System.out.println("A számláló elérte a nullát és napszél lett. A számláló visszaállt a kiindulási állapotba.");
            return;
        }
        System.out.println("A napszél számláló eggyel csökkent.");
    }

    /**
     * Kapott karaktert hozzá adja az aktív játékosok közé
     * @param actor Ez a karakter kerül a characters-be
     */
    public void addActor(Actor actor){
        if(!actors.contains(actor)){
            actors.add(actor);
        }
    }

    /**
     * Kapott aszteroidát adja az aszteroidák közé
     * @param asteroid Ez az aszteroida kerül az asteroids-ba
     */
    public void addAsteroid(Asteroid asteroid){
        if(!asteroids.contains(asteroid)){
            asteroids.add(asteroid);
        }
    }

    /**
     * gameOver flag getter-e
     * @return _
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * gameOver flag setter-e
     * @param gameOver _
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * aliveSettlerCnt getter-e
     * @return .
     */
    public int getAliveSettlerCnt() {
        return aliveSettlerCnt;
    }

    /**
     * aliveSettlerCnt setter-e
     * @param aliveSettlerCnt _
     */
    public void setAliveSettlerCnt(int aliveSettlerCnt) {
        this.aliveSettlerCnt = aliveSettlerCnt;
    }

    public List<Actor> getActors(){
        return actors;
    }

    public List<Asteroid> getAsteroids(){
        return asteroids;
    }

    public int getTurnsTillSunStorm() {
        return turnsTillSunStorm;
    }

    public int getSunStormFreq() {
        return sunStormFreq;
    }

    public void setTurnsTillSunStorm(int c){ turnsTillSunStorm = c; }

    public int asteroidCount() {
        return asteroids.size();
    }

    public int actorCount() {
        return actors.size();
    }

    public void status(){
        System.out.println("Space");
        System.out.println(aliveSettlerCnt);
        System.out.println(turnsTillSunStorm);
        System.out.println(sunStormFreq);
        if(gameOver){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
        System.out.println(asteroids.size());
        System.out.println(actors.size());
    }
}
