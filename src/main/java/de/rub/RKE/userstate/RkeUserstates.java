package de.rub.RKE.userstate;

//Temporary class
public class RkeUserstates {
  Userstate Userstate1;
  Userstate Userstate2;
  
  public RkeUserstates(Userstate Userstate1, Userstate Userstate2) {
	  this.Userstate1 = Userstate1;
	  this.Userstate2 = Userstate2;
  }
  
  public Userstate getUserstate1() {
	  return Userstate1;
  }
  
  public Userstate getUserstate2() {
	  return Userstate2;
  }
}
