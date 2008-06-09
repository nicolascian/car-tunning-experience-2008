/**
 * 
 */
package vista.visorDeImagenes;

/**
 * @author Usuario
 *
 */
public class Posicion {

	private int x=0;
	
	private int y=0;
	
	/**
	 * 
	 */
	public Posicion(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * 
	 */
	public Posicion(Posicion posicion) {
	  try{	
		this.setX(posicion.getX());
		this.setY(posicion.getY());
	  }catch(NullPointerException e){
		  this.setX(0);
		  this.setY(0);
	  }
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (this.getX()+" "+this.getY());
	}
		
}
