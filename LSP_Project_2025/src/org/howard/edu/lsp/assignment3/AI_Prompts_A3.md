# AI Prompts – Assignment 3

As required for Assignment 3, here are examples of how I used a generative AI assistant (ChatGPT) while working on this project. I’ve included actual prompts, excerpts of responses, and short notes on how I used or adapted them.

---

### Prompt 1
**My Prompt:**  
*"How do I redesign my ETL pipeline from Assignment 2 to be more object-oriented in Java?"*

**AI Response (excerpt):**  
*"Break your ETL pipeline into separate classes with clear responsibilities. For example: `CsvExtractor`, `ProductTransformer`, `CsvLoader`, and model classes like `ProductRaw` and `ProductTransformed`."*

**How I Used It:**  
I followed this suggestion to split my single class into multiple files. I kept the same extract → transform → load order but gave each step its own class, which made the code cleaner and easier to test.

---

### Prompt 2
**My Prompt:**  
*"Can you suggest how to represent PriceRange in an object-oriented way?"*

**AI Response (excerpt):**  
*"You could use an enum (e.g., `PriceRange.LOW`, `PriceRange.MEDIUM`, `PriceRange.HIGH`, `PriceRange.PREMIUM`) instead of hardcoding strings."*

**How I Used It:**  
I created a `PriceRange` enum to represent the categories. This eliminated repeated string literals in my code and made the logic more readable.

---

### Prompt 3
**My Prompt:**  
*"Write a reflection comparing Assignment 2 and Assignment 3 in a way that sounds like a college student wrote it."*

**AI Response (excerpt):**  
*"Assignment 2 had everything in one class. Assignment 3 is broken into multiple classes with clear responsibilities. It’s easier to read and more object-oriented."*

**How I Used It:**  
I adapted the reflection response, but rewrote parts to add my own voice and details about testing, encapsulation, and what I personally found different between the two assignments.

---

### Prompt 4
**My Prompt:**  
*"How do I create a Markdown file for my reflection in Eclipse?"*

**AI Response (excerpt):**  
*"Right-click the folder → New → File → name it `REFLECTION_A3.md`. Paste your text into it. Git will treat `.md` like any other text file."*

**How I Used It:**  
This helped me figure out how to actually make the required documentation files and keep them in the right folder structure.

---

### Prompt 5
**My Prompt:**  
*"Generate Javadoc comments for my classes in Assignment 3."*

**AI Response (excerpt):**  
*"Add comments like `/** Extracts rows from the CSV file and returns them as ProductRaw objects */` above your methods."*

**How I Used It:**  
I used this as a guide to create proper Javadocs for each public class and method, but I made sure to edit them so they matched exactly what my code was doing.

---

## Summary
Overall, I used AI as a helper for brainstorming design ideas, improving documentation, and writing my reflection. I didn’t copy responses word for word — instead, I adapted them into my own work to meet the assignment requirements.