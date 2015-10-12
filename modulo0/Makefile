default: all

srcdir=src
GRAMMARS=modulo0/syn/Modulo0.rsc
PARSERS=$(GRAMMARS:%.rsc=%.jar)
ASTDEFS=$(GRAMMARS:%.rsc=%AST.rsc)
PPDEFS=$(GRAMMARS:%.rsc=%PP.rsc)
INFOS=$(GRAMMARS:%.rsc=%Info.pbf)
GRDEFSS=$(GRAMMARS:%.rsc=%Grammar.pbf)


all: $(addprefix $(srcdir)/,$(PARSERS))  $(addprefix $(srcdir)/,$(ASTDEFS)) $(addprefix $(srcdir)/,$(PPDEFS)) $(addprefix $(srcdir)/,$(INFOS))

$(srcdir)/%AST.rsc $(srcdir)/%PP.rsc $(srcdir)/%Info.pbd: $(srcdir)/%Grammar.pbf
	java org.nuthatchery.pica.parsergen.GenerateAuxFiles $* $(srcdir) $(srcdir)
	
$(srcdir)/%Grammar.pbf $(srcdir)/%.jar : $(srcdir)/%.rsc
	env
	java org.nuthatchery.pica.parsergen.GenerateParser $* $(srcdir)/$(dir $*)

