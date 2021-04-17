package modell;

import modell.exceptions.NotEnoughMaterialException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialArray implements java.io.Serializable{
    private List<Material> materials;

    public MaterialArray(){
        materials = new ArrayList<>();
    }

    public MaterialArray(List<Material> materials){
        this.materials = materials;
    }



    /**
     * Visszaadja az első urán material indexét, ha nincs ilyen, akkor -1.
     * @return az index.
     */
    public int getUran() {
        for(int i = 0; i < materials.size(); i++) {
            if(materials.get(i).getType().equals(MaterialType.Uran))
                return i;
        }
        return -1;
    }

    /**
     * Visszaadja az első vas material indexét, ha nincs ilyen, akkor -1.
     * @return az index.
     */
    public int getIron() {
        for(int i = 0; i < materials.size(); i++) {
            if(materials.get(i).getType().equals(MaterialType.Iron))
                return i;
        }
        return -1;
    }

    /**
     * Visszaadja az első szén material indexét, ha nincs ilyen, akkor -1.
     * @return az index.
     */
    public int getCoal() {
        for(int i = 0; i < materials.size(); i++) {
            if(materials.get(i).getType().equals(MaterialType.Coal))
                return i;
        }
        return -1;
    }

    /**
     * Visszaadja az első jég material indexét, ha nincs ilyen, akkor -1.
     * @return az index.
     */
    public int getIce() {
        for(int i = 0; i < materials.size(); i++) {
            if(materials.get(i).getType().equals(MaterialType.Ice))
                return i;
        }
        return -1;
    }

    /**
     * Épít 2 teleport kaput
     * @return A készített teleport kapuk
     * @throws NotEnoughMaterialException Nincs elég nyersanyag az építésre
     */
    public TpGate[] buildGates()throws NotEnoughMaterialException {
        Map<MaterialType, Integer> mats = countMaterials();
        if(mats.get(MaterialType.Iron) >= 2 && mats.get(MaterialType.Ice) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Ice);
            removeMaterial((MaterialType.Uran));
            return new TpGate[]{new TpGate(), new TpGate()};
        }
        else {
            throw new NotEnoughMaterialException("missing materials");
        }
    }

    /**
     * Kiveszi a kapott materialt a materialok közül
     * @param type Kivevendő material
     */
    private void removeMaterial(MaterialType type){
        for(Material mat: materials){
            if(mat.getType() == type){
                materials.remove(mat);
                return;
            }
        }
    }

    /**
     * Egy map-ba összegyűjti a nyersanyagokat
     * @return A nyersanyagok kollekciója
     */
    private Map<MaterialType, Integer> countMaterials(){
        Map<MaterialType, Integer> mats = new HashMap<>();

        for(Material m : materials){
            MaterialType type = m.getType();
            if(!mats.containsKey(type)) mats.put(type, 0);
            mats.put(type, mats.get(type) + 1);
        }
        return mats;
    }

    /**
     * A paraméterként kapott materialt beletesz a collection-be
     * @param mat A felvevendő material
     */
    public void addMaterial(Material mat){
        materials.add(mat);
    }

    /**
     * Egy robot gyártása ha van elég nyersanyag
     * @return gyártott robot
     * @throws NotEnoughMaterialException nincs elég nyersanyag
     */
    public Robot buildRobot()throws NotEnoughMaterialException{
        Map<MaterialType, Integer> mats = countMaterials();

        if(mats.get(MaterialType.Iron) >= 1 && mats.get(MaterialType.Coal) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Coal);
            removeMaterial(MaterialType.Uran);
            return new Robot();
        }
        else {throw new  NotEnoughMaterialException("missing materials");}

    }

    /**
     * Lehet-e bázist építeni a nyersanyagokból
     * @return válasz
     */
    public boolean canBuildBase(){
        Map<MaterialType, Integer> mats = countMaterials();
        return (mats.get(MaterialType.Iron) >= 3 && mats.get(MaterialType.Coal) >= 3 && mats.get(MaterialType.Uran) >= 3 && mats.get(MaterialType.Ice) >= 3);
    }


    public List<Material> getMaterials() {
        return materials;
    }

    /**
     * Két MaterialArrayt összeadunk
     * @param ma egyik materialarray
     * @return az összeg
     */
    public MaterialArray plus(MaterialArray ma){
        materials.addAll(ma.getMaterials());
        return new MaterialArray(new ArrayList<>(materials));

    }
}
