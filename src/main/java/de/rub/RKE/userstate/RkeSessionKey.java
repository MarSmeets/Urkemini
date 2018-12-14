package de.rub.RKE.userstate;

import java.util.Arrays;

//TODO: Temporary class. Depends on cipher used for symmetric Encryption.
public class RkeSessionKey {

	byte[] sessionKey;
	
	public RkeSessionKey(byte[] keyBytes) {
		sessionKey = Arrays.copyOf(keyBytes, keyBytes.length);
	}
	
	public byte[] getKeyBytes() {
	  return sessionKey;	
	}
}
