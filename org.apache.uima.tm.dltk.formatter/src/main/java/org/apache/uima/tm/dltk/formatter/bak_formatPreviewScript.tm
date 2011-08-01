PACKAGE de.uniwue.casetrain;

SCRIPT de.uniwue.casetrain.DashTree;
SCRIPT de.uniwue.casetrain.DashTreeError;
SCRIPT de.uniwue.casetrain.BuildTree;
TYPESYSTEM de.uniwue.casetrain.TerminologyTypeSystem;

Document(CALL,DashTree);
 
DECLARE link;
entryContent curlyBracketsOpen inCurlyBrackets{-PARTOF,link}(MARK,link,3) curlyBracketsClose;

Document(RETAINTYPE);

// *** error handling ***
Document(CALL,DashTreeError);
// *** end of error handling ***

// *** statistics ***
INT count;
Document{TOTALCOUNT,error,1,1000000,count}
    (LOG,"Longmenu.txt: Es wurde(n) "+(count)+" Fehler gefunden!");
Document{TOTALCOUNT,rootRow,1,1000000,count}
    (LOG,"Longmenu.txt: Es wurde(n) "+(count)+" Wurzel-Elemente gefunden.");
Document{TOTALCOUNT,childRow,1,1000000,count}
    (LOG,"Longmenu.txt: Es wurde(n) "+(count)+" Kind-Elemente gefunden.");
// *** end of statistics ***

INT id;

ACTION IncID = (ASSIGN,id,id+1);


BLOCK(ChildRows) childRow {
    childRow[1,9](ASSIGN,id,1+id;
        CREATE,Term,
        "Text" = entryContent,
        "Info" = link,
        "Id" = "A" + (id),
        "TermType" = "answer");
}

BLOCK(RootRow) rootRow {
    rootRow(ASSIGN,id,1+id;
        CREATE,Term,
        "Text" = entryContent,
        "Info" = link,
        "Id" = "A" + (id),
        "TermType" = "answer");
}

Term{PARTOF,rootRow}(TRANSFER,Root);

Term{PARTOF,rootRow}(MARK,SubTree,1,2) Term+{-PARTOF,rootRow};
Document(CALL,BuildTree.BuildDashTree);
