package persistencia;

import model.Estudi;
import principal.GestorEstudisException;

/**
 *
 * @author cesca
 */
public interface ProveedorPersistencia {
    public void desarEstudi(String nomFitxer, Estudi estudi)throws GestorEstudisException;
    public void carregarEstudi(String nomFitxer)throws GestorEstudisException; 
}
