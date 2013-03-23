package parser;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class MathParser {

	static final String deliminatiors = "+-/*^()[]|";
	static final String operators = "+-*/^";
	//StringTokenizer tokenizer;
	
	
	String expression;
	
	public MathParser() {
		while(expression==null) {
			expression = JOptionPane.showInputDialog(null, "Enter an expression");
		}
		expression=expression.trim();
		System.out.println(expression);
		
		try {
			expression = parse(expression);
		} catch (MathSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(expression);
	}
	
	public String parse(String exp) throws MathSyntaxException {
		
		//int index = 0;
		StringTokenizer tokenizer = new StringTokenizer(exp, deliminatiors, true);
		ArrayList<String> tokens = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()) {
			String hold = tokenizer.nextToken();
			if(hold!="" && hold!=" " && hold!="\n" && hold!="\t")
				tokens.add(hold);
		}
		
		//First pass:
		for(int i=0; i<tokens.size(); i++) {
			if(tokens.get(i).equals("(")) {
				int curPar = i;
				int nextCl = nextOccurenceOf(')', tokens, i);
				int nextOp = nextOccurenceOf('(',tokens, i+1);
				if(nextCl==-1) throw new MathSyntaxException();
				while(nextOp<nextCl && nextOp!=-1) {
					nextOp = nextIndexOf(exp, '(', nextOp+1);
					//nextCl = nextIndexOf(exp, ')', nextCl+1);
				}
				
				
			}
		}
		
		for(int i=0; i<tokens.size(); i++) {
			String token = tokens.get(i);
			//System.out.println(token);
			
			if(token.equals("+")) {
				if(i==0) throw new MathSyntaxException();
				double v1 = Double.parseDouble(tokens.get(i-1)) + Double.parseDouble(tokens.get(i+1));
				//System.out.println(v1);
				int length=0;
				for(int j=0; j<i; j++) length+=tokens.get(j).length();
				tokens.remove(i);
				tokens.remove(i);
				tokens.remove(i-1);
				tokens.add(i-1, Double.toString(v1));
				i-=1;
				//exp.replaceAll(tokens.get(i-1) + tokens.get(i) + tokens.get(i+1), Double.toString(v1));
			}
			if(token.equals("-")) {
				if(i==0) {tokens.add(0, "0"); i++;}
				double v1 = Double.parseDouble(tokens.get(i-1)) - Double.parseDouble(tokens.get(i+1));
				System.out.println(v1);
				int length=0;
				for(int j=0; j<i; j++) length+=tokens.get(j).length();
				tokens.remove(i);
				tokens.remove(i);
				tokens.remove(i-1);
				tokens.add(i-1, Double.toString(v1));
				i-=1;
				//exp.replaceAll(tokens.get(i-1) + tokens.get(i) + tokens.get(i+1), Double.toString(v1));
			}
			if(token.equals("*")) {
				if(i==0) throw new MathSyntaxException();
				double v1 = Double.parseDouble(tokens.get(i-1)) * Double.parseDouble(tokens.get(i+1));
				System.out.println(v1);
				int length=0;
				for(int j=0; j<i; j++) length+=tokens.get(j).length();
				tokens.remove(i);
				tokens.remove(i);
				tokens.remove(i-1);
				tokens.add(i-1, Double.toString(v1));
				i-=1;
				//exp.replaceAll(tokens.get(i-1) + tokens.get(i) + tokens.get(i+1), Double.toString(v1));
			}
			if(token.equals("/")) {
				if(i==0) throw new MathSyntaxException();
				double v1 = Double.parseDouble(tokens.get(i-1)) / Double.parseDouble(tokens.get(i+1));
				System.out.println(v1);
				int length=0;
				for(int j=0; j<i; j++) length+=tokens.get(j).length();
				tokens.remove(i);
				tokens.remove(i);
				tokens.remove(i-1);
				tokens.add(i-1, Double.toString(v1));
				i-=1;
				//exp.replaceAll(tokens.get(i-1) + tokens.get(i) + tokens.get(i+1), Double.toString(v1));
			}
			if(token.equals("^")) {
				if(i==0) throw new MathSyntaxException();
				double v1 = Math.pow(Double.parseDouble(tokens.get(i-1)), Double.parseDouble(tokens.get(i+1)));
				System.out.println(v1);
				int length=0;
				for(int j=0; j<i; j++) length+=tokens.get(j).length();
				tokens.remove(i);
				tokens.remove(i);
				tokens.remove(i-1);
				tokens.add(i-1, Double.toString(v1));
				i-=1;
				//exp.replaceAll(tokens.get(i-1) + tokens.get(i) + tokens.get(i+1), Double.toString(v1));
			}
		}
		
		
		/*int pStart=0, pEnd=0;
		for(int i=0; i<exp.length();i++) {
			//parse parenthesis
			if(exp.charAt(i)=='(') {
				pStart=i+1;
			}
			else if(exp.charAt(i)==')') {
				pEnd=i-1;
				String toReplace=exp.substring(pStart-1, pEnd+1);
				String sec=parse(exp.substring(pStart, pEnd));
				exp.replaceFirst(toReplace, sec);
			}
			//parse exponents
			if(exp.charAt(i)=='^') {
				
			}
		}*/
		String assembly = "";
		for(int i=0; i<tokens.size(); i++) assembly = assembly + tokens.get(i);
		return assembly;
	}
	
	
	public String parseParenthesis(String exp) {
		
		
		
		return exp;
	}
	
	
	public int nextIndexOf(String s, char c, int start) {
		String sub = s.substring(start);
		return sub.indexOf(c);
	}
	public int prevIndexOf(String s, char c, int end) {
		String sub = s.substring(0, end-1);
		return sub.lastIndexOf(c);
	}
	
	public int nextOccurenceOf(char c, ArrayList<String> tokens, int start) {
		for(int i=start; i<tokens.size(); i++) {
			if(tokens.get(i).equals(c)) return i;
		}
		return -1;
	}
	public int previousOccurenceOf(char c, ArrayList<String> tokens, int end) {
		for(int i=end; i>=0; i--) {
			if(tokens.get(i).equals(c)) return i;
		}
		return -1;
	}
	
	public int startIndexOfToken(int tokenIndex, String[] tokens) {
		int index = 0;
		for(int i=0; i<tokenIndex; i++) {
			index+=tokens[i].length();
		}
		return index;
	}
	
	public static void main(String args[]) {
		new MathParser();
	}
	
	private class MathSyntaxException extends Exception {
		public MathSyntaxException() {
			super("Invalid Syntax; cannot evaluate expression");
		}
	}
}
