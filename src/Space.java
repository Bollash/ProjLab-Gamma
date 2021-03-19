import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Space {
    private int aliveSettlerCnt;

    private int turnsTillSunStorm;

    private boolean gameOver;

    private int sunStormFreq;

    private List<Asteroid> asteroids;

    private List<Character> characters;

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

    public void removeCharacter(Character character){
        characters.remove(character);
    }

    public void handleCountDown(){
        turnsTillSunStorm--;

        if(turnsTillSunStorm == 0){
            for (Character character: characters) {
                character.getSunStormed();
            }
            turnsTillSunStorm = sunStormFreq;
        }
    }

    public void addCharacter(Character character){
        if(!characters.contains(character)){
            characters.add(character);
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getAliveSettlerCnt() {
        return aliveSettlerCnt;
    }

    public void setAliveSettlerCnt(int aliveSettlerCnt) {
        this.aliveSettlerCnt = aliveSettlerCnt;
    }
}
