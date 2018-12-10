package de.rub.genericurkemini;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import de.rub.RKE.ecieskem.EciesKem;
import de.rub.RKE.ecieskem.EciesOutputKey;
import de.rub.RKE.urke.Urke;


class TestUrke {
	

	Urke urke;

	
	@BeforeAll
	static void beforeAll() {
	   System.out.println( "Testing mini generic URKE to test Generic/Interface functions" );
	   System.out.println("URKE uses Ecies Kem to Generate a session key");
	}
	
	@AfterAll
	   static void afterAll() {
	      System.out.println( "... done!" );
	   }

	@BeforeEach
	   void beforeEach(RepetitionInfo repetitionInfo) {
		 int currentRepetition = repetitionInfo.getCurrentRepetition();
		  System.out.println("********************************************");
		  System.out.println("Test run: " + currentRepetition);
	   }
	
	@AfterEach
	   void afterEach(RepetitionInfo repetitionInfo) {
			 int currentRepetition = repetitionInfo.getCurrentRepetition();
			  System.out.println("Test run: " + currentRepetition + " completed!");
		   }
	
	@RepeatedTest(10)
	@DisplayName("URKE uses Ecies Kem to Generate a EciesKemKey")
	void repeatedTest(){
		EciesKem  kem= new EciesKem();	
	    urke = new Urke(kem);
        byte[] temporary = new byte[1];
        EciesOutputKey key1 = new EciesOutputKey(temporary);
        EciesOutputKey key2 = new EciesOutputKey(temporary);
        urke.init(key1, key2);
        assertArrayEquals(key1.getKeyBytes(), key2.getKeyBytes());
		}
}


