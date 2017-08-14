package com.apple;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.net.HttpURLConnection;

import org.testng.annotations.Test;
import org.testng.Assert;

public class Search_iTunesAPI {

	

	//TestNG test cases
	@Test
	public void test1_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertTrue(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/search?term=bob+marley"));

	}

	@Test
	public void test2_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertTrue(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/search?term=bob+marley&media=music"));

	}

	@Test
	public void test3_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertTrue(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/search?term=bob+marley&media=music&country=IN"));

	}
	
	@Test
	public void test4_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertTrue(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/search?term=bob+marley&media=music&country=IN&limit=10"));

	}
	
	@Test
	public void test5_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertTrue(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/search?term=bob+marley&media=ebook&country=IN&limit=10"));

	}
	
	@Test
	public void test6_Search_iTunesAPI() {
		// https://itunes.apple.com/search?[parameterkeyvalue]
		Assert.assertFalse(fetchSearchResultsFrom_iTunesAPI(
				"https://itunes.apple.com/searchterm=bob+marley&media=ebook&country=IN&limit=10"));

	}
	
	

	//Method to fetch Search Results from Search iTunes API
	public boolean fetchSearchResultsFrom_iTunesAPI(String searchURL) {
		
		//Exception handling
		try {
			
			// url object creation
			URL url = new URL(searchURL);
			
			//HTTP connection creation
			HttpURLConnection conn_obj = (HttpURLConnection) url.openConnection();
			
			//Setting Request method 
			conn_obj.setRequestMethod("GET");
			
			//Setting Request property 
			conn_obj.setRequestProperty("Accept", "application/json");
			
			//Checking if HTTP response code is other than 200(OK)
			if (conn_obj.getResponseCode() != 200) {
				System.out.println("FAILED!!!! HTTP Response Status Error code : " + conn_obj.getResponseCode());
				return false;
			}

			
			
			Scanner sc = new Scanner(conn_obj.getInputStream());
			String output;
			
			System.out.println("----------Writing content received from iTunes Search API---------- \n");

			while (sc.hasNext()) {
				output = sc.nextLine();

				
				System.out.println("\n");
				System.out.println(output);
			}
				
			

				conn_obj.disconnect();
				return true;

			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return false;
	}

}
