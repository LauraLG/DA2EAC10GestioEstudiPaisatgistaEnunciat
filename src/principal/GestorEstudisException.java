package principal;

/**
 *
 * @author cesca
 */
public class GestorEstudisException extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public GestorEstudisException(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                this.missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                this.missatge = "No te cap torn assignat";
                break;
            case "3":
                this.missatge = "El projecte ja té assignat un dissenyador o dissenyadora";
                break;
            case "4":
                this.missatge = "Aquest jardiner ja està assignat al projecte";
                break;
            case "5":
                this.missatge = "No existeix aquest torn";
                break;
            case "6":
                this.missatge = "No existeix aquest jardiner o jardinera";
                break;
            case "7":
                this.missatge = "No existeix aquest treballador o treballadora";
                break;
            case "8":
                this.missatge = "No existeix aquest projecte";
                break;
            case "9":
                missatge = "Ja no hi caben més components";
                break;
            case "GestorXML.model":
                this.missatge = "No s'ha pogut crear el model XML per desar l'estudi";
                break;
            case "GestorXML.desar":
            case "GestorSerial.desar":
                this.missatge = "No s'ha pogut desar l'estudi a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
            case "GestorSerial.carrega":
                this.missatge = "No s'ha pogut carregar l'estudi a causa d'error d'entrada/sortida";
                break;
            default:
                this.missatge = "Error desconegut";
                break;
        }
    }

    @Override
    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }
}
