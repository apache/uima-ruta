PACKAGE org.apache.uima;

STRING var = "Test";

DECLARE T1, T2, T3, T4;

Document{REGEXP("^.*$") -> MARK(T1)};
Document{REGEXP(var, "t.*", true) -> MARK(T2)};
W{REGEXP("A.*", true) -> MARK(T3)};
W{REGEXP("A.*", false) -> MARK(T4)};