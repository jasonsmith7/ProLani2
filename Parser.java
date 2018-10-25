// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {

    private Scanner scanner;

    private void match(String s) throws SyntaxException {
    	scanner.match(new Token(s));
    }

    private Token curr() throws SyntaxException {
    	return scanner.curr();
    }

    private int pos() {
    	return scanner.pos();
    }

    private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
		    match("*");
		    return new NodeMulop(pos(),"*");
		}
		if (curr().equals(new Token("/"))) {
		    match("/");
		    return new NodeMulop(pos(),"/");
		}
		return null;
    }

    private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
		    match("+");
		    return new NodeAddop(pos(),"+");
		}
		if (curr().equals(new Token("-"))) {
		    match("-");
		    return new NodeAddop(pos(),"-");
		}
		return null;
    }

    /**
     * Method to parse relational operators
     * @return NodeRelop
     * @throws SyntaxException
     */
    private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(), "<");
		}
		if (curr().equals(new Token("<="))) {
		 	match("<=");
			return new NodeRelop(pos(), "<=");
		}
		if (curr().equals(new Token(">"))) {
		    match(">");
			return new NodeRelop(pos(), ">");
		}   
		if (curr().equals(new Token(">="))) {
		    match(">=");
		    return new NodeRelop(pos(), ">=");
		}
		if (curr().equals(new Token("=="))) {
		    match("==");
			return new NodeRelop(pos(), "==");
		}   
		if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(), "<>");
		}
		return null;
	}

	private NodeFact parseFact() throws SyntaxException {
		// added to handle unary minus
		if (curr().equals(new Token("-"))) {
		    match("-");
		    NodeFact unaryMinus = parseFact();
		    return new NodeFactUMinus(unaryMinus);
		}
		
		if (curr().equals(new Token("("))) {
		    match("(");
		    NodeExpr expr=parseExpr();
		    match(")");
		    return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
		    Token id=curr();
		    match("id");
		    return new NodeFactId(pos(),id.lex());
		}
		Token num=curr();
		match("num");
		return new NodeFactNum(num.lex());
    }

    private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact=parseFact();
		NodeMulop mulop=parseMulop();
		if (mulop==null)
		    return new NodeTerm(fact,null,null);
		NodeTerm term=parseTerm();
		term.append(new NodeTerm(fact,mulop,null));
		return term;
    }

    private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term=parseTerm();
		NodeAddop addop=parseAddop();
		if (addop==null)
		    return new NodeExpr(term,null,null);
		NodeExpr expr=parseExpr();
		expr.append(new NodeExpr(term,addop,null));
		return expr;
    }

    private NodeAssn parseAssn() throws SyntaxException {
		Token id=curr();
		match("id");
		match("=");
		NodeExpr expr=parseExpr();
		NodeAssn assn=new NodeAssn(id.lex(),expr);
		return assn;
    }

    private NodeStmt parseStmt() throws SyntaxException {
	
		if(curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			match("=");
			NodeExpr expr = parseExpr();
			return new NodeStmtAssn(id.lex(), expr);
		}
    	
    	//handle rd ..
		if(curr().equals(new Token("rd"))) {
			match("rd");
			Token id = curr();
			match("id");
			return new NodeStmtRd(id.lex());
		}
		//handle wr ..
		if(curr().equals(new Token("wr"))) {
			match("wr");
			return new NodeStmtWr(parseExpr());
		}
		//handle if..then and if..then..else
		if(curr().equals(new Token("if"))) {
			match("if");
			NodeBoolExpr bool = parseBoolExpr();
			match("then");
			NodeStmt ifStmt= parseStmt();

			if(curr().lex().equals("else")) {
				match("else");
				NodeStmt elseStmt = parseStmt();
				return new NodeStmtIfElse(bool, ifStmt, elseStmt);
			} 
			return new NodeStmtIf(bool, ifStmt);
		
		}
		//handle while..do
		if(curr().equals(new Token("while"))) {
			match("while");
			NodeBoolExpr bool = parseBoolExpr();
			match("do");
			NodeStmt stmt = parseStmt();
			return new NodeStmtWhile(bool, stmt);
		}
		//handle begin..end
		if(curr().equals(new Token("begin"))) {
			match("begin");
			NodeBlock block = parseBlock();
			match("end");
			return new NodeStmtBeg(block);
		}
		
//		NodeAssn assn=parseAssn();
//		match(";");
//		NodeStmtAssn stmt=new NodeStmtAssn(assn);
//		return stmt;
		return null;
	}

    public Node parse(String program) throws SyntaxException {
		scanner=new Scanner(program);
		scanner.next();
		return parseBlock();
    }

    /**
     * Method to parse boolean expression
     * @return NodeBoolExpr for evaluation
     * @throws SyntaxException
     */
	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolExpr(expr1, relop, expr2);
	}
	
	/**
	 * Method to parse block
	 * @return NodeBlock for evaluation
	 * @throws SyntaxException
	 */
	public NodeBlock parseBlock() throws SyntaxException {
		NodeStmt stmt = parseStmt();
		NodeBlock block = null;
		
		if(!curr().equals(new Token(";"))) {
			//match(";");
			//stmt.eval();
			return new NodeBlock(stmt);
		}
		 else {
			match(";");
			block = parseBlock();
			return new NodeBlock(stmt, block);
		 }
		
	}


}
