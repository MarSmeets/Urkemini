package de.rub.RKE.urke;

import de.rub.RKE.keminterfaces.KemEncryptedData;
import de.rub.RKE.keminterfaces.KemKeyPair;
import de.rub.RKE.keminterfaces.KemOutput;
import de.rub.RKE.keminterfaces.KemOutputKey;
import de.rub.RKE.keminterfaces.KemPrivateKey;
import de.rub.RKE.keminterfaces.KemPublicKey;
import de.rub.RKE.keminterfaces.KeyEncapsulationAlgorithmus;
import de.rub.RKE.macinterfaces.MacKey;
import de.rub.RKE.macinterfaces.MacTag;
import de.rub.RKE.macinterfaces.MessageAuthenticationCode;
import de.rub.RKE.rominterfaces.RandomOracle;
import de.rub.RKE.rominterfaces.RomOutput;
import de.rub.RKE.userstate.ChainingKey;
import de.rub.RKE.userstate.RkeCipherText;
import de.rub.RKE.userstate.RkeSessionKey;
import de.rub.RKE.userstate.RkeUserstates;
import de.rub.RKE.userstate.Transcript;
import de.rub.RKE.userstate.Userstate;

//Mini version of the Urke construction to test key encryption/decryption with a generic KEM
public class Urke{
	private final int CHAINING_KEY_SIZE = 128;
	private final int MAC_KEY_SIZE = 128;
	
	
	private KeyEncapsulationAlgorithmus<KemKeyPair, KemPublicKey, KemPrivateKey, KemOutputKey, KemEncryptedData> kem;
	private MessageAuthenticationCode<MacTag, MacKey> mac;
	private RandomOracle<RomOutput> rom;
	
	public Urke(KeyEncapsulationAlgorithmus<KemKeyPair, KemPublicKey, KemPrivateKey, KemOutputKey, KemEncryptedData> kem,
			    MessageAuthenticationCode<MacTag, MacKey> mac,
			    RandomOracle<RomOutput> rom) {
		this.kem = kem;
		this.mac = mac;
		this.rom = rom;
	}
	
  public RkeUserstates init(){
	  // Declare/Initialize needed variables
	  KemKeyPair kemKeyPair;
	  ChainingKey chainingKey;
	  MacKey macKey;
	  Transcript transcript;
	  
	  kemKeyPair = kem.gen();
      chainingKey = new ChainingKey(CHAINING_KEY_SIZE);
      macKey = mac.generateKey(MAC_KEY_SIZE);
      transcript = new Transcript();
      
      Userstate userstate1 = new Userstate();
      Userstate userstate2 = new Userstate();
      
      userstate1.setChainingKey(chainingKey);
      userstate1.setMacKey(macKey);
      userstate1.setPublicKey(kemKeyPair.getPublicKey());
      userstate1.setTranscript(transcript);
      
      userstate2.setChainingKey(chainingKey);
      userstate2.setMacKey(macKey);
      userstate2.setPrivateKey(kemKeyPair.getPrivateKey());
      userstate2.setTranscript(transcript);
      
      return new RkeUserstates(userstate1, userstate2);
  }
  
  public UrkeSendOutput send(Userstate userstate, String associatedData) {
	 KemOutput kemOutput = kem.enc(userstate.getPublicKey());
	 mac.setInput(associatedData , kemOutput.getData());
	 MacTag tag = mac.computeTag(userstate.getMacKey());
	 RkeCipherText cipherText = new RkeCipherText(kemOutput.getData(), tag);
	 Transcript transcript = userstate.getTranscript();
	 transcript.appendToTranscript(associatedData, cipherText);
	 RomOutput romOutput = rom.getOutput(userstate.getChainingKey(), kemOutput.getKey(), transcript);
	 userstate.setPublicKey((kem.genWithSeed(romOutput.getKemKeySeed())).getPublicKey());
	 userstate.setChainingKey(romOutput.getChainingKey());
	 userstate.setMacKey(romOutput.getMacKey());
	 userstate.setTranscript(transcript);
	 UrkeSendOutput urkeOutput = new UrkeSendOutput(romOutput.getSessionKey(), cipherText);
	 return urkeOutput;
  }
  
  public RkeSessionKey receive(Userstate userstate, String associatedData, RkeCipherText cipherText) {
	 mac.setInput(associatedData, cipherText.getEncryptedData());
	 if(mac.verifyTag(userstate.getMacKey(), cipherText.getMacTag())==false) {
		 return null;
	 }
	 KemOutputKey kemOutputKey = kem.dec(userstate.getPrivateKey(), cipherText.getEncryptedData());
	 if(kemOutputKey==null) {
		 return null;
	 }
	 Transcript transcript = userstate.getTranscript();
	 transcript.appendToTranscript(associatedData, cipherText);
	 RomOutput romOutput = rom.getOutput(userstate.getChainingKey(), kemOutputKey, transcript);
	 userstate.setPublicKey((kem.genWithSeed(romOutput.getKemKeySeed())).getPublicKey());
	 userstate.setChainingKey(romOutput.getChainingKey());
	 userstate.setMacKey(romOutput.getMacKey());
	 userstate.setTranscript(transcript);
	 return romOutput.getSessionKey();
  }
}
