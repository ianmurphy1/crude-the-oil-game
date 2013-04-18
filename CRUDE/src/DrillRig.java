/**
 * A Drilling Rig class(subclass) which extends the name and the symbol to the
 * Asset class
 * 
 * @authors Denis Murphy, Ian Murphy & Bill O'Keeffe
 * 
 */

public class DrillRig extends Asset
{
	private static final String D_NAME = "Drilling Rig";
	private static final String D_SYMBOL = "D";

	public DrillRig() {
		super(D_NAME, D_SYMBOL);
	}
}
