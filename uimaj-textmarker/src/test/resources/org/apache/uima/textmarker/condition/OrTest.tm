PACKAGE org.apache.uima;

DECLARE T1, T2, T3;

CW W+{-> MARK(T1,1,2)} COLON;
ANY{INLIST({"OR", "Test"}) -> MARK(T2)};
W{OR(PARTOF(T1),PARTOF(T2)) -> MARK(T3)};