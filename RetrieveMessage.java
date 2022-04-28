/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java RetrieveMessage imageName seed tapPosition
 * 
 * Description: Decodes/Decrypts a message from an image file
 * 
 */ 

public class RetrieveMessage {
    public static void main(String[] args) {
        int[][] image = ImageData.load(args[0]); // int[][] equivalent of image
        // Binary equivalent of message hidden in image
        int[] bits = new int[image.length * image[0].length - 
                             image.length * image[0].length % 7]; 
        int index = 0; // Index of bits
         
        // Iterating loop
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (index < bits.length) { // Checks if index is still within bits 
                    // Sets bits[index] depending on if image[i][j] is odd or even
                    bits[index] = image[i][j] % 2; 
                    index++;
                } else {
                    break; 
                }
            }
        }
        
        // Decrypts bits if proper commandLineArguments are provided
        if (args.length == 3) {
            Codec.decrypt(bits, args[1], Integer.parseInt(args[2]));
        } 
            
        if (Codec.decode(bits).indexOf('\0') != -1) { // Checks for \0 character
                System.out.println(Codec.decode(
                    bits).substring(0, Codec.decode(bits).indexOf('\0')));
            } else {
                System.out.println(Codec.decode(bits));
            }
    }
}