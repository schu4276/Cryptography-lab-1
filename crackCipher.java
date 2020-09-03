import java.io.*;
import java.util.*;
public class crackCipher {

    public static void main(String[] args) {
        List<Float> frequencyList = new ArrayList<Float>();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("enter the secret message");
        String message = myScanner.nextLine();
        message = removePunctuation(message);
        System.out.println(message);
        String[] blockArray = new String[message.length()/5];
        guessKeyLength(message);
        //must change first input of divideCiphertext to key length
        blockArray = divideCiphertext2(5,message);
        

        char[] letters = message.toCharArray();
        char[] alph;
        List<Double> alphFreq = new ArrayList<Double>(); 
        alphFreq.add(8.12); //a
        alphFreq.add(1.49);
        alphFreq.add(2.71);
        alphFreq.add(4.32);
        alphFreq.add(12.02); //e
        alphFreq.add(2.30);
        alphFreq.add(2.03);   
        alphFreq.add(5.92);
        alphFreq.add(7.31); //i
        alphFreq.add(0.10);
        alphFreq.add(0.69);
        alphFreq.add(3.98);
        alphFreq.add(2.61); //m
        alphFreq.add(6.95);
        alphFreq.add(7.68);
        alphFreq.add(1.82);
        alphFreq.add(0.11);
        alphFreq.add(6.02); //r
        alphFreq.add(6.28);
        alphFreq.add(9.10);
        alphFreq.add(2.88); //u
        alphFreq.add(1.11);
        alphFreq.add(2.09);
        alphFreq.add(0.17);
        alphFreq.add(2.11);
        alphFreq.add(0.07); //z
        alph = new char[] {'a','b','c','d','e','f','g','h','i','j','k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        ///////////////
        for(int i =0; i< blockArray.length; i++){
            String blockMessage = blockArray[i];
            for(int j=0; j<26; j++){
                findFrequency(alph[j],blockMessage,frequencyList);

            }
            System.out.println("*******Frequencies for " + i );
            findShift(frequencyList, alphFreq);
            frequencyList.clear();
        }
        
    }
    public static void findFrequency(char letter, String message, List<Float> list){
        float frequency =0;
        for(int i =0; i< message.length(); i++){
            if(letter == message.charAt(i)){
                frequency++;
            }
        }
        list.add(((frequency/message.length())*100));
        // list.add( letter + "= " + (frequency/message.length()*100));

    }
    public static String divideCiphertext(int keylength, String message){
    int numberOfBlocks = message.length()/keylength;  
    String blockString = "";
        for(int i=0; i<numberOfBlocks; i++){
            blockString += message.charAt(keylength*i);
        }   
        System.out.println("**********************" + blockString);
        return blockString;
    
    }//end divide method
    public static String[] divideCiphertext2(int keylength, String message){
        System.out.println(message.length());
        int blocklength = (message.length()/keylength); 
        int numberOfBlocks = message.length()/blocklength;
        System.out.println("number of blocks " + numberOfBlocks);
        System.out.println("block length is " + blocklength);
        String[] blockStringArray = new String[numberOfBlocks];
        System.out.println(blocklength);
        int i=0;
        while(i<numberOfBlocks){
            String blockString = "";
            for(int j=0; j<blocklength; j++){
                
                blockString += message.charAt((keylength*j) + i);       
            }
            System.out.println(blockString);
            blockStringArray[i] = blockString;
            i++;
        }
        return blockStringArray;
    }


    public static String removePunctuation(String message){
        //remove all unwanted characters
        message = message.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return message;
    }
    public static Float[] findShift(List<Float> cipherFreq, List<Double> englishFreq){
        int counter = 0; 
        Float[] totalsArray = new Float[cipherFreq.size()];
        while(counter<cipherFreq.size()){
            float total =0;
            for(int i =0; i< cipherFreq.size()-1; i++){
                total += (cipherFreq.get(i)* englishFreq.get((i+counter)%26));
            }
            totalsArray[counter] = total;
            counter++;
        }
        for(int i= 0; i< totalsArray.length; i++){
            System.out.println(totalsArray[i]);
          
        }
        return totalsArray;
    }
    public static void guessKeyLength(String message){
        int i = 0;
        while(i<9){
            int counter =0;
            for(int j=0; j<message.length()-i; j++){
                if(message.charAt(j)==message.charAt(j+i)){
                    counter++;
                }
            }
            System.out.println(counter + " coincidences at shift " + i);
            i++;
        }
    }

}