/*
PRACTICA:
DES y AES
 */
package safetybox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES_AES {

    static Cipher ecipher;
    static Cipher dcipher;
    String type;
    int repeticion = 0;
    static IvParameterSpec paramSpec, paramSpec_aux;
    SecretKey key_aux;
    
    public DES_AES() {
    }

    public static void Encrypt(InputStream in, OutputStream out) throws IOException {
//        byte[] header = new byte[54];
//        in.read(header);
//        out.write(header, 0, 54);
        CipherOutputStream cos = new CipherOutputStream(out, ecipher);
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = in.read(bytes)) != -1) {
            cos.write(bytes, 0, numBytes);
        }

        cos.flush();
        cos.close();
        in.close();
        out.close();      
    }

    public static void Decrypt(InputStream in, OutputStream out) throws IOException {
//        byte[] header = new byte[54];
//        in.read(header);
//        out.write(header, 0, 54);
        CipherOutputStream cos = new CipherOutputStream(out, dcipher);
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = in.read(bytes)) != -1) {
            cos.write(bytes, 0, numBytes);
        }
        cos.flush();
        cos.close();
        in.close();
        out.close();
    }

    public void initDES_AES() throws Exception {
        SecretKey key = new SecretKeySpec(Base64.getDecoder().decode("b+S3AP1Q/R3pONvaDnzl+A=="), 0, Base64.getDecoder().decode("b+S3AP1Q/R3pONvaDnzl+A==").length, "AES");//DES
        SecretKeySpec skey = new SecretKeySpec(key.getEncoded(), "AES"); //AES
        IvParameterSpec iv8 = new IvParameterSpec(new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A});//CBC,OFB,CFB
        IvParameterSpec iv = new IvParameterSpec(new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A});//CBC,OFB,CFB

        type = "CBC";
        String cipher = "AES";
        ecipher = Cipher.getInstance(cipher + "/" + type + "/NoPadding");
        dcipher = Cipher.getInstance(cipher + "/" + type + "/NoPadding");
        //SecretKey key = KeyGenerator.getInstance("AES").generateKey();
        //byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A};
        //byte[] iv = new byte[ecipher.getBlockSize()];

        ecipher.init(Cipher.ENCRYPT_MODE, skey,iv);
        dcipher.init(Cipher.DECRYPT_MODE, skey,iv);

    }

}
