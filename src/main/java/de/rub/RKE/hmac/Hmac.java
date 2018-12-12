package de.rub.RKE.hmac;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import de.rub.RKE.keminterfaces.KemEncryptedData;
import de.rub.RKE.macinterfaces.MacInput;
import de.rub.RKE.macinterfaces.MacKey;
import de.rub.RKE.macinterfaces.MacTag;
import de.rub.RKE.macinterfaces.MessageAuthenticationCode;

public class Hmac implements MessageAuthenticationCode<MacTag, MacKey>{

	  //Uses HMac provided by the bouncy castle package with SHA2-256
	  private HMac mac;
	  private SHA256Digest hash;
	  
	  private byte[] input;
	  
	  public Hmac() {
		  hash = new SHA256Digest();
		  mac = new HMac(hash);
	  }
	  
	  public void setInput(String associatedData, KemEncryptedData encryptedData) {
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
	  
	  public MacKey generateKey(int keyLength) {
		  byte[] keyBytes = new byte[keyLength];
		  SecureRandom rng = new SecureRandom();
		  rng.nextBytes(keyBytes);
		  return new HmacKey(keyBytes);
	  }
	  
	  //Computes a Tag given the key and a input byte array
	 public MacTag computeTag(MacKey key) {
		 //TODO: Need to make sure input is set.
		 if(input==null) {
			 return null;
		 }
		 byte[] output = new byte[mac.getMacSize()];

		 //Uses the methods provided by the mac class to produce the tag
		 mac.init(new KeyParameter(key.getKeyBytes()));
		 mac.update(input, 0, input.length);
		 mac.doFinal(output, 0);
		 
		 return new HmacTag(output);
	 }
	 
	 //Verifies a tag
	 public boolean verifyTag(MacKey key, MacTag tag) {
		 //TODO: Need to make sure input is set.
		 if(input==null) {
			 return false;
		 }
		 MacTag temporaryTag;
		 //Compute the tag for the input and compare the computed tag with the provided tag
		 temporaryTag = computeTag(key);
		 return temporaryTag.isEqual(tag);
	 }
}
