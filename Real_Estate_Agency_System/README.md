# Real Estate Agency System

A Java-based real estate management system demonstrating OOP principles and design patterns.

## Core Components

### Properties
- `Property` class (base class with common attributes: address, size)
- Specialized types:
  - `ResidentialProperty`: Handles residential-specific attributes (bedrooms, bathrooms)
  - `CommercialProperty`: Manages commercial spaces (offices, retail suitability)

### Contracts
- `Contract` class (base class for contract features)
- Specialized contracts:
  - `RentalContract`: For property rentals
  - `SaleContract`: For property sales

### Agent System
- `IAgent` interface: Defines agent behaviors
- `Agent` class: Implements business logic for agents, manages property listings and calculates commissions

## Key Features

1. **Property Management**
   - Categorization of properties (residential/commercial)
   - Attribute tracking and validation logic

2. **Contract Handling**
   - Supports different contract types
   - Price negotiation and contract validation

3. **Agent Operations**
   - Manages listings, commissions, and portfolio tracking

## Design Patterns Used

- Interface Segregation (IAgent)
- Inheritance (Property hierarchy)
- Generics (Listing<P, C>)
- Template Method (Contract hierarchy)

## Technical Implementation

- Object-Oriented Design
- Strong type safety using generics
- Validation logic for business rules
- Clean separation of concerns

## Testing

- All features are fully tested, with a coverage of over **70%**.
- Comprehensive tests ensure the correctness of all core functionalities.
