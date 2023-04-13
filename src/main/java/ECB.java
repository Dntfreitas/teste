import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;

/**
 * The ECB class contains the methods to encrypt and decrypt a text using AES in ECB (Electronic Code Book) mode.
 */
public class ECB {

    /**
     * Encrypts a text using AES in ECB (Electronic Code Book) mode.
     *
     * @param text The text to encrypt
     * @param key  The symmetrical key to be used in the encryption
     *
     * @return The encrypted text
     *
     * @throws Exception When the encryption mechanism fails
     */
    public static byte[] encrypt ( byte[] text , byte[] key ) throws Exception {
        Cipher cipher = Cipher.getInstance ( "AES" );
        SecretKeySpec secretKeySpec = new SecretKeySpec ( key , "AES" );
        cipher.init ( Cipher.ENCRYPT_MODE , secretKeySpec );
        // Splits the text into chunks of 15 bytes
        ArrayList < byte[] > textSplits = ByteUtils.splitByteArray ( text , 15 );
        // Encrypts each chunk
        byte[] output = new byte[ 0 ];
        for ( byte[] textSplit : textSplits ) {
            byte[] encrypted = cipher.doFinal ( textSplit );
            output = ByteUtils.concatByteArrays ( output , encrypted );
        }
        return output;
    }

    /**
     * Decrypts a text using AES in ECB (Electronic Code Book) mode.
     *
     * @param text The text to decrypt
     * @param key  The symmetrical key to be used in the decryption
     *
     * @return The decrypted text
     *
     * @throws Exception When the decryption mechanism fails
     */
    public static byte[] decrypt ( byte[] text , byte[] key ) throws Exception {
        Cipher cipher = Cipher.getInstance ( "AES" );
        SecretKeySpec secretKeySpec = new SecretKeySpec ( key , "AES" );
        cipher.init ( Cipher.DECRYPT_MODE , secretKeySpec );
        // Get padding size
        ArrayList < byte[] > textSplits = ByteUtils.splitByteArray ( text , 16 );
        // Decrypts each chunk
        byte[] output = new byte[ 0 ];
        for ( byte[] textSplit : textSplits ) {
            byte[] decrypted = cipher.doFinal ( textSplit );
            output = ByteUtils.concatByteArrays ( output , decrypted );
        }
        // Removes padding
        int paddingSize = output[ output.length - 1 ];
        byte[] outputWithoutPadding = new byte[ output.length - paddingSize ];
        System.arraycopy ( output , 0 , outputWithoutPadding , 0 , outputWithoutPadding.length );
        return outputWithoutPadding;
    }
}
