package modelo;



public class Virtual extends Jugador {

	private AlgoPesos capital;


	private control.Virtual control;
	
	
	public Virtual(control.Virtual control, Auto auto){
		setControl(control);
		setAuto(auto);
		setNombre("Virtual (PC)");
		
	}


	public void jugar(boolean X){
		this.getControl().jugar(X);
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
