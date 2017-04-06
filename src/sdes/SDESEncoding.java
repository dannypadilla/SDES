package sdes;
import sdes.CASCII;



/**
 * Created by kenluo on 4/4/17.
 */
public class SDESEncoding {

    //    Give the SDES encoding of the following CASCII plaintext using the key 0111001101. (The answer
//    is 64 bits long.)
//    CRYPTOGRAPHY
    byte [] key =  {0,1,1,1,0,0,1,1,0,1};

    public static void main(String[] args) {
        CASCII test;
        test = new CASCII();

//        char[] testArray = {'H', 'E', 'L', 'L', 'O', 'Z'};
//
//        byte[] testByte = test.Convert(testArray);
//        for (int i = 0; i < testByte.length; i++) {
//            System.out.println(testByte[i]);
//        }

        int l = 0;
        for(int counter = 31; counter != 0; counter--) {
            l = (int)Math.floor(Math.random()*(31-0) + 0);
            byte[] holder = test.convert(l);
            System.out.println(l);
            for (int i = 0; i < holder.length; i++) {
                System.out.print(holder[i] + " ");
            }
            System.out.println();
        }

//        String temp = test.toString(testByte);
//
//        byte[] holder = test.Convert(temp);
//
//        for (int i = 0; i < holder.length; i++) {
//            System.out.println(holder[i]);
//        }


    }
}
