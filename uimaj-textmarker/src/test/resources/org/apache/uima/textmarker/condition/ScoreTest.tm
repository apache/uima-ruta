PACKAGE org.apache.uima;

INT int1;

DECLARE T1, T2, T3, T4;

CW{-> MARKSCORE(10,T1)};
CW{-> MARKSCORE(20,T1)} CW;

T1{SCORE(5,15) -> MARK(T2)};
T1{SCORE(16,25) -> MARK(T3)};
T1{SCORE(26,35,int1), IF((int1 == 30)) -> MARK(T4)};