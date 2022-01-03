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
    }
    
    String getlogged(){
        return this.logged;
    }
    
    String getname(){
        return this.name;
    }
    
    date getmaked(){
        return this.maked;
    }
    
    void adddoc(){
    
    }
}

