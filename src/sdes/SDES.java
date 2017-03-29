package sdes;

public class SDES {

    static byte[] initialPBoxTable = {1, 5, 2, 0, 3, 7, 4, 6}; // initial-permutation table
    static byte[] finalPBoxTable = {3, 0, 2, 4, 6, 1, 7, 5}; // final-permutation table

    public static void main(String[] args) {


    }

    void Cipher(byte[] plainTextBlock) {

        byte[] plainText = new byte[plainTextBlock.length];

        // permute
        plainText = permute(plainTextBlock, initialPBoxTable);
        // split

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
    byte[] split(byte[] plainTextBlock, char leftOrRight) {

        // get half of the length of the plaintext
        int indexLength = plainTextBlock.length / 2;

        // empty array
        byte[] splitPlainText = new byte[indexLength];

        if(leftOrRight == 'l' || leftOrRight == 'L') {
            indexLength = 2;
        }

        for(int i = 0; i < indexLength; i++) {

        }

        return splitPlainText;
    }

    void mixer() {}

    void swapper() {}

    void desDunction() {}

    void substitute() {}

    /* Key-Generator */
    void keyGenerator() {}

    void shiftLeft() {}

    // tests used for proving permutation works and outputs properly.. initial perm for now
    void permuteTestCase() {

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

}
