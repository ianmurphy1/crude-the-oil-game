/**
 * A Gas Station class(subclass) which extends the name and the symbol to the
 * Asset class
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class Station extends Asset
{
	final static String S_NAME = "Station";
	final static String S_SYMBOL = "S";

	public Station() {
		super(S_NAME, S_SYMBOL);
	}

}
