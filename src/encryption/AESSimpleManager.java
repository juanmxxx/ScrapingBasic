package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AESSimpleManager {
    
    public static Key getKey(String password, int lenght) {
        Key key = new SecretKeySpec (password.getBytes (), 0, lenght, "AES");
        return key;
    }

    public static String encrypt(String visualText, Key key) throws Exception {
        Cipher cipher = Cipher. getInstance ("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] cipherText = cipher.doFinal(visualText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);    
    }

    public static String desencrypt(String encryptText, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding") ;
        cipher.init(Cipher.DECRYPT_MODE, key) ;
        byte [] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptText));
        return new String (plainText);
    }
    
    public static String format(String text, int lenght) {
        
        StringBuilder formatText= new StringBuilder();
        char[] letras = text.toCharArray();
        int i=0;
        while (i<text.length()) {
            if (i%lenght==0) {
                while (letras[i] != ' ') {
                    formatText.append(letras[i]);
                    i++;
                }
                formatText.append("\n");
            } else {
                formatText.append(letras[i]);
            }
            i++;
        }
        return formatText.toString();
    }
    
}
