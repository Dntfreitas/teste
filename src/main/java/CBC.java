import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;

public class CBC {

    public static byte[] encrypt ( byte[] text , byte[] ivKey , byte[] key ) throws Exception {
        Cipher cipher = Cipher.getInstance ( "AES" );
        SecretKeySpec secretKeySpec = new SecretKeySpec ( key , "AES" );
        cipher.init ( Cipher.ENCRYPT_MODE , secretKeySpec );
        // Splits the text into chunks of 15 bytes
        ArrayList < byte[] > textSplits = ByteUtils.splitByteArray ( text , 15 );
        // Encrypts each chunk
        byte[] output = new byte[ 0 ];
        byte[] keyXorOperation = ivKey;
        for ( byte[] textSplit : textSplits ) {
            byte[] encrypted = cipher.doFinal ( ByteUtils.computeXOR ( textSplit , keyXorOperation ) );
            keyXorOperation = encrypted;
            output = ByteUtils.concatByteArrays ( output , encrypted );
        }
        return output;
    }


    public static byte[] decrypt ( byte[] text , byte[] ivKey , byte[] key ) throws Exception {
        Cipher cipher = Cipher.getInstance ( "AES" );
        SecretKeySpec secretKeySpec = new SecretKeySpec ( key , "AES" );
        cipher.init ( Cipher.DECRYPT_MODE , secretKeySpec );
        // Get padding size
        ArrayList < byte[] > textSplits = ByteUtils.splitByteArray ( text , 16 );
        // Decrypts each chunk
        byte[] output = new byte[ 0 ];
        byte[] keyXorOperation = ivKey;
        for ( byte[] textSplit : textSplits ) {
            byte[] decrypted = ByteUtils.computeXOR ( cipher.doFinal ( textSplit ) , keyXorOperation );
            keyXorOperation = textSplit;
            output = ByteUtils.concatByteArrays ( output , decrypted );
        }
        // Removes padding
        int paddingSize = output[ output.length - 1 ];
        byte[] outputWithoutPadding = new byte[ output.length - paddingSize ];
        System.arraycopy ( output , 0 , outputWithoutPadding , 0 , outputWithoutPadding.length );
        return outputWithoutPadding;
    }
}
