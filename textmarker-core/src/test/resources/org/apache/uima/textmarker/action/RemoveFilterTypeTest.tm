PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5, T6, T7, T8;


Document{ -> RETAINTYPE(MARKUP)};
Document{ -> FILTERTYPE(W, NUM)};
Document{ -> REMOVEFILTERTYPE(W)};
ANY ANY MARKUP{-> MARK(T1,1,2)};

