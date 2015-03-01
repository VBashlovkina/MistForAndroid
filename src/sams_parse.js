/**
* Parse MIST code into a MIST expression.
*/
MIST.parse = function(str,prefix) {
if (!prefix) { prefix=""; }

// The kernel. Does the work, also recurses.

var kernel = function(tokens) {
// This should never happen, but let's be safe.
if (tokens.length == 0) {
MIST.parseError("Unexpected end of input", 0, 0);
}
var tok = tokens.shift();
// Check for end of input
if (tok.type == MIST.tokens.EOF) {
MIST.parseError("Unexpected end of input", tok.row, tok.col);
}
// Is it a number?
if (tok.type == MIST.tokens.NUM) {
return new MIST.Val(tok.text);
} // MIST.tokens.NUM
// Only identifiers and numbers are allowed at the top level.
else if (tok.type != MIST.tokens.ID) {
MIST.parseError("Unexpected token (" + tok.text + ")", tok.row, tok.col);
} // if it's not an identifier
// Is it a function call?
else if (peekType(tokens) == MIST.tokens.OPEN) {
tokens.shift();
var children = [];
while (peekType(tokens) != MIST.tokens.CLOSE) {
children.push(kernel(tokens));
if (peekType(tokens) == MIST.tokens.COMMA) {
tokens.shift();
if (peekType(tokens) == MIST.tokens.CLOSE) {
MIST.parseError("Close paren follows comma", tokens[0].row, tokens[0].col);
} // if there's a close paren after a comma
} // if there's a comma
} // while
tokens.shift();
return new MIST.App(prefix + tok.text, children);
} // if it's a function call
// Otherwise, it's a singleton
else {
return new MIST.Val(tok.text);
} // if it's a singleton
}; // kernel
var tokens = MIST.tokenize(str);
var result = kernel(tokens);
if ((tokens.length > 1) || (peekType(tokens) != MIST.tokens.EOF)) {
MIST.parseError("Extra text after expression", tokens[0].row,
tokens[0].col);
}
return result;
} // MIST.parse
