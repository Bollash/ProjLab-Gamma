import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Space {

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
    private List<Character> characters;

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
        characters = new ArrayList<>();
        for(int i = 0; i < settlerCnt; i++){
            characters.add(new Settler());
        }

        for (Character c: characters) {
            asteroids.get(0).addCharacter(c);
        }
    }

    /**
     * Karakter kivevése az aktív karakterek közül
     * @param character Ez a karakter halt meg
     */
    public void removeCharacter(Character character){
        characters.remove(character);
    }

    /**
     * Csükkenti a napviharszámlálót. Ha az elérte a nullát, akkor napvihar söpör végig a pályán, és a számláló a sunStormFreq értékre áll vissza.
     */
    public void handleCountDown(){
        turnsTillSunStorm--;

        if(turnsTillSunStorm == 0){
            for (Character character: characters) {
                character.getSunStormed();
            }
            turnsTillSunStorm = sunStormFreq;
        }
    }

    /**
     * Kapott karaktert hozzá adja az aktív játékosok közé
     * @param character Ez a karakter kerül a characters-be
     */
    public void addCharacter(Character character){
        if(!characters.contains(character)){
            characters.add(character);
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
}
