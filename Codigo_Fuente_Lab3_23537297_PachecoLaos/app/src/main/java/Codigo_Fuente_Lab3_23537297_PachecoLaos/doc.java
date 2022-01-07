package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;

public class doc {
    String title;
    date maked;
    String owner;
    String text;
    int id;
    ArrayList<version> versions = new ArrayList<>();
    ArrayList<access> accesses = new ArrayList<>();
    
    doc(String title, date maked, int id,String owner, String content){
        this.title = title;
        this.maked = maked;
        this.owner = owner;
        this.text = content;
        this.id = id;
        this.versions.add(new version(content, 0));
    }
    
    String geTitle(){
        return this.title;
    }
    
    date getMaked(){
        return this.maked;
    }
    
    String getOwner(){
        return this.owner;
    }
    
    int getId(){
        return this.id;
    }
    
    String getText(){
        return this.text;
    }
    
    ArrayList<version> getVersions(){
        return this.versions;
    }
    
    ArrayList<access> getAccesses(){
        return this.accesses;
    }
    
    version getLastVersion(){
        return this.versions.get(this.versions.size()-1);
    }
    access getSomeAccess(String username){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(username))
                return this.accesses.get(i);
        }
        return this.accesses.get(0);
    }
    
    boolean isOwner(String username){
        return this.getOwner().equals(username);
    }
    
    void addContent(String newcontenido){
        this.getVersions().add(new version(
                this.getLastVersion().getContent()+newcontenido,
                this.getVersions().size()
        ));
        this.text = this.getLastVersion().getContent();
    }
    
    void restoreVersion(int id){
        this.versions.add(this.getVersions().get(id));
        this.text = this.getLastVersion().getContent();
    }
    
    void revokeAccess(){
        this.accesses = new ArrayList<access>();
    }
    
    boolean existText(String word){
        for(int i = 0; i < this.getVersions().size(); i++){
            if(this.getVersions().get(i).existText(word)) return true;
        }
        return false;
    }
    
    void addAccess(String username, String permiss){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(username)){
                if(!this.accesses.get(i).samePermission(permiss)){
                    this.accesses.get(i).setPermission(permiss);
                }
                return;
            }
        }
        this.accesses.add(new access(username, permiss));
    }
    
    boolean addComment(String textSelected, String comment){
        if(this.getLastVersion().addComment(textSelected, comment))
            return true;
        return false;
    }
    
    boolean canWhat(String username, String permission){
        if(this.isOwner(username)) return true;
        for(int i = 0; i < this.accesses.size(); i ++){
            if(this.accesses.get(i).sameUsername(username)) 
                return this.accesses.get(i).canWhat(permission);
        }
        return false;
    }
    
    String toStr(String username){
        String blockversions = "";
        for(int i = 0; i < this.versions.size();i++){
            blockversions = blockversions + this.versions.get(i).toStr() + "\n";
        }
        String blockaccesses = "";
        for(int i = 0; i < this.accesses.size();i++){
            blockaccesses = blockaccesses + this.accesses.get(i).toStr() + "\n";
        }
        
        if(this.isOwner(username)){
            return String.format("########## Titulo: %s##########\nTipo de acceso: propietario\nFecha de creacion: %s\nContenido: %s\n====Versiones====\n%s====Accesos====\n%s\n",
                    this.geTitle(),
                    this.getMaked().toStr(),
                    this.getText(),
                    blockversions,
                    blockaccesses
                    );
        }else if(this.canWhat(username,"R")){
            return String.format("########## Titulo: %s##########\nFecha de creacion: %s\n====Contenido====%s\n====Tipo de acceso====\n%s\n",
            this.geTitle(),
            this.getMaked().toStr(),
            this.getText(),
            this.getSomeAccess(username).toStr()
                    );
        }else if(username.equals("")){
            return String.format("########## Titulo: %s##########\nPropietario:%s\nFecha de creacion: %s\nContenido: %s\n====Versiones====\n%s\n====Accesos====\n%s",
                    this.geTitle(),
                    this.getOwner(),
                    this.getMaked().toStr(),
                    this.getText(),
                    blockversions,
                    blockaccesses
                    );
        }
        return "";
    }
}
