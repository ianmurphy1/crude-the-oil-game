/**
 * A Well class(subclass) which extends the name and the symbol to the Asset
 * class
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */
public class Well extends Asset
{

	final static String S_NAME = "Well";
	final static String S_SYMBOL = "W";

	public Well() {
		super(S_NAME, S_SYMBOL);
	}

}
