PACKAGE org.apache.uima;

DECLARE T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25;


"CW"{-> MARK(T1)};

"CW" "SW"{-> MARK(T2)};

"SW" "SW"{-> MARK(T3)};

("CW" "COMMA")+{-> MARK(T6)};

("CW" | "SW"){->MARK(T7)};

("CW" | "SW")+{->MARK(T8)};

("CW" "COMMA")+ "CW"{-> MARK(T9,1,2)};

("CW" ("COMMA" | "SW"))+{-> MARK(T10)};

("CW" ("COMMA" | "SW"))+ "CW" "PERIOD"+{-> MARK(T11,1,2,3)};

("CW" ("COMMA" | "SW"){-> MARK(T12), MARK(T13,1,2)})+{-> MARK(T14)} 
    "CW" "PERIOD"+;

         