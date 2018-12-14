package de.rub.RKE.hkdfrom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;

import de.rub.RKE.hmac.HmacKey;
import de.rub.RKE.keminterfaces.KemOutputKey;
import de.rub.RKE.rominterfaces.RandomOracle;
import de.rub.RKE.rominterfaces.RomOutput;
import de.rub.RKE.userstate.ChainingKey;
import de.rub.RKE.userstate.RkeSessionKey;
import de.rub.RKE.userstate.Transcript;

public class HkdfRom implements RandomOracle<RomOutput>{

	//Uses the HKDF key derivation function with SHA2-512
	private HKDFBytesGenerator kdf;
	private SHA512Digest hash;
	

	//TODO: Need global declaration of key sizes etc.
	private final int SESSION_KEY_SIZE = 128;
	private final int MAC_KEY_SIZE = 128;
	private final int KEM_KEY_GENERATION_SEED_SIZE = 128;
		
	public HkdfRom() {
		hash = new SHA512Digest();
		kdf = new HKDFBytesGenerator(hash);
	}
	
	public RomOutput getOutput(ChainingKey chainingKey, KemOutputKey kemOutputKey, Transcript transcript) {
		byte[] input = null;
		byte[] sessionKeyBytes = new byte[SESSION_KEY_SIZE];
		byte[] chainingKeyBytes = new byte[chainingKey.getLength()];
		byte[] macKeyBytes = new byte[MAC_KEY_SIZE];
		byte[] secretKeySeed = new byte[KEM_KEY_GENERATION_SEED_SIZE];
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		//TODO: Exception handling
		try {
			outputStream.write(chainingKey.getKeyBytes());
			outputStream.write(kemOutputKey.getKeyBytes());
			outputStream.write(transcript.getTranscript());
	        input = outputStream.toByteArray( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HKDFParameters param = new HKDFParameters(input, null, null);
		kdf.init(param);
		kdf.generateBytes(sessionKeyBytes, 0, sessionKeyBytes.length);
		kdf.generateBytes(chainingKeyBytes, 0, chainingKeyBytes.length);
		kdf.generateBytes(macKeyBytes, 0, macKeyBytes.length);
		kdf.generateBytes(secretKeySeed, 0, secretKeySeed.length);
		RkeSessionKey sessionKey = new RkeSessionKey(sessionKeyBytes);
		ChainingKey newChainingKey = new ChainingKey(chainingKeyBytes);
		HmacKey macKey= new HmacKey(macKeyBytes);
		return new HkdfRomOutput(sessionKey, newChainingKey, macKey, secretKeySeed);
	}

}
