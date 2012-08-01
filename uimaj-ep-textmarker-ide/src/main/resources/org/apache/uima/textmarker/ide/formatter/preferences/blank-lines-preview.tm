PACKAGE org.apache.uima;

DECLARE childRow, Doc2, Doc3, Doc4, Doc5;
INT id;
BOOLEAN b = false;

BLOCK(ChildRows)childRow{}{childRow[1,9]{IF(b)->ASSIGN(id,1+id), ASSIGN(b,true)};Doc2{->MARK(childRow)};}
