package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;

public class access {
    String username;
    String permission;
    
    access(String username, String permission){
        this.username = username;
        this.permission = permission;
    }
    String getUsername(){
        return this.username;
    }
    
    String getPermission(){
        return this.permission;
    }
    
    void setPermission(String newpermission){
        this.permission = newpermission;
    }
    
    boolean canWhat(String permission){
        return this.getPermission().equals(permission);
    }
    boolean sameUsername(String username){
        return this.getUsername().equals(username);
    }
    boolean samePermission(String permiss){
        return this.getPermission().equals(permiss);
    }
    
    String toStr(){
    String permissionStr = "";
        switch(this.getPermission()){
            case "R":
                    permissionStr = "Lectura";
            case "W":
                    permissionStr = "Escritura";
            case "C":
                    permissionStr = "Comentar";
        }
        return String.format("username: %s\nTipo de permiso: %s",this.getUsername(),permissionStr);
        
        
    }
}
