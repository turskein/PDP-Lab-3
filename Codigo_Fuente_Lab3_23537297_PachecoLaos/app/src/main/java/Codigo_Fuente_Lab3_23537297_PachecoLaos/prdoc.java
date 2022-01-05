package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;

public class prdoc {
    String name;
    date maked;
    String logged;
    ArrayList<user> users = new ArrayList<>();
    ArrayList<doc> docs = new ArrayList<>();
    
    prdoc(String name, date maked){
        this.name = name;
        this.maked = maked;
        this.logged = "";
        this.users.add(new user("","",0,new date(1,1,1)));
    }
    
    String getLogged(){
        return this.logged;
    }
    
    String getName(){
        return this.name;
    }
    
    date getMaked(){
        return this.maked;
    }
    
    ArrayList<user> getUsers(){
        return this.users;
    }
    
    user getSomeUser(String username){
        for(int i = 0; i < this.getUsers().size();i ++){
            if(this.getUsers().get(i).sameUsername(username)) return this.getUsers().get(i);
        }
        return this.getUsers().get(0);
    }
    
    ArrayList<doc> getDocs(){
        return this.docs;
    }
    
    doc getSomeDoc(int id){
        return this.docs.get(id);
    }
    
    int cantDocs(){
        return this.getDocs().size();
    }
    
    int cantUsers(){
        return this.getUsers().size();
    }
    
    void addDoc(String title, date maked, String contenido){
        doc newdoc = new doc(title, maked, this.cantDocs(), this.getLogged(), contenido);
        this.docs.add(newdoc);
    }
    
    void addUser(String username, String password, date register){
        user newuser = new user(username, password, this.cantUsers(), register);
        this.users.add(newuser);
    }
    
    boolean shareDoc(String[] users, String permiss, int id){
        if(this.getSomeDoc(id).isOwner(this.getLogged())){
            for(int i = 0; i < users.length; i++){
                this.getSomeDoc(id).addAccess(users[i], permiss);
            }
            return true;
        }
        return false;
    }
    
    boolean addContentToDoc(int id, String contenido){
        if(this.getSomeDoc(id).canWhat(this.getLogged(),"W")){
            this.getSomeDoc(id).addContent(contenido);
            return true;
        }
        return false;
    }
    
    boolean existUsername(String username){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username))
                return true;
        }
        return false;
    }
    
    void log(String username){
        this.logged = username;
    }
    
    boolean authenticateUser(String username, String password){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username)){
                return this.getUsers().get(i).samePassword(password);
            }
        }
        return false;
    }
    
    String toStr(){
        if(this.getLogged().equals("")){
            String blockUsers = "";
            
            for(int i = 0; i < this.getUsers().size(); i++){
                blockUsers = blockUsers + this.getUsers().get(i).toStr();
            }
            String blockDocs = "";
            for(int i = 0; i < this.getDocs().size(); i++){
                blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
            }
            
            return String.format(
                    "===Usuarios===\n%s"+
                    "\n===Documentos===\n%s",
            blockUsers,blockDocs);
        }
        String blockDocs = "";
        for(int i = 0; i < this.getDocs().size(); i++){
            blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
        }
        return String.format("===Usuario===\n%s\n===Documentos===\n%s\n\n",this.getSomeUser(this.getLogged()).toStr(),blockDocs);
    }
}
