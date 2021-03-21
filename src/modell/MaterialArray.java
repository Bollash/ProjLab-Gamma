package modell;

import modell.exceptions.NotEnoughMaterialException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialArray {
    private List<Material> materials;

    public MaterialArray(){
        materials = new ArrayList<>();
    }

    public MaterialArray(List<Material> materials){
        this.materials = materials;
    }

    public TpGate[] buildGates()throws NotEnoughMaterialException {

        Map<MaterialType, Integer> mats = countMaterials();
        if(mats.get(MaterialType.Iron) >= 2 && mats.get(MaterialType.Ice) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Ice);
            removeMaterial((MaterialType.Uran));
            return new TpGate[]{new TpGate(), new TpGate()};
        }
        else throw new  NotEnoughMaterialException("missing materials");
    }

    private void removeMaterial(MaterialType type){
        for(Material mat: materials){
            if(mat.getType() == type){
                materials.remove(mat);
                return;
            }
        }
    }

    private Map<MaterialType, Integer> countMaterials(){
        Map<MaterialType, Integer> mats = new HashMap<>();

        for(Material m : materials){
            MaterialType type = m.getType();
            if(!mats.containsKey(type)) mats.put(type, 0);
            mats.put(type, mats.get(type) + 1);
        }
        return mats;
    }

    public Robot buildRobot()throws NotEnoughMaterialException{
        Map<MaterialType, Integer> mats = countMaterials();

        if(mats.get(MaterialType.Iron) >= 1 && mats.get(MaterialType.Coal) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Coal);
            removeMaterial(MaterialType.Uran);
            return new Robot();
        }
        else throw new  NotEnoughMaterialException("missing materials");

    }

    public boolean canBuildBase(){
        Map<MaterialType, Integer> mats = countMaterials();

        return (mats.get(MaterialType.Iron) >= 3 && mats.get(MaterialType.Coal) >= 3 && mats.get(MaterialType.Uran) >= 3 && mats.get(MaterialType.Ice) >= 3);
    }

    public List<Material> getMaterials() {
        return materials;
    }

    //nem biztos, hogy ez úgy működik ahogy akarom. Lehet elvesznek az elemek
    public MaterialArray plus(MaterialArray ma){
        materials.addAll(ma.getMaterials());
        return new MaterialArray(new ArrayList<>(materials));

    }
}
