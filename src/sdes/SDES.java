package sdes;

public class SDES {

    static byte[] initialPBoxTable = {1, 5, 2, 0, 3, 7, 4, 6}; // initial-permutation table
    static byte[] finalPBoxTable = {3, 0, 2, 4, 6, 1, 7, 5}; // final-permutation table

    // tables for SDES function
    static byte[] expansionPermutationTable = {3, 0, 1, 2, 1, 2, 3, 0}; // expansion permutation table for function
    static byte[] functionStraightPBoxTable = {1, 3, 2, 0}; // straight pbox table for the SDES function
    static byte[][] sBox1Table = {
            {1, 0, 3, 2},
            {3, 2, 1, 0},
            {0, 2, 1, 3},
            {3, 1, 3, 2}
    };
    static byte[][] sBox2Table = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {3, 0, 1, 0},
            {2, 1, 0, 3}
    };

    // tables for Key Generation
    static byte[] keyStraightPBoxTable = {2, 4, 1, 6, 3, 9, 0, 8, 7, 5}; // straight pbox table for the key generator
    static byte[] compressionPBoxTable = {5, 2, 6, 3, 7, 4, 9, 8}; // compression pbox table for key generator


    public static void main(String[] args) {



    }

    void Cipher(byte[] plainTextBlock) {

        byte[] plainText = new byte[plainTextBlock.length];

        // permute
        plainText = permute(plainTextBlock, initialPBoxTable);

        // split
        byte[] leftSplit = split(plainText, 'l');
        byte[] rightSplit = split(plainText, 'r');

        // mixer
        // combine
        // permute again

    }

    // permutes a byte array based on the permutation table passed through
    static byte[] permute(byte[] plainText, byte[] permutationTable) {

        byte[] newPlaintext = new byte[plainText.length];

        for (int i = 0; i < plainText.length; i++) {
            newPlaintext[i] = plainText[permutationTable[i]];
        }
        return newPlaintext;
    }

    // returns the indicated half of the plain text block; left or right
    static byte[] split(byte[] plainTextBlock, char leftOrRight) {

        // get half of the length of the plaintext
        int indexLength = plainTextBlock.length / 2;

        // empty array
        byte[] splitPlainText = new byte[indexLength];

        //inserted code
        int counter = indexLength;
        if(leftOrRight == 'l' || leftOrRight == 'L') {
            for(int i = 0; i < indexLength; i++){
                splitPlainText[i] = plainTextBlock[i];
            }
        }
        else{
            for(int i = 0; i < indexLength; i++, counter++){
                splitPlainText[i] = plainTextBlock[counter];
            }
        }
        return splitPlainText;
    }

    void mixer() {}

    void swapper() {}

    void desDunction() {}

    void substitute() {}

    /* Key-Generator */
    void keyGenerator() {}

    static byte[] shiftLeft(byte[] plainText) {
        byte[] shiftedLeftOne = new byte[plainText.length];
        for(int i = 0; i < shiftedLeftOne.length - 1; i++){
            shiftedLeftOne[i] = plainText[i+1];
        }
        shiftedLeftOne[shiftedLeftOne.length-1] = plainText[0];
        return shiftedLeftOne;
    }

    // tests used for proving permutation works and outputs properly.. initial perm for now
    static void permuteTestCase() {

        // original test block
        byte[] plainTextBlock = {0, 1, 1, 0, 1, 0, 0, 1};

        // to test if initial permutation was correct
        byte[] permutedPlainTextBlock = {1, 0, 1, 0, 0, 1, 1, 0};

        // permute plaintext based
        byte[] initialPBox = permute(plainTextBlock, initialPBoxTable);

        // testing to see if original permutations are equal
        for(int i = 0; i < plainTextBlock.length; i++) {
            System.out.println(initialPBox[i] == permutedPlainTextBlock[i]);
        }

    }

    static void shiftTestCase() {

        byte[] testPlainText = {1, 1, 1, 1, 0, 0, 0, 0};

        byte[] oneShift = shiftLeft(testPlainText);

        byte[] twoShifts = shiftLeft(oneShift);

        System.out.println("No shifts");
        for(int i = 0; i < testPlainText.length; i++) {
            System.out.print(testPlainText[i] );
        }

        System.out.println("\nOne shift");
        for(int i = 0; i < oneShift.length; i++) {
            System.out.print(oneShift[i] );
        }

        System.out.println("\nTwo shifts");
        for(int i = 0; i < twoShifts.length; i++) {
            System.out.print(twoShifts[i] );
        }
    }

    static void splitTestCase() {
        byte[] testPlainText = {1, 1, 1, 1, 0, 0, 0, 0};

        byte[] splitLeft = split(testPlainText, 'l');

        byte[] splitRight = split(testPlainText, 'r');

        System.out.println("Original Plain Te");
        for(int i = 0; i < testPlainText.length; i++) {
            System.out.print(testPlainText[i] );
        }

        System.out.println("\nLeft Split");
        for(int i = 0; i < splitLeft.length; i++) {
            System.out.print(splitLeft[i] );
        }

        System.out.println("\nRight Split");
        for(int i = 0; i < splitRight.length; i++) {
            System.out.print(splitRight[i] );
        }

    }
}
