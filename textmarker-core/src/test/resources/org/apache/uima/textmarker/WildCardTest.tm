PACKAGE org.apache.uima;


DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14;


CW{-PARTOF(T1)} #{-> MARK(T1,1,2)};
Document{-> DYNAMICANCHORING(true)};
#{-PARTOF(T2)-> MARK(T2,1,2)} CW;
#{-PARTOF(T3) -> MARK(T3,1,2)} PERIOD;
Document{-> DYNAMICANCHORING(false)};
CW{-PARTOF(T4)} #{-> MARK(T4,1,2,3)} PERIOD;
#{-> MARK(T5)} PERIOD;
Document{-> DYNAMICANCHORING(true)};
#{-PARTOF(T6) -> MARK(T6)} PERIOD;
Document{-> DYNAMICANCHORING(false)};
#{-> MARK(T7)};
# #{-> MARK(T8)};
"Text" "Marker" #{-> MARK(T9)} ".";
#{-PARTOF(T10)-> MARK(T10)} ".";
CW{-PARTOF(T11)} #{-> MARK(T11,1,2,3)} (PERIOD | COLON);
#{-PARTOF(T12) -> MARK(T12,1,2)} (PERIOD | COLON);
CW{-PARTOF(T13)} #{-> MARK(T13,1,2,3)} (SW PERIOD);
CW{-PARTOF(T14)} #{-> MARK(T14,1,2,3)} ("elements" PERIOD);