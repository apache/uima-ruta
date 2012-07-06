PACKAGE org.apache.uima;

DECLARE WithInitial, Initial;
DECLARE WithInitial WithInitialEnd;

DECLARE T1, T2, T3, T4, T5;

CW{-> MARK(WithInitialEnd,1,2)} CW{-> MARK(Initial)} PERIOD;

ANY{PARTOF(WithInitialEnd) -> MARK(T1)};
Initial {PARTOF(WithInitial) -> MARK(T2)};
SW{PARTOF(WithInitialEnd) -> MARK(T3)};