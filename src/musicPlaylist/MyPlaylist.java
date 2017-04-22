package musicPlaylist;

import java.util.Scanner;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author George Nguyen, Sarah Whitelaw
*   ==================== SERVICE CLASS ================
 */

public class MyPlaylist implements Cloneable {
	Scanner S = new Scanner(System.in);            
	private int numSongs;                      //  variable used to record the number of songs in a playlist
	private int[]Playlist;                     //  variable used to create an int array playlist
	private boolean Looping;                   //  variable that controls whether the user is in the program or has quit
	private static int numPlaylists= 0;        //  variable used to count the number of total playlists
	private static boolean makeNew = false;    //  variable used to control whether a new playlist will be made
	private static int c = 0;                  //  variable used to access which playlist the user is in
	private static boolean doUnion = false;	   //  variable to control when a union function will be used
    public static boolean doAppend = false;    //  variable to control when a append function will be used
    
// CONSTRUCTORS ====================================================
    
/**
 *  overloaded constructor creates Bag ADT with a given capacity. However, the
 *  overloaded constructor will not be needed because we are assuming that
 *  the initial capacity is 30 anyhow.
 * @param initialCapacity
 */
	public MyPlaylist(int initialCapacity) {
    	numSongs = 0;
    	Playlist = new int[initialCapacity];
    	Looping = true;
    	System.out.println("Playlist has been created with an initial capacity "
            	+ "of "+ initialCapacity + ".\n");
	}
    
/**
 *  default constructor takes no parameters and creates Bag ADT with capacity
 *  of 30 songs
 */
	public MyPlaylist( ) {
    	numSongs = 0;
    	Playlist = new int[30];
    	Looping = true;
    	System.out.println("Playlist has been created with an initial capacity "
            	+ "of 30.");
	}
    
	
// MODIFIER METHODS =================================================
	
/**
 *  addSong method adds a song to the first available index of playlist
 */
		public void addSong( ){
			try {
				//  if the playlist is full, do not allow to add a new song
		    	if (numSongs == Playlist.length) {
		    		TimeUnit.MILLISECONDS.sleep(700);
		        	System.out.print("Sorry, the playlist is full. \nTo add a song, "
		                	+ "please delete a song from the playlist or create a new "
		                	+ "playlist.\n\n\n");
		    	} else {
		        	System.out.print("\nPlease enter the songID of the song you want "
		                	+ "to add: ");
		        		int SongInput = S.nextInt( );
		        	//  Protecting program from invalid input
		        	if ( SongInput <= 0 ) {
		        		TimeUnit.MILLISECONDS.sleep(500);
		            	System.out.print("\nSorry, you must add a positive song ID.");
		        	} else {               	 
		            	boolean repeat = false;
		            	for (int i = 0; i < Playlist.length; i++){
		            		//  Protect the program from keeping multiple copies of a song, returns user to interface
		                	if (SongInput == Playlist[i]){
		                    	repeat = true;
		                	}
		            	}
		            	if (repeat){
		            		TimeUnit.MILLISECONDS.sleep(1000);
		            		System.out.println("\n\033[31mThis song ID was already "
		                        	+ "entered previously.\n\033[31mBecause of this, "
		                        	+ "it will not be added.\n\n");
		            	} else {
		            	//  there are no invalid inputs, so the song is added
		            		TimeUnit.MILLISECONDS.sleep(500);
		            		System.out.println("\nSong " + SongInput + " has been added.\n\n");
		            		numSongs++;
		        		// Number of songs is incremented and sorted from lowest to highest.
		        		// not entirely sorted, but will put a number greater than the numSongs in 
		        		// playlist at end, and a number less that that at it’s index
		            	if (SongInput < numSongs) {
		                	for (int o = 0; o < numSongs - SongInput; o++){
		                    	Playlist[numSongs - o - 1] = Playlist[numSongs - 2 - o];
		                	}
		                	Playlist[SongInput - 1] = SongInput;
		            	} else
		                	Playlist[numSongs - 1] = SongInput;
		            	}
		        	}
		    	}  
			} catch (InterruptedException ie){
				ie.printStackTrace();
			}
	}
    
/**
 *  removeSong method removes a song from the playlist and fills gaps
 */
	public void RemoveSong( ){
		try {
			TimeUnit.MILLISECONDS.sleep(500);
	    	System.out.println("Please enter the songID of the song you would like "
	            	+ "to remove from the playlist.");
	    	int target = S.nextInt( );
	    	int i = 0;
	    	boolean notFound = true;
	    	// while loops looks for song to remove and uses notFound to exit the loop 
	    	while (i < numSongs && notFound){
	        	if (target == Playlist[i]){
	            	numSongs--;
	            	Playlist[i] = 0;
	            	notFound = false;
	        	}
	        	i++;
	    	}
	    	// if a song has been removed, this code block moves all songs over to 
	    	// the left so there are no gaps in the playlist 
	    	if (!notFound){
	        	for (int j = i - 1; j < numSongs; j++){
	            	Playlist[j] = Playlist[j+1];
	        	}
	        	Playlist[numSongs] = 0;
	        	TimeUnit.MILLISECONDS.sleep(500);
	        	System.out.print("SongID " + target + " was removed");
	    	}
	    	//  If the song wasn’t found 
	    	if (notFound) {
	    		TimeUnit.MILLISECONDS.sleep(500);
	        	System.out.println("The song was not found.\n\n\n");
	    	}
		} catch (InterruptedException ie){
			ie.printStackTrace();
		}
	}

	
// Void Methods (Searching & printing) =================================================
	
/**
 *   linear search to search and find a song    
 */
	public void Search( ){
		try {
			TimeUnit.MILLISECONDS.sleep(500);
	    	System.out.println("Type in the song ID number and press enter to "
	                	+ "search for the song.");
	        int Target2 = S.nextInt();
	        boolean found = false;
	        
	    	for(int i=0; i < Playlist.length; i++)
	        	{
	    			//  if the song is found, the program displays which index it is found.
	            	if (Target2 == Playlist[i]){
	            		TimeUnit.MILLISECONDS.sleep(500);
	                	System.out.println("This song ID exists within this "
	                	+ "playlist at index " + i + " from the left of the playlist. " 
	                	+ "To verify, use choice #3 to display the playlist.");
	                	found = true;
	                	break;
	            	}
	        	}
	    	//  if the song is not found, the program lets the user know
	    	if (!found){
	    		TimeUnit.MILLISECONDS.sleep(500);
	        	System.out.println("The song does not exist within this playlist.");
	    	}
		} catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
  
	
	

	
/**
 *   Displays the playlist as an array, complete with square brackets [ ]
 */
	public void PrintList( ){
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("\n\nYour playlist contains the following songs: \n");
	    	System.out.println(Arrays.toString(Playlist) + "\nPlease note that the "
	            	+ "zeroes indicate that there is no song in that index.\n\n");
		} catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
    
/**
 *   displayCurrent() displays the song that is currently playing. 
 *   In this program, that’s the song at the 0th index
 */
	public void displayCurrent(){
		try {
	    	if (numSongs > 0){
	        	int current;
	        	current = Playlist[0];
	        	TimeUnit.MILLISECONDS.sleep(500);
	        	System.out.println("The song currently playing is SongID " + current + ".");
	    	}
	    	else {
	    		TimeUnit.MILLISECONDS.sleep(500);
	        	System.out.println("There is no song at play.");
	    	}
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}


	public static void setDoAppend(){
        doAppend = false;
	}
	
	public static void setMakeNew(){
    	makeNew = false;
	}
	
	public static void setDoUnion(){
    	doUnion = false;
	}
	
	public void setNumSongs(int nS){
    	numSongs = nS;
	}
	
	public void changePlaylist(){
		try {
			TimeUnit.MILLISECONDS.sleep(500);
	    	System.out.print("Which playlist would you like to use? ");
	    	c = S.nextInt() - 1;
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
//  Accessor Methods ========================================================
	
	public static boolean getDoAppend(){
        return doAppend;
    }
	
	public static boolean getMakeNew(){
    	return makeNew;
	}
	
	public static boolean getDoUnion(){
    	return doUnion;
	}
	
	public boolean GetLoop( ){
    	return Looping;
	}
    
	public static int getNumPlaylists(){
    	return numPlaylists;
	}  
   
	public static int getC(){
    	return c;
	}

    public int getNumSongs(){
        return numSongs;
    }
 
	public int [] getPlaylist(){
    	return Playlist;
	}

	
// Other Functions ============================================================	
	/**
	 *   userChoice() is the main user interface for the program, allows users 
	 *   to choose what actions to take by typing in respective numbers
	 */
	public void UserChoice( ){
		try {
	    	int Choice;
	    	TimeUnit.MILLISECONDS.sleep(1000);
	    	System.out.print(
	    			"\n\033[34m=============================================================\n"
	            	+ "\n\033[34mYou currently have " + numSongs + " song(s) in your playlist.\n"
	            	+ "\033[34mYou can add " + (Playlist.length - numSongs) + " more songs.\n\n");
	    	TimeUnit.MILLISECONDS.sleep(800);
	    	System.out.println(
	            	"In addition to this, you have " + (numPlaylists + 1) +" playlist(s). \nYou can have " + (100 - (numPlaylists+1))
	            	+ " more.\n"
	            	+ "You are currently on playlist "+ (c + 1)+ ".\n" );
	    	TimeUnit.MILLISECONDS.sleep(800);
	    	System.out.println(
	            	"\033[35mWhat would you like to do?\n\n");
	    	TimeUnit.MILLISECONDS.sleep(1300);
	    	System.out.println(
	    			"You have the following options:\n\t1. Add a song to your playlist\n"
	            	+ "\t2. Remove a song from your playlist\n\t3. Display your playlist\n"
	            	+ "\t4. Search for a specific song in your playlist\n\t5. Make a new playlist\n "
	            	+ "\t6. Change the playlist you are working on.\n\t7. Create a new playlist"
	            	+ " containing two existing playlists (UNION).\n\t8. Add an existing playlist"
	            	+ " after another (APPEND).\n\t9. Display song at play."
	            	+ "\n\t10. Exit the program.\n\n");
	   
	    	//  in the case the playlist is full, user is notified only 2 choices are available	 
	    	if (numSongs == Playlist.length){
	    		TimeUnit.MILLISECONDS.sleep(1000);
	        	System.out.print("Since your playlist is full, if you want to add a new song,"
	                	+ " you will first need to delete one.\n\n");
	    	}
	  
	    	TimeUnit.MILLISECONDS.sleep(500);
	    	System.out.print("Please enter the integer value for your choice: ");
	    	Choice = S.nextInt();
	    	//  switch case used for user input	 
	    	switch(Choice){
	        	/* Add a song to the current playlist (an 
				array).*/
				case 1: 
					addSong( );
				    break;
				/* Remove a song from the current 
				playlist (an array).*/
				case 2: 
					RemoveSong( );
				    break;
				/* Prints the songs from the current \
				playlist (an array).*/
				case 3: 
					PrintList( );
				    break;
				/* Search for a song in the current playlist (an 
				array) if the song exists in the current playlist.*/
				case 4: 
					Search( );
	            	break;
	            /* Create a new playlist */
	        	case 5: {
	            	numPlaylists++;
	            	makeNew = true;
	        	}
	        		break;
	        	/* Switch current playlist */
	        	case 6: 
	        		changePlaylist();
	            	break;
	            /* Combine two playlists into one */
	        	case 7: {
	            	numPlaylists++;
	            	doUnion = true;
	        	}
	            	break;
	            /* Add a playlist at the end of another existing playlist */
	        	case 8:{
	            	doAppend = true;
	            	}
	            	break;
	            /* Display current song */
	        	case 9: 
	        		displayCurrent();
	            	break;
	        	/* Exit the program */
	        	case 10: 
	        		Looping = false;
	            	break;
	        	default: 
	        		TimeUnit.MILLISECONDS.sleep(1000);
	        		System.out.println("\n\033[31mYou have entered an invalid output.\n"
	                	+ "\033[31mPlease try again.\n\n");
	    	}
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
}
