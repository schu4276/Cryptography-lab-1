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

		System.out.println("enter the encrypted message");
		String encryptedText = myScanner.nextLine();
		System.out.println("enter the key");
		key = myScanner.nextLine();
		System.out.println(createDecryption(encryptedText, key));


	}//end main
	public static String createEncryption(String message, String key){
		String plainText = removePunctuation(message);
		String extendedKey = generateKey(message.length(), key);
		String cipherText = encrypt(plainText, extendedKey);

		return cipherText.toUpperCase();
	}
	
	public static String createDecryption(String cryptoText, String key){
		String extendedKey = generateKey(cryptoText.length(), key);
		String cipherText = decrypt(cryptoText, extendedKey);

		return cipherText.toUpperCase();
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
		/*added the toUpperCase() here because I cant figure the ACII math out
		right when the letters were lower case */
		return key.toUpperCase();
	}

	/*This function removes spaces and punctuation from the message
		so that it can be encrypted*/
	public static String removePunctuation(String message){
		//remove all unwanted characters
		message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
		return message;
	}
	//function to decrypt text given key is known
	public static String decrypt(String key, String message){
		//this method still need to be created
		return message;
	}//end decryption method



	//function for encrypting text
	public static String encrypt(String text, String key){
		String cipherText = "";
		for(int i=0; i< text.length(); i++){
			//convert letter to number using ASCII and do mod 26
			int k = (text.charAt(i) + key.charAt(i)) %26;
			// convert back to char using ASCII
			k+= 'A';
			//add the character equivilent to the cipher text
			cipherText +=(char)(k);
		}
		return cipherText.toLowerCase();
	}

}//end Lab
