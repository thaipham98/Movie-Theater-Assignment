# Movie-Theater-Seating

This is the Movie Theater Seating Challenge programmed with Java.

Requirement: design and write a seat assignment program to maximize both customer satisfaction and customer safety. For the purpose of public safety, assume that a buffer of three seats and/or one row is required.

## Assumption
1. Public safety: a buffer of three seats and/or one row is required
2. Customer satisfaction: a group of customers will be sitting together and not splitted. The assignment is first come first serve as the first customers will take rows furthest in the back (from J to A) and from the left to the right (from 1 to 20). If the number of people in a group exceeds the number of available slots in a current row, that group can be assigned to another row which is sufficient to contain all people. If the number exceeds the maximum number of people for each row (20), this group cannot be assigned into the theater.

## Usage

1. Clone this Github repo or download the zip file at https://github.com/thaipham98/Movie-Theater-Seating.
2. Open terminal of choice and cd into the Movie-Theater-Seating directory.
3. Cd into source folder:

```bash
cd src
```

## Compile the program

First, we need to compile all .java files at `src/com/company`

```bash
javac com/company/*.java
```

## Test

Run the below command to test against 2 provided sample test cases

```bash
java com.company.SolverTest
```
```bash
java com.company.TheaterTest
```
## 

## Summary & Caveats
### Summary
The application is an attempt for the Walmart coding challenge. Specifically, it contains:
- All required basic functionalities for movie seating arrangement as required.
- Preliminary unit tests.
- README and easy dependency installations.
### Caveats
The project can be improved given more time, for examples in some areas like:
- Robustness: More extensive testing and design to bring more OOP or other design patterns to increase robustness and maintenance ease.
- Performance: Improve on the algorithm for the arrangement. For example, if a group exceeds the maximum number of people for each row (20), we should arrange that they can sit together both horizontally and vertically or split into smaller groups. Also, for better customer experience, we should arrange the middle seats of each row with better views (instead of arranging from left to right).
- Testing: Write more comprehensive test cases and use mockito to cover all the functionalities.

