/**
 * The NodeBoolExpr class creates the object to handle
 * boolean expressions
 * @author jasonsmith7
 *
 */
public class NodeBoolExpr {

    private NodeExpr expr1;
    private NodeExpr expr2;
    private NodeRelop relop;
    
    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
		this.expr1=expr1;
		this.relop=relop;
		this.expr2=expr2;
    }
    
    public double eval(Environment env) throws EvalException {
    	return relop.eval(expr1.eval(env), expr2.eval(env));
    }
}    
