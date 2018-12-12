package de.rub.RKE.hmac;

import org.bouncycastle.crypto.params.KeyParameter;


import de.rub.RKE.macinterfaces.MacKey;

public class HmacKey extends KeyParameter implements MacKey{

		public HmacKey(byte[] key) {
			super(key);
		}
		
		public HmacKey(KeyParameter key) {
			super(key.getKey());
		}
		
		public byte[] getKeyBytes() {
			return super.getKey();
		}
}

