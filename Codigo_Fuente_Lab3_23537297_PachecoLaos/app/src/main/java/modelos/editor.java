package modelos;

import docs.doc;
import modelos.date;
import java.util.ArrayList;

/**
 * clase que representa el editor donde se almacenan los users, documentos,
 * usuario logueado, etc.
 * @author
 */
public class editor {
    private String name;
    private date maked;
    private String logged;
    private ArrayList<user> users = new ArrayList<>();
    private ArrayList<doc> docs = new ArrayList<>();
    
    /**
     * Contructor editor
     * @param name nombre de la plataforma
     * @param maked fecha de creacion de la plataforma
     */
    public editor(String name, date maked){
        this.name = name;
        this.maked = maked;
        this.logged = "";
        this.users.add(new user("","", new date(1,1,1)));
    }
    /**
     * Getter de usuario logueado en el editor
     * @return nombre del usuario logueado
     */
    public String getLogged(){
        return this.logged;
    }
    /**
     * Getter de nombre de la plataforma
     * @return se obtiene el nombre de la plataforma
     */
    public String getName(){
        return this.name;
    }
    /**
     * Getter de la fecha de creacion de la plataforma
     * @return date de la fecha de creacion de la plataforma
     */
    public date getMaked(){
        return this.maked;
    }
    /**
     * Getter de la lista de usuario
     * @return se retorna un ArrayList con los usuario de la plataforma
     */
    public ArrayList<user> getUsers(){
        return this.users;
    }
    /**
     * Getter de un usuario del documento
     * @param username nombre de usuario
     * @return se retorna el usuario que coincide con el noombre ingresado
     */
    public user getSomeUser(String username){
        for(int i = 0; i < this.getUsers().size();i ++){
            if(this.getUsers().get(i).sameUsername(username)) return this.getUsers().get(i);
        }
        return this.getUsers().get(0);
    }
    /**
     * Getter de la lista de documentos de la plataforma
     * @return se retorna un ArrayList con todos los documentos
     */
    public ArrayList<doc> getDocs(){
        return this.docs;
    }
    /**
     * Getter de un documento en particular
     * @param id id del documento
     * @return se retorna un documento en particular
     */
    public doc getSomeDoc(int id){
        return this.docs.get(id);
    }
    /**
     * Devuelve la cantidad de documento que existen dentro de la plataforma
     * @return entero que representa la cantidad de documentos de la plataforma
     */
    public int cantDocs(){
        return this.getDocs().size();
    }
    /**
     * Cantidad de usuario que existen en la plataforma
     * @return entero que representa la cantidad de usuario registrados
     */
    public int cantUsers(){
        return this.getUsers().size();
    }
    /**
     * Cierra la sesion del editor
     */
    public void logout(){
        this.logged = "";
    }
    /**
     * agrega un nuevo documento a la plataforma
     * @param title titulo del documento
     * @param maked fecha de creacion del documento
     * @param contenido primer contenido del documento
     */
    public void addDoc(String title, date maked, String contenido){
        doc newdoc = new doc(title, maked, this.getLogged(), contenido);
        this.docs.add(newdoc);
    }
    /**
     * Agrega un usuario a la plataforma
     * @param username nombre de usuario de la plataforma
     * @param password contrasenia de la plataforma
     * @param register fecha de registro del usuario
     */
    public void addUser(String username, String password, date register){
        user newuser = new user(username, password, register);
        this.users.add(newuser);
    }
    /**
     * Comparte un documento con una lista de usuario senialados, solo se puede compartir documentos si se es owner
     * @param users Array de nombres de usuarios con los que compartir el documento
     * @param permiss Tipo de permiso a dar a los usuarios 
     * @param id id del documento a compartir
     * @return booleano que representa si se realizo la accion correspondientemente
    */
    public boolean shareDoc(String[] users, String permiss, int id){
        if(this.getSomeDoc(id).isOwner(this.getLogged())){
            for(int i = 0; i < users.length; i++){
                this.getSomeDoc(id).addAccess(users[i], permiss);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Agrega contenido a un documento en particular siempre y cuando se pueda
     * escribir en el documento
     * @param id        id del documento a afectar
     * @param contenido contenido a agregar en el documento
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean addContentToDoc(int id, String contenido){
        if(this.getSomeDoc(id).canWhat(this.getLogged(),"W")){
            this.getSomeDoc(id).addContent(contenido);
            return true;
        }
        return false;
    }
    /**
     * Lleva al tope de la lista de las versiones la version senialiada por su id
     * @param idDoc     id del documento a afectar
     * @param idVersion id de la version a restaurar
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean restoreVersion(int idDoc, int idVersion){
        if(this.getSomeDoc(idDoc).isOwner(this.getLogged())){
            this.getSomeDoc(idDoc).restoreVersion(idVersion);
            return true;
        }
        return false;
    }
    
    /**
     * elimina todos los accesos de la lista de accesos
     * 
     * @param idDoc id del documento a afectar
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean revokeAccess(int idDoc){
        if(this.getSomeDoc(idDoc).isOwner(this.getLogged())){
            this.getSomeDoc(idDoc).revokeAccess();
            return true;
        }
        return false;
    }
    /**
     * busca en todos los documentos en los que se puede como minimo leer una palabra en particular
     * @param word palabra a ser buscada
     * @return retorna como string todos los documentos donde existe esta informacion
     */
    public String search(String word){
        String docs = "";
        int i;
        for(i = 0; i < this.cantDocs(); i++){
            if(this.getSomeDoc(i).canWhat(this.getLogged(),"R")){
                docs = docs + this.getSomeDoc(i).toStr(this.getLogged())+"\n";
                i ++;
                break;
            }
        }
        while(i < this.cantDocs()){
            if(this.getSomeDoc(i).canWhat(this.getLogged(),"R")){
                docs = docs + this.getSomeDoc(i).toStr(this.getLogged()) + "\n";
            }
            i ++;
        }
        return docs;
    }
    
    /**
     * agrega un comentario a un documento senialado
     * 
     * @param id           id del documento al cual se la hace el comentario
     * @param textSelected texto seleccionado al que hacerle el comentario
     * @param comment      comentario que se realiza
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean addComment(int id,String textSelected, String comment){
        if(this.getSomeDoc(id).canWhat(this.getLogged(),"C"))
            return this.getSomeDoc(id).addComment(textSelected,comment);
        return false;
    }
    /**
     * verifica la existencia de un usuario en la plataforma
     * @param username nombre de usuario a verificar
     * @return booleano que representa la exitencia del usuario
     */
    public boolean existUsername(String username){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username))
                return true;
        }
        return false;
    }
    /**
     * verifica las credenciales de un usuario con las ingresados
     * @param username nombre de usuario a verificar
     * @param password contrasenia a verificar
     * @return booleano que representa la verificacion de algun usuario
     */
    public boolean authenticateUser(String username, String password){
        for(int i = 0; i < this.cantUsers(); i++){
            if(this.getUsers().get(i).sameUsername(username)){
                if(this.getUsers().get(i).samePassword(password)){
                    this.logged = username;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * Elimina un numero determinado de caracteres en un documento
     * 
     * @param id       id del documento a afectar
     * @param numChars numero de caracteres a eliminar
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean delete(int id, int numChars){
        return this.getSomeDoc(id).delete(this.getLogged(), numChars);
    }
    
    /**
     * Reemplaza cierto contenido del documento
     * 
     * @param id      id del documento a afectar
     * @param oldChar palabra a reemplazar
     * @param newChar palabra por la que reemplazar
     * @return booleano que representa si se realizo la accion correspondientemente
     */
    public boolean replace(int id, String oldChar, String newChar){
        return this.getSomeDoc(id).replace(this.logged,oldChar,newChar);
    }
    /**
     * Transforma en String todo el contenido de la plataforma
     * @return String con todo el contenido de la plataforma
     */
    public String toStr(){
        if(this.getLogged().equals("")){
            String blockUsers = "";
            
            for(int i = 1; i < this.getUsers().size(); i++){
                blockUsers = blockUsers + this.getUsers().get(i).toStr()+"\n";
            }
            String blockDocs = "";
            for(int i = 0; i < this.getDocs().size(); i++){
                blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
            }
            
            return String.format(
                    "<<<<<<<<<< Usuarios >>>>>>>>>>\n%s"+
                    "\n<<<<<<<<<< Documentos >>>>>>>>>>\n%s",
            blockUsers,blockDocs);
        }
        String blockDocs = "";
        for(int i = 0; i < this.getDocs().size(); i++){
            blockDocs = blockDocs + this.getSomeDoc(i).toStr(this.getLogged());
        }
        return String.format("<<<<<<<<<< Usuarios >>>>>>>>>>\n%s\n<<<<<<<<<< Documentos >>>>>>>>>>\n%s\n\n",this.getSomeUser(this.getLogged()).toStr(),blockDocs);
    }
}

