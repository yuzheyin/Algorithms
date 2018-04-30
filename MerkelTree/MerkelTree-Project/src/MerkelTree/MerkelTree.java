package MerkelTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import edu.cmu.andrew.yuzhey.SinglyLinkedList;

public class MerkelTree {
	
	public static void main(String args[]) throws FileNotFoundException, NoSuchAlgorithmException{
		
		System.out.println("Please enter a file name: "
				+ "(smallFile.txt, CrimeLatLonXY.csv, CrimeLatLonXY1990_Size2.csv, CrimeLatLonXY1990_Size3.csv)");
		Scanner file = new Scanner(System.in);
		String name = file.nextLine();
		Scanner scan = new Scanner(new File(name));
		SinglyLinkedList merkelTree = new SinglyLinkedList();       // State the list of lists
		
		SinglyLinkedList data = new SinglyLinkedList();            // Create the list of data read from files L0
		String temp = new String();
		while(scan.hasNextLine()){
			temp = scan.nextLine();
			data.addAtEndNode(temp);
		}

		if(data.countNodes()%2 != 0)
			data.addAtEndNode(temp);
	
		merkelTree.addAtEndNode(data);
		
		SinglyLinkedList current = new SinglyLinkedList();
		SinglyLinkedList hashList;
		String s1 = new String(), s2 = new String();
		
		current = (SinglyLinkedList) merkelTree.getLast();   // Generate the first hash list L1
		hashList = new SinglyLinkedList();
		current.reset();
		while(current.hasNext()){
			temp = (String)current.next();
			hashList.addAtEndNode(h(temp));
		}
		
		merkelTree.addAtEndNode(hashList);
		
		while(true){                                            // Generate the rest lists
			current = (SinglyLinkedList) merkelTree.getLast();
			hashList = new SinglyLinkedList();
			current.reset();
			while(current.hasNext()){
				s1 = (String) current.next();
                s2 = (String) current.next();
				hashList.addAtEndNode(h(s1+s2));
			}
			if(hashList.countNodes() == 1){
				merkelTree.addAtEndNode(hashList);
				break;
			}
			if(hashList.countNodes() %2 != 0)           // If the number of the node is odd, make a duplicate node at the end
				hashList.addAtEndNode(h(s1 + s2));

			merkelTree.addAtEndNode(hashList);
			
		}
		
		System.out.println("The Merkel Tree root is: ");
		System.out.println(((SinglyLinkedList)merkelTree.getLast()).toString());
		
	}
	
	public static String h(String text) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 31; i++) {
			byte b = hash[i];
			sb.append(String.format("%02X", b));
		}
		
		return sb.toString();
	}

}
