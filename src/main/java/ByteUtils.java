import java.util.ArrayList;

public class ByteUtils {

    public static ArrayList < byte[] > splitByteArray ( byte[] text , int chunkSize ) {
        ArrayList < byte[] > chunks = new ArrayList <> ( );
        for ( int i = 0 ; i < text.length ; i += chunkSize ) {
            int nElements = Math.min ( chunkSize , text.length - i );
            byte[] chunk = new byte[ nElements ];
            System.arraycopy ( text , i , chunk , 0 , nElements );
            chunks.add ( chunk );
        }
        // Adds padding to the last chunk (if required)
        byte[] lastChunk = chunks.get ( chunks.size ( ) - 1 );
        if ( lastChunk.length < chunkSize ) {
            byte[] paddedChunk = new byte[ chunkSize ];
            System.arraycopy ( lastChunk , 0 , paddedChunk , 0 , lastChunk.length );
            int paddingSize = chunkSize - lastChunk.length;
            for ( int i = 0 ; i < paddingSize ; i++ ) {
                paddedChunk[ lastChunk.length + i ] = ( byte ) paddingSize;
            }
            chunks.set ( chunks.size ( ) - 1 , paddedChunk );
        }
        return chunks;
    }


    public static byte[] concatByteArrays ( byte[] output , byte[] encrypted ) {
        byte[] newOutput = new byte[ output.length + encrypted.length ];
        System.arraycopy ( output , 0 , newOutput , 0 , output.length );
        System.arraycopy ( encrypted , 0 , newOutput , output.length , encrypted.length );
        return newOutput;
    }

    public static byte[] computeXOR ( byte[] op1 , byte[] op2 ) {
        byte[] output = new byte[ op1.length ];
        for ( int i = 0 ; i < op1.length ; i++ ) {
            output[ i ] = ( byte ) ( op1[ i ] ^ op2[ i ] );
        }
        return output;
    }


}
