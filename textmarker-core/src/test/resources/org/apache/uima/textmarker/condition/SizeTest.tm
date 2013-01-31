PACKAGE org.apache.uima;

INT counter = 0;
INTLIST intList = {1,2,3,4,5};
STRINGLIST stringList = {"a","b","c","d","e"};

DECLARE T1, T2, T3, T4;

"SIZE"{SIZE(intList) -> MARK(T1)};
"SIZE"{SIZE(intList,4,10) -> MARK(T2)};
"SIZE"{SIZE(intList,6,10) -> MARK(T3)};
"SIZE"{SIZE(stringList,5,10,counter) -> MARK(T4)};