module modulo0::syn::Modulo0

start syntax CompilationUnit = CompilationUnit: PackageDecl ImportDecl* ModuleDecl*;

syntax PackageDecl
	= Package: "package" QualifiedIdentifier ";"
	;
	
syntax ImportDecl
	= Import: "import" QualifiedIdentifier ";"
	;

syntax ModuleDecl
	= Module: "module" Identifier "{" Decl* "}"
	;
	
syntax Decl
	= Def: "def" QualifiedIdentifier
	| Use: "use" QualifiedIdentifier
	;

syntax Identifier
	= Name: LexIdentifier
	;

syntax QualifiedIdentifier
	= QName: {Identifier "."}*
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
	
