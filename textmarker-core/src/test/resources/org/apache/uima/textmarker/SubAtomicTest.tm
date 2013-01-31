PACKAGE org.apache.uima;

DECLARE Annotation SubAtomic1;
DECLARE Annotation SubAtomic2;

DECLARE T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25;

COMMA SubAtomic1{-> MARK(T1,2,3)} SubAtomic2 COMMA;
SubAtomic2{-> MARK(T2)} COMMA;
COMMA SubAtomic1{-> MARK(T3)};
W{CONTAINS(SubAtomic1) -> MARK(T4)};
SubAtomic1 W{-> MARK(T5)};

