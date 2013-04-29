PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5, T6, T7, T8;

TYPELIST list1 = {ANY, W, CW};
TYPELIST list2;

Document{-> ADD(list1, NUM)};
Document{-> ADD(list2, NUM)};

Document{ -> CLEAR(list1), CLEAR(list2)};
Document{SIZE(list1,3,3) -> MARK(T1)};
Document{SIZE(list2,0,0) -> MARK(T2)};