package de.rub.RKE.userstate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

//TODO: Discuss if ChainingKey/Transcript needs to be generic. If yes, in what amount.
public class Transcript {
  byte[] transcript;
  
  public Transcript() {
	  String initialization = "";
	  this.transcript = initialization.getBytes();  
  }
  
  public Transcript(byte[] transcript) {
	  this.transcript = Arrays.copyOf(transcript, transcript.length);  
  }
  
  public byte[] getTranscript() {
	  return transcript;
  }
  
  public void setTranscript(byte[] transcript) {
	  this.transcript = Arrays.copyOf(transcript, transcript.length);  
  }
  
  public void appendToTranscript(byte[] transcript) {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
	//TODO: Exception handling
	try {
		outputStream.write(this.transcript);
		outputStream.write(transcript);
	    this.transcript = outputStream.toByteArray( );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void appendToTranscript(String associatedData, RkeCipherText cipherText) {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
	//TODO: Exception handling
	try {
		outputStream.write(associatedData.getBytes());
		outputStream.write(cipherText.getCipherTextBytes());
	    this.transcript = outputStream.toByteArray( );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
  } 
}
