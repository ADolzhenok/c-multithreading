# Multithreading Course
The project demonstrates specific concurrency concepts and solutions

## Features
### Module 2 - JMM
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

**2. Deadlock Simulator**

An interface and 3 implementations demonstrate deadlock and 2 solutions of it:
* WrongLockableProcessor - creates a `deadlock` by acquiring resource monitors
* OrderedLockableResourceProcessor - solution 1: using a `Resourсe Ordering` strategy.
* TryLockLockableResourceProcessor - solution 2: using `ReentrantLock.tryLock()` with backoff

### Module 3 - Asynchronous & Virtual Threads
**Story Pipeline**

An asynchronous story processing pipeline built with `CompletableFuture`:
* Compares the execution speed of platform threads and virtual threads under combined I/O and CPU workloads.
* Demonstrates pool isolation using a `FixedThreadPool` and the `ForkJoinPool.commonPool()`

The pipeline runs 4 sequential steps for each task:

`Fetch Draft (I/O)` -> `Edit Text (CPU)` -> `Finalize Story (I/O)` -> `Save to Storage (I/O)`

StoryStorage - thread-safe storage backed by `ConcurrentHashMap`.
