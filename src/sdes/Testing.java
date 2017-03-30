package sdes;

/**
 * Created by dannypadilla on 3/29/17.
 */
public class Testing {

    // 8 bit plain text
    byte[][] testPlaintText = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0}
    };

    // 8 bit cipher text
    byte[][] testCipherText = {
            {0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0}
    };

    // 10 bit key
    byte[][] testRawKeys = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            {1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    //encryption of DES
    public static byte[] Encrypt(byte[] rawKey, byte[] plainText) {

        byte[] cipheredText = {}; // return after encrypted

        // take in 8-bit plain text


        /* ************ Key Generation ************ */
        // creates two 8-bit keys out of a 10-bit cipher key

        // take 10-bit cipher key
        byte[] cipherKey = rawKey;

        /* ***** Straight P-Box ***** */
        byte[] keyGenStraightPBoxTable = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6}; // table for Straight P-Box\

//rawKeyAfterStraightPBoxTable
        byte[] newRawKey = new byte[keyGenStraightPBoxTable.length];

        for(int i = 0; i < newRawKey.length; i++){
            newRawKey[i] = rawKey[keyGenStraightPBoxTable[i]];
        }

        /* ***** Shift Left ***** */
        // circular shift r bits where r is the round number (in this case 1)

//shift once
        byte[] shift1 = new byte[keyGenStraightPBoxTable.length];

        for(int i = 0; i < keyGenStraightPBoxTable.length - 1; i++){
            shift1[i] = keyGenStraightPBoxTable[i+1];
        }
        shift1[9] = keyGenStraightPBoxTable[0];
        // then twice for round 2

//shift twice
        byte[] shiftleft2 = new byte[keyGenStraightPBoxTable.length];

        for(int i = 0; i < 8; i++){
            shiftleft2[i] = keyGenStraightPBoxTable[i+2];
        }
        shiftleft2[8] = keyGenStraightPBoxTable[0];
        shiftleft2[9] = keyGenStraightPBoxTable[1];

        /* ***** Compression P-Box***** */
        byte[] keyGenCompressionPBoxTable = {6, 3, 7, 4, 8, 5, 10, 9}; // table for Compression P-Box

        /* **************************************** */


        /* S-DES Cipher */

        /* * Initial permutation ** */
        byte[] initialPBoxTable= {2, 6, 3, 1, 4, 8, 5, 7}; // initial-permutation table


        // split after initial pbox
//newrawkey split
        byte[] L0 = new byte[newRawKey.length/2];
        byte[] R0 = new byte[newRawKey.length/2];
        int counter = 0;
        for(int i = 0; i < newRawKey.length; i++){
            if(i < L0.length){
                L0[i] = newRawKey[i];
            }
            else{
                R0[counter] = newRawKey[i];
                counter++;
            }
        }

//shifted 1 L0 R0
        byte[] shiftedL0 = new byte[L0.length];
        byte[] shiftedR0 = new byte[R0.length];

        for(int i = 0; i < shiftedL0.length - 1; i++){
            shiftedL0[i] = L0[i+1];
            shiftedR0[i] = R0[i+1];
        }
        shiftedL0[shiftedL0.length-1] = L0[0];
        shiftedR0[shiftedR0.length-1] = R0[0];

//shifted 2 shiftedL0 shiftedR0
        byte[] shifted2L0 = new byte[shiftedL0.length];
        byte[] shifted2R0 = new byte[shiftedR0.length];
        for(int i = 0; i < shiftedL0.length - 2; i++){
            shiftedL0[i] = L0[i+2];
            shiftedR0[i] = R0[i+2];
        }
        shifted2L0[shifted2L0.length-2] = L0[0];
        shifted2L0[shifted2L0.length-1] = L0[1];
        shifted2R0[shifted2R0.length-2] = R0[0];
        shifted2R0[shifted2R0.length-1] = R0[1];

//combine shifted 2
        byte[] combinedShifted2 = new byte[shifted2R0.length + shifted2L0.length];
        for(int i = 0; i < shifted2L0.length; i++){
            combinedShifted2[i] = shifted2L0[i];
            combinedShifted2[i + shifted2L0.length] = shifted2R0[i];
        }

//compression box
//byte[] keyGenCompressionPBoxTable = {6, 3, 7, 4, 8, 5, 10, 9}; // table for Compression P-Box

        byte[] afterCompressionBox = new byte[keyGenCompressionPBoxTable.length];
        for(int i = 0; i < afterCompressionBox.length; i++){
            afterCompressionBox[i] = combinedShifted2[keyGenCompressionPBoxTable[i]];
        }





        /* ****************************** S-DES Function ******************************** */

        /* ****************** ROUND 1 ****************** */

        /* *** Expansion P-Box *** */
        byte[] expansionPBoxTable = {4, 1, 2, 3, 2, 3, 4 ,1}; // expansion permutation table

        /* *** Whitener (XOR) *** */
        // just xor the bits (R0) with the KEY

        /* *** S-Boxes *** */
        byte[][] table1SBox = {
                {1, 0, 3, 2},
                {3, 2, 1, 0},
                {0, 2, 1, 3},
                {3, 1, 3, 2}
        };
        byte[][] table2SBox = {
                {0, 1, 2, 3},
                {2, 0, 1, 3},
                {3, 0, 1, 0},
                {2, 1, 0, 3}
        };
        /* *** Straight P-Box *** */
        byte[] straightPermutationTable = {2, 4, 3, 1}; // permutation table

        /* ********************************************* */

        byte[] L1 = {};
        byte[] R1 = {};

        /* ****************** ROUND 2 ****************** */
        /* *** Expansion P-Box *** */
        /* *** Whitener (XOR) *** */
        /* **** Key Generation **** */
        /* *** S-Boxes *** */
        /* *** Straight P-Box *** */
        /* ********************************************* */

        /* ****************************************************************************** */

        /* * Final permutation * */
        byte[] finalPBox= {4, 1, 3, 5, 7, 2, 8, 6}; // final-permutation table

        // return 8-bit cipher text

        return cipheredText;
    }

    public static byte[] Decrypt(byte[] rawKey, byte[] cipherText) {
        byte[] publicKey = {};

        return publicKey;

    }

}
