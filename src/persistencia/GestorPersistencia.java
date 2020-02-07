package persistencia;

import model.Estudi;
import principal.GestorEstudisException;

/**
 *
 * @author cesca
 */
public class GestorPersistencia {
    
    private ProveedorPersistencia gestor;

    public ProveedorPersistencia getGestor() {
        return gestor;
    }

    public void setGestor(ProveedorPersistencia pGestor) {
        gestor = pGestor;
    }

    public void desarEstudi(String tipusPersistencia, String nomFitxer, Estudi estudi) throws GestorEstudisException{
        switch(tipusPersistencia){
            
            case "XML":
                gestor = new GestorXML();
                break;                
            default:
                gestor = new GestorSerial();
                break;
            
        }

        gestor.desarEstudi(nomFitxer, estudi);
    }

    public void carregarEstudi(String tipusPersistencia, String nomFitxer) throws GestorEstudisException{
       
        switch(tipusPersistencia){
            
            case "XML":
                gestor = new GestorXML();
                break;                
            default:
                gestor = new GestorSerial();
                break;
            
        }

        gestor.carregarEstudi(nomFitxer);
    }
}
