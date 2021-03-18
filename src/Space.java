import java.util.List;

public class Space {
    private int aliveSettlerCnt;

    private int turnsTillSunStorm;

    private boolean gameOver;

    private int sunStormFreq;

    private List<Asteroid> asteroids;

    private List<Character> characters;

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
}
