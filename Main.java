//package progettoasd;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		String word = takingInput(); 
		ArrayList<Character> charList = createListOfChar(word); 
		BinarySearchTree bt = new BinarySearchTree(charList);

		int[] newGraph = bt.newGraph(); 
		System.out.println('\n' + "|V|=" + newGraph[0] + ", |E|=" + newGraph[1] ); 
		System.out.println(bt); 
	}



	public static String takingInput() {
		
		Scanner input = new Scanner(System.in);
		String word = "";
		
		try {
		word = input.nextLine();
		input.close();
		} catch (Exception e) {
			return null;
		}
		
		if ( !inputValidation(word) ) {
			System.err.println("input non valido");
			System.exit(1);
		}
		return word;
		
	}



	private static boolean inputValidation(String word) {
		for (int i=0; i<word.length(); i++) {
			if ( (int) word.charAt(i) - (int) 'a' < 0 || (int) word.charAt(i) - (int) 'z' > 0)
				return false;
		}
		return true;
	}



	public static ArrayList<Character> createListOfChar(String word) {

		ArrayList<Character> charList = new ArrayList<Character>();
		for (int i=0; i < word.length(); i++)
			charList.add(word.charAt(i));

		return charList;
	}


} // class
