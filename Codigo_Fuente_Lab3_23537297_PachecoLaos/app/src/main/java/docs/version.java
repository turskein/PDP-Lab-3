package docs;

import java.util.ArrayList;
/**
 * Clase que representa el TDA version el cual cierta 
 * informacion en particular, el id correspondiente a la version
 * y el array de comentarios ligado a el
 * @author Alex Pacheco Laos
 */
public class version {
    private String content;
    private int id;
    private ArrayList<comment> comments = new ArrayList<>();
    
    /**
     * Constructor de version
     * @param content contenido de la version
     * @param id id correspondiente a la version en particular
     */
    version(String content, int id){
        this.content = content;
        this.id = id;
    }
    /**
     * Getter de contenido de la version
     * @return retorna el contenido de la version
     */
    String getContent(){
        return this.content;
    }
    /**
     * getter de el id de la version
     * @return id de la version
     */
    int getId(){
        return this.id;
    }
    /**
     * getter de los comentarios de la version
     * @return retorna la lista de comentarios ligados a la version
     */
    ArrayList<comment> getComments(){
        return this.comments;
    }
    
    /**
     * Verifica si cierto contenido pertenece a la version
     * @param word palabra a ser buscada dentro de la version
     * @return retorna el boolean de acuerdo a la existencia de la palabra o no
     */
    boolean existText(String word){
        return this.content.contains(word);
    }
    /**
     * Agrega un comentario a la version
     * @param textSelected texto seleccionado de la version
     * @param comment texto de 
     * @return retorna un booleano de acuerdo a si se realizo la accion
     */
    boolean addComment(String textSelected, String comment){
        if(this.existText(textSelected)){
           this.comments.add(new comment(textSelected, comment));
           return true;
        }
        return false;
    }
    /**
     * Transforma el objeto en un string
     * @return formato de version en string
     */
    String toStr(){
        String blockComments = "";
        if(!this.getComments().isEmpty()){
            blockComments = "\n     Comentarios:\n";
            for(int i = 0; i < this.getComments().size();i++){
                blockComments = blockComments+ String.format("            Texto: %s, Comentario: %s\n",this.getComments().get(i).getText(),this.getComments().get(i).getComment());
            }
        }
        
        return String.format("Contenido: %s\nid: %d%s",
                    this.getContent(),
                    this.getId(),
                    blockComments
                );
    }
    
}
