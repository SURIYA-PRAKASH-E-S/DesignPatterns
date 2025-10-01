
## Pattern Name
**Adapter**

## Pattern Category
Structural

---

## Description
- The **Adapter Pattern** allows incompatible interfaces to work together.  
- Acts as a bridge between a client and an existing class with a different interface.  
- Useful for integrating **third-party or legacy code** without modifying it.

**Key Idea:**  
*"Convert an interface of a class into another interface clients expect."*

---

## The Problem
- Media player app supports standard formats via **MediaPlayer** interface.  
- Some formats (like **VLC**) have incompatible interfaces.  
- Without adaptation, the app cannot play all formats seamlessly.

---

## The Solution
- Define a **MediaPlayer interface** for standard playback.  
- Implement **Concrete Players** (MP3Player, MP4Player) that follow the interface.  
- Create an **Adapter class** (VLCAdapter) that converts VLC’s interface into **MediaPlayer** interface.  
- Client uses the same interface for all formats.

---

## Code Flow

**What This Example Does:**  
- Demonstrates playing multiple media formats (MP3, MP4, VLC) using a standard interface.  
- Uses an **adapter** to make incompatible formats compatible.  

**Step-by-Step Code Flow:**

1. **MediaPlayer Interface (MediaPlayer.java)**  
   - Method: `play(String fileName)` → standard playback method.  

2. **Concrete Media Players (MP3Player.java, MP4Player.java)**  
   - Implement `MediaPlayer`.  
   - Play their respective formats directly.  

3. **AdvancedMediaPlayer Interface (AdvancedMediaPlayer.java)**  
   - Different interface for formats like VLC.  
   - Method: `playVLC(String fileName)`.  

4. **Concrete Adaptee (VLCPlayer.java)**  
   - Implements `AdvancedMediaPlayer`.  

5. **Adapter Class (VLCAdapter.java)**  
   - Implements `MediaPlayer`.  
   - Wraps `VLCPlayer` and converts `play()` calls to `playVLC()`.  

6. **Client Program (AudioPlayer.java)**  
   - Uses `MediaPlayer` interface.  
   - Can play MP3, MP4 directly, and VLC via adapter.  

---

## Real-World Example: Media Player App

**Without Adapter Pattern:**  
- Cannot play incompatible formats like VLC.  
- Would require rewriting existing players or multiple conditional checks.  

**With Adapter Pattern:**  
- Existing media players remain unchanged.  
- Adapter makes VLC compatible.  
- Supports multiple media formats seamlessly.

---

## Benefits
- **Reusability:** Integrate legacy or third-party code.  
- **Flexibility:** Add support for new formats without modifying existing code.  
- **Open/Closed Principle:** Existing classes remain unchanged.  
- **Simplified Client Code:** Client interacts with a standard interface.

---

## Use Cases
- Media player apps (MP3, MP4, VLC).  
- Payment gateways with different APIs.  
- Legacy system integration.  
- Third-party library adaptation.  

---

## Drawbacks
- **Extra Layer:** Adds additional classes (adapters).  
- **Overhead:** Slight performance cost due to delegation.  
- **Complexity:** Can become confusing if multiple adapters are nested.

---

## How to Execute the Code
1. Navigate to the builder folder in terminal:
```bash
   cd structural/decorator/
```
2. Compile:
```bash
javac *.java
```
3. Run:
```bash
java Main
```
---
## Expected Output
```sh
--- Adapter Pattern ---

Playing MP3 file: song.mp3
Playing MP4 file: movie.mp4
Playing VLC file: video.vlc
```
---
## Key Takeaways

- Adapter lets incompatible interfaces work together.

- Supports reuse of existing code without modification.

- Client interacts with a single standard interface.

- New formats can be integrated easily via adapters.

---
## Analogy

*Think of it like power plug converter:*

- Device = Client needing 220V

- Power Source = Existing 110V socket

- Adapter = Converts voltage to compatible format

`Result:` Device works correctly without changing its internal design.