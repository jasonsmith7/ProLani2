/**
 * The NodeStmtIf class creates the object to handle
 * the grammar entity if ... then ...
 * @author jasonsmith7
 *
 */
public class NodeStmtIf extends NodeStmt {

	private NodeBoolExpr bool;
	private NodeStmt ifStmt;

	public NodeStmtIf(NodeBoolExpr bool, NodeStmt ifStmt) {
		this.bool=bool;
		this.ifStmt=ifStmt;
	}

	public double eval(Environment env) throws EvalException {
		if((bool.eval(env)) == 1.0) {
			return ifStmt.eval(env);
		}
		return bool.eval(env);
	}
	
}
