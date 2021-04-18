package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.LayerNot0Exception;

public interface iMine {
    void mine() throws CantBeMinedException, LayerNot0Exception;
}
