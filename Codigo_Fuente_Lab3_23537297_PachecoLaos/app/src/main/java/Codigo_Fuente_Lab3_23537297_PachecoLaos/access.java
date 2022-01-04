package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;

public class access {
    String username;
    ArrayList<String> permissions = new ArrayList<>();
    
    access(String username, ArrayList permissions){
        this.username = username;
        this.permissions = permissions;
    }
    String getUsername(){
        return this.username;
    }
    
    ArrayList getPermissions(){
        return this.permissions;
    }
    
    void setPermissions(ArrayList newpermissions){
        this.permissions = newpermissions;
    }
    
    boolean canWhat(String permission){
        for(int i = 0; i < this.permissions.size(); i ++){
            if(this.permissions.get(i).equals(permission)) return true;
        }
        return false;
    }
    boolean sameUsername(String username){
        return this.getUsername().equals(username);
    }
    
    String toStr(){
    String permissionsStr = "";
        switch(this.permissions.get(0)){
            case "R":
                    permissionsStr = "Lectura";
            case "W":
                    permissionsStr = "Escritura";
            case "C":
                    permissionsStr = "Comentar";
        }
        for(int i = 1; i < this.permissions.size(); i ++){
            switch(this.permissions.get(i)){
            case "R":
                    permissionsStr.concat("-Lectura");
            case "W":
                    permissionsStr.concat("-Escritura");
            case "C":
                    permissionsStr.concat("-Comentar");
            }
        }
        return String.format("username: %s\nTipos de permiso: %s",this.getUsername(),permissionsStr);
        
        
    }
}
