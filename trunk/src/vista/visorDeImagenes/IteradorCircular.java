package vista.visorDeImagenes;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Usuario
 *
 */
public class IteradorCircular implements Iterator {

	private List lista;
	private int cursor=0;
	
	/**
	 * 
	 */
	public IteradorCircular(List lista) {
		this.lista=lista;
		cursor=0;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if(!lista.isEmpty())
		  return true;
		else
		  return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		if(!hasNext())
			throw new NoSuchElementException();
		Object retorno=lista.get(cursor);
		//incremento de cursor
		if(cursor<(lista.size()-1)){
			cursor++;
		}
		else{
			cursor=0;
		}
		return(retorno);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		lista.remove(cursor);		
	}

}
