package de.rub.RKE.rominterfaces;


import de.rub.RKE.macinterfaces.MacKey;
import de.rub.RKE.userstate.ChainingKey;
import de.rub.RKE.userstate.RkeSessionKey;

public interface RomOutput {
   public ChainingKey getChainingKey();
   public RkeSessionKey getSessionKey();
   public MacKey getMacKey();
   public byte[] getKemKeySeed();
}
