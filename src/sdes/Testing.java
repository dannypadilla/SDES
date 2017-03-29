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
        byte[] keyGenStraightPBoxTable = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6}; // table for Straight P-Box

        /* ***** Shift Left ***** */
        // circular shift r bits where r is the round number (in this case 1)
        // then twice for round 2

        /* ***** Compression P-Box***** */
        byte[] keyGenCompressionPBoxTable = {6, 3, 7, 4, 8, 5, 10, 9}; // table for Compression P-Box

        /* **************************************** */


        /* S-DES Cipher */

        /* * Initial permutation ** */
        byte[] initialPBoxTable= {2, 6, 3, 1, 4, 8, 5, 7}; // initial-permutation table

        // split after initial pbox
        byte[] L0 = {};
        byte[] R0 = {};



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
