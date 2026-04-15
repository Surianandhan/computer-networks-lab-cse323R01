# Computer Networks Lab

This repository contains implementations and simulations of fundamental networking concepts including cryptography, error detection, routing algorithms, and network protocols using Java, and NS2.

## Topics Covered

### Cryptography
- Secure File Transfer using Caesar Cipher

### Error Detection & Correction
- Cyclic Redundancy Check (CRC)
- Hamming Code (7,4)

### Network Protocols
- Sliding Window Protocols:
  - Stop-and-Wait
  - Go-Back-N
  - Selective Repeat
- DHCP (Dynamic Host Configuration Protocol)
- DNS Lookup (Client-Server)

### Network Simulations (NS2)
- Wired Network Simulation (TCP & UDP)
- Wireless Ad-hoc Network (AODV Routing)
- Distance Vector Routing (Bellman-Ford)
- Link State Routing (Dijkstra’s Algorithm)
- Congestion Control:
  - Leaky Bucket
  - Token Bucket

### Network Infrastructure Study
- Study of SASTRA University Network Architecture
- High-speed LAN, HPC, Security, Cloud Integration

---

## Key Implementations

### Caesar Cipher
- Basic encryption and decryption
- File transfer over TCP using encrypted data

### CRC
- Polynomial division-based error detection
- Sender and receiver simulation

### Hamming Code
- Single-bit error detection and correction
- Syndrome calculation and correction logic

### Sliding Window Protocols
- Performance comparison using NS2
- Throughput, delay, packet loss analysis

### DHCP
- DORA process simulation using UDP sockets
- Dynamic IP allocation

### DNS
- Domain-to-IP resolution using Java sockets
- Recursive server implementation

### Routing Protocols
- Distance Vector (Bellman-Ford)
- Link State (Dijkstra)
- Performance comparison and convergence analysis

### Congestion Control
- Leaky Bucket vs Token Bucket
- Traffic shaping and burst handling

---

## Technologies Used
- Java
- NS2 (Network Simulator)
- AWK (Performance Analysis)
- Socket Programming (TCP/UDP)

---

## How to Run

### Java Programs
```
javac filename.java
java filename
```

### NS2 Simulations
```
ns filename.tcl
nam filename.nam
awk -f analysis.awk filename.tr
```

---

## Key Learnings
- Practical understanding of encryption and secure communication
- Implementation of error detection and correction mechanisms
- Deep insight into network protocols and routing algorithms
- Performance evaluation using simulation tools
- Real-world networking concepts applied through NS2

---

## Author
Surianandhan Sridhar
