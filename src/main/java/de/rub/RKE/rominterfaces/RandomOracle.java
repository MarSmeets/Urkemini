package de.rub.RKE.rominterfaces;

import de.rub.RKE.keminterfaces.KemOutputKey;
import de.rub.RKE.userstate.ChainingKey;
import de.rub.RKE.userstate.Transcript;

public interface RandomOracle <RomOutputType>{
	
  //TODO: Discuss if input needs to be generic, because ROM input is defined in ARKE paper
  public RomOutputType getOutput(ChainingKey chainingKey, KemOutputKey kemOutputKey, Transcript transcript);
}
