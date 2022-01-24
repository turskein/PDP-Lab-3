package docs;
/**
 * Clase que representa el TDa comentario con el texto 
 * al que se le hace el comentario y el comentario que se realiza
 * @author Alex Pacheco Laos
 */
public class comment {
    private String text;
    private String comment;
    
    /**
     * Contructor de comment
     * @param text texto seleccionado
     * @param comment comentario realizado
     */
    comment(String text, String comment){
        this.text = text;
        this.comment = comment;
    }
    
    /**
     * obtener texto selecionado
     * @return string que representa el texto seleccionado
     */
    String getText(){
        return this.text;
    }
    /**
     * obtener comentario realizado
     * @return string que representa el comentario realizado
     */
    String getComment(){
        return this.comment;
    }
    /**
     * representacion en string de la clase comment
     * @return retorno de objeto comment con formato string
     */
    String toStr(){
        return String.format("Texto comentado: %s \n Comentario: ");
    }
}
