package de.rub.RKE.userstate;

import java.security.SecureRandom;
import java.util.Arrays;

//TODO: Discuss if ChainingKey/Transcript needs to be generic. If yes, in what amount.
public class ChainingKey {

	//Since in URKE the chaining Key is simply a input to the Random Oracle, we declare it as byte[] for now
	byte[] key;
	
	public ChainingKey(byte[] key) {
		this.key = Arrays.copyOf(key, key.length);
	}
	
	public ChainingKey(int length) {
		SecureRandom rng = new SecureRandom();
		key = new byte[length];
		//TODO: Research/Discuss security of Java's SecureRandom
		rng.nextBytes(key);
	}
	
	public void setKey(byte[] key) {
		this.key = Arrays.copyOf(key, key.length);	
	}
	
	public byte[] getKeyBytes() {
		return key;
	}
	
	public int getLength() {
		return key.length;
	}
}
