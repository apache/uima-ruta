package org.apache.uima.ruta.resource;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CSVTableTest {
    private static final String CUSTOM_SEPARATOR = "#|#";

    @Test
    public void testDefaultLookup() throws IOException {
        CSVTable csvTable = new CSVTable(CSVTable.class.getResourceAsStream("test_csvfile.csv"), CSVTable.DEFAULT_CSV_SEPARATOR);
        checkValue(csvTable,0, 0, "this is the first line first column");
        checkValue(csvTable,0, 1, "ONE");
        checkValue(csvTable,1, 0, "this is the second line first column");
        checkValue(csvTable,1, 1, "TWO");
        checkValue(csvTable,2, 0, "this is the a line with custom");
        checkValue(csvTable,2, 1, " non default separator used#|#THREE");
    }

    @Test
    public void testDefaultLookupWithEmptyColumn() throws IOException {
        CSVTable csvTable = new CSVTable(CSVTable.class.getResourceAsStream("test_csvfile.csv"), CSVTable.DEFAULT_CSV_SEPARATOR);
        checkValue(csvTable,3, 0, "line with empty column");
        checkValue(csvTable,3, 1, " "); // spacer added by table implementation
        checkValue(csvTable,3, 2, "AFTER_EMPTY_COLUMN");
    }

    @Test
    public void testLookupWithCustomSeparator() throws IOException {
        CSVTable csvTable = new CSVTable(CSVTable.class.getResourceAsStream("test_csvfile.csv"), CUSTOM_SEPARATOR);
        checkValue(csvTable,0, 0, "this is the first line first column;ONE");
        checkValue(csvTable,1, 0, "this is the second line first column;TWO");
        checkValue(csvTable,2, 0, "this is the a line with custom; non default separator used");
        checkValue(csvTable,2, 1, "THREE");
    }

    @Test
    public void testLookupWithCustomSeparatorAndEmptyColumn() throws IOException {
        CSVTable csvTable = new CSVTable(CSVTable.class.getResourceAsStream("test_csvfile.csv"), CUSTOM_SEPARATOR);
        checkValue(csvTable,4, 0, "line with empty column custom separator");
        checkValue(csvTable,4, 1, " "); // spacer added by table implementation
        checkValue(csvTable,4, 2, "AFTER_EMPTY_COLUMN2");
    }

    private void checkValue(CSVTable table, int row, int column, String expectedValue) {
        String actualValue = table.getEntry(row, column);
        assertThat(actualValue, is(expectedValue));
    }

}