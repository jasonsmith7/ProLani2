/**
 * The NodeStmtBeg class creates the object to handle
 * the grammar entity begin ... end
 * @author jasonsmith7
 *
 */
public class NodeStmtBeg extends NodeStmt {
	private NodeBlock block;

	public NodeStmtBeg(NodeBlock block) {
		this.block = block;
	}

	public double eval(Environment env) throws EvalException {
		return block.eval(env);
	}
}
