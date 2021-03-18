public class Settler extends Character{
    private MaterialArray materials;
    private Tpgate[] tpGates;

    public void mine(){
        Material material = currentAsteroid.getMined();
        if(materials.getMaterials().size() < 10){
            materials.addMaterial(material);
        }
    }

    @Override
    public void radExplode() {
        die();
    }

    @Override
    public void act() {
        //TODO: Ki kéne találni ide mi jöjjön
    }

    @Override
    public void die() {
        space.removeCharacter(this);
        space.setAliveSettlerCnt(space.getAliveSettlerCnt() - 1);
    }

    public void buildBase(){}

    public void craftGates(){}

    public void craftRobots(){}

    public void putMaterialBack(Material mat){}

    public void putTpGateDown(){}

    public void removeMaterial(Material mat){}

    public void countMaterials(MaterialArray ma){}
}
