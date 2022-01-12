package modelos;

import modelos.date;

public class user {
    private String username;
    private String password;
    private date register;
    private static int counterIds = 0;
    private int id;
    
    public user(String username, String password, date register){
        this.username = username;
        this.password = password;
        this.id = this.counterIds++;
        this.register = register;
    }
    
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    
    public int getId(){
        return this.id;
    }
    
    public date getRegister(){
        return this.register;
    }
    
    public boolean sameUsername(String anotherUsername){
        return this.getUsername().equals(anotherUsername);
    }
    public boolean samePassword(String anotherPassword){
        return this.getPassword().equals(anotherPassword);
    }
    
    public String toStr(){
        return String.format("Nombre de usuario: %s\nId: %d\nFecha de registro: %s",
                this.getUsername(),
                this.getId(),
                this.getRegister().toStr()
                );
    }
}
