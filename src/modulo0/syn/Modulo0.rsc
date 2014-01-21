module modulo0::syn::Modulo0


syntax Module
	= Module: "module" Identifier "{" Decl* "}"
	;
	
syntax Decl
	= ProcDecl: "def" Type Identifier "(" {ParamDecl ","}* ")" "{" Stat* "}"
	| TypeDecl: "type" Identifier "{" FieldDecl* "}"
	;
	
syntax FieldDecl
	= Field: Type Identifier ";"
	;

syntax ParamDecl
	= Param: Type Identifier
	;
	
syntax Type
	= NamedType: QualifiedIdentifier
	| Tuple: "(" {Type ","}* ")"
	;

syntax Expr
	= Apply: QualifiedIdentifier "(" {Expr ","}* ")"
	| Var: QualifiedIdentifier
	| Int: LexDecimal
	;

syntax Stat
	= VarDecl: Type Identifier "=" Expr ";"
	| If: "if" Expr "then" Stat* Elsif* "else" Stat* "end"
	| Return: "return" Expr ";"
	| Nop: ";"
	;
	
syntax Elsif
	= "elsif" Stat*
	;


syntax Identifier
	= Name: LexIdentifier
	;

syntax QualifiedIdentifier
	= QName: {Identifier "::"}*
	;

lexical LexIdentifier = [a-zA-Z_] !<< [a-zA-Z_] [a-zA-Z_0-9]* !>> [a-zA-Z_0-9];

lexical LexDecimal = [0-9] !<< [0-9]+ !>> [0-9];

layout Layout 
	= WSorCOM*
	  !>> [\u0009-\u000D \u0020 \u0085 \u00A0 \u1680 \u180E \u2000-\u200A \u2028 \u2029 \u202F \u205F \u3000]
	  !>> "//";
  
lexical WSorCOM 
	= Whitespace | Comment
	; 

lexical Comment
	= @category="Comment" "//" ![\n\r]* $
	;

lexical Whitespace
	= [\u0009-\u000D \u0020 \u0085 \u00A0 \u1680 \u180E \u2000-\u200A \u2028 \u2029 \u202F \u205F \u3000]
	; 
	
