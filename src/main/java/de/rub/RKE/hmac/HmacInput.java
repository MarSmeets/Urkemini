package de.rub.RKE.hmac;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import de.rub.RKE.keminterfaces.KemEncryptedData;
import de.rub.RKE.macinterfaces.MacInput;


//Might be removed
public class HmacInput extends MacInput{
	   byte[] input;
	   
	   public HmacInput(String associatedData, KemEncryptedData encryptedData) {
		   super(associatedData,encryptedData);
		   ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
			try {
				outputStream.write(associatedData.getBytes());
				outputStream.write(encryptedData.getData());
		        input = outputStream.toByteArray( );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }

	   public HmacInput(byte[] input) {
		   super();
		   this.input = Arrays.copyOf(input, input.length);
	   }
	   
	   public void setInput(byte[] input){
		 this.input = Arrays.copyOf(input, input.length);
	   }
	   
	   public byte[] getInput() {
		   return input;
	   }
	   
	   public int getLength() {
		   return input.length;
	   }
}
