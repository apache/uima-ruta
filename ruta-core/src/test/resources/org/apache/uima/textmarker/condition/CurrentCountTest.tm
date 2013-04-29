PACKAGE org.apache.uima;


DECLARE T1, T2, T3, T4, T5;

INT count;

W{CURRENTCOUNT(W,3,3) -> MARK(T1)};
W{CURRENTCOUNT(W,2,8,count), IF((count>3)) -> MARK(T2)};
W{CURRENTCOUNT(W,2,8,count), IF((count<3)) -> MARK(T3)};