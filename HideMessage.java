/*
 * Name: Alan Wu
 * Pennkey: wualan
 * Execution: java HideMessage imageName messageFile seed tapPosition
 * 
 * Description: Encodes/Encrypts a message file into the given image
 */ 

public class HideMessage {
    public static void main(String[] args) {   
        int[][] image = ImageData.load(args[0]); // Array equivalent of image
        In inStream = new In(args[1]); // Creates file reader
        // Binary version of message
        int[] message = Codec.encode(inStream.readAll() + "\0");
        
        // Checks if the message can fit in the image
        if (image.length * image[0].length < message.length) {
            throw new IllegalArgumentException("Message is too long");
        }
        
        // Encrypts message if proper commandLineArguments are given
        if (args.length == 4) {
            Codec.encrypt(message, args[2], Integer.parseInt(args[3]));
        }
        
        int index = 0; // Index of message
        // Iterating loop
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int[] binary = new int[24]; // Holds binary rep of image[i][j]
                //Initializes binary
                for (int k = binary.length - 1; k >= 0; k--) {
                    binary[k] = image[i][j] % 2;
                    image[i][j] /= 2;
                }
                
                // Sets last index of binary to message bit
                binary[binary.length - 1] = message[index];
                
                
                int temp = 0; // Holds new decimal value of image
                // Iterating loop
                for (int k = binary.length - 1; k >= 0; k--) {
                    if (binary[k] == 1) {
                        temp += Math.pow(2, 23 - k);
                    }
                }
                
                image[i][j] = temp; // Sets image back to decimal value
                index++;
                
                // Checks if index exceeds method.length
                if (index >= message.length) { 
                    break;
                }
            }
            
            // Checks if index exceeds method.length
            if (index >= message.length) {
                break;
            }
        }
            
        ImageData.show(image);
        ImageData.save(image, "picture.png");
    }      
}