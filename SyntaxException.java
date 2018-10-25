// The SyntaxException class creates the object
// that handles situations where there is a
// Syntax error.

public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
