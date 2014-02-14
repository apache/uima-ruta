PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5;

DECLARE Annotation C(Annotation a, Annotation b, INT count);
W{REGEXP("A")->MARK(T1)};
W{REGEXP("B")->MARK(T2)};
INT count;
(T1 T2){COUNT(W,0,100,count)-> CREATE(C, "a" = T1, "b" = T2, "count" = count)};