PACKAGE org.apache.uima;

TYPELIST types = {PERIOD, NUM};

DECLARE SentenceStart, SentenceEnd;
DECLARE T1, T2, T3, T4;

PERIOD{-> MARK(T1)};
W{AFTER(PERIOD) -> MARK(T2)};
W{AFTER(types) -> MARK(T3)};
W{AFTER({PERIOD, NUM}) -> MARK(T4)};