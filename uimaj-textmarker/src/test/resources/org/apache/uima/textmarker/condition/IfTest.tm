PACKAGE org.apache.uima;

BOOLEAN b1 = true;
BOOLEAN b2 = false;
INT int1 = 5;

DECLARE T1, T2, T3;

Document{IF(b1) -> MARK(T1)};
Document{IF(b2) -> MARK(T2)};
Document{IF((int1 > 3)) -> MARK(T3)};