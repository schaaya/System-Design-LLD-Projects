# Vending Machine Design

## High-level Design

### Main interface, classes, and Exceptions
- **VendingMachine**: An interface defining the public API of a vending machine.
- **VendingMachineImpl**: A general-purpose implementation of the `VendingMachine` interface.
- **Inventory**: A type-safe inventory for holding objects, acting as an adapter or wrapper over `java.util.Map`.
- **Item**: A type-safe Enum representing items supported by vending machines.
- **Coin**: A type-safe Enum representing coins acceptable by a vending machine.
- **Bucket**: A Holder class for holding two types together.
- **SoldOutException**: Thrown when the user selects a product that is sold out.
- **NotSufficientChangeException**: Thrown when the vending machine doesn't have enough change for the current transaction.
- **NotFullPaidException**: Thrown when the user tries to collect an item without paying the full amount.

### Data structures used
- Map data structure is used to implement cash and item inventories.
- List is used for returning change as it can contain duplicates (multiple coins of the same denomination).

### Motivation behind design decisions
- Factory design pattern for encapsulating the creation logic of VendingMachine.
- Adapter pattern for creating Inventory by wrapping `java.util.Map`.
- `java.lang.Enum` for representing Item and Coins due to compile-time safety, no need for manual validation, and reusability.
- `long` is used to represent price and totalSales, considering the finite capacity of coin inventory.

## Low-level Design

### Methods
- The `getChange()` method uses a greedy algorithm to determine if there are sufficient coins to support a given amount.

### Initialization
- When the Vending Machine is created, it's initialized with default cash amount and item inventory, both set to a quantity of 5 for each coin and item.

### Testing Strategy
- Three primary test cases: `testWithExactPrice()`, `testWithMorePrice()`, and `testRefund()` to cover general use cases.
- Negative test cases to handle exceptions like `SoldOutException`, `NotSufficientChangeException`, and `NotFullPaidException`.

### Benefits
- Abstraction to create reusable, small classes for improved readability and testability.
- Encapsulation of Items and Coins in their respective classes for easy addition of new items and coins.
- Inventory is a general-purpose class that can be used elsewhere, encapsulating all inventory operations.

### Assumptions
- Vending Machine is single-threaded, with only one user operating at a time.
- A call to `reset()` will reset the item and balance, making them zero.

## UML Diagram

Here is a simplified UML diagram for the Vending Machine solution:

![UML Diagram](https://4.bp.blogspot.com/-Sao6FMujcmo/V1bTFdBywpI/AAAAAAAAGH8/ia5lyVjKd-YR-RGehZZDg0XkUOSGo5pVQCLcB/w553-h387/Vending%2BMachine%2BUML%2BDiagram%2Bin%2BJava.png)

This diagram illustrates that `VendingMachineImpl` extends or implements the `VendingMachine` interface and is associated with the `Item` and `Inventory` classes.

