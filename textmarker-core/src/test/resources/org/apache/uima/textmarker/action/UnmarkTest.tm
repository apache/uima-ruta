PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5, T6, T7, T8;

CW{-> MARK(T1)};
W W W{-> MARK(T2)};
W PERIOD{ -> MARK(T3,1,2)};

T1 {-> UNMARK(T1)};
W W W{-> UNMARK(T2)};
W {-> UNMARK(T3, true)};

