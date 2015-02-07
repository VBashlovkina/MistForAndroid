import java.io.PrintWriter;

public class Tokenizer {

    char[] code;

    int index; // the char we haven't looked at yet
    String peeked;

    public Tokenizer(String code) {
	this.code = code.toCharArray();
	index = 0;
	peeked = "";
    }

    public boolean hasNext() {
	return index < code.length;
    }

    public String next() {

	
	index += peeked.length();
	return this.peek();
    }

    // TODO deal with whitespace!
    public String peek() {
	// clear peeked
	peeked = "";
	int i = 0;
	char c;
	if (this.hasNext()) {
	    // Deal with single-char tokens
	    c = code[index + i];
	    if (c == '(' || c == ')' || c == ',')
		return (peeked = c + "");
	    // otherwise, parse a word token
	    while (index + i < code.length - 1
		    && (c = code[index + i]) != '(' && c != ')' && c != ',') {
		// discard whitespace but increment index to account for skipping
		if (Character.isWhitespace(c))
		    index++;
		else
		{
		 peeked += c;
		 i++;
		}
	    }// while reading a word token
	}// if has
	return peeked;
    }

    public String toString()
    {
	return new String(code) + " with peeked: " + peeked;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
	String test = "rgb(sq(sum(x,y)),x,y)";
	String okay = "rgb(wsum(x, y, y, t.s), sum(sum(wsum(x, x, y, neg(t.s), x), wsum(y, y, x, neg(t.s), y)), sum(wsum(y, x, x, t.s), wsum(x, y, y, t.s))), sum(wsum(x, x, y, neg(t.s), x), wsum(y, y, x, neg(t.s), y)))";
	String zoes = "mult(sum(cos(square(sum(sin(x), cos(mult(t.m, 5))))), cos(square(sum(sin(y), cos(mult(t.m, 5)))))), 10)";
	Tokenizer t = new Tokenizer(zoes);
	PrintWriter pen = new PrintWriter(System.out, true);
	while (t.hasNext())
	{
	    pen.println(t.next());
	}
    }// main

}// Tokenizer
