/**
 * The NodeStmtWr class creates the object to handle
 * the grammar entity wr .. to write to system out
 * @author jasonsmith7
 *
 */
public class NodeStmtWr extends NodeStmt {
	private NodeExpr expr;

	public NodeStmtWr (NodeExpr expr) {
		this.expr=expr;
	}

	public double eval (Environment env) throws EvalException {
		double val = expr.eval(env);
		System.out.println(val);
		return val;
	}
}
