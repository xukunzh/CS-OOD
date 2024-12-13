# Template Processing System

A Java-based template processing and file generation system using command pattern and template method pattern.

## System Architecture

### Core Components

1. **Command Processing (`d_command/`)**
   - `Command` class for handling command operations
   - `CommandLineParser` for parsing input arguments
   - Command pattern for operation control

2. **File Processing (`b_processor/`)**
   - `IProcessor<T>` interface defining processing contract
   - `AbstractProcessor<T>` with common processing logic
   - Specialized processors:
     - `CsvProcessor`: Handles CSV files
     - `TemplateProcessor`: Processes template files

3. **Generation (`c_generator/`)**
   - `Generator` class for file generation
   - Template processing and placeholder replacement
   - File output management

4. **Input/Output Management**
   - `a_input/`: Input file and command handling
   - `e_output/`: Output generation and management

## Design Patterns

- **Command Pattern**: Encapsulates commands as objects for various operations
- **Template Method Pattern**: Defines the skeleton of file processing, specialized by processors
- **Strategy Pattern**: Supports different processing strategies for CSV and templates

## Technical Features

- Generic type implementations (`<T>`)
- File I/O handling
- Command line argument parsing
- Pattern matching and replacement
- CSV data processing
- Template processing

## Key Functionalities

1. Command line argument parsing
2. File validation and processing
3. Template replacement
4. CSV data handling
5. File generation with templates

## Testing

All major functionalities are fully tested. The tests cover a range of use cases for each component, ensuring the reliability and correctness of the system.

### Test Coverage

- **Command Processing**:
  - Tests for parsing valid and invalid command-line arguments
  - Tests for command execution logic and error handling

- **File Processing**:
  - **CsvProcessor**:
    - Tests for correct CSV parsing and validation
    - Tests for handling various edge cases like missing or incorrect data
  - **TemplateProcessor**:
    - Tests for correct template loading and placeholder replacement
    - Tests for invalid templates and missing placeholders

- **Generation**:
  - Tests for the file generation logic, ensuring proper output formatting
  - Tests for output file creation and file permissions handling

- **Input/Output Management**:
  - Tests for input file parsing and validation
  - Tests for output generation, ensuring the generated files are in the correct format
