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
    
    ArrayList<doc> getDocs(){
        return this.docs;
    }
    
    int cantDocs(){
        return this.getDocs().size();
    }
    
    int cantUsers(){
        return this.getUsers().size();
    }
    
    void addDoc(String title, date maked, String owner, String contenido){
        doc newdoc = new doc(title, maked, this.cantDocs(), owner, contenido);
        this.docs.add(newdoc);
    }
    
    void addUser(String username, String password, date register){
        user newuser = new user(username, password, this.cantUsers(), register);
        this.users.add(newuser);
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
}

