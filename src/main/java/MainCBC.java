public class MainCBC {

    public static void main ( String[] args ) throws Exception {
        String text = "This is a secret message!";
        String key = "G-KaPdSgVkYp3s6v";
        String ivKey = "E)H@McQfTjWnZr4u"; // the number of bytes must be equal to the block size of the algorithm (for example, 16 bytes)
        byte[] textEncrypted = CBC.encrypt ( text.getBytes ( ) , ivKey.getBytes ( ) , key.getBytes ( ) );
        byte[] textDecrypted = CBC.decrypt ( textEncrypted , ivKey.getBytes ( ) , key.getBytes ( ) );
        System.out.println ( new String ( textDecrypted ) );
    }


}
