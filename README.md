# Multithreading Course
The project demonstrates specific concurrency concepts and solutions

## Features
### Module 2
**1. Counter Suite** 

An interface and 5 counter implementations tested for data accuracy and execution speed under heavy workload.
* CounterLoad - a class that runs a stress test for each implementation. 
All threads run at the same time using a `CountDownLatch` barrier.

5 implementations of a counter:
* UnsafeCounter - not thread-safe 
* SynchronizedCounter - safe. Protected by the `synchronized` keyword
* ReentrantLockCounter - safe. Protected by explicit `ReentrantLock`
* AtomicCounter - safe and fast. Uses lock-free `AtomicLong` operations
* LongAdderCounter - the fastest for many threads. Uses `LongAdder` class

