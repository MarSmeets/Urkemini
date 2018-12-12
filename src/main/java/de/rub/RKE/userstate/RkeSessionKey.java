package de.rub.RKE.userstate;

import java.util.Arrays;

//TODO: Temporary class. Needs discussion.
public class RkeSessionKey {

	byte[] sessionKey;
	
	public RkeSessionKey(byte[] keyBytes) {
		sessionKey = Arrays.copyOf(keyBytes, keyBytes.length);
	}
	
	public byte[] getKeyBytes() {
	  return sessionKey;	
	}
}
