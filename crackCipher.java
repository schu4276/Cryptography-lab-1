import java.io.*;
import java.util.*;
public class crackCipher {

    public static void main(String[] args) {
        List<Float> frequencyList = new ArrayList<Float>();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("enter the secret message");
        String message = myScanner.nextLine();
        message = removePunctuation(message);
        String[] blockArray = new String[message.length()/5];
        guessKeyLength(message);
        System.out.println();
        System.out.println("****Look at output for coiencidences, greatest number is key length, type keylength to continue");
        int lengthInput = myScanner.nextInt();
        //must change first input of divideCiphertext to key length
        blockArray = divideCiphertext2(lengthInput,message);
        

        char[] letters = message.toCharArray();
        char[] alph;
        List<Double> alphFreq = new ArrayList<Double>(); 
        alphFreq.add(8.12); //a
        alphFreq.add(1.49); //b
        alphFreq.add(2.71); //c
        alphFreq.add(4.32); //d
        alphFreq.add(12.02); //e
        alphFreq.add(2.30); //f
        alphFreq.add(2.03); //g  
        alphFreq.add(5.92); //h
        alphFreq.add(7.31); //i
        alphFreq.add(0.10); //j
        alphFreq.add(0.69); //k
        alphFreq.add(3.98); //l
        alphFreq.add(2.61); //m
        alphFreq.add(6.95); //n
        alphFreq.add(7.68); // o 
        alphFreq.add(1.82); //p
        alphFreq.add(0.11); // q
        alphFreq.add(6.02); //r
        alphFreq.add(6.28); //s
        alphFreq.add(9.10); //t
        alphFreq.add(2.88); //u
        alphFreq.add(1.11); //v
        alphFreq.add(2.09); //w
        alphFreq.add(0.17); //x
        alphFreq.add(2.11); //y
        alphFreq.add(0.07); //z
        alph = new char[] {'a','b','c','d','e','f','g','h','i','j','k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        /* this for loop uses the 'blocks' that were obtained in the 
         divideCiphertext method, and calculates, then displays the relative frequencies for 
         each block*/
        for(int i =0; i< blockArray.length; i++){
            
            String blockMessage = blockArray[i];
            for(int j=0; j<25; j++){
                findFrequency(alph[j],blockMessage,frequencyList);

            }
             System.out.println("*******Frequency sums for " + i ); 
           
            findShift(frequencyList, alphFreq);
            // clearing the list after each block, so numbers dont overlap
            frequencyList.clear();
        }
        System.out.println(" ****Observe frequency totals.  The highest total for each block correlates with shift \n count starting from 0***");
        
    }
    // This function calculates the frequencies for each char in the 'secret message' 
    public static void findFrequency(char letter, String message, List<Float> list){
        float frequency =0;
        for(int i =0; i< message.length(); i++){
            if(letter == message.charAt(i)){
                frequency++;
            }
        }
        list.add(((frequency/message.length())*100));

    }

    // This method is used for dividing the ciphertext into blocks based upon the keylength
    public static String[] divideCiphertext2(int keylength, String message){
        int blocklength = (message.length()/keylength); 
        int numberOfBlocks = message.length()/blocklength;
        String[] blockStringArray = new String[numberOfBlocks];
        int i=0; 
        while(i<numberOfBlocks){
            String blockString = "";
            for(int j=0; j<blocklength; j++){
                
                blockString += message.charAt((keylength*j) + i);       
            }
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

    // Finds the shift for a given block
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
            if(i>0){
               System.out.println(counter + " coincidences at key length " + i); 
            }
            i++;
        }
    }

}