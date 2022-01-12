package docs;

import java.util.ArrayList;

public class version {
    private String content;
    private int id;
    private ArrayList<comment> comments = new ArrayList<>();
    
    version(String content, int id){
        this.content = content;
        this.id = id;
    }
    
    String getContent(){
        return this.content;
    }
    int getId(){
        return this.id;
    }
    ArrayList<comment> getComments(){
        return this.comments;
    }
    
    boolean existText(String word){
        return this.content.contains(word);
    }
    
    boolean addComment(String textSelected, String comment){
        if(this.existText(textSelected)){
           this.comments.add(new comment(textSelected, comment));
           return true;
        }
        return false;
    }
    
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
