/* 
 * This is a helper class to define a node program
 * @author Sarah Nielson
 */
public class NodeProgram extends Node{
	private NodeBlock block;
	public NodeProgram(NodeBlock block) {
		this.block=block;
	}
	public double eval(Environment env) throws EvalException{
		return block.eval(env);
	}

}
