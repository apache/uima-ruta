PACKAGE uima.textmarker.example;

// import the types of this type system 
// (located in the descriptor folder -> uima.textmarker.example folder)
TYPESYSTEM types.BibtexTypeSystem;

SCRIPT uima.textmarker.example.Author;
SCRIPT uima.textmarker.example.Title;
SCRIPT uima.textmarker.example.Year;

// execute the Year script on the complete input document. 
// (Try the the go into: ctrl left mouse button on "Year" in the action)
Document{-> CALL(Year)};
// execute the Author script on the complete input document
Document{-> CALL(Author)};
// execute the Title script on the complete input document
Document{-> CALL(Title)};

// create bibtex annotation
Document{-> CREATE(Bibtex, "author" = Author, "title" = Title, "year" = Year)};

