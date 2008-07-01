package modelo;

import control.*;

public class Virtual extends Jugador {

	private AlgoPesos capital;


	private control.Virtual control;
	
	
	public Virtual(control.Virtual control, Auto auto){
		setControl(control);
		setAuto(auto);
		
	}


	public void jugar(){
		this.getControl().jugar();
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
