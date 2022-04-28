/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java Codec
 * 
 * Description: Creates the Codec class and its associated methods
 * 
 */ 

public class Codec {
    
    /*
     * Input: char ch
     * Output: int[] binaryRepresentation
     * 
     * Description: Converts a character into its binary representation as an array
     * 
     */ 
    private static int[] charToIntArray(char ch) {
        int[] binary = new int[7]; // Binary representation of characyer
        
        // Iterating loop
        for (int i = binary.length - 1; i >= 0; i--) {
            binary[i] = ch % 2; // Stores remainder in last open slot
            ch /= 2; // Divides character value
        }
        
        return binary;
    }
        
    /*
     * Input: int[] binaryRepresentation
     * Output: char ch
     * 
     * Description: Converts a set of binary bits to its character form
     * 
     */     
    private static char intArrayToChar(int[] bitString) {
        int c = 0; // Int representation 
        
        // Iterating loop
        for (int i = 6; i >= 0; i--) {
            if (bitString[i] == 1) { // Checks if bitString[i] is a 1
                c += Math.pow(2, 6 - i); // Updates c
            }
        }
        
        return (char) c;
    }
    
    /*
     * Input: String message
     * Output: int[] encodedMessage
     * 
     * Description: Converts a given string into its binary equivalent
     * 
     */
    public static int[] encode(String str) {
        // Checks if string is empty or null
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return new int[str.length() * 7];
        }
        
        // Iterating loop
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 127) { // Checks if unicode is > 127
                throw new 
                    IllegalArgumentException("String contains illegal characters");
            }
        }
        
        int[] bitString = new int[str.length() * 7]; // Binary representation
        int charNum = 0; // Index of character being converted to binary
        
        // Sets starting index for new charcter being converted
        for (int i = 0; i < bitString.length; i += 7) { 
            // Holds bits of current character in str
            int[] tempBits = charToIntArray(str.charAt(charNum));
            int index = 0; // Index of tempBits
            
            // Sets index for set of 7 bits to be initialized
            for (int j = i; j < i + 7; j++) { // 
                bitString[j] = tempBits[index];
                index++;
            }
            
            charNum++;
        }
        
        return bitString;
    }
    
    /*
     * Input: int[] binaryRepresentation
     * Output: String message
     * 
     * Description: Converts binary representation into its equivalent message
     * 
     */ 
    
    public static String decode(int[] bits) {
        if (bits == null) { // Checks if bits is null
            return null;
        } else if (bits.length % 7 != 0) { // Checks if bits is not a multiple of 7
            throw new IllegalArgumentException("Array is incorrect length");
        }
        
        // Iterating loop
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] != 0 && bits[i] != 1) { // Checks for non 0 or 1 values
                throw new IllegalArgumentException("Array contains illegal values");
            }
        }
        
        String message = ""; // String form of bits
        
        // Sets starting index of new character being converted
        for (int i = 0; i < bits.length; i += 7) {
            int index = 0; // Index for temp array
            int[] temp = new int[7]; // Holds bits to be converted to a char
            
            for (int j = i; j < i + 7; j++) { // Iterates over 7 bits of the char
                temp[index] = bits[j]; // Initializes temp
                index++;
            }
            message += "" + intArrayToChar(temp); // Updates message
        }
        
        return message;
    }
    
    /*
     * Input: int[] binaryRepresentation, String seed, int tapPosition
     * Output: None
     * 
     * Description: Encrypts message by running message through an LFSR
     * 
     */
    
    public static void encrypt(int[] message, String seed, int tapPosition) {
        LFSR password = new LFSR(seed, tapPosition); // Creates LFSR
        
        if (message == null) { // Checks if message is null
            return;
        }
        
        if (message.length % 7 != 0) { // Checks if message is correct length
            throw new IllegalArgumentException("message is improper length");
        } else {
            // Iterating loop
            for (int i = 0; i < message.length; i++) {
                if (message[i] != 0 && message[i] != 1) { // Checks for a non 0 or 1
                    throw new 
                        IllegalArgumentException("Message contains illegal values");
                }
            }
        }
        
        // Iterating loop
        for (int i = 0; i < message.length; i++) {
            message[i] = message[i] ^ password.nextBit(); // Encrypts message
        }
    }
    
    /*
     * Input: int[] encryptedRepresentation, String seed, int tapPosition
     * Output: None
     * 
     * Description: Runs cipher through encrypt() again to undo its effects
     * 
     */ 
    
    public static void decrypt(int[] cipher, String seed, int tapPosition) {
        encrypt(cipher, seed, tapPosition); // Reverses encryption
    }
    
    public static void main(String[] args) {
//         // Test 1
//         int[] test1 = charToIntArray('F');
//         for (int i = 0; i < test1.length; i++) {
//             System.out.println(test1[i]);
//         }
       
//         // Test 2
//         int[] test2 = {1, 0, 1, 0, 1, 1, 1};
//         char test2C = intArrayToChar(test2);
//         System.out.println(test2C);
        
//         // Test 3
//         System.out.println(intArrayToChar(charToIntArray('W')));
        
//         // Test 4
//         for (int i = 0; i < 7; i ++) {
//             int[] test4 = {1, 0, 0, 0, 0, 1, 1};
//             int[] newTest4 = charToIntArray(intArrayToChar(test4));
//             System.out.println(newTest4[i]);
//         }
        
//         // Test 5
//         System.out.println(intArrayToChar(charToIntArray('B')));
//         System.out.println(intArrayToChar(charToIntArray('u')));

        // Test 6
//         int[] test6 = encode("Grace");
//         for (int i = 0; i < test6.length; i++) {
//             System.out.println(test6[i]);
//         }  
        
//         // Test 7
//         int[] test7 = encode("Alan");
//         String test7M = decode(test7);
//         System.out.println(test7M);
        
//         // Test 8
//         int[] message = {1, 0, 0, 0, 0, 1, 1};
//         encrypt(message, "01101000010", 8);
//         for (int i = 0; i < message.length; i++) {
//             System.out.println(message[i]);
//         }
//         decrypt(message, "01101000010", 8);
//         for (int i = 0; i < message.length; i++) {
//             System.out.println(message[i]);
//         }
    }
}