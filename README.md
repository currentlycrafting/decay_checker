# Circular Food Queue Management System

Welcome to the Circular Food Queue Management System! This project is a simulation of a food storage management system that utilizes circular queues to handle food items efficiently. The system is designed for managing food expiration, storage, and processing across multiple queues with a user-friendly interface.

---

## Features

- **Circular Queue Implementation**: Efficient storage and retrieval of food items with FIFO (First-In-First-Out) behavior.
- **Multi-Queue Storage**: Manage multiple circular queues for better organization.
- **Food Expiration Check**: Automatically identify and remove expired food items.
- **User Interface**: Interactive console-based UI for managing food items and queues.
- **Simulation Mode**: Automatically simulates food item processing and expiration management over time.
- **Expandable Design**: Modular architecture for easy enhancements.

---

## Project Structure

- **CircularFoodQueue.java**: Core implementation of the circular queue for managing food items.
- **FoodStorage.java**: Handles multiple queues, allowing efficient food item distribution and transfer between queues.
- **UserInterface.java**: Interactive UI for adding, removing, and viewing food items and queues.
- **Simulation.java**: Simulates food queue operations over time, including random addition and removal of food items.
- **FoodItem.java**: Represents a food item with properties like name, storage date, and expiration date.

---

## Key Concepts

- **Circular Queue**: Maintains a continuous array-based queue, cycling through indices to minimize wasted space.
- **Modular Design**: Separate classes for distinct responsibilities (e.g., storage, UI, and simulation).
- **Real-Time Expiration Management**: Ensures expired food items are promptly identified and removed.

---
