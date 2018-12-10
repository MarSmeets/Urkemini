package de.rub.RKE.ecieskem;

import java.util.Arrays;

import de.rub.RKE.keminterfaces.KemEncryptedData;


//Datatype/Class for the encrypted Data, which is computed by the ECIES KEM
public class EciesEncryptedData implements KemEncryptedData{
   private byte[] data;
   
   public EciesEncryptedData() {
   }
   
   public EciesEncryptedData(byte[] input) {
	   data = Arrays.copyOf(input, input.length);
   }
   
   public byte[] getData() {
	   return data;
   }
   
   public void setData(byte[] input) {
	   data = Arrays.copyOf(input, input.length);
   }
   
}
