PACKAGE org.apache.uima;

INT var;

DECLARE T1, T2, T3, T4;

ANY+{-PARTOF(PERIOD), -PARTOF(T1) -> MARK(T1)};
CW{CONTEXTCOUNT(T1,1,2,var) -> MARK(T2)};
Document{IF((var == 3)) -> MARK(T3)};