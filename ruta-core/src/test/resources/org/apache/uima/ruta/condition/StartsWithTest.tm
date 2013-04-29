PACKAGE org.apache.uima;

TYPELIST types = {SW, CW};

DECLARE T1, T2, T3, T4;

SW{STARTSWITH(Document) -> MARK(T1)};
CW{STARTSWITH(Document) -> MARK(T2)};
Document{STARTSWITH(types) -> MARK(T3)};
Document{STARTSWITH({SW, CW}) -> MARK(T4)};