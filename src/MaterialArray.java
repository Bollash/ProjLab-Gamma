import exceptions.NotEnoughMaterialException;

import java.util.ArrayList;
import java.util.List;

public class MaterialArray {
    private List<Material> materials;

    public MaterialArray(){
        materials = new ArrayList<>();
    }

    public MaterialArray(List<Material> materials){
        this.materials = materials;
    }

    public TpGate[] buildGates()throws NotEnoughMaterialException {
        asd;
        asd;
    }

    public void addMaterial(Material mat){}

    public Robot buildRobot()throws NotEnoughMaterialException{}

    public boolean canBuildBase(){}

    public List<Material> getMaterials() {
        return materials;
    }

    //nem biztos, hogy ez úgy működik ahogy akarom. Lehet elvesznek az elemek
    public MaterialArray plus(MaterialArray ma){
        materials.addAll(ma.getMaterials());
        return new MaterialArray(new ArrayList<>(materials));

    }
}
