public class MainECB {

    public static void main ( String[] args ) throws Exception {
        String text = "This is a secret message!";
        String key = "G-KaPdSgVkYp3s6v";
        byte[] textEncrypted = ECB.encrypt ( text.getBytes ( ) , key.getBytes ( ) );
        byte[] textDecrypted = ECB.decrypt ( textEncrypted , key.getBytes ( ) );
        System.out.println ( new String ( textDecrypted ) );
    }


}
