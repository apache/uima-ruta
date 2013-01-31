PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4;
Document{-> RETAINTYPE(BREAK)};
ANY+{-PARTOF(BREAK), -PARTOF(T1) -> MARK(T1)};
Document{-> RETAINTYPE};

W PM W{-> MARK(T2,1,2,3)};

BLOCK(forEach) T1{}{
    T2{-> MARK(T3)};
    ANY T2{-> MARK(T4)};
}