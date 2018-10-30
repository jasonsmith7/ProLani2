/**
 * The NodeStmtWhile class creates the object to handle
 * the grammar entity while ... do
 * @author jasonsmith7
 *
 */
public class NodeStmtWhile extends NodeStmt {
	private NodeBoolExpr bool;
	private NodeStmt stmt;

	public NodeStmtWhile (NodeBoolExpr bool, NodeStmt stmt) {
		this.bool=bool;
		this.stmt=stmt;
	}

	public double eval (Environment env) throws EvalException {
		while((bool.eval(env)) == 1.0) {
			stmt.eval(env);
		}
		return stmt.eval(env);
	}
}


