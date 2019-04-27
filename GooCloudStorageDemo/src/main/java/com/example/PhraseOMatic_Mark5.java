package com.example;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//[START gcs_imports]
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
 
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
//[FINISH gcs_imports]



public class PhraseOMatic_Mark5 {
	
   public static final boolean SERVE_USING_BLOBSTORE_API = false;

   /**
    * This is where backoff parameters are configured. Here it is aggressively retrying with
    * backoff, up to 10 times but taking no more that 15 seconds total to do so.
    */
   private final static GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
       .initialRetryDelayMillis(10)
       .retryMaxAttempts(10)
       .totalRetryPeriodMillis(15000)
       .build());

   /**Used below to determine the size of chunks to read in. Should be > 1kb and < 10MB */
   private static final int BUFFER_SIZE = 2 * 1024 * 1024;
   
   @SuppressWarnings("resource")
public static String getPhrase() throws FileNotFoundException {

     // make some sets of words to choose from
	    String[] prephraseListZed = {"I neeeeedzzz", "Gimme Gimme", "Pleeeezze gimme", "Can hazzzz"};
        String[] wordListOne = {"skin-deep", "occasionally available", "globally-scraped", "fructitious", "nasally administered"};
        String[] wordListTwo = {"heevy hoavy","sticky", "creepy", "bowlegged", "flustered",  "fractal", "smyrckled", "freckled"};
        String[] wordListThree = {"tripping-point", "mosh-pit", "frisson", "surfactant", "borogrove", "god-warrior", "heedra", "man-o-war", "knuckleball", "dodecahedron"};

        List<String> prephraseList  = new ArrayList<String>();
        List<String> wordList1  = new ArrayList<String>();
        List<String> wordList2  = new ArrayList<String>();
        List<String> wordList3  = new ArrayList<String>();
        
        String bucketName = "gae-gcs-servlet.appspot.com";
        String fn_0 = "prephraselist.txt";
        String fn_1 = "wordlist1.txt";        
        String fn_2 = "wordlist2.txt";     
        String fn_3 = "wordlist3.txt";     

        try {
            GcsFilename fileName_0 = new GcsFilename(bucketName, fn_0);
            GcsInputChannel readChannel_0 = gcsService.openPrefetchingReadChannel(fileName_0, 0, BUFFER_SIZE); 
            InputStream instrm_0 = Channels.newInputStream(readChannel_0);        	
	        Scanner scan_0 = new Scanner(instrm_0).useDelimiter(",");

	        while(scan_0.hasNext() )
	        {
	        	prephraseList.add(scan_0.next());
	        }
	        scan_0.close();
        }
        catch(NullPointerException ex) {
            System.out.println("Caught nullptr excptn getting instream 0.");
        }        

        try {
            GcsFilename fileName_1 = new GcsFilename(bucketName, fn_1);
            GcsInputChannel readChannel_1 = gcsService.openPrefetchingReadChannel(fileName_1, 0, BUFFER_SIZE); 
            InputStream instrm_1 = Channels.newInputStream(readChannel_1);        	
	        Scanner scan_1 = new Scanner(instrm_1).useDelimiter(",");

	        while(scan_1.hasNext() )
	        {
	        	wordList1.add(scan_1.next());
	        }
	        scan_1.close();
        }
        catch(NullPointerException ex) {
            System.out.println("Caught nullptr excptn getting instream 1.");
        }

        try {
            GcsFilename fileName_2 = new GcsFilename(bucketName, fn_2);
            GcsInputChannel readChannel_2 = gcsService.openPrefetchingReadChannel(fileName_2, 0, BUFFER_SIZE); 
            InputStream instrm_2 = Channels.newInputStream(readChannel_2);        	
	        Scanner scan_2 = new Scanner(instrm_2).useDelimiter(",");

	        while(scan_2.hasNext() )
	        {
	        	wordList2.add(scan_2.next());
	        }
	        scan_2.close();
	        }
        catch(NullPointerException ex) {
            System.out.println("Caught nullptr excptn getting instream 2.");
        }

        try {
            GcsFilename fileName_3 = new GcsFilename(bucketName, fn_3);
            GcsInputChannel readChannel_3 = gcsService.openPrefetchingReadChannel(fileName_3, 0, BUFFER_SIZE); 
            InputStream instrm_3 = Channels.newInputStream(readChannel_3);        	
	        Scanner scan_3 = new Scanner(instrm_3).useDelimiter(",");

	        while(scan_3.hasNext() )
	        {
	        	wordList3.add(scan_3.next());
	        }
	        scan_3.close();
	        }
        catch(NullPointerException ex) {
            System.out.println("Caught nullptr excptn getting instream 3.");
        }

		// find out how many words are in each list
		int zedLength = prephraseList.size();
		prephraseList = zedLength < 1 ?  Arrays.asList(prephraseListZed): prephraseList;
		zedLength = prephraseList.size();
		
		int oneLength = wordList1.size();
		wordList1 = oneLength < 1 ?  Arrays.asList(wordListOne): wordList1;
		oneLength = wordList1.size();

		int twoLength = wordList2.size();
		wordList2 = twoLength < 1 ?  Arrays.asList(wordListTwo): wordList2;
		twoLength = wordList2.size();

		int threeLength = wordList3.size();
		wordList3 = threeLength < 1 ?  Arrays.asList(wordListThree): wordList3;
		threeLength = wordList3.size();

		System.out.println ("wordList1 : " + wordList1 );
		System.out.println ("oneLength : " + oneLength);


		// generate three random numbers, to pull random words from each list
		int rand0 = (int) (Math.random() * zedLength);
		int rand1 = (int) (Math.random() * oneLength);
		int rand2 = (int) (Math.random() * twoLength);
		int rand3 = (int) (Math.random() * threeLength);

		System.out.println ("rand1 : " + rand1);
 		System.out.println ("wordList1. get(rand1) : " + wordList1.  get(rand1));


		// now make a phrase, bubba
 		
 		String prephrase = prephraseList.get(rand0).trim().replaceAll("^\"|\"$", "");
		String phrase = wordList1.get(rand1).trim().replaceAll("^\"|\"$", "") + " " + wordList2.get(rand2).trim().replaceAll("^\"|\"$", "") + " " + wordList3.get(rand3).trim().replaceAll("^\"|\"$", "") + "." ;
	//	String string = phrase.replaceAll("^\"|\"$", "");  to strip quotes, thanx BalusC on Stack-Ovrflow
		return (prephrase + " a " + phrase);

	}
  }






