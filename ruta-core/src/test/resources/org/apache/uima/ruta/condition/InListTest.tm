PACKAGE org.apache.uima;

WORDLIST list1 = 'InListTestList.txt';

DECLARE T1, T2;

W{INLIST(list1) -> MARK(T1)};
W{INLIST({"INLIST","and","or"}) -> MARK(T2)};