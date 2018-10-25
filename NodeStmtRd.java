import java.util.Scanner;
/**
 * The NodeStmtRd class creates the object to handle
 * the grammar entity rd ... to read from input
 * @author jasonsmith7
 *
 */
public class NodeStmtRd extends NodeStmt {
	private String id;
	static Scanner scnr;

	public NodeStmtRd (String id) {
		this.id=id;
	}

	public double eval (Environment env) throws EvalException {
		scnr = new Scanner(System.in);
		return env.put(id, scnr.nextDouble());
	}
}
