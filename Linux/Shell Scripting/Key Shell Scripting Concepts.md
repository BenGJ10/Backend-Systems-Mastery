# Key Shell Scripting Concepts

## Overview

Shell scripting enables automation by combining Linux commands with programming constructs.

To write effective scripts, it is important to understand several core concepts such as:

* variables
* user input
* conditional statements
* loops
* functions
* command substitution
* exit status
* arguments and parameters

These concepts allow scripts to perform **logic, automation, and decision-making**, making them powerful tools for system administration and DevOps workflows.

---

## Shell Script Structure

A typical shell script follows this structure:

```bash
#!/bin/bash

# Variables
name="Ben"

# Commands
echo "Hello $name"

# Logic
if [ "$name" = "Ben" ]; then
    echo "Welcome back"
fi
```

---

## 1️⃣ Variables

Variables store data values that scripts can use.

### Defining Variables

```bash
name="Linux"
version=5
```

Important rule: **No spaces around `=`**

---

### Accessing Variables

```bash
echo $name
```

Or using braces:

```bash
echo ${name}
```

---

### Example Script

```bash
#!/bin/bash

user="admin"
echo "Current user is $user"
```

---

## 2️⃣ Command Substitution

Allows storing the output of a command inside a variable.

### Modern Syntax

```bash
today=$(date)
```

Example:

```bash
echo "Today is $today"
```

---

### Older Syntax

```bash
today=`date`
```

Modern `$()` syntax is preferred.

---

## 3️⃣ User Input

Scripts can accept user input using `read`.

Example:

```bash
#!/bin/bash

echo "Enter your name:"
read name

echo "Hello $name"
```

---

### Silent Input (Passwords)

```bash
read -s password
```

---

## 4️⃣ Positional Parameters

Shell scripts accept command-line arguments.

Example:

```bash
./script.sh file1.txt file2.txt
```

Inside script:

| Parameter | Meaning             |
| --------- | ------------------- |
| `$0`      | Script name         |
| `$1`      | First argument      |
| `$2`      | Second argument     |
| `$#`      | Number of arguments |
| `$@`      | All arguments       |

---

### Example

```bash
#!/bin/bash

echo "First argument: $1"
echo "Second argument: $2"
```

---

## 5️⃣ Conditional Statements

Conditional statements allow decision-making.

---

## If Statement

```bash
if [ condition ]
then
    commands
fi
```

---

### Example

```bash
#!/bin/bash

num=10

if [ $num -gt 5 ]
then
    echo "Number is greater than 5"
fi
```

---

### If–Else

```bash
if [ condition ]
then
    command1
else
    command2
fi
```

---

### If–Else If

```bash
if [ condition ]
then
    command1
elif [ condition ]
then
    command2
else
    command3
fi
```

---

## Comparison Operators

| Operator | Meaning          |
| -------- | ---------------- |
| `-eq`    | equal            |
| `-ne`    | not equal        |
| `-gt`    | greater than     |
| `-lt`    | less than        |
| `-ge`    | greater or equal |
| `-le`    | less or equal    |

---

## String Comparison

| Operator | Meaning          |
| -------- | ---------------- |
| `=`      | equal            |
| `!=`     | not equal        |
| `-z`     | string empty     |
| `-n`     | string not empty |

---

## 6️⃣ Loops

Loops repeat commands multiple times.

---

### For Loop

```bash
for i in 1 2 3
do
    echo $i
done
```

Example:

```bash
for file in *.log
do
    echo "Processing $file"
done
```

---

### While Loop

```bash
while [ condition ]
do
    commands
done
```

Example:

```bash
count=1

while [ $count -le 5 ]
do
    echo $count
    ((count++))
done
```

---

### Until Loop

Runs until condition becomes true.

```bash
until [ $count -gt 5 ]
do
    echo $count
    ((count++))
done
```

---

## 7️⃣ Functions

Functions help reuse code.

---

### Function Syntax

```bash
function_name() {
    commands
}
```

---

### Example

```bash
greet() {
    echo "Hello $1"
}

greet Ben
```

---

## 8️⃣ Exit Status

Every Linux command returns an exit status.

| Exit Code | Meaning |
| --------- | ------- |
| 0         | Success |
| Non-zero  | Failure |

---

### Example

```bash
ls file.txt

echo $?
```

`$?` stores exit code of last command.

---

## 9️⃣ Logical Operators

Used to combine conditions.

| Operator | Meaning |   |    |
| -------- | ------- | - | -- |
| `&&`     | AND     |   |    |
| `        |         | ` | OR |
| `!`      | NOT     |   |    |

---

Example:

```bash
if [ $age -gt 18 ] && [ $age -lt 60 ]
then
    echo "Working age"
fi
```

---

## 🔟 Case Statement

Used for multiple condition branching.

```bash
case $option in
    start)
        echo "Starting service"
        ;;
    stop)
        echo "Stopping service"
        ;;
    restart)
        echo "Restarting service"
        ;;
    *)
        echo "Invalid option"
        ;;
esac
```

---

## 1️⃣1️⃣ Arrays

Arrays store multiple values.

```bash
fruits=("apple" "banana" "orange")
```

Access element:

```bash
echo ${fruits[1]}
```

Output:

```
banana
```

---

## 1️⃣2️⃣ Debugging Scripts

Run script in debug mode:

```bash
bash -x script.sh
```

Shows commands as they execute.

---

## Best Practices for Shell Scripts

* Use meaningful variable names

* Add comments for readability

* Validate user input

* Handle errors properly

* Avoid hardcoding paths

* Use functions for reusable logic

---

## Real Automation Example

```bash
#!/bin/bash

LOG_DIR="/var/log/myapp"

for file in $LOG_DIR/*.log
do
    echo "Checking $file"

    if grep -q "ERROR" "$file"
    then
        echo "Error found in $file"
    fi
done
```

This script scans all log files for errors.

---

## Interview Questions

### 1. What is `$?` in shell scripting?

**Answer:**
It stores the exit status of the last executed command.

---

### 2. Difference between `$*` and `$@`?

**Answer:**
Both represent all arguments, but `$@` preserves argument separation.

---

### 3. What does `read` do?

**Answer:**
It takes user input from the terminal.

---

### 4. What is the use of `case` statement?

**Answer:**
It allows multi-condition branching similar to switch statements.

---

### 5. How do you debug a shell script?

**Answer:**

```bash
bash -x script.sh
```

---

## Summary

Key shell scripting concepts include:

* variables and command substitution

* user input and parameters

* conditional statements

* loops and functions

* logical operators and case statements

* arrays and debugging

---