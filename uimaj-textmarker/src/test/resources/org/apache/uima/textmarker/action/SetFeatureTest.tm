PACKAGE org.apache.uima;

DECLARE Document LanguageStorage(STRING language);

Document{ -> MARK(LanguageStorage)};
LanguageStorage{-> SETFEATURE("language", "en")};
