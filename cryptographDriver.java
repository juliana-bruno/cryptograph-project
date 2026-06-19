/*
 * Name: Juliana Bruno
 * Project: 3
 * Title: cryptographDriver.java
 * Summary: uses a user inputed file to encrypt and decrypt user inputed messages
 * Date: 4/27/25
 */

import java.util.Scanner;
import java.io.*;

public class cryptographDriver {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		Scanner fileInput = null;
		PrintWriter fileOutput = null;
		
		int choice = 0;
		String message = "";
		String encryption = "";
		String outputFile = "";
		
		System.out.println("Welcome to the cryptography program!");
		while (choice != 3) {
			System.out.println("Main menu:\n1 - Encode/Encrypt a message\n2 - Decode/Decrypt a message\n3 - Exit");
			choice = keyboard.nextInt();
			
			if (choice == 1) {
				keyboard.nextLine();

				System.out.println("Please enter the name of the file that contains the encryption key.");
				encryption = keyboard.nextLine();
				
				System.out.println("Please enter the name of the output file.");
				outputFile = keyboard.nextLine();
				
				try {
					fileInput = new Scanner(new FileInputStream(encryption));
					fileOutput = new PrintWriter(new FileOutputStream(outputFile, true));
				} catch (FileNotFoundException e) {
					System.err.println("File not found.");
					System.exit(0);
				} catch (Exception e) {
					System.err.println("File could not be opened");
					System.exit(0);
				}
				
				if (fileInput.hasNextLine() == false) {
					System.err.println("File is empty.");
					System.exit(1);
				}
				
				String alphabet = fileInput.nextLine();
				String key = fileInput.nextLine();
				
				if (alphabet.length() != 52 && key.length() != 52) {
					System.err.println("The encryption key does not have 52 characters.");
					System.exit(2);
				}
				
				System.out.println("Please enter your message.");
				message = keyboard.nextLine();
				
				encrypt(message, alphabet, key, fileOutput);
				
			} else if (choice == 2) {
				keyboard.nextLine();

				System.out.println("Please enter the name of the file that contains the encryption key.");
				encryption = keyboard.nextLine();
				
				System.out.println("Please enter the name of the output file.");
				outputFile = keyboard.nextLine();
				
				try {
					fileInput = new Scanner(new FileInputStream(encryption));
					fileOutput = new PrintWriter(new FileOutputStream(outputFile));
				} catch (FileNotFoundException e) {
					System.err.println("File not found.");
					System.exit(0);
				}
				
				if (fileInput.hasNextLine() == false) {
					System.err.println("File is empty.");
					System.exit(1);
				}
				
				String alphabet = fileInput.nextLine();
				String key = fileInput.nextLine();
				
				if (alphabet.length() != 52 && key.length() != 52) {
					System.err.println("The encryption key does not have 52 characters.");
					System.exit(2);
				}
				
				System.out.println("Please enter your message.");
				message = keyboard.nextLine();
				
				decrypt(message, alphabet, key, fileOutput);
				
			} else if (choice == 3){
				System.out.println("Thank you for using the cryptography program. Goodbye!");
				fileOutput.close();
			} else {
				System.out.println("Please enter a valid number.");

			}
			
			
			
		}// end of while loop
		
		
/* Output:
* 
* 
Welcome to the cryptography program!
Main menu:
1 - Encode/Encrypt a message
2 - Decode/Decrypt a message
3 - Exit
1
Please enter the name of the file that contains the encryption key.
encryptionKey.txt
Please enter the name of the output file.
output.txt
Please enter your message.
Hide this message
Psyr hpsf crffgqr
Main menu:
1 - Encode/Encrypt a message
2 - Decode/Decrypt a message
3 - Exit
2
Please enter the name of the file that contains the encryption key.
encryptionKey.txt
Please enter the name of the output file.
output.txt
Please enter your message.
Agkg sf mdb
Java is fun
Main menu:
1 - Encode/Encrypt a message
2 - Decode/Decrypt a message
3 - Exit
3
Thank you for using the cryptography program. Goodbye!
* 
*  
*/
		
	}//end of main method

	public static void decrypt(String message, String alphabet, String key, PrintWriter fileOutput) {
		String decryptedMessage = "";
			
		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);			
			int index = key.indexOf(c);
			
			if (index == -1) {
				decryptedMessage = decryptedMessage + " ";
			} else {
				decryptedMessage = decryptedMessage + alphabet.charAt(index);
			}//end of if else
		}//end of for loop
		fileOutput.println(decryptedMessage);
		System.out.println(decryptedMessage);
	}
	
	public static void encrypt(String message, String alphabet, String key, PrintWriter fileOutput) {
		String encryptedMessage = "";
		
		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);			
			int index = alphabet.indexOf(c);
			
			if (index == -1) {
				encryptedMessage = encryptedMessage + " ";
			} else {
				encryptedMessage = encryptedMessage + key.charAt(index);
			}
		}
		fileOutput.println(encryptedMessage);
		System.out.println(encryptedMessage);
	}
	
	
}// end of class
