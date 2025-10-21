Howard University
College of Engineering and Architecture
Department of Electrical Engineering & Computer Science
Large Scale Programming
Fall 2025
Midterm Exam
October 20, 2025
Instructions
• Exam Format:
Your examination consists of both essay and programming problems.
• Essay Questions:
Complete all essay (rationale) questions inline in this document.
My preference is Microsoft Word (.docx), but .txt or .pdf are acceptable alternatives.
Upload your completed essay file to your repository under the package:
org.howard.edu.lsp.midterm.doc
You may use any file name.
• Programming Problems:
Each programming problem must be uploaded to your repository using the package specified in the question.
For example:
org.howard.edu.lsp.midterm.question1
• Committing Your Work:
If using a third-party IDE or tool to commit, commit early and often.
Do not wait until the end of the exam to push your code.
If you encounter problems committing, you may manually upload your code to your repository.
If you are unable to commit or upload, you may zip your project and email it to
bwoolfolk@whiteboardfederal.com.
⚠️ This will result in a 20% deduction from your final exam score.
• Citations and References:
You must cite all references for any material obtained from the internet.
Any AI-generated content (e.g., ChatGPT conversations) must be included in full.
Each package you upload must include a references document corresponding to that package’s content.
⚠️ Failure to provide references will result in a zero for that question.
• Exam Policy:
This is an OPEN BOOK, OPEN NOTES exam.
Collaboration of any kind is strictly prohibited. Any violations will be handled in accordance with university academic integrity guidelines.
Question 1. (20 pts.)
Given the following, analyze the class below and answer the below questions. This question does NOT require you to
write any code.
package org.howard.edu.lsp.studentPortalHelper;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class StudentPortalHelper {
 // Data cache (in-memory)
 private static final Map<String, String> cache = new HashMap<>();
 // GPA calculation
 public static double computeGPA(List<Integer> grades) {
 if (grades == null || grades.isEmpty()) return 0.0;
 int sum = 0;
 int count = 0;
 for (int g : grades) { sum += g; count++; }
 double avg = (double) sum / count;
 // simple mapping: 90–100=A=4, 80–89=B=3, etc.
 if (avg >= 90) return 4.0;
 if (avg >= 80) return 3.0;
 if (avg >= 70) return 2.0;
 if (avg >= 60) return 1.0;
 return 0.0;
 }
 // CSV export to disk
 public static void exportRosterToCsv(String filename, List<String> names) {
 try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
 pw.println("name");
 for (String n : names) {
 pw.println(n);
 }
 } catch (IOException e) {
 System.err.println("Failed to export roster: " + e.getMessage());
 }
 }
 // Email formatting
 public static String makeWelcomeEmail(String studentName) {
 return "Welcome " + studentName + "! Please visit the portal to update your
profile.";
 }
 // Date formatting (UI concern)
 public static String formatDateForUi(LocalDate date) {
 return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
 }
 // Payment processing (stub)
 public static boolean processTuitionPayment(String studentId, double amount) {
 if (amount <= 0) return false;
 // pretend to call external gateway...
 return true;
 }
 // Password strength check (security)
 public static boolean isStrongPassword(String pwd) {
 if (pwd == null || pwd.length() < 8) return false;
 boolean hasDigit = false, hasUpper = false;
 for (char c : pwd.toCharArray()) {
 if (Character.isDigit(c)) hasDigit = true;
 if (Character.isUpperCase(c)) hasUpper = true;
 }
 return hasDigit && hasUpper;
 }
 // Ad-hoc caching
 public static void putCache(String key, String value) {
 cache.put(key, value);
 }
 public static String getCache(String key) {
 return cache.get(key);
 }
}
Tasks:
Using one or more Arthur Riel heuristics, analyze whether the StudentPortalHelper class demonstrates high or low
cohesion.
a) Should a well-designed class have high or low cohesion? Explain and defend your answer. (5 pts.)
b) Based on your analysis, discuss—only if you believe changes are needed—how you would reorganize or redesign the class
to improve its structure. Your answer should (1) identify the class as having high, low or perfect cohesion and (2) describe a
general approach to refactoring the class. If you believe the class already has good cohesion, justify why no changes are
necessary. (15 pts)
(If you believe the class already has good cohesion, justify why no changes are necessary.)
Question 1
(a) Cohesion: what should a well-designed class have, and why? (5 pts)
A good class should have high cohesion. That means everything inside the class should
serve one clear purpose. Arthur Riel’s heuristic basically says a class should “capture one
and only one key abstraction.” When a class is focused like that, it’s easier to read, test,
and update later.
Low cohesion usually shows up when a class starts doing too many unrelated things—kind
of like a “utility dump.” High cohesion, on the other hand, keeps the logic organized and
makes the code feel cleaner, more reusable, and way easier to maintain.
(b) Analysis of StudentPortalHelper and redesign (15 pts)
1) My take on cohesion
This class definitely has low cohesion. It tries to handle everything that could possibly exist
in a student portal—GPA calculations, file exports, date formatting, emails, payment
processing, password checks, and even caching. Those are completely different
responsibilities living in one place.
According to Riel’s rule that a class should represent one clear abstraction, this setup
doesn’t really follow that. Everything’s static, and there’s no single concept that ties the
methods together. It feels more like a big catch-all helper instead of a focused piece of a
system.
2) How I’d refactor it
To make it more cohesive, I’d break the class into smaller, more specific ones. For
example:
• GpaCalculator → handles GPA logic only.
• RosterExporter → writes names to a CSV file.
• EmailTemplateService → formats welcome messages or other emails.
• DateFormatter → handles UI date formatting.
• PaymentService → deals with tuition payments and external gateways.
• PasswordPolicy → checks password strength and rules.
• CacheManager → manages temporary data storage instead of using a global
HashMap.
Each of these would have one clear job, which lines up with Riel’s heuristic and the Single
Responsibility Principle. It also makes testing and debugging easier because you’d know
exactly where to look for specific logic.
3) Why this makes sense
Right now, StudentPortalHelper is a god class, meaning it knows too much and does too
much. Splitting it up would make the code more modular and scalable. If someone needed
to change how GPA is calculated or update payment logic, they wouldn’t risk breaking
unrelated features like password validation.
By having each class focus on one area, the system becomes more maintainable,
readable, and testable. That’s what high cohesion is all about.
All in all:
Current cohesion: Low
Recommended: split into separate, focused classes
Result: easier maintenance, cleaner design, better adherence to object-oriented
principles (Riel’s heuristic + SRP)
AI Prompt:
I’m analyzing a class called StudentPortalHelper that handles GPA
calculation, CSV export, and password checking. Can you explain how to tell
if this class has high or low cohesion based on Arthur Riel’s heuristics?
AI Response:
The class has low cohesion because it tries to handle too many unrelated tasks like GPA
computation, file export, date formatting, and password checking. According to Riel’s
heuristic, a well-designed class should represent one clear abstraction. I’d suggest
splitting it into smaller, focused classes — for example, a GpaCalculator for grades, a
RosterExporter for file I/O, and a PasswordPolicy for security. That would make each class
easier to test and maintain.
Question 2. (20 pts.)
Write a class AreaCalculator in the package org.howard.edu.lsp.midterm.question2 with the following
overloaded methods: This should be uploaded to your repo.
// Circle area
public static double area(double radius)
// Rectangle area
public static double area(double width, double height)
// Triangle (base & height) area
public static double area(int base, int height)
// Square (side length) area
public static double area(int side)
Requirements:
Each method should compute and return the correct area.
• Circle area: π (use class Math.PI) × r²
• Rectangle area: width × height
• Triangle area: ½ × base × height
• Square area: side²
• For all methods: throw an IllegalArgumentException if any dimension is ≤ 0.
Create a class named Main that invokes each overloaded method statically to produce exactly the following output:
Circle radius 3.0 → area = 28.274333882308138
Rectangle 5.0 x 2.0 → area = 10.0
Triangle base 10, height 6 → area = 30.0
Square side 4 → area = 16.0
Finally, invoke at least one of the area methods with a value that causes an IllegalArgumentException to be thrown.
• Catch the exception using a try/catch block.
• Print an error message to System.out. (Any message is fine.)
Briefly (2–3 sentences as a comment in class Main) explain if overloading or simply use methods with different names, i.e.,
rectactangleArea, circleArea, etc..
Category Description Points
1. Implementation
Correct use of method overloading (same name, different
signatures), correct formulas, and proper exception handling
in each method.
10
2. Program Behavior
Main correctly invokes all methods statically, produces the
required output exactly, and includes a working exception
demonstration.
6
3. Conceptual
Understanding
Brief explanation of why or why not overloading is the
better design choice. 4
Prompt 
I’m writing an AreaCalculator class with overloaded methods for circle,
rectangle, triangle, nd square. Can please please you remind me how
method overloading works in Java and how to handle invalid input properly?
AI Answer:
Method overloading means you can use the same method name — in this case, area() —
but with different parameter lists. Java picks the correct method based on the argument
types. For this assignment, each version should calculate the correct formula for its shape,
and if any parameter is ≤ 0, throw an IllegalArgumentException. You can catch the
exception in Main to print an error message and demonstrate that your validation works.
Question 3.
Given the following, answer the below questions.
(20 pts.)

Given:
A car manufacturer uses Java software to track current vehicles being built. The UML diagram below shows an excerpt of the
current software structure. You should assume the presence of other appropriate fields and methods
Each car can be built to one of three trim levels: Base, Luxury or Sport. They can also be configured with an electric or
petrol engine. At various points in the manufacturing process the customer can choose to change the trim level. 
Task:
a. Explain in detail why the current structure does or does not support this. (10 pts.)
a. Describe how to refactor the structure to allow trim-level change for a car to dynamically change. Hint: How would
you modify Car to use composition to solve the problem? (10 pts.)
here’s how i’d explain it, straight from a student point of view but hitting all the details.
⸻
B) — does the current design let us change a car’s trim mid-build?
from the UML, Car has subclasses for trim: Base, Sports, Luxury. that means the trim is baked into
the class type. in java you can’t change an object’s class at runtime. so if i build new Sports(...) and
the customer switches to Luxury, the only way to “change” it is to create a new Luxury object and
copy everything over (vin, engine, options, timestamps, whatever). that’s messy and risky—
references to the original object could break, and you can easily lose state or history.
this is basically the god helper/type-as-configuration
problem: the trim is a configuration that can change during the process, but inheritance makes it a
type, which can’t change. also, it’s not great for extension—every new trim forces a new subclass
and probably instanceof checks somewhere (violates open/closed).
the engine side shows Engine with Electric and Petrol. that could work if Car has an Engine field
(composition), because then you can swap the engine object. but the diagram doesn’t show that
relationship explicitly. either way, the trim design definitely doesn’t support dynamic switching
because it uses inheritance.
bottom line: current structure does not support mid-build trim changes, because you can’t change a
class type at runtime.
⸻
B) — how i’d refactor it so trim can change dynamically
make trim a thing the car has (state/role), not the class the car is. in other words: composition over
inheritance. i’d use a small Strategy/State style design.
define a trim role
interface TrimLevel {
 String name(); // "Base", "Luxury", "Sport"
 // optional: msrpDelta(), includesSportSuspension(), etc.
}
final class BaseTrim implements TrimLevel { public String name() { return "Base"; } }
final class SportTrim implements TrimLevel { public String name() { return "Sport"; } }
final class LuxuryTrim implements TrimLevel { public String name() { return "Luxury"; } }
let Car compose a TrimLevel (and an Engine)
class Car {
 private final String vin;
 private TrimLevel trim; // composition
 private Engine engine; // composition (matches your UML side for engines)
 Car(String vin, TrimLevel trim, Engine engine) {
 this.vin = vin;
 this.trim = trim;
 this.engine = engine;
 }
 // required by the UML
 public String getTrimLevel() { return trim.name(); }
 // dynamic reconfiguration mid-build
 public void setTrimLevel(TrimLevel newTrim) {
 if (newTrim == null) throw new IllegalArgumentException("trim required");
 this.trim = newTrim; // same object identity, new trim behavior
 }
 public void setEngine(Engine newEngine) { this.engine = newEngine; }
}
why this works
• same object, new trim: we keep the same Car instance (same VIN, same references), and just
swap the TrimLevel object. no data copying or broken links.
• clean responsibilities: trim logic lives with trim; Car orchestrates. adding a new trim = add a new
TrimLevel implementation (no touching Car).
• matches reality: customers can “flip” configuration during the build; that’s a state change, not a
type change.
note on engines
do the same composition pattern for engines:
interface Engine { void accelerate(int pct); int getFuelLevel(); }
final class ElectricEngine implements Engine { /* ... */ }
final class PetrolEngine implements Engine { /* ... */ }
then car.setEngine(new ElectricEngine()) if the order changes.
• current: trim via inheritance ⇒ can’t change class at runtime ⇒ not flexible for mid-build
changes.
• refactor: move to composition (Car has a TrimLevel and an Engine) and delegate; now trim can be
swapped dynamically with a setter.
I used ChatGPT to help me refine how I explained my reasoning and organize the structure
of my answer. I already understood that the main issue with the UML was that inheritance
made the trim-levels static, and that using composition would make them dynamic. I just
wanted help phrasing that idea more clearly and confirming that my reasoning aligned with
good object-oriented design principles.
Prompt: Please grammar check this and also check if my reasoning makes sense. Like
make it sound more professional
Prompt 2:
Can you help me understand how to refactor my procedural ETL pipeline into
an object-oriented version that follows good class design? Explain it to me
like im 15.
Answer: Think of your ETL pipeline as three main responsibilities: extract,
transform, and load. In object-oriented design, each of those should be its
own class, like Extractor, Transformer, and Loader. Each class should handle
just one stage of the process, which increases cohesion and makes your
program easier to extend later. The key idea is to treat each part as an object
with clear inputs and outputs, not just as a group of functions.
Question 4. (30 pts.)
Design and implement a small smart-campus device system showing both class inheritance (concrete classes extend a common
abstract class) and interface implementation.
1) Abstract Base Class — Device
The following Device class is partially implemented for you.
You must not modify this code, but you will use it in your subclasses:
package org.howard.edu.lsp.midterm.question4;
public abstract class Device {
 private String id;
 private String location;
 private long lastHeartbeatEpochSeconds;
 private boolean connected;
 // PROVIDED CONSTRUCTOR
 public Device(String id, String location) {
 if (id == null || id.isEmpty() || location == null || location.isEmpty()) {
 throw new IllegalArgumentException("Invalid id or location");
 }
 this.id = id;
 this.location = location;
 this.lastHeartbeatEpochSeconds = 0;
 this.connected = false;
 }
 public String getId() {
 return id;
 }
 public String getLocation() {
 return location;
 }
 public long getLastHeartbeatEpochSeconds() {
 return lastHeartbeatEpochSeconds;
 }
 public boolean isConnected() {
 return connected;
 }
 protected void setConnected(boolean connected) {
 this.connected = connected;
 }
 public void heartbeat() {
 this.lastHeartbeatEpochSeconds = System.currentTimeMillis() / 1000;
 }
 public abstract String getStatus();
}
You will extend this class in your DoorLock, Thermostat, and Camera implementations.
All subclasses must call super(id, location) in their constructors.
2) Capability Interfaces (behaviors only)
Networked
void connect();
void disconnect();
boolean isConnected();
Behavior:
• connect() brings the device online by setting connected = true.
• disconnect() sets connected = false.
• isConnected() reports the current connection state.
(Concrete classes may satisfy this using Device’s protected setter and public getter.)
BatteryPowered
int getBatteryPercent(); // 0..100
void setBatteryPercent(int percent);
Behavior:
• getBatteryPercent() returns current battery %.
• setBatteryPercent(int) updates it; throw IllegalArgumentException if outside 0..100 inclusive.
3) Concrete Devices (must extend Device and implement interfaces)
All fields must be private. Implement methods exactly as specified.
A. DoorLock — extends Device, implements Networked, BatteryPowered
Private fields
private int batteryPercent;
Constructor
public DoorLock(String id, String location, int initialBattery)
• Call super(id, location).
• Initialize battery by calling setBatteryPercent(initialBattery) (enforces 0..100).
Implemented methods
// Networked
@Override public void connect() { setConnected(true); }
@Override public void disconnect() { setConnected(false); }
@Override public boolean isConnected() { return super.isConnected(); }
// BatteryPowered
@Override public int getBatteryPercent() { return batteryPercent; }
@Override public void setBatteryPercent(int percent) {
 if (percent < 0 || percent > 100) throw new IllegalArgumentException("battery
0..100");
 this.batteryPercent = percent;
}
// Status
@Override public String getStatus() {
 String connStatus = isConnected() ? "up" : "down";
 return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
 ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
}
B) Thermostat — extends Device, implements Networked
Private fields
private double temperatureC;
Constructor
public Thermostat(String id, String location, double initialTempC)
• Call super(id, location).
• Initialize temperatureC to initialTempC.
Accessors
public double getTemperatureC();
public void setTemperatureC(double temperatureC);
Implemented methods
// Networked
@Override public void connect() { setConnected(true); }
@Override public void disconnect() { setConnected(false); }
@Override public boolean isConnected() { return super.isConnected(); }
// Status
@Override public String getStatus() {
 String connStatus = isConnected() ? "up" : "down";
 return "Thermostat[id=" + getId() + ", loc=" + getLocation() +
 ", conn=" + connStatus + ", tempC=" + temperatureC + "]";
}
C) Camera — extends Device, implements Networked, BatteryPowered
Private fields
private int batteryPercent;
Constructor
public Camera(String id, String location, int initialBattery)
• Call super(id, location).
• Initialize battery by calling setBatteryPercent(initialBattery).
Implemented methods
// Networked
@Override public void connect() { setConnected(true); }
@Override public void disconnect() { setConnected(false); }
@Override public boolean isConnected() { return super.isConnected(); }
// BatteryPowered
@Override public int getBatteryPercent() { return batteryPercent; }
@Override public void setBatteryPercent(int percent) {
 if (percent < 0 || percent > 100) throw new IllegalArgumentException("battery
0..100");
 this.batteryPercent = percent;
}
// Status
@Override public String getStatus() {
 String connStatus = isConnected() ? "up" : "down";
 return "Camera[id=" + getId() + ", loc=" + getLocation() +
 ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
}
4) Provided Driver
Do not modify this file. Your classes must compile and run with it unchanged.
package org.howard.edu.lsp.midterm.question4;
import java.util.*;
public class Main {
 public static void main(String[] args) {
 Device lock = new DoorLock("DL-101", "DormA-1F", 85);
 Device thermo = new Thermostat("TH-202", "Library-2F", 21.5);
 Device cam = new Camera("CA-303", "Quad-North", 72);
 // === Invalid battery test ===
 System.out.println("\n== Exception test ==");
 try {
 Device badCam = new Camera("CA-404", "Test-Lab", -5);
 System.out.println("ERROR: Exception was not thrown for invalid battery!");
 } catch (IllegalArgumentException e) {
 System.out.println("Caught expected exception: " + e.getMessage());
 }
 // === Heartbeat demonstration ===
 System.out.println("\n== Heartbeat timestamps BEFORE ==");
 for (Device d : Arrays.asList(lock, thermo, cam)) {
 System.out.println(d.getId() + " lastHeartbeat=" +
d.getLastHeartbeatEpochSeconds());
 }
 lock.heartbeat();
 thermo.heartbeat();
 cam.heartbeat();
 System.out.println("\n== Heartbeat timestamps AFTER ==");
 for (Device d : Arrays.asList(lock, thermo, cam)) {
 System.out.println(d.getId() + " lastHeartbeat=" +
d.getLastHeartbeatEpochSeconds());
 }
 // === Base-class polymorphism ===
 List<Device> devices = Arrays.asList(lock, thermo, cam);
 System.out.println("\n== Initial status via Device ==");
 for (Device d : devices) {
 System.out.println(d.getStatus());
 }
 // === Interface polymorphism: Networked ===
 System.out.println("\n== Connect all Networked ==");
 for (Device d : devices) {
 if (d instanceof Networked) {
 ((Networked) d).connect();
 }
 }
 // === Interface polymorphism: BatteryPowered ===
 System.out.println("\n== Battery report (BatteryPowered) ==");
 for (Device d : devices) {
 if (d instanceof BatteryPowered) {
 BatteryPowered bp = (BatteryPowered) d;
 System.out.println(d.getClass().getSimpleName() + " battery = " +
bp.getBatteryPercent() + "%");
 }
 }
 // === Final status check ===
 System.out.println("\n== Updated status via Device ==");
 for (Device d : devices) {
 System.out.println(d.getStatus());
 }
 }
}
5) Brief Rationale (2–4 sentences)
• Why is Device defined as an abstract class?
• How do the Networked and BatteryPowered interfaces add behavior to your concrete classes?
• Is this design an example of multiple inheritance in Java? Explain why or why not.
Grading (30 pts)
Category Description Points
Implementation
Correct use of inheritance and interfaces; meets all required
method signatures and behaviors; uses the provided Device
constructor; correctly implements Networked and
BatteryPowered; uses setConnected(boolean) properly;
validates inputs.
15
Program
Behavior
Code compiles and runs with the provided Main.java
unchanged; heartbeat behavior works; base-class and interface
polymorphism demonstrated; exception thrown for invalid
battery input; getStatus() output matches required formats.
9
Rationale
Clear, thoughtful, and specific answers to the four questions
above. References to the student's own code are present. Shows
conceptual understanding of abstraction, interface-based
behavior, and multiple inheritance in Java.
6
Question 4 Rationale
Device is defined as an abstract class because it provides shared fields and methods (like
ID, location, and heartbeat) that all devices use, but leaves getStatus() for each subclass
to define individually.
Networked and BatteryPowered interfaces add optional behaviors that not all devices need
they let classes mix in connection and battery features without forcing everything into one
inheritance chain
This isn’t true multiple inheritance because Java only allows one class to be extended, but
a class can implement multiple interfaces basically giving flexibility without the complexity
of inheriting from multiple parents.
AI Prompt
I’m working on my smart-campus device system for the midterm. My
subclass constructors call super from the Device abstract class, but I’m
getting an error that says Device can’t be instantiated. What am I doing
wrong?
AI response
That error happens because Device is an abstract class, which means you
can’t create objects of it directly — only subclasses like DoorLock,
Thermostat, or Camera can extend and use it. Calling super(id, location) in
the subclass constructor is correct because it initializes the base fields. You
just need to make sure you’re not using new Device(...) anywhere. Each
subclass should override getStatus() to provide its own output and, if
necessary, implement the correct interfaces like Networked or
BatteryPowered.
Question 5 (10 pts)
Reflection on AI Use in Learning and Problem Solving
Discuss your personal experience using AI tools (such as ChatGPT, GitHub Copilot, or others) before and during this course.
In your response, address the following points:
1. How have you used AI to support your learning or programming in this course?
2. What benefits or limitations did you encounter?
3. Looking ahead, how do you expect AI to influence the way you solve problems academically or professionally?
Your answer should be 1–2 well-developed paragraphs.
Throughout this course, I’ve used AI tools like ChatGPT as a way to clarify concepts and
troubleshoot errors, not as a shortcut for solutions. For example, when I struggled to
understand inheritance and interface implementation, I used AI to explain why my abstract
class structure wasn’t compiling and how Java handles method overriding. I also used it to
review syntax, check edge cases, and make sure my code followed the assigments
requirements. Rather than just copying answers, I treated AI like a tutor — a tool to confirm
my understanding, identify mistakes faster, and strengthen the reasoning behind my code.
The biggest benefit I’ve experienced is instant feedback and the ability to get multiple
explanations in different styles until something finally clicks. It’s helped me develop better
debugging habits and more confidence when approaching new programming problems.
However, one limitation is that AI can occasionally oversimplify or misinterpret context, so
I’ve learned to double-check its suggestions against my own logic and the course material.
Looking ahead, I expect AI to become a regular part of how I solve problems professionally
— not by replacing critical thinking, but by accelerating research, brainstorming, and
testing ideas more efficiently. My goal is to use it as a support system, not a substitute for
my own learning.