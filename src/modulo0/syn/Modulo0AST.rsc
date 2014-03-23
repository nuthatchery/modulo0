module modulo0::syn::Modulo0AST
data AST = CompilationUnit(AST arg0, AST arg1, AST arg2); // sort("CompilationUnit")
data AST = Def(AST arg0); // sort("Decl")
data AST = Import(AST arg0); // sort("ImportDecl")
data AST = Module(AST arg0, AST arg1); // sort("ModuleDecl")
data AST = Name(AST arg0); // sort("Identifier")
data AST = Package(AST arg0); // sort("PackageDecl")
data AST = QName(AST arg0); // sort("QualifiedIdentifier")
data AST = Use(AST arg0); // sort("Decl")
