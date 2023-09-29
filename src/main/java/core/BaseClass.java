package core;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;



public class BaseClass {
	
	public static WebDriver driver;
	public static Properties properties;
	public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	private String userDirectory = System.getProperty("user.dir");
	private String ppfile = ".\\src\\test\\resources\\API-properties\\config.properties";
	private String pathtoPP = userDirectory + ppfile;
   public static Logger logger;
	
	public BaseClass() {
		try {
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(pathtoPP));
			properties = new Properties();

			try {
				properties.load(reader);
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		logger = logger.getLogger("Logger_File");
		PropertyConfigurator.configure(".\\src\\test\\resources\\API-properties\\Log4j2.properties");
	}
	
	
	
	public static String baseURI() {
	String baseURI = properties.getProperty("baseURI");
	return baseURI;
	
	}
	public static String basePath() {
	String basePath = properties.getProperty("basePath");
	return basePath;
	}
	
	public static String getUsers() {
	String getAllUsers = properties.getProperty("allUsersURI");
	return getAllUsers;
	
	}
	public static String postUser() {
		String postUser = properties.getProperty("allUsersURI");
		return postUser;
		
		}
		
	public static long getPageLoadTimeOut() {
	String pageLoadTimeOut =	properties.getProperty("pageLoadTimeOut");
	return Long.parseLong(pageLoadTimeOut);
		
	}
	public static void initializeURI() {
		driver.get(baseURI());
		driver.get(basePath());
		
	}
	
	public static void tearDown() {
		
		driver.close();
		
	}


		
	}







