
## Pattern Name
**Decorator**

## Pattern Category
Structural

---

## Description
- The **Decorator Pattern** allows adding responsibilities or behaviors to an object dynamically without modifying its original class.  
- Each decorator wraps the original object and enhances its behavior.  
- Useful for **flexible, runtime customization**.  

**Key Idea:**  
*"Add new behavior to an object dynamically, instead of creating a subclass for every combination."*

---

## The Problem
- Users want multiple text formats like **Bold, Italic, Underline** in a text editor.  
- Creating a separate class for every combination leads to **class explosion**.  
- Base text class must remain unchanged for maintainability.

---

## The Solution
- Define a **Text interface** for all text objects.  
- Create **ConcreteText** for normal text.  
- Implement **decorator classes** (Bold, Italic, Underline) that wrap a Text object.  
- Decorators add formatting behavior dynamically at runtime.

---

## Code Flow

**What This Example Does:**  
- Demonstrates applying multiple text formats (Bold, Italic, Underline) dynamically using decorators.  
- Shows how the base text class remains unchanged.

**Step-by-Step Code Flow:**

1. **Text Interface (Text.java)**  
   - Declares `render()` method to return text content.  

2. **ConcreteText Class (PlainText.java)**  
   - Implements `Text`.  
   - Holds the original text content.  

3. **Abstract Decorator (TextDecorator.java)**  
   - Implements `Text`.  
   - Holds a reference to a `Text` object.  
   - Delegates `render()` to the wrapped object.  

4. **Concrete Decorators (BoldDecorator.java, ItalicDecorator.java, UnderlineDecorator.java)**  
   - Extend `TextDecorator`.  
   - Add their own formatting to `render()`.  

5. **Main Program (TextEditor.java)**  
   - Create a `PlainText` object.  
   - Wrap it with multiple decorators dynamically.  
   - Call `render()` → displays all applied formats.

---

## Real-World Example: Text Editor

**Without Decorator Pattern:**  
- Need separate classes for every combination: BoldText, ItalicText, BoldItalicText, etc.  
- Code becomes hard to maintain.

**With Decorator Pattern:**  
- Base text stays the same.  
- Formatting is added dynamically via decorators.  
- Adding new formatting (Highlight, Color) only requires a new decorator.

---

## Benefits
- **Flexible & Dynamic:** Add behavior at runtime.  
- **Open/Closed Principle:** Base class remains unchanged.  
- **Avoids Class Explosion:** No need for a class per combination.  
- **Reusable Decorators:** Can mix and match easily.

---

## Use Cases
- Text formatting in editors (Bold, Italic, Underline, Highlight).  
- GUI components with borders, scrollbars, or colors.  
- Stream processing (adding encryption, compression dynamically).  
- Notification systems (adding logging, filters, or alerts).  

---

## Drawbacks
- **Complexity:** Multiple layers of decorators can be hard to track.  
- **Debugging Difficulty:** Nested decorators may make debugging tricky.  
- **Overhead:** Slight runtime overhead due to wrapping.

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
--- Decorator Pattern ---

Plain Text: Hello World
Bold: **Hello World**
Bold + Italic: ***Hello World***
Bold + Italic + Underline: _***Hello World***_
```
---
## Key Takeaways

- Decorators let you dynamically add features to objects.

- Avoids creating multiple subclasses for every feature combination.

- Supports clean, maintainable, and extendable code.

- Each decorator wraps the original object without modifying it.