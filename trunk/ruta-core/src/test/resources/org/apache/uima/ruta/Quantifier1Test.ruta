PACKAGE org.apache.uima;


DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17;
DECLARE T20, T21, T22;


W {REGEXP("A") -> MARK(T20)};
W {REGEXP("B") -> MARK(T21)};
W {REGEXP("C") -> MARK(T22)};


T20{-> MARK(T1, 1, 2)} T21?;
T20{-> MARK(T2, 1, 2)} T21??;
T20{-> MARK(T3, 1, 2)} T21*;
T20{-> MARK(T4, 1, 2)} T21*?;
T20{-> MARK(T5, 1, 2)} T21+;
T20{-> MARK(T6, 1, 2)} T21+?;
T20{-> MARK(T7, 1, 2)} T21[2];
T20{-> MARK(T8, 1, 2)} T21[2,];
T20{-> MARK(T9, 1, 2)} T21[1,2];
T20{-> MARK(T10, 1, 2)} T21[0,1];
T20{-> MARK(T11, 1, 2)} T21[0,];

T20{-> MARK(T12, 1, 2, 3)} T21? T22;
T20{-> MARK(T13, 1, 2, 3)} T21?? T22;
T20{-> MARK(T14, 1, 2, 3)} T21* T22;
T20{-> MARK(T15, 1, 2, 3)} T21*? T22;
T20{-> MARK(T16, 1, 2, 3)} T21+ T22;
T20{-> MARK(T17, 1, 2, 3)} T21+? T22;


