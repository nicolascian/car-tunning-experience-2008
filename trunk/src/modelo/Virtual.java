package modelo;

import control.*;

public class Virtual {

	private AlgoPesos capital;
	
	private Auto auto;

	private control.Virtual control;
	
	
	public Virtual(control.Virtual control, Auto auto){
		setControl(control);
		setAuto(auto);
		
	}



	public Auto getAuto() {
		return auto;
	}



	public void setAuto(Auto auto) {
		this.auto = auto;
	}



	public AlgoPesos getCapital() {
		return capital;
	}



	public void setCapital(AlgoPesos capital) {
		this.capital = capital;
	}



	public control.Virtual getControl() {
		return control;
	}



	public void setControl(control.Virtual control) {
		this.control = control;
	}
	
}
