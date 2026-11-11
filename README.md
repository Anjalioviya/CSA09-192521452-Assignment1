# Campus Smart-Parking System – Assignment 1

Java 21 console implementation for the 12-task assignment.

## Requirements
- JDK 17 or later (recommended: JDK 21)
- VS Code, IntelliJ IDEA, Eclipse, or terminal
- No external libraries are required

## Project structure
- `src/main/java/parking/model/` – vehicles, users, slots and tickets
- `src/main/java/parking/service/` – parking facility operations
- `src/main/java/parking/app/` – runnable application
- `Task03/` – completed standalone `ParkingFacility` exercise
- `Task08/` – completed standalone `FeeDemo` exercise
- `Task01/` … `Task12/` – task evidence folders

## Compile and run (Windows PowerShell)
From the project root:

```powershell
mkdir out
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp out parking.app.ParkingApp
```

Run the two required standalone exercises:

```powershell
javac -d out Task03/ParkingFacility.java
java -cp out ParkingFacility

javac -d out Task08/FeeDemo.java
java -cp out FeeDemo
```

## Compile and run (macOS/Linux)
```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out parking.app.ParkingApp

javac -d out Task03/ParkingFacility.java
java -cp out ParkingFacility

javac -d out Task08/FeeDemo.java
java -cp out FeeDemo
```

## Assumptions used in this implementation
The assignment gives examples but does not prescribe all monetary rates or detailed pass conditions. These are explicit implementation assumptions; confirm or change them to match faculty instructions.

| Rule | Assumption |
|---|---|
| Two-wheeler | Rs 10 per started hour |
| Car / Electric car | Rs 30 per started hour |
| EV charging | Rs 12 per kWh, added to parking fee |
| E-bike (Task 12) | Flat Rs 20 per visit |
| Delivery van (Task 12) | Double car hourly rate for billable hours starting from 09:00 through 16:59; normal car rate at other hours |
| Billing | Every started hour is charged in full; zero-minute stay is Rs 0 |
| Student/staff pass | Valid monthly pass makes parking fee free |
| Visitor pass | Valid one-day pass makes parking fee free for the entry day |
| EV slot | Prefer a charging-enabled slot; if none exists, use a normal slot |
| Slot structure | Jagged two-dimensional array, allowing different capacities per floor |
| Duplicate registration | Only one active ticket per registration number |
| Ticket number | Static `AtomicLong` creates unique increasing ticket numbers in this running process |

The sample rates and rules are for demonstrating the required OOP concepts, not official university tariffs.

## Main implementation features
- Encapsulation through private fields and validated constructors
- Abstract `Vehicle` class and vehicle subclasses
- `Chargeable` interface for EV charging
- Runtime polymorphism through `calculateParkingFee()`
- Static ticket-number counter
- Jagged floor/slot structure
- `HashMap<String, ParkingTicket>` for average O(1) active-ticket lookup
- Student, staff and visitor pass handling
- E-bike and delivery-van extensions for Task 12

## Expected standalone outputs

### Task 3
```text
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

### Task 8
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

## Example main application output
Ticket numbers increase from 1001 for a fresh JVM run. The allocated slot and total are printed. For the included example:
- EV parking: 3 started hours × Rs 30 = Rs 90
- Charging: 5 kWh × Rs 12 = Rs 60
- Total: Rs 150

## Before submission
1. Replace placeholders in your report and this README with your actual name and register number.
2. Run every program yourself in your own Java environment.
3. Capture genuine screenshots of your own execution; none are fabricated or included here.
4. Add task-specific analysis, pseudocode, UML diagram, complexity discussion and test evidence to your report.
5. Create the required GitHub repository and commit progressively (at least one commit per task).
6. Review the code and make sure you can explain it during the viva.
