PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5;

DECLARE Annotation C(Annotation a, Annotation b);
W{REGEXP("A")->MARK(T1)};
W{REGEXP("B")->MARK(T2)};
T1 T2{-> GATHER(C, 1, 2, "a" = 1, "b" = 2)};