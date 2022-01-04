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
        this.versions.add(new version(content, 0,maked));
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
    
    ArrayList getVersions(){
        return this.versions;
    }
    
    ArrayList getAccesses(){
        return this.accesses;
    }
    
    boolean isOwner(String username){
        return this.getOwner().equals(username);
    }
    
    void addVersion(version newversion){
        this.versions.add(newversion);
    }
    
    void addAccess(access newaccess){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(newaccess.getUsername())){
                this.accesses.get(i).setPermissions(newaccess.getPermissions());
                return;
            }
        }
        this.accesses.add(newaccess);
    }
    
    boolean canWhat(String username, String permission){
        for(int i = 0; i < this.accesses.size(); i ++){
            if(this.accesses.get(i).sameUsername(username)) 
                return this.accesses.get(i).canWhat(permission);
        }
        return false;
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
            return String.format("Titulo: %s\nTipo de acceso: propietario\nFecha de creacion: %s\n====Versiones====%s\n====Accesos====\n%s\n",
                    this.geTitle(),
                    this.getMaked().toStr(),
                    blockversions,
                    blockaccesses
                    );
        }else if(this.canWhat(username,"R")){
            return String.format("Titulo: %s\nFecha de creacion: %s\n====Contenido====%s\n====Tipo de acceso====\n%s\n",
            this.geTitle(),
            this.getMaked().toStr(),
            this.getLastVersion().toStr(),
            this.getSomeAccess(username).toStr()
                    );
        }else if(username.equals("")){
            return String.format("Titulo: %s\nPropietario:%s\nFecha de creacion: %s\n====Versiones====%s\n====Accesos====\n%s\n",
                    this.geTitle(),
                    this.getOwner(),
                    this.getMaked().toStr(),
                    blockversions,
                    blockaccesses
                    );
        }
        return "";
    }
}
