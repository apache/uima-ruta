PACKAGE org.apache.uima;

DECLARE T1, T2, T3, T4, T5, T6, T7;

"A(.*?)C" -> T1, 1 = T2;

"^B(.*?)B" -> T7, 1 = T3;

"B(.*?)B(.)" -> 1 = T4, 2 = T5;


BLOCK(limited) T7{} {
    "B(.*?)B" -> 1 = T6;
}




