# Theater Booking System

A Java-based theater seat reservation system that implements domain-driven design principles and object-oriented patterns for managing reservations.

## Domain Model

The system is built around key domain entities:
- **Theater**: Represents the venue, containing rows of seats and information about wheelchair-accessible rows.
- **Row**: Manages a collection of seats, with each row potentially being wheelchair accessible.
- **Seat**: Represents an individual seat in the theater, with a reservation status.

## Key Features

1. **Intelligent Seat Selection**: 
   - Automatically selects the best available seats near the center.
   - Handles accessibility needs and keeps groups together.

2. **Flexible Seating Layout**: 
   - Configurable number of rows and seats, with support for wheelchair-accessible rows.
   - Dynamic seat naming system (A, B, C, etc.).

3. **Interactive User Interface**:
   - Command-line interface for seat reservation.
   - Visual display of available seats and reservation feedback.

4. **Input Validation**:
   - Validates the number of seats requested and checks for availability.
   - Ensures valid seat numbers and correct input formats.

## Architecture

The system is designed using the following layers:
- **Domain Layer**: Defines the core entities (`Theater`, `Row`, `Seat`).
- **Service Layer**: Contains business logic for reserving seats, finding available rows, and displaying seating information.
- **User Interface Layer**: Provides a command-line interface for interacting with the system.

## Design Patterns & Principles

- **Domain-Driven Design**: Emphasizes rich domain models and separation of concerns between different parts of the system.
- **Object-Oriented Principles**: Uses encapsulation, inheritance, and single responsibility in the design of the core classes.
- **Service Pattern**: Separates business logic into services, ensuring clear boundaries between UI and logic.

## Testing

The system includes unit tests to ensure the correctness and reliability of its functionality. Tests cover all major components, including:
- Seat reservation logic
- Seat selection algorithms
- Input validation and error handling
