package docs;

import java.util.ArrayList;

public class access {
    private String username;
    private String permission;
    
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
        if(permission.equals('W')){
            return this.getPermission().equals('W');
        }else if(permission.equals('R')){
            return  this.getPermission().equals('R') ||
                    this.getPermission().equals('W') ||
                    this.getPermission().equals('C');
        }else if(permission.equals('C')){
            return  this.getPermission().equals('W') ||
                    this.getPermission().equals('C');
        }
        return false;
    }
    boolean sameUsername(String username){
        return this.getUsername().equals(username);
    }
    boolean samePermission(String permiss){
        return this.getPermission().equals(permiss);
    }
    
    String toStr(){
    String permissionStr = "";
        if(this.getPermission().equals("R")) permissionStr = "Lectura";
        if(this.getPermission().equals("W")) permissionStr = "Escritura";
        if(this.getPermission().equals("C")) permissionStr = "Comentar";

        return String.format("username: %s\nTipo de permiso: %s",this.getUsername(),permissionStr);
        
        
    }
}