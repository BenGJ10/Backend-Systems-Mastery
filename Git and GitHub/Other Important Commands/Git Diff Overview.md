# `git diff` — Overview and Practical Usage

## Overview

`git diff` is a Git command used to **compare changes between different states of a repository**.  

It shows line-by-line differences, highlighting **what was added, removed, or modified** across files.  

This makes it an essential tool for reviewing work before committing, debugging issues, and understanding how code has evolved.

Rather than relying on guesswork, `git diff` gives precise visibility into *how the working directory differs from staging, staging differs from commits, or two commits differ from each other*.

---

## Why `git diff` Matters

In real backend projects, you need to answer questions like:

- What exactly changed since my last commit?
- What did I stage vs what is still unstaged?
- What changed between two commits or branches?
- What is different before creating a pull request?

`git diff` answers these questions by showing **contextual changes**, reducing mistakes such as committing unintended modifications.

---

## How `git diff` Works Conceptually

Git stores snapshots of files across:

1. **Working directory** — your current edits
2. **Staging area (index)** — changes prepared for commit
3. **Repository history** — committed versions

`git diff` compares any two of these layers.

---

## Common `git diff` Use Cases

### 1. Compare Working Directory vs Staging Area

Shows **unstaged changes** (what you edited but have not added yet):

```bash
git diff
```

Used before staging files to review modifications.

---

### 2. Compare Staging Area vs Last Commit

Shows **what is staged and will be committed**:

```bash
git diff --staged
```

(or `--cached`, both identical)

Important to verify before committing.

---

### 3. Compare Working Directory vs Last Commit

```bash
git diff HEAD
```

Shows everything changed since the last commit (staged + unstaged).

---

## Understanding the Output Format

A typical diff block looks like:

```text
- return "Hello User";
+ return "Welcome User";
```

Meaning:

* Lines starting with **`-`** were removed or changed
* Lines starting with **`+`** were added or replaced
* Unchanged lines are shown for context

This helps quickly understand semantic impact.

---

## Comparing Branches

Compare two branches:

```bash
git diff main feature-auth
```

Used during:

* code reviews
* PR validation
* analyzing feature changes

---

## Comparing Specific Files

```bash
git diff HEAD UserService.java
```

Restricts output to one file, useful for focused inspection.

---

## Comparing Commits

```bash
git diff commit1 commit2
```

Example:

```bash
git diff a1b2c3 d4e5f6
```

Used when analyzing historical changes.

---

## Visual Diff Tools

Git can integrate with GUI diff tools such as:

* `git difftool`
* IDE diff viewers (IntelliJ, VS Code)

These are especially useful for large backend codebases.

---

## Practical Backend Engineering Scenarios

`git diff` is invaluable when:

* reviewing changes before commit to avoid misconfiguration
* validating API contract modifications
* checking accidental secrets/config changes
* understanding defect introduction points
* reviewing refactors vs functional changes

---

## Important Variants and Flags

### Show only file names changed

```bash
git diff --name-only
```

### Ignore whitespace changes

```bash
git diff -w
```

Useful when formatters run automatically.

---

## Interview Questions

1. **What does `git diff` do, and when would you use it?**
   Explain comparison between working directory, staging, and commits.

2. **What is the difference between `git diff` and `git diff --staged`?**
   Discuss unstaged vs staged comparisons.

3. **How would you compare changes between two branches?**
   Provide appropriate command.

4. **Why is `git diff` important before committing code?**
   Explain prevention of accidental or incorrect commits.

5. **How do you see only the names of files changed instead of full diffs?**
   Mention `--name-only`.

---

## Summary

* `git diff` compares changes between repository states

* It helps inspect unstaged, staged, or committed differences

* It is critical for reviews, debugging, and safe committing

* Supports comparison across files, commits, and branches

* Mastery of `git diff` improves code quality and reduces errors

---