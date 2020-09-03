import java.io.*;
import java.util.*;
public class CryptLab1{
	public static void main(String[] args)throws FileNotFoundException{
		// takes in input from user to get message and key
		Scanner myScanner = new Scanner(System.in);
		System.out.println("enter the secret message");
		String message = myScanner.nextLine();
		System.out.println("enter the key");
		String key = myScanner.nextLine();
		String cipherText = createEncryption(message, key);
		System.out.println("your encrypted message is: " + cipherText.toLowerCase());

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
		String cipherText = decrypt(cryptoText.toUpperCase(), extendedKey);

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
		String decryptedText = "";
		for(int i=0; i<message.length(); i++){
			int k = key.charAt(i) - message.charAt(i);
			if(k<0){
				k += 26;
			}
			k += 'A';

			decryptedText += (char)(k);
		}
		return decryptedText;
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
	public static String[] dictToArray() throws FileNotFoundException{
		File file = new File("C:\\Practicum\\engmix.txt");
		Scanner myScanner = new Scanner(file);
		List<String> lines = new ArrayList<String>();
		while(myScanner.hasNextLine()) {
			lines.add(myScanner.nextLine());
		}
		String[] dictArray = lines.toArray(new String[0]);
		System.out.println(dictArray.length);
		return dictArray;
	}
	public static ArrayList<String> getPotentialAnswer(String[] dictArray, String message){
		String word = "";
		int counter =0;
		message = message.toUpperCase();
		ArrayList<String> possibleKeys = new ArrayList<String>();
		// get a word from the dictonary with 8 characters or less
		for(int i=0; i<dictArray.length; i++){
			if(dictArray[i].length() < 9 && dictArray[i].length()>2){
				word = dictArray[i];
			}
			else{
				continue;
			}

		
		// decrypt the message using this chosen word
		String answer = decrypt(generateKey(message.length(),word), message);
		counter++;
		//convert answer to a char array
		char[] answerArray = new char[answer.length()];
		for(int j=0; j< answer.length(); j++){
			answerArray[j] = answer.charAt(j);
		}
		// Check to see if answer has an english first word
		for(int j=0; j<dictArray.length; j++){
			String checkWord = dictArray[j];
			String firstMessageWord = "";
			// create word from messsage equal amount of characters 
			for(int k =0;k<checkWord.length(); k++){
				firstMessageWord += answerArray[k];

			}
			// check to see if the words match
			checkWord = checkWord.toUpperCase();
			if(firstMessageWord.equalsIgnoreCase(checkWord)){
				System.out.println("potential match found, key = " + word);
				possibleKeys.add(word);
			}
		}

		}
		System.out.println(counter);
		return possibleKeys;
	}//end potential answers method

}//end Lab
