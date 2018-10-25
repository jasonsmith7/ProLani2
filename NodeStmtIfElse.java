/**
 * The NodeStmtIfElse class creates the object to handle
 * the grammar entity if ... then ... else
 * @author jasonsmith7
 *
 */
public class NodeStmtIfElse extends NodeStmt {
	private NodeBoolExpr bool;
	private NodeStmt ifStmt;
	private NodeStmt elseStmt;

	public NodeStmtIfElse(NodeBoolExpr bool, NodeStmt ifStmt, NodeStmt elseStmt) {
		this.bool=bool;
		this.ifStmt=ifStmt;
		this.elseStmt=elseStmt;
	}

	public double eval(Environment env) throws EvalException {
		if ((bool.eval(env)) == 1.0)
			return ifStmt.eval(env);
		else
			return elseStmt.eval(env);
	}
}

