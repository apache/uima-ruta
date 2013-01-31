PACKAGE org.apache.uima;

INT int1 = 0;

DECLARE T1, T2, T3, T4;

Document{-> RETAINTYPE(SPACE)};
Document{-> RETAINTYPE(NEWLINE)};

Document{TOTALCOUNT(CW) -> MARK(T1)};
Document{TOTALCOUNT(SPACE,1,5) -> MARK(T2)};
Document{TOTALCOUNT(PM,1,10,int1), IF((int1 == 2)) -> MARK(T3)};