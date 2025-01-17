package TicTacToe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Credentials {
	 String record,validator,errorMsg;
	 Long wins,losses;
	 Boolean taken = false;
	public static String getJsonFromFile(String filename) {
		String jsonText = "";
		try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
				
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonText += line + "\n";
				}
				bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found, will be created");
		}
		catch (Exception e) {
					e.printStackTrace();
				}
		return jsonText;
	}
	
	@SuppressWarnings("unchecked")
	public void createRecordsFile() {
		File f = new File("JSON\\\\credentials.json"); 
		
		  if(f.exists() && f.isFile()) {			  
			String strJson = getJsonFromFile("credentials.json");
			FileWriter emptyFile;
				if(strJson.isBlank()) {
					try {
						emptyFile = new FileWriter("credentials.json", false);
						JSONObject emptyObj = new JSONObject();
						emptyObj.put("placeHolder", "place hoder");
						emptyFile.write(emptyObj.toJSONString());
						emptyFile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						errorMsg = "There was an error creating the file";
					}
			  }
		  }
		  else {
		FileWriter emptyFile;
		try {
			emptyFile = new FileWriter("credentials.json", false);
			JSONObject emptyObj = new JSONObject();
			emptyObj.put("placeHolder", "place hoder");
			emptyFile.write(emptyObj.toJSONString());
			emptyFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			errorMsg = "There was an error creating the file";
		}
		  }
	}
	
//	to check if records file exists and if not creates it
	Credentials(Boolean check){
		createRecordsFile();
	}
	
//	Login function
	Credentials(String username, String pass){
		String strJson = getJsonFromFile("credentials.json");
		try {
            JSONParser parser = new JSONParser();
//            JSON is now an object
            Object object = parser.parse(strJson);
            JSONObject mainJsonObject = (JSONObject) object;

            
            /*************** Pull fields from user ****************/
            if(mainJsonObject.containsKey(username)) {
            	
            	JSONObject user = (JSONObject) mainJsonObject.get(username);
            	String password = (String) user.get("password");
            	if(password.equals(pass)) {
            		wins = (Long) user.get("wins");
            		losses = (Long) user.get("losses");
            		validator="valid";
            	}
            	else {
            		validator="Invalid password";
            	}
            }
            else {
            	validator="Invalid username";
            }

        }
        catch(ClassCastException ex) {
        	validator = "Something went wrong";
        	System.out.println("Wrong data types being assigned");

        	
        }
		catch(Exception ex) {
			ex.printStackTrace();
		}
    
	}
//	To Pull Records for Game
	Credentials(Boolean x,String username){
		String strJson = getJsonFromFile("credentials.json");
		if (x) {
			
			try {
				JSONParser parser = new JSONParser();
//            JSON is now an object
				Object object = parser.parse(strJson);
				JSONObject mainJsonObject = (JSONObject) object;
				/*************** Pull fields from user ****************/
				if(mainJsonObject.containsKey(username)) {
					
	            	JSONObject user = (JSONObject) mainJsonObject.get(username);
            		wins = (Long) user.get("wins");
            		losses = (Long) user.get("losses");
            		validator="valid";
	            		
	            
	            }
	            else {
	            	validator="Invalid username";
	            	record = "No records found";
	            }

	        }
	        catch(Exception ex) {
	            ex.printStackTrace();
	        }
		}
		
	}
@SuppressWarnings("unchecked")
//	To update user record
	Credentials(String username, Long newWins, Long newLosses){
		String strJson = getJsonFromFile("credentials.json");
		
			try {
				JSONParser parser = new JSONParser();
//            JSON is now an object
				Object object = parser.parse(strJson);
				JSONObject mainJsonObject = (JSONObject) object;
				
				/*************** Pull fields from user ****************/
				if(mainJsonObject.containsKey(username)) {
            		validator="valid";
            		FileWriter credFile = new FileWriter("credentials.json", false);            
                    JSONObject obj = new JSONObject();
                    obj = mainJsonObject ;
                    ((HashMap) obj.get(username)).put("wins", newWins);
                    ((HashMap) obj.get(username)).put("losses", newLosses);
            		credFile.write(obj.toJSONString());
                    credFile.close();
	            		
	            
	            }
	            else {
	            	validator="Invalid username";
	            	record = "No records found";
	            }

	        }
	        catch(Exception ex) {
	            ex.printStackTrace();
	        }
		
		
	}
// To Register New User
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Credentials(Boolean x, String username, String password){
		String strJson = getJsonFromFile("credentials.json");
		if(x) {
			
			try {
				JSONParser parser = new JSONParser();
//        JSON is now an object ;
				Object object;
				if (strJson.isBlank()) {
					
				}
				JSONObject mainJsonObject = (JSONObject) parser.parse(strJson);
				
				/*************** Pull fields from user ****************/
				if(!mainJsonObject.containsKey(username)) {
					FileWriter credFile = new FileWriter("credentials.json", false);            
					JSONObject obj = new JSONObject();
					JSONObject nestedObj = new JSONObject();
					JSONObject newObj = new JSONObject();
					((HashMap)nestedObj).put("wins", 0);
					((HashMap)nestedObj).put("losses", 0);
					((HashMap)nestedObj).put("password", password);
					((HashMap)obj).put(username, nestedObj);
					newObj = mainJsonObject ;
					((HashMap) newObj).putAll(obj);
					((HashMap) newObj).remove("placeHolder");
					credFile.write(newObj.toJSONString());
					credFile.close();
					taken = false;
					
					
				}
				else {
            		taken = true;
					validator="username taken";
					record = "No records found";
				}
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void next() {
		TicTacToe tictactoe = new TicTacToe();		
	}
    
}
