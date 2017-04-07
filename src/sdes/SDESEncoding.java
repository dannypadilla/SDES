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

        byte[][] convertedArray = new byte[array.length][5];
        for(int i = 0; i < array.length; i++){
            convertedArray[i] = cascii.convert(cascii.Convert(array[i]));
        }

        //convert to 1d array
        byte[] onedarray = new byte[convertedArray.length * convertedArray[0].length];
        int counter = 0;
        for(int i = 0; i < convertedArray.length; i++){
            for(int k = 0; k < convertedArray[i].length; k++){
                onedarray[counter] = convertedArray[i][k];
                counter++;
            }
        }

        int holder = (array.length*5)%8;

        byte[] newarrayPadded = new byte[array.length*5 + holder];
        for(int i = 0; i < newarrayPadded.length; i++){
            if(i < onedarray.length){
                newarrayPadded[i] = onedarray[i];
            }
            else{
                newarrayPadded[i] = 0;
            }
        }
        System.out.println("new array padded");
        sdes.print(newarrayPadded);
        System.out.println();

        //split back into 2d after padding
        byte[][] split = new byte[newarrayPadded.length/8][8];
        counter = 0;
        for(int i = 0; i < split.length ;i++){
            for(int k = 0; k < split[i].length; k++){
                split[i][k] = newarrayPadded[counter];
                counter++;
            }
        }
        System.out.println("2d array after padding");
        for(int i = 0; i < split.length; i++){
            sdes.print(split[i]);
            System.out.println();
        }

        System.out.println();
        byte[][] container = new byte[split.length][split[0].length];
        for(int i = 0; i < split.length; i++){
            container[i] = sdes.Encryption(key, split[i]);
        }

        for(int i = 0; i < container.length; i++){
            for(int k = 0; k < container[0].length; k++){
                System.out.print(container[i][k]);
            }
        }
    }
    //0110000111001010101101101111010011000101011101101111110001110111


}
