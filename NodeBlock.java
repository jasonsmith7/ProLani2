/**
 * The NodeBlock Class creates an object to handle
 * the grammar entity block
 * @author jasonsmith7
 *
 */
public class NodeBlock extends NodeStmt {
	
    private NodeStmt stmt;
    private NodeBlock block;
    
    public NodeBlock(NodeStmt stmt, NodeBlock block) {
	this.stmt = stmt;
	this.block = block;
    }
    
    public NodeBlock(NodeStmt stmt) {
    	this.stmt = stmt;
    }
    
    public double eval(Environment env) throws EvalException {
    	return stmt.eval(env);
    }
    
}	    
	
