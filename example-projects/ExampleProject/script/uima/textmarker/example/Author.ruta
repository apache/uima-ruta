PACKAGE uima.textmarker.example;

// import the types of this type system:
TYPESYSTEM types.BibtexTypeSystem;

// define and import an external dictionary containing first names
WORDLIST FirstNameList = 'FirstNames.txt';

// define some useful annotations
DECLARE FirstName, FirstNameInitial, Name, NameListPart;

//find the first names
Document{-> MARKFAST(FirstName, FirstNameList)};


// something that may links names
DECLARE NameLinker;
W{REGEXP("and", false) -> MARK(NameLinker)};
COMMA{ -> MARK(NameLinker)};
SPECIAL{REGEXP("&") -> MARK(NameLinker)};

// first name initials
CW{REGEXP(".") -> MARK(FirstNameInitial,1,2)} PERIOD;

// maybe a name
FirstName+ FirstNameInitial* CW{-> MARK(Name, 1, 2, 3)};
FirstNameInitial+{-PARTOF(Name)} CW{-> MARK(Name, 1, 2, 3)};
CW{-PARTOF(Name), -REGEXP(".")} COMMA? FirstNameInitial+{-> MARK(Name, 1, 2, 3)};

// list of names
Name{-PARTOF(NameListPart)} NameLinker[1,2]{-> MARK(NameListPart,1,2)};
NameListPart+ Name{-PARTOF(Author),-PARTOF(NameListPart) -> MARK(Author,1,2,3)} ;
NameListPart+{-PARTOF(Author) -> MARK(Author)};

// expand the author to the following punctation mark
Author{-> SHIFT(Author,1,2)} PM;
