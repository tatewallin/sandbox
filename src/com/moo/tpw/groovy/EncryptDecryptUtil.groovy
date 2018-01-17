package com.moo.tpw.groovy

import com.mutualofomaha.utility.security.encryption.TripleDES
import org.junit.Before
import org.junit.Test
import org.apache.commons.codec.binary.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.NoSuchAlgorithmException

/**
 * Created by req69775 on 7/27/17.
 */
class EncryptDecryptUtil {
    // AES = Encryption algorithm used
    // CBC = block mode
    // PKCS5Padding = padding
    private static final String xform = "AES/CBC/PKCS5Padding"

    public static final String DECRYPT_KEY = "governance101rocks"
    public static final String TOGA_SERVICE_ACCOUNT_CRYPT_KEY ="fhhDB17YrXf+2T6rIdkf8g==";
    // not quite sure how this was generated
    // common key for the encryption/decryption process
    private static final byte[] iv = [ 0x0a, 0x01, 0x02, 0x03, 0x04, 0x0b, 0x0c, 0x0d, 0x01, 0x02, 0x03, 0x00, 0x00, 0x06, 0x02, 0x05 ]


    @Before
    public void setup(){

    }

    @Test
    public void encryptTripleDes0(){
        String clearValue = " s+9\\o1Q<zIE.dR"
        String key = TOGA_SERVICE_ACCOUNT_CRYPT_KEY
        String e = encryptTripleDes(clearValue, key)
        System.out.println("TripleDes encryption...")
        System.out.println("clear value     - " + clearValue)
        System.out.println("Key value       - " + key)
        System.out.println("encrypted value - " + e)
    }

    @Test
    public void decryptTripleDes0(){
        String encryptedValue = "M1Wk4bscH4L2t3F2EPN6sg=="
        String key = "mKY+Eq8RFJXU1x7G/yoN0g=="
        String e = decryptTripleDes(encryptedValue, key)
        System.out.println("TripleDes encryption...")
        System.out.println("encrypted value     - " + encryptedValue)
        System.out.println("Key value       - " + key)
        System.out.println("decrypted value - " + e)
    }

    @Test
    public void decryptTripleDes1(){
        String encryptedValue = "qSjAgW66v4w="
        String key = "req69775"+DECRYPT_KEY
        String e = decryptTripleDes(encryptedValue, key)
        System.out.println("TripleDes encryption...")
        System.out.println("encrypted value     - " + encryptedValue)
        System.out.println("Key value       - " + key)
        System.out.println("decrypted value - " + e)
    }

    public String encryptTripleDes(String clearText, String keyString){
        TripleDES tripDes = new TripleDES(keyString);
        return tripDes.encrypt(clearText);
    }

    public String decryptTripleDes(String encryptedText, String keyString){
        TripleDES tripDes = new TripleDES(keyString)
        return tripDes.decrypt(encryptedText)
    }

    // This is only used when generating a SecretKey instance. After that, the generated SecretKey will be stored
    // in most of our cases, this is the *_CRYPT_KEY in our property files.  Similar to the passPhrase/keyString for TripleDES
    public String generateKeyString() {
        // Generate a secret key
        KeyGenerator kg
        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e)
        }
        kg.init(128);
        byte[] key = kg.generateKey().getEncoded();
        return Base64.encodeBase64String(key)
    }

    public String encrypt(String clearText, String base64EncodedKeyString) {
        return Base64.encodeBase64String(crypt(Cipher.ENCRYPT_MODE, clearText.getBytes(), base64EncodedKeyString))
    }

    public String decrypt(String base64EncodedEncryptedText, String base64EncodedKeyString) {
        return new String(crypt(Cipher.DECRYPT_MODE, Base64.decodeBase64(base64EncodedEncryptedText), base64EncodedKeyString))
    }

    private byte[] crypt(int mode, byte[] textToCryptInByteArray, String base64EncodedKeyString) {
        try {
            byte[] decodedKeyStringBytes = Base64.decodeBase64(base64EncodedKeyString.getBytes())
            SecretKey key = new SecretKeySpec(decodedKeyStringBytes, "AES")
            IvParameterSpec ips = new IvParameterSpec(iv)

            Cipher cipher = Cipher.getInstance(xform)
            cipher.init(mode, key, ips)

            return cipher.doFinal(textToCryptInByteArray)
        } catch (Throwable t) {
            throw new RuntimeException(t)
        }

    }
}
