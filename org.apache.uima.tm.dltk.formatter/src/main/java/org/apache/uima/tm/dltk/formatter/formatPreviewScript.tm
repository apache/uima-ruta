PACKAGE de.uniwue.casetrain;

SCRIPT de.uniwue.casetrain.DashTree;
SCRIPT de.uniwue.casetrain.DashTreeError;
SCRIPT de.uniwue.casetrain.BuildTree;
TYPESYSTEM de.uniwue.casetrain.TerminologyTypeSystem;

Document{->CALL(DashTree)}; 
DECLARE link1, link2, link3, link4;
link1{PARTOF(link2)->MARK(link,3)};

// *** error handling ***
Document{->CALL(DashTreeError)};
// *** end of error handling ***

// *** blocks ***
DECLARE childRow, Doc2;
INT id;
BOOLEAN b = false;

BLOCK(ChildRows)childRow{}{childRow[1,9]{IF(b)->ASSIGN(id,1+id), ASSIGN(b,true)};Doc2{->MARK(childRow)};}

// *** statistics ***
INT count;
Document{TOTALCOUNT(error,1,1000000,count)->LOG,"Longmenu.txt: Es wurde(n) "+(count)+" Fehler gefunden!")};