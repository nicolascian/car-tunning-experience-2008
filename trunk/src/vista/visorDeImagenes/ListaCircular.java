/**
 * 
 */
package vista.visorDeImagenes;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import vista.visorDeImagenes.IteradorCircular;
/**
 * @author Usuario
 *
 */
public class ListaCircular extends LinkedList<BufferedImage> {

	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#iterator()
	 */
	@Override
	public Iterator iterator() {
		return (Iterator) (new IteradorCircular(this));
	}


}
