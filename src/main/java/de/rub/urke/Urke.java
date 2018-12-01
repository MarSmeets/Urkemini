package de.rub.urke;

import de.rub.keminterfaces.KemEncryptedData;
import de.rub.keminterfaces.KemKeyPair;
import de.rub.keminterfaces.KemOutput;
import de.rub.keminterfaces.KemOutputKey;
import de.rub.keminterfaces.KemPrivateKey;
import de.rub.keminterfaces.KemPublicKey;
import de.rub.keminterfaces.KeyEncapsulationAlgorithmus;

//Mini version of the Urke construction to test key encryption/decryption with a generic KEM
public class Urke{
	private KeyEncapsulationAlgorithmus<KemKeyPair, KemPublicKey, KemPrivateKey, KemOutputKey, KemEncryptedData> kem;
	
	public Urke(KeyEncapsulationAlgorithmus<KemKeyPair, KemPublicKey, KemPrivateKey, KemOutputKey, KemEncryptedData> inpKem) {
		kem=inpKem;
	}
	
  public void init(KemOutputKey key1, KemOutputKey key2){
	  // Declare/Initialize needed variables
	  KemKeyPair kemKpair;
	  KemPublicKey kemPublicK;
	  KemPrivateKey kemPrivK;
      kemKpair = kem.gen();
      
      kemPublicK = kemKpair.getPublicK();
      kemPrivK = kemKpair.getPrivateK();
      KemOutput kemOutput;
      int len = 128;
      
      kemOutput = kem.enc(len, kemPublicK);
      key1 = kemOutput.getKey();
      key2 = kem.dec(kemOutput.getData() , len, kemPrivK);
      
  }
}
