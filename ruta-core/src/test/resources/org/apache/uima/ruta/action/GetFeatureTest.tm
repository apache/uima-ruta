PACKAGE org.apache.uima;

DECLARE T1;

STRING language;
Document{-> GETFEATURE("language", language)};
Document{REGEXP(language, "x-unspecified") -> MARK(T1)};
