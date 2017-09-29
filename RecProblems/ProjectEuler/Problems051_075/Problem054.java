package Problems051_075;

import java.util.*;
import utilities.Files;

public class Problem054 {

	public static void main(String[] args) {
		ArrayList<String> file = Files.fileList("C:/Users/Ryan Lapchynski/EclipseWorkspace/RecProblems/ProjectEuler/Problems051_075/p054_poker.txt");
		
		int t=0;
		for(String hands : file){
			String[] cards = hands.split(" ");
			String[] p1A = {cards[0], cards[1], cards[2], cards[3], cards[4]};
			String[] p2A = {cards[5], cards[6], cards[7], cards[8], cards[9]};
			ArrayList<String> p1 = new ArrayList<>( Arrays.asList(p1A));
			ArrayList<String> p2 = new ArrayList<>( Arrays.asList(p2A));
			
			
			if(rankHand(p1) < rankHand(p2)){
				t++;
			}
		}
		System.out.println(t);
	}
	
	public static int rankHand(ArrayList<String> cards){
		/*
		 * 9+ = High Card: Highest value card.
		 * 8 = One Pair: Two cards of the same value.
		 * 7 = Two Pairs: Two different pairs.
		 * 6 = Three of a Kind: Three cards of the same value.
		 * 5 = Straight: All cards are consecutive values.
		 * 4 = Flush: All cards of the same suit.
		 * 3 = Full House: Three of a kind and a pair.
		 * 2 = Four of a Kind: Four cards of the same value.
		 * 1 = Straight Flush: All cards are consecutive values of same suit.
		 * 0 = Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
		 */
		
		return 0;
		
	}
	
	public static String[] orderHand(ArrayList<String> cards){
		String[] ret = new String[5];
		for(int i=0; i<4; i++){
			@SuppressWarnings("unused")
			String lowest = "2";
			for(int a=0; a<4; a++){
				
			}
		}
		return ret;
	}

}
