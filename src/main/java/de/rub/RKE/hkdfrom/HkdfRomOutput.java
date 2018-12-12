package de.rub.RKE.hkdfrom;

import java.util.Arrays;

import de.rub.RKE.macinterfaces.MacKey;
import de.rub.RKE.rominterfaces.RomOutput;
import de.rub.RKE.userstate.ChainingKey;
import de.rub.RKE.userstate.RkeSessionKey;

public class HkdfRomOutput implements RomOutput{

	ChainingKey chainingKey;
	RkeSessionKey sessionKey;
	MacKey macKey;
	byte[] kemKeySeed;
	
	public HkdfRomOutput(RkeSessionKey sessionKey, ChainingKey chainingKey, MacKey macKey, byte[] kemKeySeed) {
	  this.sessionKey = sessionKey;
	  this.chainingKey = chainingKey;
	  this.macKey = macKey;
	  this.kemKeySeed = Arrays.copyOf(kemKeySeed, kemKeySeed.length);
	}
	
	
	@Override
	public ChainingKey getChainingKey() {
		return chainingKey;
	}

	@Override
	public RkeSessionKey getSessionKey() {
		return sessionKey;
	}

	@Override
	public MacKey getMacKey() {
		return macKey;
	}

	@Override
	public byte[] getKemKeySeed() {
		return kemKeySeed;
	}

}
