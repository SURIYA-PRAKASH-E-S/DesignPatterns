# Console-Based Real-Time Chat System

##  Topic
A simple **console-based real-time chat system** in Java, built with **OOP principles and design patterns** (Observer, Singleton, Adapter).

---
## Problem Statement
Build a console-based real-time chat application where users can create or join chat rooms using a unique ID. Users must be able to send/receive messages instantly, view active participants, and optionally use private messaging and message history

##  Description
This project demonstrates how to build a **multi-user chat system** in Java using sockets.  
It allows users to join or create chat rooms, send messages, view active users, and access chat history.
It focuses on logic, OOP, and design quality rather than UI, making it a practical example of applying software engineering principles to real-time communication.  

*Focus areas:*
- **Observer Pattern**: Notify all clients in a room of new events.
- **Singleton Pattern**: Manage chat rooms globally.
- **Adapter Pattern**: Abstract communication layer for extensibility.
- **OOP & SOLID**: Encapsulation, modularity, extensibility.

---

##  Functionality
1. Users can **create/join chat rooms** with unique IDs.
2. **Send and receive** real-time public messages.
3. Display list of **active users**.
4. **Private messaging** between users.(`/pm`)
5. **Message history** replay on joining a room.
6. **Private message history** available (`/pmh`).

---

##  Code Flow & Components

1. **ChatServer**  
   - Accepts client connections.  
   - Spawns `ClientHandler` threads.

2. **ClientHandler** (Adapter + Observer)  
   - Parses client commands.  
   - Forwards messages to `ChatRoom`.  
   - Adapts socket I/O via `MessageSender`.

3. **ChatRoom** (Observer Subject)  
   - Holds active users.  
   - Broadcasts system/public/private messages.  
   - Stores public and private message history.

4. **ChatRoomManager** (Singleton)  
   - Ensures global state of rooms.  
   - Thread-safe with eager initialization.

5. **ChatClient**  
   - Console-based client interface.  
   - Sends commands, displays incoming messages asynchronously.

6. **Message**  
   - Immutable model class.  
   - Formats messages consistently.

---

##  How to Execute Code
1. Compile all `.java` files:
   ```bash
   javac *.java
   ```
2. Start server (default port 9090):
  ```bash
  java ChatServer 9090
  ```
3. Start clients (in separate terminals):
  ```bash
  java ChatClient localhost 9090
  ```
4. Use commands:

- `/jn` <roomId> <username> → join room

- `/msg` <text> → send public message

- `/pm` <user> <text> → send private message

- `/u` → list active users

- `/h` → view public history

- `/pmh` <user> → view private history with user

- `/q` → quit

---
## 🖥️ Expected Output

*Example session with 3 clients:* (in Separate terminals)
`client-1 (Alice)`
```sh
WELCOME SimpleChatServer.Please /jn <roomId> <username>
Connected to chat server localhost:9090
Type commands: /jn /msg /pm /u /h /pmh /q
/jn Room143 Alice
[SYSTEM] Alice joined the room
[USERS] Alice
JOINED Room143
=== History start ===
[2025-09-30T22:12:34.4159297] SYSTEM: Alice joined the room
=== History end ===
[SYSTEM] Bob joined the room
[USERS] Alice,Bob
Hello,Everyone!! 
[2025-09-30T22:14:03.6064206] Alice: Hello,Everyone!!
[SYSTEM] Charlie joined the room
[USERS] Alice,Bob,Charlie
[2025-09-30T22:16:49.024142] Charlie: Hi guys
/pm Charlie How's it going?
[2025-09-30T22:17:52.2639271] (private) Alice -> Charlie: How's it going?
[2025-09-30T22:18:36.4118809] (private) Charlie -> Alice: Fine bro
Good bye guys
[2025-09-30T22:19:38.2106897] Alice: Good bye guys
/pmh Charlie
=== Private History with Charlie start ===
[2025-09-30T22:17:52.2639271] (private) Alice -> Charlie: How's it going?
[2025-09-30T22:18:36.4118809] (private) Charlie -> Alice: Fine bro
=== Private History end ===
[SYSTEM] Bob left the room
[USERS] Alice,Charlie
Connection closed.
```
`client-2 (Bob)`
```sh
WELCOME SimpleChatServer.Please /jn <roomId> <username>
Connected to chat server localhost:9090
Type commands: /jn /msg /pm /u /h /pmh /q
/jn Room143 Bob
[SYSTEM] Bob joined the room
[USERS] Alice,Bob
JOINED Room143
=== History start ===
[2025-09-30T22:12:34.4159297] SYSTEM: Alice joined the room
[2025-09-30T22:12:52.9457592] SYSTEM: Bob joined the room
=== History end ===
[2025-09-30T22:14:03.6064206] Alice: Hello,Everyone!!
[SYSTEM] Charlie joined the room
[USERS] Alice,Bob,Charlie
[2025-09-30T22:16:49.024142] Charlie: Hi guys
/pmh Alice
[SYSTEM] No private history with Alice
[2025-09-30T22:19:38.2106897] Alice: Good bye guys
/q
Connection closed.
```
`client-3 (Charlie)`
```sh
WELCOME SimpleChatServer.Please /jn <roomId> <username>
Connected to chat server localhost:9090
Type commands: /jn /msg /pm /u /h /pmh /q
/jn Room143 Charlie
[SYSTEM] Charlie joined the room
[USERS] Alice,Bob,Charlie
JOINED Room143
=== History start ===
[2025-09-30T22:12:34.4159297] SYSTEM: Alice joined the room
[2025-09-30T22:12:52.9457592] SYSTEM: Bob joined the room
[2025-09-30T22:14:03.6064206] Alice: Hello,Everyone!!
[2025-09-30T22:16:25.4020573] SYSTEM: Charlie joined the room
=== History end ===
Hi guys
[2025-09-30T22:16:49.024142] Charlie: Hi guys
[2025-09-30T22:17:52.2639271] (private) Alice -> Charlie: How's it going?
/pm Alice Fine bro
[2025-09-30T22:18:36.4118809] (private) Charlie -> Alice: Fine bro
[2025-09-30T22:19:38.2106897] Alice: Good bye guys
[SYSTEM] Bob left the room
[USERS] Alice,Charlie
[SYSTEM] Alice left the room
[USERS] Charlie
Connection closed.
```
`ChatServer (localhost - 9090)`

```sh
Sept 30, 2025 10:12:11 PM ChatServer start
INFO: ChatServer started on port 9090
Sept 30, 2025 10:12:17 PM ChatServer start
INFO: Accepted new client connection: /127.0.0.1:57086
Sept 30, 2025 10:12:17 PM ClientHandler run
INFO: Client connected: /127.0.0.1:57086
Sept 30, 2025 10:12:34 PM ChatRoom addUser
INFO: User 'Alice' joined ChatRoom Room143
Sept 30, 2025 10:12:34 PM ClientHandler handleJoin
INFO: User 'Alice' joined room: Room143
Sept 30, 2025 10:12:42 PM ChatServer start
INFO: Accepted new client connection: /127.0.0.1:57087
 . . . . .
 ```
 ---

## Benefits

- Demonstrates design patterns in practice.

- Modular, extensible codebase.

- Real-time messaging with multiple clients.

- Clean, console-based implementation.

---

## Key Takeaways

- Observer pattern effectively models chat notifications.

- Singleton ensures consistent room management.

- Adapter provides protocol flexibility.

- SOLID OOP design results in clean, extensible, testable code.
