package musicPlaylist;

import java.util.*;

/**
 * Program Assignment 1
 * @author George Nguyen and Sarah Whitelaw
 *
 * This program simulates a music playlist, that allows the user certain functions,
 * such as adding songs, removing songs, searching songs, creating new playlists,
 * and merging playlists.
*   ============  APPLICATION CLASS ===================
 */

public class MyMusicPlaylist {
	public static void main(String[] args){
		// creates a MyPlyalist array list with a capacity of 100 MyPlaylist objects	 
    	MyPlaylist [] List = new MyPlaylist[100];
    	List[0] = new MyPlaylist();
    	Scanner S = new Scanner(System.in);
 	 
    	System.out.println("Welcome to the app of all apps, The Music Playlist.");
    	// puts user into a loop while program is still running    	
    	do {
    		// determines which playlist the user is in
        	List[MyPlaylist.getCurrentPlaylist()].UserChoice( );
        	
        	// if the user makes a new playlist, this code creates a new playlist and updates the playlist count  	 
        	if (MyPlaylist.getMakeNew()){
        		List[MyPlaylist.getNumPlaylists()] = new MyPlaylist();
            	System.out.println("Playlist " + (MyPlaylist.getNumPlaylists() + 1) + " has been created.\n\n");
            	MyPlaylist.setMakeNew();
        	}
        	
        	// if user wants to Union (x + y = z) two playlists	 
        	if (MyPlaylist.getDoUnion()){
            	System.out.print("Enter the first Playlist you want to add.");
            	int one = S.nextInt();
            	System.out.print("Enter the second Playlist you want to add.");
            	int two = S.nextInt();
            	//  creates a new playlist with an initial capacity equal to the total number of songs
            	//  in both playlists
            	List[MyPlaylist.getNumPlaylists()]= new MyPlaylist(List[one - 1].getNumSongs()+ List[two - 1].getNumSongs());
            	//   the new playlist array is filled with all the songs from the first playlist  	 
		    	System.arraycopy(List[one - 1].getPlaylist(), 0, List[MyPlaylist.getNumPlaylists()].getPlaylist(), 0, List[one - 1].getNumSongs());
		    	//   the new playlist array is filled with all the songs from the second playlist
		    	System.arraycopy(List[two - 1].getPlaylist(), 0, List[MyPlaylist.getNumPlaylists()].getPlaylist(), List[one - 1].getNumSongs(), List[two - 1].getNumSongs());
		    	//   creates a new variable to store the total number of songs in the new playlist          	 
            	int nS = List[one - 1].getNumSongs() + List[two - 1].getNumSongs();
            	List[MyPlaylist.getNumPlaylists()].setNumSongs(nS);
            	System.out.println("Playlist " + (MyPlaylist.getNumPlaylists() + 1) + " has been created\n"
                    	+ "by adding the elements of Playlist " + one + " and Playlist " + two + ".");
            	MyPlaylist.setDoUnion();
        	}

        	// if user wants to append a playlist (x += y)           	 
            	if (MyPlaylist.getDoAppend()){
	            	int nS;
	            	System.out.print("Enter the Playlist you would like to append.");
	            	int one = S.nextInt();
	            	System.out.print("Enter the Playlist you would like to be added into your appended Playlist.");
	            	int two = S.nextInt();
	            	int spaceLeft = List[one - 1].getPlaylist().length - (List[one - 1].getNumSongs());
	
	            	// if there is not enough space in the array to add all the songs
	            	if (spaceLeft > 0 && spaceLeft < List[two - 1].getNumSongs()){
		            	System.arraycopy(List[two - 1].getPlaylist(), 0, List[one - 1].getPlaylist(), List[one - 1].getNumSongs(), spaceLeft);
		            	System.out.println("There was not enough space to append all of Playlist " + two + " to "
		                    	+ "Playlist " + one + ".\nHowever, " + spaceLeft + " elements were able to be added.");
		            	nS = List[one - 1].getNumSongs() + spaceLeft;
		            	List[one - 1].setNumSongs(nS);
	            	} 
	            	else if (spaceLeft >= List[two - 1].getNumSongs()){
	                	System.arraycopy(List[two - 1].getPlaylist(), 0, List[one - 1].getPlaylist(), List[one - 1].getNumSongs(), List[two - 1].getNumSongs());
	                	System.out.println("Playlist " + two + " was appended on to the end of Playlist " + one + "\nTo view the appended playlist, go to the playlist that was changed using Choice #6.");
	                	nS = List[one - 1].getNumSongs() + List[two - 1].getNumSongs();
	                	List[one - 1].setNumSongs(nS);
	            	}
	            	else
	                	System.out.println("There is insuffient space to add elements of Playlist " + two + " to Playlist " + one);
	           	 
	            	MyPlaylist.setDoAppend();
            	}
    	}
    	// do while condition is met while user does not exit system
    	while (List[MyPlaylist.getCurrentPlaylist()].GetLoop( )); 
	}
}



