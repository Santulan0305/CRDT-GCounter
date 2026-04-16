# CRDT G-Counter Simulation (Java GUI)

## 📌 Project Overview

This project demonstrates the concept of **Conflict-Free Replicated Data Types (CRDT)** using a **G-Counter algorithm**. It shows how distributed systems allow multiple nodes to update data independently and still achieve **eventual consistency** without conflicts.

The project is implemented in Java using Swing and provides a graphical interface to visualize how nodes interact and merge their states.

---

## 🎯 Features

* Two-node distributed system simulation
* Independent increment operations
* Animated merge process
* Real-time state updates
* Simple and interactive GUI

---

## ⚙️ How It Works

1. Each node maintains its own counter.
2. Nodes increment their values independently.
3. During merge, each node takes the **maximum value** from others.
4. Final value is calculated as the sum of all counters.
5. Both nodes reach the same state after merging.

---

## 🛠️ Technologies Used

* Java
* Swing (GUI)

---

## 📸 Output Screenshot





## 💡 Key Concepts

* CRDT (Conflict-Free Replicated Data Types)
* Eventual Consistency
* Distributed Systems
* Conflict-Free Merging

---

## 🚀 Conclusion

This project successfully demonstrates how CRDT ensures consistency in distributed systems without requiring synchronization or locking. It proves that even with independent updates, all nodes eventually converge to the same state.

---
