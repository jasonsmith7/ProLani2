/**
 * The NodeFactUMinus creates an object to handle
 * the unary minus operation. 
 * @author jasonsmith7
 *
 */
public class NodeFactUMinus extends NodeFact {

     private NodeFact val;
     public NodeFactUMinus(NodeFact val) {
	this.val = val;
     }
     
     public double eval(Environment env) throws EvalException {
	double unaryMinus = val.eval(env);
	unaryMinus *= -1;
	return unaryMinus;
     }
}

