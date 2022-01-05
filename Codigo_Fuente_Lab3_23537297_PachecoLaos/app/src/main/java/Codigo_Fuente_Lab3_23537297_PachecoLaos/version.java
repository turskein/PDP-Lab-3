package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class version {
    private String content;
    private int id;
    
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
    
    boolean existText(String word){
        return this.content.contains(word);
    }
    
    String toStr(){
        return String.format("Contenido: %s\nid: %d",
                    this.getContent(),
                    this.getId()
                );
    }
    
}
