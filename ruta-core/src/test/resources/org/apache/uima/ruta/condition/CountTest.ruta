PACKAGE org.apache.uima;

INT var;
STRINGLIST list = {"A", "B", "A"};

DECLARE T1, T2, T3;

ANY+{-PARTOF(PERIOD), -PARTOF(T1) -> MARK(T1)};
T1{COUNT(CW,2,5,var), IF((var == 3)) -> MARK(T2)};
T2{COUNT(list,"A",2,5,var), IF((var == 2)) -> MARK(T3)};