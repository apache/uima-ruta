PACKAGE uima.textmarker.example;

// import the types of this type system:
TYPESYSTEM types.BibtexTypeSystem;

// simpified rules for extracting the title
DECLARE TitleStopper;
PM W{REGEXP("in", true) -> MARK(TitleStopper)};

// annotate everything between the Author (and an optional a punctation mark) and
Author PM? ANY+?{-> MARK(Title)} TitleStopper;