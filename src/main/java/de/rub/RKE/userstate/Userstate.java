package de.rub.RKE.userstate;

import de.rub.RKE.keminterfaces.KemPrivateKey;
import de.rub.RKE.keminterfaces.KemPublicKey;
import de.rub.RKE.macinterfaces.MacKey;

public class Userstate {

	KemPrivateKey privateKey;
	KemPublicKey publicKey;
	ChainingKey chainingKey;
	MacKey macKey;
	Transcript transcript;
	
	public Userstate() {
	}
	
	public void setPrivateKey(KemPrivateKey privateKey) {
		this.privateKey= privateKey;
	}
	public void setPublicKey(KemPublicKey publicKey) {
		this.publicKey= publicKey;
	}
	public void setChainingKey(ChainingKey chainingKey) {
		this.chainingKey = chainingKey;
	}
	public void setMacKey(MacKey macKey) {
		this.macKey = macKey;
	}
	public void setTranscript(Transcript transcript) {
		this.transcript= transcript;
	}

	public KemPrivateKey getPrivateKey() {
		return privateKey;
	}
	public KemPublicKey getPublicKey() {
		return publicKey;
	}
	public ChainingKey getChainingKey() {
		return chainingKey;
	}
	public MacKey getMacKey() {
		return macKey;
	}
	public Transcript getTranscript() {
		return transcript;
	}
}
