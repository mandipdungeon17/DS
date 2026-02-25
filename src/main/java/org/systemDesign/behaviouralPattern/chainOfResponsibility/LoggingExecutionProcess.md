# Chain of Responsibility Pattern - Logger Implementation Explained

## 🎯 What is Chain of Responsibility?

The **Chain of Responsibility** pattern allows a request to pass through a chain of handlers. Each handler decides either to process the request or pass it to the next handler in the chain.

---

## 🏗️ Class Structure

```
┌─────────────────────────────────────────────────────────────┐
│                    Logger (Abstract Handler)                │
├─────────────────────────────────────────────────────────────┤
│  - level: int                                               │
│  - nextLogger: Logger  ←── Reference to next in chain       │
├─────────────────────────────────────────────────────────────┤
│  + setNextLogger(Logger)                                    │
│  + logMessage(level, message)  ←── Template method          │
│  # write(message)  ←── Abstract (subclasses implement)      │
└─────────────────────────────────────────────────────────────┘
              △
              │ extends
    ┌─────────┼─────────┐
    │         │         │
┌───▼───┐ ┌───▼───┐ ┌───▼───┐
│Console│ │ File  │ │ Error │
│Logger │ │Logger │ │Logger │
│level=1│ │level=3│ │level=4│
└───────┘ └───────┘ └───────┘
```

---

## 📊 Log Levels Hierarchy

```java
DEBUG   = 1   // Lowest priority (logs everything)
INFO    = 2
WARNING = 3
ERROR   = 4   // Highest priority (logs only errors)
```

**Key Rule**: A logger with level `X` will process messages with level `>= X`

| Logger        | Level | Processes DEBUG(1)? | INFO(2)? | WARNING(3)? | ERROR(4)? |
|---------------|-------|---------------------|----------|-------------|-----------|
| ConsoleLogger | 1     | ✅ Yes              | ✅ Yes   | ✅ Yes      | ✅ Yes    |
| FileLogger    | 3     | ❌ No               | ❌ No    | ✅ Yes      | ✅ Yes    |
| ErrorLogger   | 4     | ❌ No               | ❌ No    | ❌ No       | ✅ Yes    |

---

## 🔗 Chain Setup

```java
// In LoggingSystem.getChainOfLogger()

Logger errorLogger = new ErrorLogger(Logger.ERROR);      // level = 4
Logger fileLogger = new FileLogger(Logger.WARNING);      // level = 3
Logger consoleLogger = new ConsoleLogger(Logger.DEBUG);  // level = 1

// Build chain: Console → File → Error
consoleLogger.setNextLogger(fileLogger);
fileLogger.setNextLogger(errorLogger);

return consoleLogger;  // Return head of chain
```

**Visual Chain:**
```
┌───────────────┐      ┌────────────┐      ┌─────────────┐
│ ConsoleLogger │ ───► │ FileLogger │ ───► │ ErrorLogger │ ───► null
│   level = 1   │      │  level = 3 │      │  level = 4  │
└───────────────┘      └────────────┘      └─────────────┘
      HEAD                                       TAIL
```

---

## 🔄 The Core Logic - `logMessage()` Method

```java
public void logMessage(int level, String message) {
    // Step 1: Check if THIS handler should process the message
    if (this.level <= level) {
        write(message);  // Process it!
    }
    
    // Step 2: ALWAYS pass to next handler (if exists)
    if (nextLogger != null) {
        nextLogger.logMessage(level, message);
    }
}
```

**Important**: This implementation processes AND passes (not "process OR pass"). Every handler in the chain gets a chance to handle the message.

---

## 🚀 Step-by-Step Execution

### Example 1: `logMessage(DEBUG, "Debug information")` - Level 1

```
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 1: ConsoleLogger (level=1) receives message (level=1)          │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(1) <= messageLevel(1) ? ✅ YES                    │
│ Action: write() → prints "[CONSOLE] Debug information"              │
│ Next: Pass to FileLogger                                            │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 2: FileLogger (level=3) receives message (level=1)             │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(3) <= messageLevel(1) ? ❌ NO (3 > 1)             │
│ Action: SKIP write()                                                │
│ Next: Pass to ErrorLogger                                           │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 3: ErrorLogger (level=4) receives message (level=1)            │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(4) <= messageLevel(1) ? ❌ NO (4 > 1)             │
│ Action: SKIP write()                                                │
│ Next: null (end of chain)                                           │
└─────────────────────────────────────────────────────────────────────┘

OUTPUT:
[CONSOLE] Debug information
```

---

### Example 2: `logMessage(WARNING, "Low disk space")` - Level 3

```
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 1: ConsoleLogger (level=1) receives message (level=3)          │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(1) <= messageLevel(3) ? ✅ YES                    │
│ Action: write() → prints "[CONSOLE] Warning: Low disk space"        │
│ Next: Pass to FileLogger                                            │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 2: FileLogger (level=3) receives message (level=3)             │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(3) <= messageLevel(3) ? ✅ YES                    │
│ Action: write() → prints "[FILE] Writing to file: Warning:..."      │
│ Next: Pass to ErrorLogger                                           │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 3: ErrorLogger (level=4) receives message (level=3)            │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(4) <= messageLevel(3) ? ❌ NO (4 > 3)             │
│ Action: SKIP write()                                                │
│ Next: null (end of chain)                                           │
└─────────────────────────────────────────────────────────────────────┘

OUTPUT:
[CONSOLE] Warning: Low disk space
[FILE] Writing to file: Warning: Low disk space
```

---

### Example 3: `logMessage(ERROR, "System crash")` - Level 4

```
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 1: ConsoleLogger (level=1) receives message (level=4)          │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(1) <= messageLevel(4) ? ✅ YES                    │
│ Action: write() → prints "[CONSOLE] Error: System crash"            │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 2: FileLogger (level=3) receives message (level=4)             │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(3) <= messageLevel(4) ? ✅ YES                    │
│ Action: write() → prints "[FILE] Writing to file: Error:..."        │
└─────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│ STEP 3: ErrorLogger (level=4) receives message (level=4)            │
├─────────────────────────────────────────────────────────────────────┤
│ Check: this.level(4) <= messageLevel(4) ? ✅ YES                    │
│ Action: write() → prints "[ERROR LOG] ⚠️  Error: System crash"      │
└─────────────────────────────────────────────────────────────────────┘

OUTPUT:
[CONSOLE] Error: System crash
[FILE] Writing to file: Error: System crash
[ERROR LOG] ⚠️  Error: System crash
```

---

## 📋 Complete Program Output

```
=== DEBUG Message ===
[CONSOLE] Debug information

=== INFO Message ===
[CONSOLE] Information message

=== WARNING Message ===
[CONSOLE] Warning: Low disk space
[FILE] Writing to file: Warning: Low disk space

=== ERROR Message ===
[CONSOLE] Error: System crash
[FILE] Writing to file: Error: System crash
[ERROR LOG] ⚠️  Error: System crash
```

---

## 🧠 Summary Table

| Message Level | ConsoleLogger (1) | FileLogger (3) | ErrorLogger (4) |
|---------------|-------------------|----------------|-----------------|
| DEBUG (1)     | ✅ Prints         | ❌ Skips       | ❌ Skips        |
| INFO (2)      | ✅ Prints         | ❌ Skips       | ❌ Skips        |
| WARNING (3)   | ✅ Prints         | ✅ Prints      | ❌ Skips        |
| ERROR (4)     | ✅ Prints         | ✅ Prints      | ✅ Prints       |

---

## 🎓 Key Takeaways

1. **Chain Building**: Handlers are linked via `setNextLogger()`
2. **Flexible Processing**: Each handler decides independently whether to process
3. **Pass-Through**: Message flows through ALL handlers in the chain
4. **Level Filtering**: Lower level handlers (like Console) handle more messages
5. **Decoupling**: Client only knows about the first handler (head of chain)
6. **Easy Extension**: Add new loggers without modifying existing code

---

## 💡 Real-World Use Cases

- **Logging systems** (like this example)
- **Event handling** in GUI frameworks
- **Middleware** in web frameworks (Express.js, Spring)
- **Exception handling** chains
- **Request validation** pipelines
- **Authentication/Authorization** filters
