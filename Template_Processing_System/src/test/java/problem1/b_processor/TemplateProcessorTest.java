package problem1.b_processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateProcessorTest {

    TemplateProcessor templateProcessor;
    String testTemplateFilePath = "src/test/resources/testTemplate.txt"; // Ensure this file exists with some content

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary template file for testing
        templateProcessor = new TemplateProcessor(testTemplateFilePath);
    }

    @Test
    void process_ValidTemplate_ReturnsCorrectString() {
        String expectedContent = "Hello, [[name]]!"  + System.lineSeparator()
            + "I'm [[mood]]." + System.lineSeparator()
            + "Bye!" + System.lineSeparator(); // Expecting a newline at the end due to how the file is read
        String actualContent = templateProcessor.process();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void testEquals() {
        TemplateProcessor anotherProcessorWithSamePath = new TemplateProcessor(testTemplateFilePath);
        assertEquals(templateProcessor, anotherProcessorWithSamePath);
        assertFalse(templateProcessor.equals(null));
        assertFalse(templateProcessor.equals(1));
        assertTrue(templateProcessor.equals(templateProcessor));
    }

    @Test
    void testHashCode() {
        TemplateProcessor anotherProcessorWithSamePath = new TemplateProcessor(testTemplateFilePath);
        assertEquals(templateProcessor.hashCode(), anotherProcessorWithSamePath.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "TemplateProcessor{TemplateFilePath='" + testTemplateFilePath + "'}";
        assertEquals(expectedString, templateProcessor.toString());
    }
}
