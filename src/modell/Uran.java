package modell;

public class Uran extends Material implements java.io.Serializable{

    private int counter;

    public Uran(){
        counter = 0;
    }
    /**
     * Ez a függvény visszaadja, hogy az adott material Uran
     * @return az adott material Uran típusú
     */
    @Override
    public MaterialType getType() {
        return MaterialType.Uran;
    }

    @Override
    public void status() {
        System.out.println("Uran");
        System.out.println(counter);
    }

    /**
     * Radioaktív robbanás következik be és meghívja az aszteroida explode metódusát.
     * @param ast az adott aszteroida, amiben az Uran van.
     */
    @Override
    public void closeToSunAction(Asteroid ast) {
        counter++;
        if(counter == 3){
            ast.explode();
        }
    }
}
