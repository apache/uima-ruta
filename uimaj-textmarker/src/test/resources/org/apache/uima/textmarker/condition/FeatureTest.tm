PACKAGE org.apache.uima;

DECLARE T1, T2;

Document{FEATURE("begin",0) -> MARK(T1)};
Document{-> SETFEATURE("begin",40)};
Document{FEATURE("begin",40) -> MARK(T2)};