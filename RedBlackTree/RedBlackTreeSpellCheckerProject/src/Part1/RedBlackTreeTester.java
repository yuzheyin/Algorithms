package Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedBlackTreeTester {
	
	public static void main(String args[]) throws FileNotFoundException{
		
		String fileName;
		String command;
		String word;
		boolean check = false;
		System.out.println("Please enter a file name");
		Scanner scan = new Scanner(System.in);
		fileName = scan.nextLine();

	
		Scanner s = new Scanner(new File(fileName));

		RedBlackTree rbt = new RedBlackTree();
		while(s.hasNext()){
			rbt.insert(s.next());
		}
		
		
			System.out.println("java RedBlackTreeSpellChecker " + fileName);
			System.out.println("Loading a tree of English words from " + fileName);
			System.out.printf("Red Black Tree is loaded with %d words \n",rbt.getSize());
			System.out.printf("Initial tree height is %d \n",rbt.height());
			System.out.printf("Never worse than 2 * Lg( n+1) = %f \n\n", (Math.log(rbt.getSize()+1) / Math.log(2))*2);
			
			System.out.println("Legal commands are:");
			System.out.println("d    display the entire word tree with a level order traversal.");
			System.out.println("s    print the words of the tree in sorted order (using an inorder traversal).");
			System.out.println("r    print the words of the tree in reverse sorted order (reverse inorder traversal). ");
			System.out.println("c   <word> to spell check this word.");
			System.out.println("a   <word> add this word to tree.");
			System.out.println("f   <fileName> to check this text file for spelling errors.");
			System.out.println("i   display the diameter of the tree.");
			System.out.println("m   view this menu.");
			System.out.println("!   to quit.");
			
		while(true){			
	        s = new Scanner(System.in);
			command = s.next();
			
			switch(command.toLowerCase()){
				case "d": 
					System.out.println("Level order traversal");
					rbt.levelOrderTraversal();
				    break;
				case "s": 
					System.out.println("Sorted order traversal");
					rbt.inOrderTraversal();
					break;
				case "r": 
					System.out.println("Reverse order traversal");
					rbt.reverseOrderTraversal();
					break;
				case "c": 
					word = s.next();
					if(rbt.contains(word)){
						System.out.printf("Found %s after %d comparisons \n",word,rbt.getRecentCompares());
					}
					else{
						System.out.printf("%s Not in dictionary. Perhaps you mean \n %s\n",word,rbt.closeBy(word));
					}
					break;
				case "a":
					word = s.next();
					if(rbt.contains(word)){
						System.out.println("The word '"+word+"' is already in the dictionary");
					}
					else{
						rbt.insert(word);
						System.out.println("'"+word+"' was added in the dictionary");
					}
					break;
				case "f":
					word = s.next();
					Scanner f = new Scanner(new File(word));
					while(f.hasNext()){
						word = f.next();
						if(!rbt.contains(word)){
							System.out.println("'"+word+"' was not found in the dictionary.");
							check = true;
						}
					}
					if(check == false)
						System.out.println("No spelling errors found.");
					break;
				case "i":
					System.out.println("The diameter of the Red Black Tree is " + rbt.getDiameter());
					break;
				case "m":
					System.out.println("Legal commands are:");
					System.out.println("d    display the entire word tree with a level order traversal.");
					System.out.println("s    print the words of the tree in sorted order (using an inorder traversal).");
					System.out.println("r    print the words of the tree in reverse sorted order (reverse inorder traversal). ");
					System.out.println("c   <word> to spell check this word.");
					System.out.println("a   <word> add this word to tree.");
					System.out.println("f   <fileName> to check this text file for spelling errors.");
					System.out.println("i   display the diameter of the tree.");
					System.out.println("m   view this menu.");
					System.out.println("!   to quit.");
					break;
				case "!":
					System.out.println("Bye!");
					return;
				default:
					System.out.println("Invalid command");
					continue;
			}
			
		}

		
	}
}
