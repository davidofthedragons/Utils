package math.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import utils.Utils;

public class MathParser {

	static final String deliminatiors = "+-/*^()[]| ";
	static final String operators = "+-*/^";
	static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	//StringTokenizer tokenizer;
	
	
	String expression;
	
	public MathParser() {
		expression = JOptionPane.showInputDialog(null, "Enter an expression");
		if(expression=="" || expression == null) return;
		expression=expression.trim();
		expression=expression.replaceAll(" ", "");
		//System.out.println(expression);
		
		try {
			expression = parse(expression);
		} catch (MathSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
		System.out.println(expression);
	}
	
	public static String parse(String exp) throws MathSyntaxException, NumberFormatException, StringIndexOutOfBoundsException {
		//System.out.println("Entering parse()");
		//System.out.println("Checking for parenthesis");
		for(int i=0; i<exp.length(); i++) {
			if(exp.charAt(i)=='(') {
				int startP = i;
				//System.out.println("Found open parenthesis at " + startP);
				int endP = findParPair(exp.substring(startP)) + i;
				if(endP<=-1 || startP<=-1) System.out.println("Fail!");
				//System.out.println(startP + ", " + endP);
				//System.out.println("Found corresponding close parenthesis at " + endP);
				String s = parse(exp.substring(startP+1, endP));
				if (i>0) {
					if(alpha.contains(Character.toString(exp.charAt(i-1)))) {
						int j;
						for(j=i-1; j>0 && alpha.contains(Character.toString(exp.charAt(j))); j--);
						System.out.println("s " + s);
						s = doFunc(exp.substring(j, i), s);
						startP = j;
					}
					else if (!operators.contains(Character.toString(exp.charAt(i - 1)))) { //  5(8) --> 5*(8)
						exp = exp.substring(0, startP) + "*" + exp.substring(startP);
						startP++;
						endP++;
					}
				}
				exp = exp.replace(exp.substring(startP, endP+1), s);
			}
		}
		//System.out.println(exp);
		//int index = 0;
		StringTokenizer tokenizer = new StringTokenizer(exp, deliminatiors, true);
		ArrayList<String> tokens = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()) {
			String hold = tokenizer.nextToken();
			if(hold!="" && hold!=" " && hold!="\n" && hold!="\t")
				tokens.add(hold);
		}
		//System.out.println("Finished Tokenizing");
		
		for(int i=0; i<tokens.size(); i++) {
			String token = tokens.get(i);
			if(token.equals("^")) {
				if(i==0) throw new MathSyntaxException();
				double o1 = (Double.parseDouble(tokens.get(i-1)));
				if(i>1 && tokens.get(i-2).equals("-")) {
					o1 *= -1;
					tokens.remove(i-2);
					i--;
				}
				double o2 = (tokens.get(i+1).equals("-"))? (-1*Double.parseDouble(tokens.get(i+2))) : (Double.parseDouble(tokens.get(i+1)));
				//System.out.println(o1 + "^" + o2);
				double v1 = Math.pow(o1, o2);
				//System.out.println(v1);
				tokens.add(i, Double.toString(v1));
				tokens.remove(i+1);
				if(tokens.get(i+1).equals("-")) tokens.remove(i+1);
				tokens.remove(i+1);
				tokens.remove(i-1);
			}
		}
		for(int i=0; i<tokens.size(); i++) {
			String token = tokens.get(i);
			if(token.equals("*")) {
				if(i==0) throw new MathSyntaxException();
				double o1 = (Double.parseDouble(tokens.get(i-1)));
				if(i>1 && tokens.get(i-2).equals("-")) {
					o1 *= -1;
					tokens.remove(i-2);
					i-=1;
				}
				double o2 = (tokens.get(i+1).equals("-"))? (-1*Double.parseDouble(tokens.get(i+2))) : (Double.parseDouble(tokens.get(i+1)));
				//System.out.println(o1 + "^" + o2);
				double v1 = o1*o2;
				//System.out.println(v1);
				tokens.add(i, Double.toString(v1));
				tokens.remove(i+1);
				if(tokens.get(i+1).equals("-")) tokens.remove(i+1);
				tokens.remove(i+1);
				tokens.remove(i-1);
				i--;
			}
			
			if(token.equals("/")) {
				if(i==0) throw new MathSyntaxException();
				double o1 = (Double.parseDouble(tokens.get(i-1)));
				if(i>1 && tokens.get(i-2).equals("-")) {
					o1 *= -1;
					tokens.remove(i-2);
					i--;
				}
				double o2 = (tokens.get(i+1).equals("-"))? (-1*Double.parseDouble(tokens.get(i+2))) : (Double.parseDouble(tokens.get(i+1)));
				//System.out.println(o1 + "^" + o2);
				double v1 = o1/o2;
				//System.out.println(v1);
				tokens.add(i, Double.toString(v1));
				tokens.remove(i+1);
				if(tokens.get(i+1).equals("-")) tokens.remove(i+1);
				tokens.remove(i+1);
				tokens.remove(i-1);
				i--;
			}
		}
		for(int i=0; i<tokens.size(); i++) {
			String token = tokens.get(i);
			if(token.equals("+")) {
				if(i==0) throw new MathSyntaxException();
				double o1 = (Double.parseDouble(tokens.get(i-1)));
				if(i>1 && tokens.get(i-2).equals("-")) {
					o1 *= -1;
					tokens.remove(i-2);
					i--;
				}
				double o2 = (tokens.get(i+1).equals("-"))? (-1*Double.parseDouble(tokens.get(i+2))) : (Double.parseDouble(tokens.get(i+1)));
				//System.out.println(o1 + "^" + o2);
				double v1 = o1+o2;
				//System.out.println(v1);
				tokens.add(i, Double.toString(v1));
				tokens.remove(i+1);
				if(tokens.get(i+1).equals("-")) tokens.remove(i+1);
				tokens.remove(i+1);
				tokens.remove(i-1);
				i--;
			}
			
			if(token.equals("-")) {
				if(i==0 || deliminatiors.contains(tokens.get(i-1))) {tokens.add((i==0)? 0 : i-1, "0"); i++;}
				double v1 = Double.parseDouble(tokens.get(i-1)) - Double.parseDouble(tokens.get(i+1));
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
		}
		
		//System.out.println("Exiting parse();");
		String assembly = "";
		for(int i=0; i<tokens.size(); i++) assembly = assembly + tokens.get(i);
		//System.out.println(assembly);
		return assembly;
	}
	
	private int locInString(ArrayList<String> tokens, int i) {
		int length=0;
		for(int j=0; j<i; j++) length+=tokens.get(j).length();
		return length;
	}
	private static int findParPair(String s) {  //CAN"T RETURN i!!!!!! 
		//System.out.println("findParPair(" + s + ")");
/*		//int sp; //start paren
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '(') {
				i = findParPair(s.substring(i+1));
				if(i==-1) throw new MathSyntaxException();
				//continue;
			}
			else if(s.charAt(i) == ')') {
				return i;
			}
			//System.out.println(i);
		}
		
		return -1;
		*/
		int pPairs = -1;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='(') {
				if(pPairs<=-1) pPairs = 1;
				else pPairs++;
			}
			else if(s.charAt(i)==')') {
				pPairs--;
				if(pPairs==0) return i;
			}
		}
		return -1;
	}
	
	private static String doFunc(String fName, String arg) throws MathSyntaxException {
		System.out.println("Function:" + fName + "(" + arg + ")");
		if(fName.equalsIgnoreCase("sin")) {
			return Double.toString(Utils.round(Math.sin(Math.toRadians(Double.parseDouble(arg))), .00001));
		} else if(fName.equalsIgnoreCase("cos")) {
			return Double.toString(Utils.round(Math.cos(Math.toRadians(Double.parseDouble(arg))), .00001));
		} else if(fName.equalsIgnoreCase("tan")) {
			return Double.toString(Utils.round(Math.tan(Math.toRadians(Double.parseDouble(arg))), .00001));
		} else if(fName.equalsIgnoreCase("sqrt")) {
			return Double.toString(Utils.round(Math.cos(Double.parseDouble(arg)), .00001));
		}
		
		else throw new MathSyntaxException();
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
	
	public static void printTokens(ArrayList<String> list) {
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i));
		}
		System.out.println();
	}
	
	public static void main(String args[]) {
		new MathParser();
	}
	
	public static class MathSyntaxException extends Exception {
		public MathSyntaxException() {
			super("Invalid Syntax; cannot evaluate expression");
		}
	}
}
