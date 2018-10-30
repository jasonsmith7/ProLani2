/**
 * The NodeStmtAssn class creates the object to handle
 * the grammar entity assn
 * @author jasonsmith7
 *
 */
public class NodeStmtAssn extends NodeStmt {

//	private String id;
//	private NodeExpr expr;
//	//private NodeAssn assn;
//
//	public NodeStmtAssn(String id, NodeExpr expr) {
//		this.id=id;
//		this.expr=expr;
//	}
//	
//
//	public double eval(Environment env) throws EvalException {
//		return env.put(id, expr.eval(env));
//	}
private NodeAssn assn;
	
	public NodeStmtAssn(NodeAssn assn) {
		this.assn=assn;
	}
	public double eval(Environment env) throws EvalException{
		return assn.eval(env);
	}
}
