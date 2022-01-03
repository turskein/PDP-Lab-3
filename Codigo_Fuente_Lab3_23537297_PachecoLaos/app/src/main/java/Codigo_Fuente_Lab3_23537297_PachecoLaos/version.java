package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class version {
    private String content;
    private int id;
    private date maked;
    
    version(String content, int id, date maked){
        this.content = content;
        this.id = id;
        this.maked = maked;
    }
    
    String getcontent(){
        return this.content;
    }
    int getid(){
        return this.id;
    }
    
    date getdate(){
        return this.maked;
    }
    
    String toStr(){
        return String.format("Contenido: %s\nid: %d,\ngenerado: %s",
                    this.getcontent(),
                    this.getid(),
                    this.getdate().toStr()
                );
    }
    
}
