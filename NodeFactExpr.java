// The NodeFactExpr handles the grammar
// entity "expr"

public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    // modified to support double values
    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
