package sdes;


import com.sun.deploy.util.SystemUtils;

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
    static byte[] keyCompressionPBoxTable = {5, 2, 6, 3, 7, 4, 9, 8}; // compression pbox table for key generator

    public static void main(String[] args) {

        byte[] test = {0, 1, 0, 1};

        sbox(test, sBox1Table);

        //print(s);

//        String test = "1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
//        char[] tester = test.toCharArray();
//        for(int i = 0; i < tester.length; i++){
//            System.out.println(tester[i]);
//        }
//        System.out.println(tester.length);


    }

    void Cipher(byte[] plainTextBlock) {

        byte[] plainText = new byte[plainTextBlock.length];

        /* initial permute */
        plainText = permute(8, 8, plainTextBlock, initialPBoxTable);

        /* split */
        byte[] leftSplit = split(plainText, 'l');
        byte[] rightSplit = split(plainText, 'r');

        /* mixer (DES Function and XOR) */
        /* * DES Function */
        // * expansion pbox 4bits -> 8 bits
        // * XOR 8bits -> 8bits
        // * Sboxes 8bits
        // * -split 8bits -> 2 4bits
        // * -sbox1 4bits -> 2bits
        // * -sbox2 4bits -> 2bits
        // * -combine 2 2bits -> 4bits
        // * straight pbox 4bits -> 4bits

        // Left XOR DES (Right) 4bits -> 4bits




        /* combine (XOR) */
        /* final permute again */

    }

    // permutes a byte array based on the permutation table passed through
    static byte[] permute(int numberOfBitsIn, int numberOfBitsOut, byte[] plainText, byte[] permutationTable) {

        byte[] newPlaintext = new byte[numberOfBitsOut];

        for (int i = 0; i < numberOfBitsOut; i++) {
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
        if (leftOrRight == 'l' || leftOrRight == 'L') {
            for (int i = 0; i < indexLength; i++) {
                splitPlainText[i] = plainTextBlock[i];
            }
        } else {
            for (int i = 0; i < indexLength; i++, counter++) {
                splitPlainText[i] = plainTextBlock[counter];
            }
        }
        return splitPlainText;
    }

    static byte[] whitenerXOR(byte[] plainText, byte[] key) {

        int bits = plainText.length;

        byte[] xor = new byte[bits];

        for (int i = 0; i < bits; i++) {
            xor[i] = (byte) (plainText[i] ^ key[i]);
        }
        return xor;
    }

    static void sbox(byte[] input, byte[][] sBoxTable) {

        // take row and column values
        byte[] sBox1Row = {input[0], input[3] };
        byte[] sBox1Column= {input[1], input[2] };

        // byte[] array to a string for easier processing
        String rowString = "";
        String columnString = "";
        // append row string
        for(int i = 0; i < 2; i++) {
            rowString += sBox1Row[i];
        }
        // append to column string
        for(int i = 0; i < 2; i++) {
            columnString += sBox1Column[i];
        }
        // convert string binary to int
        int row = Integer.parseInt(rowString, 2);
        int column = Integer.parseInt(columnString, 2);

        // look up sbox table for bit value to return
        int sBoxResult = sBoxTable[row][column];

        // convert int to binary
        String sBoxStringResult = Integer.toBinaryString(sBoxResult);

        System.out.println(sBoxStringResult);

        byte[] result = new byte[2];

//        for(int i = 0; i < result.length; i++) {
//            if(result.length == 1) {
//                result[i] = (byte)0;
//                result[i + 1] = 1;
//            } else {
//
//            }

//            if(result.length == 1) {
        byte[] swapper = new byte[2];
        swapper[0] = 1;
        swapper[1] = 1;
        result = swapper;
        System.out.println("swapper stuff");
        System.out.println(result[0] + " 0");
        System.out.println(result[1] + " 0");
//            }
//        }

        System.out.println("string to byte");
        String h = "10";
        for(int i = 0; i < 2; i++){
            result[i] = (byte)h.charAt(i);
        }
        for(int i = 0; i < result.length; i++){
            //subtract 48 because ascii code for 0 starts at 48.
            System.out.print(result[i]-48);
        }



        //return result;

    }

    void mixer() {
    }

    void swapper() {
    }

    void desFunction() {
    }

    void substitute() {
    }

    // key generator
    static byte[][] keyGenerator(byte[] cipherKey, int rounds) {

        int outputKeyLength = 8; // only for SDES

        byte[][] storedKeys = new byte[rounds][outputKeyLength];

        // permute straight P box
        byte[] permutedKey = permute(10, 10, cipherKey, keyStraightPBoxTable);
        // split
        byte[] leftSplitKey = split(permutedKey, 'l');
        byte[] rightSplitKey = split(permutedKey, 'r');

        // shift left
        leftSplitKey = shiftLeft(leftSplitKey);
        // shift right key
        rightSplitKey = shiftLeft(rightSplitKey);

        // combine then permute.... All in one line. Got Lazy
        storedKeys[0] = permute(10, 8, combine(leftSplitKey, rightSplitKey), keyCompressionPBoxTable);

        // round 2
        // shift left
        leftSplitKey = shiftLeft(shiftLeft(leftSplitKey));
        // shift right key
        rightSplitKey = shiftLeft(shiftLeft(rightSplitKey));

        storedKeys[1] = permute(10, 8, combine(leftSplitKey, rightSplitKey), keyCompressionPBoxTable);

        return storedKeys;

    }

    static byte[] shiftLeft(byte[] plainText) {
        byte[] shiftedLeftOne = new byte[plainText.length];
        for (int i = 0; i < shiftedLeftOne.length - 1; i++) {
            shiftedLeftOne[i] = plainText[i + 1];
        }
        shiftedLeftOne[shiftedLeftOne.length - 1] = plainText[0];
        return shiftedLeftOne;
    }

    static byte[] combine(byte[] left, byte[] right) {

        int totalLength = left.length + right.length;

        byte[] combined = new byte[totalLength];

        int index = 0;

        for (; index < left.length; index++) {
            combined[index] = left[index];
        }

        for (int i = 0; index < totalLength; index++, i++) {
            combined[index] = right[i];
        }

        return combined;
    }

    // don't use this chet
    static byte[] addBinary(byte[] input) {
        int len = input.length / 2 - 1;
        byte[] newSBox = new byte[4];
        byte carry = 0;

        byte[] leftSplitBits = split(input, 'l');
        byte[] rightSplitBits = split(input, 'r');

        for (int i = len; i > 0; i--) {
            newSBox[i] = sum(leftSplitBits[i], rightSplitBits[i], carry);
            carry = carryIn(leftSplitBits[i], rightSplitBits[i], carry);
        }

        return input;
    }

    // for printing byte[] array
    static void print(byte[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }


    /* *********** Testing *********** */

    // handles initial permutation, expansion, and compression permutation
    static void permuteTestCase() {

        // original test block
        byte[] plainTextBlock = {0, 1, 1, 0, 1, 0, 0, 1};

        // to test if initial permutation was correct
        byte[] permutedPlainTextBlock = {1, 0, 1, 0, 0, 1, 1, 0};

        // permute plaintext based
        byte[] initialPBox = permute(8, 8, plainTextBlock, initialPBoxTable);

        // testing to see if original permutations are equal
        System.out.println("** Initial Permutation test **");
        System.out.println("Original Test block: ");
        for (int i = 0; i < plainTextBlock.length; i++) {
            System.out.print(plainTextBlock[i]);
        }

        System.out.println("\n\nPermuted by hand to compare if next line is correct:");
        for (int i = 0; i < permutedPlainTextBlock.length; i++) {
            System.out.print(permutedPlainTextBlock[i]);
        }

        System.out.println("\n\nAfter running permuted function. Should equal top permuted line:");
        for (int i = 0; i < initialPBox.length; i++) {
            System.out.print(initialPBox[i]);
        }

        // test sample for expansion
        byte[] expansionTest = {1, 2, 3, 4};
        // use expansion permutation table: 4 bits to 8 bits
        byte[] expansion = permute(4, 8, expansionTest, expansionPermutationTable);

        System.out.println("** Expansion Test **");
        System.out.println("\n\nOriginal test sample to run expansion test: ");
        for (int i = 0; i < expansion.length; i++) {
            System.out.print(expansion[i]);
        }

        System.out.println("\n\nAfter running expansion permutation: ");
        for (int i = 0; i < expansion.length; i++) {
            System.out.print(expansion[i]);
        }
        System.out.println("\n");

        // compression pbox test

        byte[] compressionTest = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        byte[] compressed = permute(10, 8, compressionTest, keyCompressionPBoxTable);

        System.out.println("Compression PBox Test");
        System.out.println("Initial set");
        for (int i = 0; i < compressionTest.length; i++) {
            System.out.print(compressionTest[i]);
        }

        System.out.println("\n\nAfter running compression permutation: ");
        for (int i = 0; i < compressed.length; i++) {
            System.out.print(compressed[i]);
        }
        System.out.println();

    }

    static void shiftTestCase() {

        byte[] testPlainText = {1, 1, 1, 1, 0, 0, 0, 0};

        byte[] oneShift = shiftLeft(testPlainText);

        byte[] twoShifts = shiftLeft(oneShift);

        System.out.println("No shifts");
        for (int i = 0; i < testPlainText.length; i++) {
            System.out.print(testPlainText[i]);
        }

        System.out.println("\nOne shift");
        for (int i = 0; i < oneShift.length; i++) {
            System.out.print(oneShift[i]);
        }

        System.out.println("\nTwo shifts");
        for (int i = 0; i < twoShifts.length; i++) {
            System.out.print(twoShifts[i]);
        }
    }

    static void splitTestCase() {
        byte[] testPlainText = {1, 1, 1, 1, 0, 0, 0, 0};

        byte[] splitLeft = split(testPlainText, 'l');

        byte[] splitRight = split(testPlainText, 'r');

        System.out.println("Original Plain Te");
        for (int i = 0; i < testPlainText.length; i++) {
            System.out.print(testPlainText[i]);
        }

        System.out.println("\nLeft Split");
        for (int i = 0; i < splitLeft.length; i++) {
            System.out.print(splitLeft[i]);
        }

        System.out.println("\nRight Split");
        for (int i = 0; i < splitRight.length; i++) {
            System.out.print(splitRight[i]);
        }

    }

    static void keyGeneratorTestCase() {

        // byte[] testCipherKey = {0, 1, 1, 0, 0, 1, 1, 0, 0, 1};
        byte[] testCipherKey = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // straight PBox
        byte[] straightPBox = permute(10, 10, testCipherKey, keyStraightPBoxTable);

        System.out.println("\nStraight P Box: ");
        print(straightPBox);

        // split
        byte[] leftKey = split(straightPBox, 'l');
        byte[] rightKey = split(straightPBox, 'r');

        System.out.println("\n5 Left keys: ");
        print(leftKey);
        System.out.println("\n5 Right keys: ");
        print(rightKey);

        // first shift
        leftKey = shiftLeft(leftKey);
        rightKey = shiftLeft(rightKey);

        System.out.println("\nLeft 5 key shifted: ");
        print(leftKey);
        System.out.println("\nRight 5 key shifted: ");
        print(rightKey);

        // combine
        byte[] combinedKey = combine(leftKey, rightKey);

        System.out.println("\nCombined Key: ");
        print(combinedKey);

        // first compression
        byte[] roundOneKey = permute(10, 8, combinedKey, keyCompressionPBoxTable);
        System.out.println("\n** Round One Key ** ");
        print(roundOneKey);

        // second shift
        byte[] leftKeySplitTwice = shiftLeft(shiftLeft(leftKey));
        byte[] rightKeySplitTwice = shiftLeft(shiftLeft(rightKey));

        System.out.println("\nLeft 5 key shifted twice!: ");
        print(leftKeySplitTwice);
        System.out.println("\nRight 5 key shifted twice!: ");
        print(rightKeySplitTwice);

        // forgot to combine
        combinedKey = combine(leftKeySplitTwice, rightKeySplitTwice);

        // second compression
        byte[] roundTwoKey = permute(10, 8, combinedKey, keyCompressionPBoxTable);
        System.out.println("\n** Round Two Key ** ");
        print(roundTwoKey);

    }

    static void cipherKeyGenTestCase() {

        // this test is from the book - TABLE O.1 pg. 664
        byte[] ciphCase1 = {1, 0, 1, 1, 1, 0, 0, 1, 1, 0};
        byte[] ciphCase2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] ciphCase3 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte[][] newCiph1 = keyGenerator(ciphCase1, 2);
        byte[][] newCiph2 = keyGenerator(ciphCase2, 2);
        byte[][] newCiph3 = keyGenerator(ciphCase3, 2);

        System.out.println("\n** Case 1 ** ");
        System.out.println("Original Key cipher");
        print(ciphCase1);
        System.out.println("Round 1 Key");
        print(newCiph1[0]);
        System.out.println("Round 2 Key");
        print(newCiph1[1]);

        System.out.println("\n** Case 2 ** ");
        System.out.println("Original Key cipher");
        print(ciphCase2);
        System.out.println("Round 1 Key");
        print(newCiph2[0]);
        System.out.println("Round 2 Key");
        print(newCiph2[1]);

        System.out.println("\n** Case 3 ** ");
        System.out.println("Original Key cipher");
        print(ciphCase3);
        System.out.println("Round 1 Key");
        print(newCiph3[0]);
        System.out.println("Round 2 Key");
        print(newCiph3[1]);
    }

    static void combineTestCase() {
        byte[] test1 = {0, 1, 1, 0, 1};
        byte[] test2 = {0, 0, 0, 1, 1};

        byte[] sample = combine(test1, test2);

        System.out.println("\nLeft array: ");
        print(test1);

        System.out.println("\nRight array:");
        print(test2);

        System.out.println("\nCombined array:");
        print(sample);

    }

    static void whitenerXORTestCase() {

        byte[] plainText = {1, 1, 1, 1, 0, 0, 1, 0};

        byte[] roundOneKey = {1, 0, 1, 1, 1, 1, 0, 0};

        // uses whitenerXOR function here
        byte[] xor = whitenerXOR(plainText, roundOneKey);

        System.out.println("\nOriginal Plain Text");
        print(plainText);
        System.out.println("\nOriginal Round One Key");
        print(roundOneKey);

        System.out.println("\nXOR array");
        print(xor);
        System.out.println();

        // show as a XOR table
        print(plainText);
        print(roundOneKey);
        System.out.println("-----------");
        print(xor);

    }

    static void sboxTestCase() {

        byte[] test = {0, 1, 1, 0, 1, 1, 1, 0};

        byte[] leftSplitSbox1 = split(test, 'l');

        byte[] rightSplitSbox2 = split(test, 'r');

        System.out.println("8 bit split into 2; left and right");
        print(leftSplitSbox1);
        print(rightSplitSbox2);

        byte[] sBox1Row = {leftSplitSbox1[0], leftSplitSbox1[3] };
        byte[] sBox1Column= {leftSplitSbox1[1], leftSplitSbox1[2] };

        System.out.println("Left Split SBox 1: Row, Column: ");
        print(sBox1Row);
        print(sBox1Column);

        String rowString = "";
        String columnString = "";

        for(int i = 0; i < 2; i++) {
            rowString += sBox1Row[i];
        }

        for(int i = 0; i < 2; i++) {
            columnString += sBox1Column[i];
        }

        int row = Integer.parseInt(rowString, 2);
        int column = Integer.parseInt(columnString, 2);

        int sBox1Result = sBox1Table[row][column];
        String sBox1Test = Integer.toBinaryString(sBox1Table[row][column]);

        System.out.println("wat");
        System.out.println(sBox1Result);
        System.out.println(sBox1Test);

    }

    static byte sum(byte a, byte b, byte carry) {
        return (byte) (a ^ b ^ carry);
    }

    static byte carryIn(byte a, byte b, byte carry) {
        return (byte) ((a | b) & (a | carry) & (b | carry));
    }

}
