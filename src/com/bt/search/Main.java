package com.bt.search;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	/*	this field holds an array of letters to be discarded during formatting */
	private static char[] discardChars = {'a','e','i','h','o','u','w','y'};
	/* this field contains the arrays consisting of equivalent letters */
	private static char[][] equivalentChars = {{'a','e','i','o','u'},
												{'c','g','j','k','q','s','x','y','z'},
												{'b','f','p','v','w'},
												{'d','t'},
												{'m','n'}};
	
	/* this field will hold our list of surnames from a given file */
	private static List<String> fileSurnames = new ArrayList<String>();
	
	/* this field will hold our list of letters to be discarded during formatting */
	private static List<Character> discards = new ArrayList<Character>();
	/* this field will hold the lists consisting of equivalent letters */
	private static List<List<Character>> equivalents = new ArrayList<List<Character>>();
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// populate our list of characters to be discarded
		for(char c : discardChars){
			discards.add(c);
		}
		
		// loop through the arrays of equivalent characters
		for(char[] cArray : equivalentChars){
			// populate our lists of equivalent characters
			List<Character> newC = new ArrayList<Character>();
			for(char c : cArray){
				newC.add(c);
			}
			equivalents.add(newC);
		}		
		
		// store the names given in the arguments
		String[] argNames = args;
		
		// initialise a scanner to read in the input file
		Scanner in = new Scanner(System.in);
		// add all of the names in the input file to our list
		while(in.hasNextLine()){
			fileSurnames.add(in.nextLine());
		}
		
		// loop through all the names we were given as arguments
		for(String name : argNames){
			// output the name to the user
			System.out.print(name + ": ");
			// format the name
			String formatArgName = formatName(name);
			// initialise a variable to keep track of how many matches we have found
			int matchNum = 0;
			// loop through the names passed in the file
			for(String surname : fileSurnames){
				// format these names too
				String formatFileName = formatName(surname);
				// check to see whether the two formatted names are equivalent
				if(checkEquivalence(formatArgName, formatFileName)){
					// increment the match count
					matchNum += 1;
					// if this is the first match, we need not include the preceding comma
					if(matchNum == 1){
						System.out.print(surname);
					}
					else{
						System.out.print(", " + surname);
					}
				}
			}
			// if no matches were found, then output this to the user
			if(matchNum == 0){
				System.out.print("No matches found");
			}
			System.out.print("\n");
		}
	}
	
	
	/**
	 * this method takes a name and constructs a formatted version of that name
	 * by ignoring non-alphabetic characters and removing any characters that 
	 * should be discarded
	 * 
	 * @param surname
	 * 			the name to be formatted
	 * @return
	 * 		the formatted name
	 */
	private static String formatName(String surname){
		// remove cases and convert name to char array
		char[] nameChars = surname.toLowerCase().toCharArray();
		// initialise a string builder to construct our formatted name
		StringBuilder formattedName = new StringBuilder();
		// always add the first character in the name
		formattedName.append(nameChars[0]);
		// loop through every other character in the name
		for (int  i = 1; i < nameChars.length; i++){
			char currentChar = nameChars[i];
			// check that the character is a alphabetic 
			if(Character.isLetter(currentChar)){
				// make sure the character is not one that should be discarded
				if(!discards.contains(currentChar)){
					// loop through our lists of equivalent characters
					for(List<Character> l : equivalents){
						// check to find which list our character belongs in
						if(l.contains(currentChar)){
							// check that we do not have consecutive equivalent characters
							if(!l.contains(nameChars[i-1])){
								// if this character is not equivalent to the previous one, we can append it
								formattedName.append(currentChar);
							}
						}
					}				
				}
			}
		}
		// finally, return the constructed string
		return formattedName.toString();
	}
	
	
	/**
	 * this method takes two formatted names and checks whether or no they are 
	 * equivalent based on each individual character
	 * 
	 * @param surname
	 * 			the first name to be checked against
	 * @param compareName
	 * 			the other name to check for equivalence with
	 * @return
	 * 		true if the names are equivelent, false otherwise
	 */
	private static boolean checkEquivalence(String surname, String compareName){
		
		// since consecutive equivlant characters have already been formatted out
		// then if the name lengths do not match at this point, they are not equivalent
		if(surname.length() != compareName.length()){
			return false;
		}
		
		// initialise the boolean to hold whether the characters math
		boolean match = false;
		char[] nameChars = surname.toLowerCase().toCharArray();
		char[] compareChars = compareName.toLowerCase().toCharArray();
		// loop through all the characters in our surname
		for(int i = 0; i < nameChars.length; i++){
			// check all of the lists of equivalent characters
			for(List<Character> l : equivalents){
				// check to find which list our character belongs in
				if(l.contains(nameChars[i])){
					// if the character we are comparing with is in the same list
					if(l.contains(compareChars[i])){
						// the characters indicate a match
						match = true;
					}
					else{
						match = false;
					}
				}
			}
			// if only one character does not match, then the names are not equivalent
			if (match == false){
				return false;
			}
		}

		// if we reach this point, then all characters must have been equivalent
		return true;
	}

}


