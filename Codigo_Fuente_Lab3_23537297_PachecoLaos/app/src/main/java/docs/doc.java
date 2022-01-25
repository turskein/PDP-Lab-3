package docs;

import modelos.date;
import java.util.ArrayList;

/**
 * Clase que representa el documento donde es posible
 *  agregar contenido, compartirlo, etc.
 * @author Alex Pacheco Laos
 */
public class doc {
    private String title;
    private date maked;
    private String owner;
    private String text;
    private int id;
    private static int  counterIds = 0;
    private ArrayList<version> versions = new ArrayList<>();
    private ArrayList<access> accesses = new ArrayList<>();
    
    /**
     * Constructor de la clase Documento
     * @param title     titulo del documento a crear
     * @param maked     fecha de la creación del documento
     * @param owner     propietario del documento
     * @param content   Primer contenido del documento a crear
     */
    public doc(String title, date maked,String owner, String content){
        this.title = title;
        this.maked = maked;
        this.owner = owner;
        this.text = content;
        this.id = this.counterIds++;
        this.versions.add(new version(content, 0));
    }
    /**
     * Getter del titulo
     * @return retorno del titulo del documento
     */
    public String geTitle(){
        return this.title;
    }
    /**
     * Getter de la fecha de creacion
     * @return retorno de la fecha de creacion
     */
    public date getMaked(){
        return this.maked;
    }
    /**
     * Getter del nombre del propietario del documento
     * @return retorno del nombre del propietario del documento
     */
    public String getOwner(){
        return this.owner;
    }
    /**
     * Getter del id del documento
     * @return retorno del id del documento
     */
    public int getId(){
        return this.id;
    }
    /**
     * Getter del texto actual del documento
     * @return retorna el texto actual del documento
     */
    public String getText(){
        return this.text;
    }
    /**
     * Getter de las versiones
     * @return retorna un ArrayList de todas las versiones del documento
     */
    public ArrayList<version> getVersions(){
        return this.versions;
    }
    
    /**
     * Getter de los accesos
     * @return retorna un ArrayList de todas los accesos del documento
     */
    public ArrayList<access> getAccesses(){
        return this.accesses;
    }
    /**
     * Obtener la ultima version de la lista de versiones
     * @return retorna el ultimo elemento de la lista de versiones
     */
    public version getLastVersion(){
        return this.versions.get(this.versions.size()-1);
    }
    /**
     * obtiene el acceso de un usuario en particular
     * @param username nombre del usuario en particular
     * @return acceso del usuario que se está ingresando
     */
    public access getSomeAccess(String username){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(username))
                return this.accesses.get(i);
        }
        return this.accesses.get(0);
    }
    /**
     * Agrega una nueva version a la lista de versiones
     * @param newVersion nueva version que sera agregada a la lista
     */
    public void addVersion(version newVersion){
        this.versions.add(newVersion);
    }
    /**
     * verifica si un usuario es propietario o no
     * @param username nombre de usuario a verificar
     * @return booleano de si es propietario o no
     */
    public boolean isOwner(String username){
        return this.getOwner().equals(username);
    }
    /**
     * Cantidad de versiones que existe en un documento
     * @return Numero de versiones que hay en el doc
     */
    public int cantVersions(){
        return this.getVersions().size();
    }
    /**
     * Agrega contenido a un documento
     * @param newcontenido string que sera agregado al doc
     */
    public void addContent(String newcontenido){
        this.addVersion(new version(
                this.getLastVersion().getContent()+newcontenido,
                this.cantVersions()
        ));
        this.text = this.getLastVersion().getContent();
    }
    /**
     * Copia en la lista de versiones una version en particular senialando su id
     * @param id id de la version a restaurar
     */
    public void restoreVersion(int id){
        this.addVersion(new version(this.getVersions().get(id).getContent(),this.cantVersions()));
        this.text = this.getLastVersion().getContent();
    }
    /**
     * Elimina todos los accesos del documento
     */
    public void revokeAccess(){
        this.accesses = new ArrayList<access>();
    }
    /**
     * Verifica la existencia de cierto string en el documento completo
     * @param word Palabra a buscar
     * @return booleano de tipo existe o no existe
     */
    public boolean existText(String word){
        for(int i = 0; i < this.cantVersions(); i++){
            if(this.getVersions().get(i).existText(word)) return true;
        }
        return false;
    }
    /**
     * Agrega un acceso al documento señalando el username y el tipo de permiso
     * @param username Nombre del usuario
     * @param permiss Tipo de permiso
     */
    public void addAccess(String username, String permiss){
        for(int i = 0; i < this.accesses.size(); i++){
            if(this.accesses.get(i).sameUsername(username)){
                if(!this.accesses.get(i).samePermission(permiss)){
                    this.accesses.get(i).setPermission(permiss);
                }
                return;
            }
        }
        this.accesses.add(new access(username, permiss));
    }
    /**
     * Realiza comentarios a la utlima version del doc
     * @param textSelected texto al que se le hace el comentario
     * @param comment comentario
     * @return realizacion del comentario si o no
     */
    public boolean addComment(String textSelected, String comment){
        if(this.getLastVersion().addComment(textSelected, comment))
            return true;
        return false;
    }
    /**
     * Verifica si un usuario tiene determinado permiso
     * @param username nombre de usuario
     * @param permission tipo de permiso
     * @return booleano de tipo si tiene permiso para ello o no
     */
    public boolean canWhat(String username, String permission){
        if(this.isOwner(username)) return true;
        for(int i = 0; i < this.accesses.size(); i ++){
            if(this.accesses.get(i).sameUsername(username)) 
                return this.accesses.get(i).canWhat(permission);
        }
        return false;
    }
    /**
     * Elimina ciertos caracteres del contenido del documento
     * @param username usuario que realiza la accion
     * @param numChars numero de caracteres que se estan borrando
     * @return boolean de tipo se realizo la accion o no
     */
    public boolean delete(String username, int numChars){
        if(this.canWhat(username,"W")){
            String contentLastVersion = this.getLastVersion().getContent();
            if(numChars > contentLastVersion.length()){
                this.addVersion(new version("", this.cantVersions()));
            }
                this.addVersion(
                        new version(
                                contentLastVersion.substring(0,contentLastVersion.length()-numChars),
                                this.cantVersions()
                        )
                );
                this.text = contentLastVersion.substring(0,contentLastVersion.length()-numChars);
                return true;
        }
        return false;
    }
    
    /**
     * Reemplaza cierta palabra dentro del documento
     * @param username usuario que realiza la accion
     * @param oldChar palabra a ser reemplazada
     * @param newChar palabra por la cual realizar el reemplazo
     * @return boolean de tipo se realizo la accion o no
     */
    public boolean replace(String username, String oldChar, String newChar){
        if(this.canWhat(username, "W")){
            String text = this.text.replace(oldChar,newChar);
            this.addVersion(new version(text,this.cantVersions()));
            this.text = text;
            return true;
        }
        return false;
    }
    
    /**
     * Transforma todo el documento en str con un formato en particular
     * @param username usuario que realiza la accion
     * @return string con el formato de todo el documento
     */
    public String toStr(String username){
        String blockversions = "";
        for(int i = 0; i < this.versions.size();i++){
            blockversions = blockversions + this.versions.get(i).toStr() + "\n";
        }
        String blockaccesses = "";
        for(int i = 0; i < this.accesses.size();i++){
            blockaccesses = blockaccesses + this.accesses.get(i).toStr() + "\n";
        }
        
        if(this.isOwner(username)){
            return String.format("########## Titulo: %s##########\nTipo de acceso: propietario\nFecha de creacion: %s\nContenido: %s\n====Versiones====\n%s====Accesos====\n%s\n",
                    this.geTitle(),
                    this.getMaked().toStr(),
                    this.getText(),
                    blockversions,
                    blockaccesses
                    );
        }else if(this.canWhat(username,"R")){
            return String.format("########## Titulo: %s##########\nFecha de creacion: %s\n====Contenido====%s\n====Tipo de acceso====\n%s\n",
            this.geTitle(),
            this.getMaked().toStr(),
            this.getText(),
            this.getSomeAccess(username).toStr()
                    );
        }else if(username.equals("")){
            return String.format("########## Titulo: %s##########\nId: %d\nPropietario:%s\nFecha de creacion: %s\nContenido: %s\n====Versiones====\n%d\n====Accesos====\n%s",
                    this.geTitle(),
                    this.getId(),
                    this.getOwner(),
                    this.getMaked().toStr(),
                    this.getText(),
                    this.cantVersions(),
                    blockaccesses
                    );
        }
        return "";
    }
}
