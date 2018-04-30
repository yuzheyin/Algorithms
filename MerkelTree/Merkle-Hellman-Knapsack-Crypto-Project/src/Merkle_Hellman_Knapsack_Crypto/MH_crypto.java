package Merkle_Hellman_Knapsack_Crypto;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import edu.cmu.andrew.yuzhey.SinglyLinkedList;

public class MH_crypto {
	
	public static void main(String args[]){
		
		SinglyLinkedList w = new SinglyLinkedList();        // State the two lists to hold the super-increasing sequence of BigIntefer for decryption 
		SinglyLinkedList b = new SinglyLinkedList();        // And the public key material used for encryption
		
		/***************************************
		 * Key generation 
		 */
		BigInteger sum = new BigInteger("1");               // Create the super-increasing w list
		for(int i = 0; i < 640; i++){
			BigInteger n = new BigInteger(3, new Random());
			w.addAtFrontNode(sum.add(n));
			sum = sum.add(sum.add(n));
		}

		BigInteger q = sum.add(new BigInteger(3, new Random()));   // Generate number q that is greater than the sum.
		
		BigInteger r = new BigInteger("1");                        // Generate number r that is in the range [1,q) and is coprime to q.
		while(true){
			r = r.add(new BigInteger(6, new Random()));
			if(r.gcd(q).equals(new BigInteger("1")))
				break;
		}
		
		w.reset();
		while(w.hasNext()){                                      // Generate the public key b
			BigInteger temp  = (BigInteger)w.next();
			b.addAtEndNode(temp.multiply(r).mod(q));
		}
		
	    System.out.println("Enter a string and I will encrypt it as single large integer."); // Prompt for the message input
		String message = new String();
		Scanner scan = new Scanner(System.in);
		message = scan.nextLine();
		while(message.length()>80){
			System.out.println("The content is too long, please try again (<=80)");         // Check if the message is too long
		    scan = new Scanner(System.in);
			message = scan.nextLine();	
		}
		System.out.println("Clear text: ");
		System.out.println(message);
		System.out.println("Number of clear text bytes = " + message.length());
		
		/***********************************************
		 * Encryption
		 */
		BigInteger m = new BigInteger("0");   // Use m to store the encrypted message
		String binary = new String();
		char[] str = message.toCharArray();   // Generate the binary representation of the message
		for(int p = str.length-1; p >= 0; p--){
			for(int k = 0; k < 8; k++){
				binary += str[p] & 1;
				str[p] >>=  1;
			}
		}

		b.reset();
		for(int p = 0; p<binary.length(); p++){   // Generate the encrypted message
			BigInteger o = (BigInteger)b.next();
			if(binary.charAt(p) == '1')
				m = m.add(o);
		}
		System.out.println(message + " is encrypted as");
		System.out.println(m);
       
		
		/******************************************
		 * 
		 * Decryption 
		 */
		m = m.multiply(r.modInverse(q)).mod(q);
		char a = 0;
		int e = 0;
		String newBinary = new String();
		
		w.reset();
		StringBuilder result = new StringBuilder();
		for(int x = 0; x < 640; x++){                          // Decrypt using list w
			BigInteger l = (BigInteger)w.next();   
			if(m.subtract(l).compareTo(new BigInteger("0")) >= 0){
				m = m.subtract(l);
                newBinary += '1';
			}
			else
				newBinary += '0';
		}

		for(int v = newBinary.length()-1; v >= 0; v--){       // Convert the binary representation to string
			a <<= 1;
			if(newBinary.charAt(v) == '1')
			a |= 1;
			e++;
			if(e%8 == 0){
				result.append(String.valueOf(a));
				a = 0;
				e = 0;
			}
		}

		System.out.println("Result of decryption: " + result.toString().trim());
		
	}
	

}
