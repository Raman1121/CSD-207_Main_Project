package main.java;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    private static SecretKeySpec secretKeySpec;
    private static byte[] key;

    public static void setKey(String secretKey){
        MessageDigest sha = null;
        try {
            key = secretKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("#AES.java NoSuchAlgorithmException " + e);
        }
        catch(UnsupportedEncodingException e){
            System.out.println("#AES.java UnsupportedEncodingException " + e);
        }
    }

    public static String encrypt(String toEncrypt,String secret){
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
        }
        catch(InvalidKeyException e){
            System.out.println("#AES.java InvalidKeyException " + e);
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("#AES.java NoSuchAlgorithmException " + e);
        }
        catch(NoSuchPaddingException e){
            System.out.println("#AES.java NoSuchPaddingException " + e);
        }
        catch(UnsupportedEncodingException e){
            System.out.println("#AES.java UnsupportedEncodingException " + e);
        }
        catch(IllegalBlockSizeException e){
            System.out.println("#AES.java IllegalBlockSizeException " + e);
        }
        catch(BadPaddingException e){
            System.out.println("#AES.java BadPaddingException " + e);
        }
        return null;
    }

    public static String decrypt(String toDecrypt,String secret){
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
        }
        catch(InvalidKeyException e){
            System.out.println("#AES.java InvalidKeyException " + e);
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("#AES.java NoSuchAlgorithmException " + e);
        }
        catch(NoSuchPaddingException e){
            System.out.println("#AES.java NoSuchPaddingException " + e);
        }
        catch(IllegalBlockSizeException e){
            System.out.println("#AES.java IllegalBlockSizeException " + e);
        }
        catch(BadPaddingException e){
            System.out.println("#AES.java BadPaddingException " + e);
        }
        return null;
    }

}
