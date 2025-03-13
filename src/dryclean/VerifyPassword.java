package dryclean;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class VerifyPassword {
    protected static boolean validatePassword(String enteredPassword, String storedPassword)
    throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        System.out.println(hash.length);
        System.out.println("Test hash:" + toHex(testHash));

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private static byte[] fromHex(String hex)
        throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() /2];
        for(int i = 0; i < bytes.length; i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2* i + 2), 16);
        }
        return bytes;
    }
    private static String toHex(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);
        for (byte b : array) {
            sb.append(String.format("%02x", b & 0xff)); // Convert byte to 2-digit hex
        }
        return sb.toString();
    }

}
