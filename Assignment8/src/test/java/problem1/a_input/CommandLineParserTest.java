package problem1.a_input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static problem1.a_input.CommandLineParser.ERROR_MESSAGE;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {

    CommandLineParser parser;

    @BeforeEach
    void setUp() {
        String[] args = {"--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        parser = new CommandLineParser(args);

    }

    @Test
    void whenValidArgs_thenCorrectlyParsed() {
        assertEquals(List.of("--email"), parser.getInputCommands());
        assertEquals("src/test/java/problem1/a_input/email-template.txt", parser.getInputOptions().get("--email-template"));
        assertEquals("src/test/java/problem1/a_input/insurance-company-members.csv", parser.getInputOptions().get("--csv-file"));
        assertEquals("src/test/java/problem1/g_output", parser.getInputOptions().get("--output-dir"));
    }

    @Test
    void testEqualsAndHashCode() {
        String[] args1 = {"--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/f_output"};
        String[] args2 = {"--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/f_output"};
        CommandLineParser parser1 = new CommandLineParser(args1);
        CommandLineParser parser2 = new CommandLineParser(args2);

        assertEquals(parser1, parser2);
        assertEquals(parser1.hashCode(), parser2.hashCode());
        assertTrue(parser1.equals(parser1));
        assertFalse(parser1.equals(null));
        assertFalse(parser1.equals("S"));
    }

    @Test
    void testToString() {
        String expectedString = "CommandLineParser{" +
                "inputCommands=" + parser.getInputCommands() +
                ", inputOptions=" + parser.getInputOptions() +
                '}';
        assertEquals(expectedString, parser.toString());
    }

    @Test
    void duplicateCommands() {
        String[] args = {"--email", "--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = "Duplicate commands: --email";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void duplicateOptions() {
        String[] args = {"--email", "--email-template","src/test/java/problem1/a_input/email-template.txt", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = "Duplicate options: --email-template";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void missingArgument() {
        String[] args = {"--email", "--email-template"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = "Missing argument for option: --email-template";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void emailFileNotExist() {
        String[] args = {"--email", "--email-template", "src/test/java/problem1/a_input/email.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = " does not exist: src/test/java/problem1/a_input/email.txt";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void letterFileNotExist() {
        String[] args = {"--letter", "--letter-template", "src/main/resources/letter.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = " does not exist: ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void missingCommand() {
        String[] args = {"--email-template", "src/test/java/problem1/a_input/email-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = "No commands provided. Please include --email and/or --letter.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void allGood() {
//        String[] args = {"--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--letter", "--letter-template", "src/main/resources/letter-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
//        assertDoesNotThrow(() -> {
//            new CommandLineParser(args);
//        });
////        String expectedMessage = "No commands provided. Please include --email and/or --letter.";
////        String actualMessage = exception.getMessage();
////        assertTrue(actualMessage.contains(expectedMessage));
//    }

    @Test
    void missingTemplates() {
        String[] args = {"--email", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = ERROR_MESSAGE;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void threeItems() {
        String[] args = {"--email", "--email-template", "src/test/java/problem1/a_input/email-template.txt", "--letter-template", "src/main/resources/letter-template.txt", "--csv-file", "src/test/java/problem1/a_input/insurance-company-members.csv", "--output-dir", "src/test/java/problem1/g_output"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommandLineParser(args);
        });
        String expectedMessage = "One command with two templates provided. Executed the matching one.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}