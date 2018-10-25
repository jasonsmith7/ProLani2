// The NodeFactNum class creates the object
// to handle taking in a string that needs 
// to be a int or double and changes it.
// Changed from int to double per project
// requirements.

public class NodeFactNum extends NodeFact {

    private String num;

    public NodeFactNum(String num) {
	this.num=num;
    }

    //changed to support double values
    public double eval(Environment env) throws EvalException {
	return Double.parseDouble(num);
    }

}
