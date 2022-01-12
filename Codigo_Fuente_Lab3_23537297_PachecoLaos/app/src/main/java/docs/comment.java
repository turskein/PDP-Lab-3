package docs;

public class comment {
    private String text;
    private String comment;
    
    comment(String text, String comment){
        this.text = text;
        this.comment = comment;
    }
    
    String getText(){
        return this.text;
    }
    
    String getComment(){
        return this.comment;
    }
    
    String toStr(){
        return String.format("Texto comentado: %s \n Comentario: ");
    }
}
