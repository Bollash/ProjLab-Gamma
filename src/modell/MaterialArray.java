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
        System.out.println("Belépett a buildGates-be.");
        Map<MaterialType, Integer> mats = countMaterials();
        if(mats.get(MaterialType.Iron) >= 2 && mats.get(MaterialType.Ice) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Ice);
            removeMaterial((MaterialType.Uran));
            System.out.println("Kilépett a buildGates-ből.");
            return new TpGate[]{new TpGate(), new TpGate()};
        }
        else {
            System.out.println("NotEnoughMaterilasExceptiont dobott");
            throw new NotEnoughMaterialException("missing materials");
        }
    }

    private void removeMaterial(MaterialType type){
        System.out.println("Belépett a removeMateria-ba");
        for(Material mat: materials){
            if(mat.getType() == type){
                materials.remove(mat);
                System.out.println("Kilépett a removeMateria-ból");
                return;
            }
        }
    }

    private Map<MaterialType, Integer> countMaterials(){
        System.out.println("Belépett a countMaterials-ba");
        Map<MaterialType, Integer> mats = new HashMap<>();

        for(Material m : materials){
            MaterialType type = m.getType();
            if(!mats.containsKey(type)) mats.put(type, 0);
            mats.put(type, mats.get(type) + 1);
        }
        System.out.println("Kilépett a countMaterials-ból");
        return mats;
    }

    public void addMaterial(Material mat){
        System.out.println("Belépett az addMaterials-ba");
        materials.add(mat);
        System.out.println("Kilépett az addMaterials-ba");
    }

    public Robot buildRobot()throws NotEnoughMaterialException{
        System.out.println("Belépett a buildRobot-ba");
        Map<MaterialType, Integer> mats = countMaterials();

        if(mats.get(MaterialType.Iron) >= 1 && mats.get(MaterialType.Coal) >= 1 && mats.get(MaterialType.Uran) >= 1){
            removeMaterial(MaterialType.Iron);
            removeMaterial(MaterialType.Coal);
            removeMaterial(MaterialType.Uran);
            System.out.println("Kilépett a buildRobot-ból");
            return new Robot();
        }
        else {System.out.println("NotEnoughMaterialException-t dobott"); throw new  NotEnoughMaterialException("missing materials");}

    }

    public boolean canBuildBase(){
        System.out.println("Belépett a canBuildBase-be");
        Map<MaterialType, Integer> mats = countMaterials();
        System.out.println("Kilépett a canBuildBase-ből");
        return (mats.get(MaterialType.Iron) >= 3 && mats.get(MaterialType.Coal) >= 3 && mats.get(MaterialType.Uran) >= 3 && mats.get(MaterialType.Ice) >= 3);
    }

    public List<Material> getMaterials() {
        System.out.println("Belépett a getMaterials-ba");
        System.out.println("Kilépett a getMaterials-ból");
        return materials;
    }

    public MaterialArray plus(MaterialArray ma){
        System.out.println("Belépett a MaterialArray plus függvényébe");
        materials.addAll(ma.getMaterials());
        System.out.println("Kilépett a MaterialArray plus függvényéből");
        return new MaterialArray(new ArrayList<>(materials));

    }
}
