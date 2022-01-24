package modelos;

import modelos.date;

/**
 * Clase que representa el TDA user con su username,
 * contraseña, fecha de resgistro y su id correspondiente 
 * @author Alex Pacheco laos
 */
public class user {
    private String username;
    private String password;
    private date register;
    private static int counterIds = 0;
    private int id;
    
    /**
     * Constructor de user
     * @param username nombre del usuario
     * @param password contrasenio del usuario
     * @param register fecha de registro del usuario
     */
    public user(String username, String password, date register){
        this.username = username;
        this.password = password;
        this.id = this.counterIds++;
        this.register = register;
    }
    
    /**
     * getter para el username del usuario
     * @return el nombre del usuario
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * getter de password del usuario
     * @return retorna la constrasenia del usuario
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Getter del id del user
     * @return retorna el id del usuario
     */
    public int getId(){
        return this.id;
    }
    /**
     * getter de la fecha de registro del user
     * @return retorna la fecha de registro del user
     */
    public date getRegister(){
        return this.register;
    }

    /**
     * verifica si el texto ingresado coincide con el nombre de usuario
     * @param anotherUsername nombre de usuario a verificar
     * @return booleano que dependera de si el nombre de usuario coincide
     */
    public boolean sameUsername(String anotherUsername){
        return this.getUsername().equals(anotherUsername);
    }
    
    /**
     * verifica si el texto ingresado coincide con la contrasenia
     * @param anotherPassword contrasenia a verificar
     * @return booleano que dependera de si la contrasenia coincide
     */
    public boolean samePassword(String anotherPassword){
        return this.getPassword().equals(anotherPassword);
    }
    /**
     * transformacion de objeto a strin
     * @return retorna usuario transformado en string
     */
    public String toStr(){
        return String.format("Nombre de usuario: %s\nId: %d\nFecha de registro: %s",
                this.getUsername(),
                this.getId(),
                this.getRegister().toStr()
                );
    }
}
