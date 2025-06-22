package testOperations;
//
import java.util.Date;

import configuration.ConfigXML;

public class TestBusinessLogic {
	TestDataAccess dbManagerTest;
 	
    
	   public TestBusinessLogic()  {
			
			System.out.println("Creating TestBusinessLogic instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
	
		


}
