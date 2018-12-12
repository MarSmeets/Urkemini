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
import de.rub.RKE.hkdfrom.HkdfRom;
import de.rub.RKE.hmac.Hmac;
import de.rub.RKE.urke.Urke;
import de.rub.RKE.urke.UrkeSendOutput;
import de.rub.RKE.userstate.RkeSessionKey;
import de.rub.RKE.userstate.RkeUserstates;
import de.rub.RKE.userstate.Userstate;


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
		Hmac mac = new Hmac();
		HkdfRom rom = new HkdfRom();
	    urke = new Urke(kem, mac, rom);
	    RkeUserstates userstates = urke.init();
	    Userstate user1 = userstates.getUserstate1();
	    Userstate user2 = userstates.getUserstate2();
	    String associatedData = "port22";
        UrkeSendOutput urkeOutput;
        RkeSessionKey sessionKey;
        for(int i=0; i>10 ; i++) {
          urkeOutput = urke.send(user1, associatedData);
          sessionKey = urke.receive(user2, associatedData, urkeOutput.getCipherText());
  		  assertArrayEquals(urkeOutput.getSessionKey().getKeyBytes(), sessionKey.getKeyBytes());
  		  assertArrayEquals(user1.getChainingKey().getKeyBytes(), user2.getChainingKey().getKeyBytes());
  		  assertArrayEquals(user1.getMacKey().getKeyBytes(), user2.getMacKey().getKeyBytes());
  		  assertArrayEquals(user1.getTranscript().getTranscript(), user2.getTranscript().getTranscript());
  		}
		}
}


