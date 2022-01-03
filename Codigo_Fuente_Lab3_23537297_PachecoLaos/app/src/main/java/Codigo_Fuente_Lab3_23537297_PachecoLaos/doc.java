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
    
    String getitle(){
        return this.title;
    }
    
    date getmaked(){
        return this.maked;
    }
    
    String getowner(){
        return this.owner;
    }
    
    int getid(){
        return this.id;
    }
    
    ArrayList getversions(){
        return this.versions;
    }
    
    ArrayList getaccesses(){
        return this.accesses;
    }
    
    boolean isOwner(String username){
        return this.getowner().equals(username);
    }
    
    void addVersion(version newversion){
        this.versions.add(newversion);
    }
    
    void addAccess(access newaccess){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(newaccess.getusername())){
                this.accesses.get(i).setpermissions(newaccess.getpermissions());
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
            return String.format(
                    "Titulo: %s\nTipo de acceso: propietario\nFecha de creacion: %s\n====Versiones====%s\n====Accesos====\n%s\n",
                    this.getitle(),
                    this.getmaked().toStr(),
                    blockversions,
                    blockaccesses
                    );
        }else if(this.canWhat(username,"R")){
            return String.format("Titulo: %s\nFecha de creacion: %s\n====Contenido====%s\n====Tipo de acceso====\n%s\n",
            this.getitle(),
            this.getmaked().toStr(),
            this.getLastVersion().toStr(),
            this.getSomeAccess(username).toStr()
                    );
        }else if(username.equals("")){
            return String.format(
                    "Titulo: %s\nPropietario:%s\nFecha de creacion: %s\n====Versiones====%s\n====Accesos====\n%s\n",
                    this.getitle(),
                    this.getowner(),
                    this.getmaked().toStr(),
                    blockversions,
                    blockaccesses
                    );
        }
        return "";
    }
}
