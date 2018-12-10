package de.rub.ecieskem;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

import de.rub.keminterfaces.KemKeyPair;


//Datatype/Class for the public-private Keypair which is used and generated by the ECIES KEM
public class EciesKeyPair extends AsymmetricCipherKeyPair implements KemKeyPair{


	
	
	public EciesKeyPair(AsymmetricKeyParameter arg0, AsymmetricKeyParameter arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	public EciesKeyPair(AsymmetricCipherKeyPair keypair) {
		super(keypair.getPublic(), keypair.getPrivate());
	}

	public EciesPublicKey getPublicK() {
		return new EciesPublicKey((ECPublicKeyParameters)super.getPublic());
	}
    
	public EciesPrivateKey getPrivateK() {
		return new EciesPrivateKey((ECPrivateKeyParameters)super.getPrivate());
	}

}
