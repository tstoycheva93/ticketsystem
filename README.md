# Ticket System

A professionally architected event ticket management system demonstrating advanced Java design patterns and SOLID principles. This application showcases best practices in software architecture through command pattern implementation, modular design, and adherence to SOLID design principles.

## Architecture & Design Patterns

This project is built on industry-standard design patterns and SOLID principles to create maintainable, scalable, and testable code.

### Design Patterns Implemented

#### 1. **Command Pattern**
The cornerstone of the application's architecture. All user interactions are processed through the Command pattern via the `Invoker` class located in the `menu.invoker` package.

```
User Input → Menu System → Invoker → Command → Receiver (Operations)
```

**Benefits:**
- Decouples command senders from receivers
- Enables command queuing, logging, and undo/redo functionality
- Provides flexibility to add new commands without modifying existing code

**Implementation:**
- `menu.invoker.Invoker`: Central command processor
- Each ticket operation encapsulated as a separate command
- Operations executed independently from UI layer

#### 2. **Single Responsibility Principle (SRP)**
Each class has a single, well-defined responsibility:

- **`entities/`**: Data models only (Event, Ticket, Venue classes)
  - Focused solely on representing domain objects
  - No business logic or UI concerns
  
- **`operations/`**: Business logic implementation
  - All ticket operations isolated here
  - Operations independent of data representation
  
- **`menu/`**: User interface layer
  - Menu presentation and user interaction
  - Invoker pattern for command dispatch
  
- **`exceptions/`**: Custom exception handling
  - Domain-specific exceptions
  - Clear error semantics
  
- **`utils/`**: Cross-cutting utilities
  - Helper functions and data utilities
  - Reusable components

**Example:**
```java
// SRP: Main class does ONE thing - bootstrap the application
public class Main {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.invoke();
    }
}
```

#### 3. **Open/Closed Principle (OCP)**
The application is open for extension but closed for modification:

- **Extensible Command System**: New ticket operations can be added without modifying existing command infrastructure
- **Plugin Architecture**: New commands extend abstract command base or implement command interface
- **Modular Packages**: New features can be added by creating new packages without touching core code

**Example Structure:**
```
New Operation Request
        ↓
Extend Command Base/Interface
        ↓
Implement in operations/ package
        ↓
Register with Invoker
        ↓
No modification to existing commands needed
```

#### 4. **Liskov Substitution Principle (LSP)**
Subtypes are substitutable for their base types:

- **Operation Hierarchy**: All ticket operations implement a common interface/extend common base class
- **Polymorphic Command Processing**: Invoker treats all commands uniformly
- **Predictable Behavior**: Each command implementation honors the contract of its parent

**Implementation Benefit:**
```java
// Invoker can work with any Command implementation
// without knowing specific details
for (Command cmd : commandList) {
    cmd.execute();  // LSP: Guaranteed behavior
}
```

#### 5. **Interface Segregation Principle (ISP)**
Clients depend on minimal, focused interfaces:

- **Targeted Operations**: Operations interface only exposes relevant methods
- **Menu Interface**: Menu system has only necessary methods
- **Entity Interfaces**: Data models expose only relevant properties

**Benefits:**
- Reduced coupling between components
- Easier to mock for testing
- Clear contracts between layers

#### 6. **Dependency Inversion Principle (DIP)**
Depend on abstractions, not concrete implementations:

- **Menu-to-Invoker**: Menu depends on Invoker abstraction
- **Invoker-to-Operations**: Commands executed through abstract interfaces
- **Data Persistence**: Abstracted file operations in utils layer

**Architecture:**
```
High-level modules (Main, Menu)
        ↓
Depend on abstractions (Command Interface, Invoker)
        ↓
Low-level modules (Operations, Entities)
```

### Supporting Design Patterns

#### **Factory Pattern**
- Event and Ticket creation encapsulated in factory methods
- Centralized object instantiation
- Consistent object initialization

#### **Strategy Pattern**
- Different ticket operation strategies (reserve, cancel, view)
- Runtime algorithm selection
- Easy to switch strategies without code changes

#### **Template Method Pattern**
- Common command execution workflow
- Specific implementations override template steps
- Promotes code reuse

## Tech Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 8+ |
| **Build System** | Maven/IntelliJ IDEA |
| **Architecture Pattern** | Command Pattern |
| **Design Principles** | SOLID |
| **Documentation** | Javadoc |
| **Storage** | File-based (data.txt) |

## Key Architectural Decisions

### 1. Command Pattern for Operations
**Why:** Encapsulates requests as objects, enabling queuing, logging, and undo/redo

### 2. Layered Architecture
**Why:** Clear separation of concerns makes code maintainable and testable
- Presentation Layer (menu)
- Business Logic Layer (operations)
- Data Layer (entities)
- Utility Layer (utils)

### 3. Exception Hierarchy
**Why:** Domain-specific exceptions provide semantic clarity and enable precise error handling

### 4. File-Based Persistence
**Why:** Simple implementation focusing on design patterns rather than infrastructure

## Usage

### Running the Application

```bash
# Compile
javac -d bin src/**/*.java

# Run
java -cp bin Main
```

### Interacting with the System

The application presents an interactive menu where users can:

1. **Create Tickets**: Command pattern routes to CreateTicketCommand
2. **Cancel Tickets**: Routed through Command pattern to CancelTicketCommand
3. **View Tickets**: Query operations handled via ViewTicketCommand
4. **Manage Venues**: Venue operations as separate Command implementations

### Example Command Flow

```
User selects "Create Ticket"
    ↓
Menu captures input (ISP: Menu only knows its responsibility)
    ↓
Invoker.execute(CreateTicketCommand) (Command Pattern)
    ↓
CreateTicketCommand invokes TicketOperation (DIP: depends on abstraction)
    ↓
TicketOperation.createTicket() uses Entity classes (SRP: entities separate)
    ↓
Exception handling through custom exceptions (SRP: dedicated layer)
    ↓
Result returned to user
```

## Extensibility Examples

### Adding a New Ticket Operation

```java
// 1. Create new command (OCP: Open for extension)
public class ExportTicketsCommand implements Command {
    @Override
    public void execute() {
        // Implementation
    }
}

// 2. Register with Invoker (no modification to existing code)
invoker.registerCommand("export", new ExportTicketsCommand());

// 3. Menu automatically supports new command
// Invoker handles polymorphic execution (LSP)
```

### Adding a New Exception Type

```java
// Create domain-specific exception (SRP)
public class InsufficientFundsException extends TicketException {
    // Specific error semantics
}

// Used in operations with clear intent (ISP: specific contracts)
if (customerBalance < ticketPrice) {
    throw new InsufficientFundsException("...");
}
```

## Documentation

- **Javadoc**: Generated API documentation in `javadoc/` directory
- **Architecture PDF**: Detailed design documentation in `documentation.pdf`
- **Source Code**: Well-commented with JavaDoc annotations explaining design patterns


