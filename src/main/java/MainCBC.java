public class MainCBC {

    public static void main ( String[] args ) throws Exception {
        String text = "This is a secret message!";
        String key = "G-KaPdSgVkYp3s6v";
        String ivKey = "E)H@McQfTjWnZr4u";
        byte[] textEncrypted = CBC.encrypt ( text.getBytes ( ) , key.getBytes ( ) , ivKey.getBytes ( ) );
        byte[] textDecrypted = CBC.decrypt ( textEncrypted , key.getBytes ( ) , ivKey.getBytes ( ) );
        System.out.println ( new String ( textDecrypted ) );
    }


}
