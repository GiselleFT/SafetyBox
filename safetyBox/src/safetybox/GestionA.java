
package safetybox;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionA {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public GestionA() {
    }
    
    public String AbrirTexto(File archivo){
        String contenido = "";
        try{
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci = entrada.read())!=-1){
                char caracter = (char)ascci;
                contenido += caracter;
            }
        } catch (IOException ex) {
        }
        return contenido;
    }
    
    /*Guardar archivos de texto*/
    
    public String GuardarATexto(File archivo, String contenido) throws IOException{
        String respuesta=null;
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se guardo el archivo";
        } catch (FileNotFoundException ex) {
        }
        return respuesta;
    }
    
    public byte[] AbrirAImagen(File archivo){
        byte[] bytesImg = new byte[1024*1024*100];
        try{
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        }catch(Exception e){
        }
        return bytesImg;
    }
    
    public String GuardarAImagen(File archivo, byte[] bytesImg){
        String respuesta = null;
        try{
            salida = new FileOutputStream(archivo);
            salida.write(bytesImg);
            respuesta = "La imagen se guardo";
        }catch(Exception e){
        }
        return respuesta;
    }
    
    
    public static void main(String[] args) {
        new Administrador_archivos().setVisible(true);
        
    }
}
