# Rideshare Dispatch Simulator

A Java-based simulator designed to model and analyze rideshare service operations, focusing on optimizing driver-customer matching and tracking system performance metrics.

## Core Features

- **Driver Dispatch Optimization**: Efficiently assigns drivers to ride requests based on proximity and availability.
- **Real-time Ride Request Handling**: Handles incoming ride requests dynamically.
- **Multiple Ride Types Support**: Supports various ride types, including standard and priority.
- **Priority-based Queue Management**: Manages ride requests with priority to minimize wait times.
- **Performance Statistics Tracking**: Collects and reports key performance metrics like wait time and driver utilization.

## System Architecture

The system is built around key components:
- **Event System**: Event-driven design for handling ride requests and completions.
- **Driver Management**: Tracks driver status, availability, and location for optimal dispatch.
- **Queue Management**: Handles ride requests using priority queues to ensure efficient matching.
- **Statistics Collection**: Tracks key performance indicators such as average wait time and ride completion rate.

## Key Components

1. **Event System**: Custom event processing and concurrent handling.
2. **Driver Management**: Tracks location, status, and assigns rides to drivers.
3. **Queue Management**: Prioritizes ride requests based on customer need and driver availability.

## Performance Metrics

The simulator tracks key performance indicators to evaluate system efficiency:
- Average wait time
- Driver utilization rate
- Ride completion rate
- Customer satisfaction metrics

## Key Findings

From the simulation results:
- **Optimal driver/ride ratio**: 50 drivers for 100 rides
- **Average wait time**: 9.32 minutes
- **Average rides per driver**: 2.0

## Testing

The system includes comprehensive unit and scenario-based tests, ensuring the reliability and performance of the simulation under various conditions.
