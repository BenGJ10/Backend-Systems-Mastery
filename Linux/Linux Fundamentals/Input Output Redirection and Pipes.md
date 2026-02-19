# Input, Output, Redirection & Pipes in Linux

## Overview

Linux treats everything as a **stream of data**.

Every command interacts with three standard streams:

* **Standard Input (stdin)**
* **Standard Output (stdout)**
* **Standard Error (stderr)**

Understanding input/output and pipes is essential for:

* log analysis
* automation
* backend debugging
* shell scripting
* DevOps workflows

---

## Standard Streams in Linux

Every process automatically gets:

| Stream | Number | Purpose          |
| ------ | ------ | ---------------- |
| stdin  | 0      | Input to program |
| stdout | 1      | Normal output    |
| stderr | 2      | Error messages   |

---

### Example

```bash
ls
```

* Output goes to **stdout**
* Errors (if any) go to **stderr**

---

## Input Redirection (`<`)

Redirect input from a file instead of keyboard.

```bash
command < file.txt
```

### Example

```bash
wc -l < file.txt
```

Counts lines from file input.

---

## Output Redirection (`>`)

Redirect output to a file (overwrite).

```bash
command > output.txt
```

Example:

```bash
ls > files.txt
```

If file exists → it is overwritten.

---

## Append Output (`>>`)

Append instead of overwrite.

```bash
command >> output.txt
```

Example:

```bash
echo "New log entry" >> app.log
```

---

## Redirect Errors (`2>`)

Redirect stderr.

```bash
command 2> errors.txt
```

Example:

```bash
ls nonexistingfile 2> error.log
```

---

## Redirect Both stdout & stderr

```bash
command > all_output.txt 2>&1
```

Or modern syntax:

```bash
command &> all_output.txt
```

---

## Discard Output (`/dev/null`)

`/dev/null` discards data.

Example:

```bash
command > /dev/null
```

Suppress errors:

```bash
command 2> /dev/null
```

Suppress everything:

```bash
command &> /dev/null
```

Used in scripts to silence output.

---

## Pipes (`|`)

A pipe takes output of one command and sends it as input to another.

```
Command1 | Command2
```

This creates a data flow pipeline.

---

### Example 1: Count Files

```bash
ls | wc -l
```

Flow:

```
ls → stdout → pipe → wc → result
```

---

### Example 2: Search Logs

```bash
cat app.log | grep "ERROR"
```

Better version:

```bash
grep "ERROR" app.log
```

---

### Example 3: Process Chaining

```bash
ps aux | grep nginx | wc -l
```

Pipeline:

```
ps → grep → wc
```

---


## Command Chaining

Linux also supports logical chaining.

---

### `&&` — Run If Previous Succeeds

```bash
mkdir project && cd project
```

Second command runs only if first succeeds.

---

### `||` — Run If Previous Fails

```bash
mkdir project || echo "Failed"
```

---

### `;` — Run Regardless

```bash
command1 ; command2
```

---

### Here Documents (`<<`)

Provide multi-line input.

```bash
cat << EOF
Hello
World
EOF
```

Useful in scripting.

---

## File Descriptors (Advanced Concept)

| Descriptor | Meaning |
| ---------- | ------- |
| 0          | stdin   |
| 1          | stdout  |
| 2          | stderr  |

Example:

```bash
command 1> output.txt
command 2> error.txt
```

---


## Why Pipes Are Powerful

They allow:

* modular commands
* readable workflows
* automation
* performance efficiency
* data processing without temporary files

Linux shell becomes a data-processing engine.

---

## Interview Questions

### 1. What are the three standard streams?

**Answer:**
stdin (0), stdout (1), stderr (2).

---

### 2. Difference between `>` and `>>`?

**Answer:**
`>` overwrites; `>>` appends.

---

### 3. How do you redirect stderr?

**Answer:**
Using `2>`.

---

### 4. What does `2>&1` mean?

**Answer:**
Redirect stderr to stdout.

---

### 5. What is a pipe?

**Answer:**
It sends output of one command as input to another.

---

## Summary

* Linux commands use stdin, stdout, stderr

* `>` redirects output

* `>>` appends output

* `2>` redirects errors

* Pipes (`|`) connect commands

* `/dev/null` discards output

* Command chaining enables conditional execution

Mastering I/O and pipes unlocks true Linux power.

---
