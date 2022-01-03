package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class user {
    String username;
    String password;
    int id;
    
    user(String username, String password, int id){
        this.username = username;
        this.password = password;
        this.id = id;
    }
    
    String getusername(){
        return this.username;
    }
    String getpassword(){
        return this.password;
    }
    
    int getid(){
        return this.id;
    }
    
    boolean sameUsername(String anotherUsername){
        return this.getusername().equals(anotherUsername);
    }
    boolean samePassword(String anotherPassword){
        return this.getpassword().equals(anotherPassword);
    }
    
    String toStr(){
        return String.format("Nombre de usuario: %s\nContrasenia: %s\nId: %d\n",
                this.getusername(),
                this.getpassword(),
                this.getid()
        );
    }
}
