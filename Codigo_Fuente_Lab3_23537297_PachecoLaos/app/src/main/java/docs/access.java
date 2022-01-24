package docs;

import java.util.ArrayList;
/**
 * Clase que representa el TDA acceso
 * que da forma al tipo de acceso que tiene un usuario
 * @author Alex Pacheco Laos
 */
public class access {
    private String username;
    private String permission;
    
    /**
     * Contructor del acceso
     * @param username nombre del usuario
     * @param permission Tipo de permiso que tiene
     */
    access(String username, String permission){
        this.username = username;
        this.permission = permission;
    }
    /**
     * obtiene el nombre de usuario del acceso en particular
     * @return retorna usuario del acceso
     */
    String getUsername(){
        return this.username;
    }
    /**
     * obtiene el tipo de permiso del acceso
     * @return retorna el tipo de permiso que tiene el acceso
     */
    String getPermission(){
        return this.permission;
    }
    /**
     * Settea el permiso del acceso por el ingresado
     * @param newpermission tipo de permiso nuevo
     */
    void setPermission(String newpermission){
        this.permission = newpermission;
    }
    /**
     * verifica que tipo de acceso tiene el usuario empleando la jerarquia de accesos
     * @param permission tipo de permiso
     * @return booleano de tipo si verifica tal tipo de permiso
     */
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
    /**
     * verifica si un acceso coincide con un nombre de usuario
     * @param username nombre de usuario a verificar
     * @return booleano que representa si el nombre usuario ingresado coincide con el del acceso
     */
    boolean sameUsername(String username){
        return this.getUsername().equals(username);
    }
    /**
     * verifica si un acceso tiene determinado permiso
     * @param permiss tipo de permiso
     * @return booleano de tipo este acceso tiene este permiso
     */
    boolean samePermission(String permiss){
        return this.getPermission().equals(permiss);
    }
    /**
     * transforma el acceso a str
     * @return String con formato del acceso
     */
    String toStr(){
    String permissionStr = "";
        if(this.getPermission().equals("R")) permissionStr = "Lectura";
        if(this.getPermission().equals("W")) permissionStr = "Escritura";
        if(this.getPermission().equals("C")) permissionStr = "Comentar";

        return String.format("username: %s\nTipo de permiso: %s",this.getUsername(),permissionStr);
    }
}
