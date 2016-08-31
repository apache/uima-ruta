PACKAGE org.apache.uima;

TYPELIST list = {PERIOD, COMMA};

DECLARE T1, T2, T3;

PM{IS(COLON) -> MARK(T1)};
ANY{IS({PERIOD, COMMA}) -> MARK(T2)};
ANY{IS(list) -> MARK(T3)};