package sdes;
import com.sun.deploy.util.SystemUtils;
import sdes.CASCII;
import sdes.SDES;



/**
 * Created by kenluo on 4/4/17.
 */
public class SDESEncoding {

    //    Give the SDES encoding of the following CASCII plaintext using the key 0111001101. (The answer
//    is 64 bits long.)
//    CRYPTOGRAPHY

    public static void main(String[] args) {
        byte[] key = {0, 1, 1, 1, 0, 0, 1, 1, 0, 1};
        SDES sdes = new SDES();
        CASCII cascii = new CASCII();

        char[] array = {'C', 'R', 'Y', 'P','T','O','G','R','A','P','H','Y'};

        byte[][] paddedArray = new byte[array.length][8];

        for(int i = 0; i < array.length; i++){
            paddedArray[i]= padding(cascii.convert(cascii.Convert(array[i])));
        }

        byte[][] encrypted = new byte[paddedArray.length][paddedArray[0].length];
        for(int i = 0; i < paddedArray.length; i++){
            encrypted[i] = sdes.Encryption(key, paddedArray[i]);
        }
        for(int i = 0; i < encrypted.length; i++){
            sdes.print(encrypted[i]);
        }




//        CASCII test;
//        test = new CASCII();
//
//        char[] testArray = {'H', 'E', 'L', 'L', 'O', 'Z'};
//
//
//        int l = 0;
//        for (int counter = 31; counter != 0; counter--) {
//            l = (int) Math.floor(Math.random() * (31 - 0) + 0);
//            byte[] holder = test.convert(l);
//            System.out.println(l);
//            for (int i = 0; i < holder.length; i++) {
//                System.out.print(holder[i] + " ");
//
//                byte[] testByte = test.Convert(testArray);
//
//                for (int i = 0; i < testByte.length; i++) {
//                    for (int j = 0; j < test.convert(testByte[i]).length; j++) {
//                        System.out.print(test.convert(testByte[i])[j]);
//
//                    }
//                    System.out.println();
//                }

//        byte[] fiveBitsFromTxtFile = {1, 1, 1, 1, 1};
//
//        String fiveBitString = "";
//
//        for(int i = 0; i < fiveBitsFromTxtFile.length; i++) {
//            fiveBitString += fiveBitsFromTxtFile[i];
//        }
//
//        int fiveBitsToInt = Integer.parseInt(fiveBitString, 2);
//
//        System.out.println(fiveBitsToInt);
//
//        byte[] holder = test.convert(fiveBitsToInt);
//        for(int i = 0; i < holder.length; i++){
//            System.out.print(holder[i] + " ");
//        }


//        String temp = test.toString(testByte);
//
//        byte[] holder = test.Convert(temp);
//
//        for (int i = 0; i < holder.length; i++) {
//            System.out.println(holder[i]);
//        }


            //}
//        }
    }
    public static byte[] padding(byte[] array){
        byte[] padded = new byte[8];
        for(int i = 0; i < 3; i++){
            padded[i] = 0;
        }
        int counter = 3;
        for(int i = 0; i < array.length; i++, counter++){
            padded[counter] = array[i];
        }

        return padded;
    }
}
