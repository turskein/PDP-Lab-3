package docs;

import modelos.date;
import java.util.ArrayList;

public class doc {
    private String title;
    private date maked;
    private String owner;
    private String text;
    private int id;
    private static int  counterIds = 0;
    private ArrayList<version> versions = new ArrayList<>();
    private ArrayList<access> accesses = new ArrayList<>();
    
    public doc(String title, date maked,String owner, String content){
        this.title = title;
        this.maked = maked;
        this.owner = owner;
        this.text = content;
        this.id = this.counterIds++;
        this.versions.add(new version(content, 0));
    }
    
    public String geTitle(){
        return this.title;
    }
    public date getMaked(){
        return this.maked;
    }
    public String getOwner(){
        return this.owner;
    }
    int getId(){
        return this.id;
    }
    public String getText(){
        return this.text;
    }
    public ArrayList<version> getVersions(){
        return this.versions;
    }
    public ArrayList<access> getAccesses(){
        return this.accesses;
    }
    public version getLastVersion(){
        return this.versions.get(this.versions.size()-1);
    }
    public access getSomeAccess(String username){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(username))
                return this.accesses.get(i);
        }
        return this.accesses.get(0);
    }
    public void addVersion(version newVersion){
        this.versions.add(newVersion);
    }
    
    public boolean isOwner(String username){
        return this.getOwner().equals(username);
    }
    public int cantVersions(){
        return this.getVersions().size();
    }
    public void addContent(String newcontenido){
        this.addVersion(new version(
                this.getLastVersion().getContent()+newcontenido,
                this.cantVersions()
        ));
        this.text = this.getLastVersion().getContent();
    }
    public void restoreVersion(int id){
        this.addVersion(new version(this.getVersions().get(id).getContent(),this.cantVersions()));
        this.text = this.getLastVersion().getContent();
    }
    public void revokeAccess(){
        this.accesses = new ArrayList<access>();
    }
    public boolean existText(String word){
        for(int i = 0; i < this.cantVersions(); i++){
            if(this.getVersions().get(i).existText(word)) return true;
        }
        return false;
    }
    public void addAccess(String username, String permiss){
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
    public boolean addComment(String textSelected, String comment){
        if(this.getLastVersion().addComment(textSelected, comment))
            return true;
        return false;
    }
    public boolean canWhat(String username, String permission){
        if(this.isOwner(username)) return true;
        for(int i = 0; i < this.accesses.size(); i ++){
            if(this.accesses.get(i).sameUsername(username)) 
                return this.accesses.get(i).canWhat(permission);
        }
        return false;
    }
    public boolean delete(String username, int numChars){
        if(this.canWhat(username,"W")){
            String contentLastVersion = this.getLastVersion().getContent();
            if(numChars > contentLastVersion.length()){
                this.addVersion(new version("", this.cantVersions()));
            }
                this.addVersion(
                        new version(
                                contentLastVersion.substring(0,contentLastVersion.length()-numChars),
                                this.cantVersions()
                        )
                );
                this.text = contentLastVersion.substring(0,contentLastVersion.length()-numChars);
                return true;
        }
        return false;
    }
    public boolean replace(String username, String oldChar, String newChar){
        if(this.canWhat(username, "W")){
            String text = this.text.replace(oldChar,newChar);
            this.addVersion(new version(text,this.cantVersions()));
            this.text = text;
            return true;
        }
        return false;
    }
    
    public String toStr(String username){
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
