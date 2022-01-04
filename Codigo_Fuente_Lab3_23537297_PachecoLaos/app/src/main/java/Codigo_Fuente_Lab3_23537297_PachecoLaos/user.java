package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class user {
    String username;
    String password;
    date register;
    int id;
    
    user(String username, String password, int id, date register){
        this.username = username;
        this.password = password;
        this.id = id;
        this.register = register;
    }
    
    String getUsername(){
        return this.username;
    }
    String getPassword(){
        return this.password;
    }
    
    int getId(){
        return this.id;
    }
    
    date getRegister(){
        return this.register;
    }
    
    boolean sameUsername(String anotherUsername){
        return this.getUsername().equals(anotherUsername);
    }
    boolean samePassword(String anotherPassword){
        return this.getPassword().equals(anotherPassword);
    }
    
    String toStr(){
        return String.format("Nombre de usuario: %s\nContrasenia: %s\nId: %d\nFecha de registro: %s",
                this.getUsername(),
                this.getPassword(),
                this.getId(),
                this.getRegister().toStr()
                );
    }
}
