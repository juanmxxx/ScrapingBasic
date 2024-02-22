package petitions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.List;
import java.util.Map;

import encryption.AESSimpleManager;
import variables.EncryptVariables;
import variables.SaveVariables;
import variables.Variables;

public class RegularMethods {
    /**
     * Le pasamos una linea de texto y nos devuelve la informacion encriptada
     * @param info
     * @return
     */
    public static String encrypt(String info) {
        String encryptText = null;

        try{
            Key key = AESSimpleManager.getKey(EncryptVariables.PASSWORD, EncryptVariables.BLOCKLENGHT);
            encryptText = AESSimpleManager.encrypt(info, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptText;
    }

    /**
     * Le pasamos una linea encriptada y nos devuelve la informacion desencriptada
     * @param encryptText
     * @return
     */
    public static String desencrypt(String encryptText) {
        String info = null;

        try {
            Key key = AESSimpleManager.getKey(EncryptVariables.PASSWORD, EncryptVariables.BLOCKLENGHT);
            info = AESSimpleManager.desencrypt(encryptText, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public static StringBuilder generateHTML() throws Exception {

        StringBuilder result = new StringBuilder();

        URL url = new URL(Variables.URL);

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type", "text/plain");
        conexion.setRequestProperty("charset", "utf-8");
        conexion.setRequestProperty("User-Agent", "Mozilla/5.0");

        int state = conexion.getResponseCode();

        Reader streamReader = null;

        if (state == HttpURLConnection.HTTP_OK) {
            streamReader = new InputStreamReader(conexion.getInputStream());
            int character;
            while ((character = streamReader.read()) != -1) {
                result.append((char) character);
            }
        } else {
            Map<String, List<String>> redireccion = conexion.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : redireccion.entrySet())
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            throw new Exception("Error HTTP " + state);
        }
        conexion.disconnect();
        return result;
    }

    public static void writeHTMLFile(){
        StringBuilder result = null;

        try {
            result = generateHTML();
        } catch (Exception e) {
            e.printStackTrace();
        }

        createDirectory(SaveVariables.HTMLDIRECTORY);

        try {
            Path path = Paths.get(SaveVariables.HTMLFILENAME);
            byte[] strToBytes = result.toString().getBytes();
            Files.write(path, strToBytes);
            System.out.println("Html creado y guardado con exito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectory(String filePath){
        Path path = Paths.get(filePath);
        try{
            //Poner createDirectories para que cree el directorio y si ya existe no de error
            //Si no existe el directorio lo crea y si ya existe no hace nada
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void encryptAndWrite(List<String> info) throws IOException {
        createDirectory(SaveVariables.ENCRYPTDIRECTORY);
        FileWriter fileWriter = new FileWriter(SaveVariables.ENCRYPTFILENAME);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(String line : info) {
            String encryptLine = encrypt(line);
            bufferedWriter.write(encryptLine);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static List<String> desencryptAndRead() throws IOException {
        List<String> info = null;
        try {
            info = Files.readAllLines(Paths.get(SaveVariables.ENCRYPTFILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line : info) {
            String desencryptLine = desencrypt(line);
            System.out.println(desencryptLine);
        }
        return info;
    }

    public static void desencryptAndPrint() throws IOException {
        List<String> info = desencryptAndRead();

        for (String line : info)
            System.out.println(line);

    }

    public static void encryptAndPrint(List<String> info) {
        for(String line : info) {
            String encryptLine = encrypt(line);
            System.out.println(encryptLine);
        }
    }

}
