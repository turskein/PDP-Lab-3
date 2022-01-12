package modelos;

import docs.doc;
import modelos.date;
import java.util.ArrayList;

public class editor {
    private String name;
    private date maked;
    private String logged;
    private ArrayList<user> users = new ArrayList<>();
    private ArrayList<doc> docs = new ArrayList<>();
    
    public editor(String name, date maked){
        this.name = name;
        this.maked = maked;
        this.logged = "";
        this.users.add(new user("","", new date(1,1,1)));
    }
    
    public String getLogged(){
        return this.logged;
    }
    public String getName(){
        return this.name;
    }    
    public date getMaked(){
        return this.maked;
    }
    public ArrayList<user> getUsers(){
        return this.users;
    }
    public user getSomeUser(String username){
        for(int i = 0; i < this.getUsers().size();i ++){
            if(this.getUsers().get(i).sameUsername(username)) return this.getUsers().get(i);
        }
        return this.getUsers().get(0);
    }
    public ArrayList<doc> getDocs(){
        return this.docs;
    }
    public doc getSomeDoc(int id){
        return this.docs.get(id);
    }
    
    public int cantDocs(){
        return this.getDocs().size();
    }
    public int cantUsers(){
        return this.getUsers().size();
    }
    public void addDoc(String title, date maked, String contenido){
        doc newdoc = new doc(title, maked, this.getLogged(), contenido);
        this.docs.add(newdoc);
    }
    public void addUser(String username, String password, date register){
        user newuser = new user(username, password, register);
        this.users.add(newuser);
    }
    public boolean shareDoc(String[] users, String permiss, int id){
        if(this.getSomeDoc(id).isOwner(this.getLogged())){
            for(int i = 0; i < users.length; i++){
                this.getSomeDoc(id).addAccess(users[i], permiss);
            }
            return true;
        }
        return false;
    }
    public boolean addContentToDoc(int id, String contenido){
        if(this.getSomeDoc(id).canWhat(this.getLogged(),"W")){
            this.getSomeDoc(id).addContent(contenido);
            return true;
        }
        return false;
    }
    public boolean restoreVersion(int idDoc, int idVersion){
        if(this.getSomeDoc(idDoc).isOwner(this.getLogged())){
            this.getSomeDoc(idDoc).restoreVersion(idVersion);
            return true;
        }
        return false;
    }
    public boolean revokeAccess(int idDoc){
        if(this.getSomeDoc(idDoc).isOwner(this.getLogged())){
            this.getSomeDoc(idDoc).revokeAccess();
            return true;
        }
        return false;
    }
    public String search(String word){
        String docs = "";
        int i;
        for(i = 0; i < this.cantDocs(); i++){
            if(this.getSomeDoc(i).canWhat(this.getLogged(),"R")){
                docs = docs + String.format("%d",i);
            }
        }
        while(i < this.cantDocs()){
            if(this.getSomeDoc(i).canWhat(this.getLogged(),"R")){
                docs = docs + String.format("-%d",i);
            }
            i ++;
        }
        return docs;
    }
    public boolean addComment(int id,String textSelected, String comment){
        if(this.getSomeDoc(id).canWhat(this.getLogged(),"C"))
            return this.getSomeDoc(id).addComment(textSelected,comment);
        return false;
    }
    
    public boolean existUsername(String username){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username))
                return true;
        }
        return false;
    }
    public boolean authenticateUser(String username, String password){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username)){
                if(this.getUsers().get(i).samePassword(password)){
                    this.logged = username;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    public boolean delete(int id, int numChars){
        return this.getSomeDoc(id).delete(this.getLogged(), numChars);
    }
    public boolean replace(int id, String oldChar, String newChar){
        return this.getSomeDoc(id).replace(this.logged,oldChar,newChar);
    }
    public String toStr(){
        if(this.getLogged().equals("")){
            String blockUsers = "";
            
            for(int i = 1; i < this.getUsers().size(); i++){
                blockUsers = blockUsers + this.getUsers().get(i).toStr()+"\n";
            }
            String blockDocs = "";
            for(int i = 0; i < this.getDocs().size(); i++){
                blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
            }
            
            return String.format(
                    "<<<<<<<<<< Usuarios >>>>>>>>>>\n%s"+
                    "\n<<<<<<<<<< Documentos >>>>>>>>>>\n%s",
            blockUsers,blockDocs);
        }
        String blockDocs = "";
        for(int i = 0; i < this.getDocs().size(); i++){
            blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
        }
        return String.format("<<<<<<<<<< Usuarios >>>>>>>>>>\n%s\n<<<<<<<<<< Documentos >>>>>>>>>>\n%s\n\n",this.getSomeUser(this.getLogged()).toStr(),blockDocs);
    }
}

