import java.io.*;
import java.util.*;
public class CryptLab1{
	public static void main(String[] args){
		//takes in input from user to get message and key
		Scanner myScanner = new Scanner(System.in);
		System.out.println("enter the secret message");
		String message = myScanner.nextLine();
		System.out.println("enter the key");
		String key = myScanner.nextLine();
		String cipherText = createEncryption(message, key);
		System.out.println("your encrypted message is: " + cipherText);
	}//end main
	public static String createEncryption(String message, String key){
		String plainText = removePunctuation(message);
		String extendedKey = generateKey(message.length(), key);
		String cipherText = encrypt(plainText, extendedKey);

		return cipherText;
	}




	//function to generate cyclical key 
	public static String generateKey(int length, String key){
		for(int i=0; ; i++){
			if(length == i){
				i =0;
			}
			// when key is correct length, stop
			if(key.length() == length){
				break;
			}
			key+=(key.charAt(i));
		}
		return key.toUpperCase();
	}

	/*This function removes spaces and punctuation from the message
		so that it can be encrypted*/
	public static String removePunctuation(String message){
		//remove spaces
		message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
		return message;
	}

	//function for encrypting text
	public static String encrypt(String text, String key){
		String cipherText = "";
		for(int i=0; i< text.length(); i++){
			//convert letter to number 0-25
			int k = (text.charAt(i) + key.charAt(i)) %26;
			// convert back to alphabet using ASCII
			k+= 'A';

			cipherText +=(char)(k);
		}
		return cipherText.toLowerCase();
	}
}//end Lab