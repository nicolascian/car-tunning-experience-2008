/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * El ABS (Antilock Brake System). 
 * Consiste en una bomba que se incorpora a los circuitos del líquido de freno 
 * y en unos detectores que controlan las revoluciones de las ruedas. Si en una 
 * frenada brusca una o varias ruedas reducen repentinamente sus revoluciones, 
 * el ABS lo detecta e interpreta que las ruedas están a punto de quedar bloqueadas 
 * sin que el vehículo se haya detenido. Esto quiere decir que el vehículo comenzará 
 * a patinar, y por lo tanto, a deslizarse sobre el suelo sin control. Para que esto 
 * no ocurra, los sensores envían una señal a la Central del sistema ABS, que reduce 
 * la presión realizada sobre los frenos, sin que intervenga en ello el conductor. 
 * Cuando la situación se ha normalizado y las ruedas giran de nuevo correctamente, 
 * el sistema permite que la presión sobre los frenos vuelva a actuar con toda la 
 * intensidad. El ABS controla nuevamente el giro de las ruedas y actúa otra vez si 
 * éstas están a punto de bloquearse por la fuerza del freno. En el caso de que este 
 * sistema intervenga, el procedimiento se repite de forma muy rápida, unas 50 a 100 
 * veces por minuto, lo que se traduce en que el conductor percibe una vibración en el 
 * pedal del freno.
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Antilock_Brake_System">Freno ABS - Wikipedia</a>
 */
public class FrenoABS extends Freno{

}
