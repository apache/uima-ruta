PACKAGE uima.textmarker.example;

// import the types of this type system:
TYPESYSTEM types.BibtexTypeSystem;

// find something that maybe indicates the year: a number with four digits starting with 19 or 20
NUM{REGEXP("19..|20..") -> MARK(Year,1,2)} PM?;

// add parentheses if there are some (by removing the old Year annotation and creating a new one)
SPECIAL{REGEXP("[(]")} Year{ -> SHIFT(Year,1,2,3,4)} SPECIAL{REGEXP("[)]")} PM?;

