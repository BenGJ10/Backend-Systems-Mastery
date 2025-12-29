# `git stash` — Temporarily Saving Uncommitted Changes

## Overview

`git stash` is used to **temporarily save uncommitted changes** (both staged and unstaged) without committing them, and revert the working directory to a clean state.  

This allows developers to switch branches, pull updates, or work on something else **without losing ongoing work**.

It is especially useful when you are in the middle of a task and suddenly need to:

- fix a production bug
- switch branches
- pull latest changes
- test something quickly

---

## What `git stash` Does Conceptually

When you run `git stash`:

- your current changes are **saved in a separate stash stack**
- your working directory is **reverted to last commit state**
- nothing is committed to history
- you can later **reapply or discard** those saved changes

Think of it as a **temporary shelf for unfinished work**.

---

## Basic Usage

### Save Current Changes

```bash
git stash
```

This stashes:

* modified tracked files
* staged files

and cleans your working directory.

---

### View Stash List

```bash
git stash list
```

Output example:

```text
stash@{0}: WIP on feature-auth: 91c23d2 add login API
stash@{1}: WIP on main: 7ab45e1 update config
```

Each stash entry is indexed.

---

### Reapply Last Stash

```bash
git stash apply
```

Reapplies `stash@{0}` but **does not remove it from stash list**.

---

### Reapply Specific Stash

```bash
git stash apply stash@{1}
```

Useful when multiple stashes exist.

---

### Apply and Remove (Recommended)

```bash
git stash pop
```

* reapplies last stash
* removes it from stash stack

---

## Stashing Untracked Files

By default, **untracked files are not stashed**.

To include them:

```bash
git stash -u
```

Or:

```bash
git stash --include-untracked
```

---

## Stashing Only Specific Files

```bash
git stash push -m "partial work" UserService.java
```

Saves stash with message and includes only selected file(s).

---

## Creating Named Stashes

```bash
git stash push -m "auth module refactor"
```

Helpful for large projects and multiple pending tasks.

---

## Dropping and Clearing Stashes

### Delete a Specific Stash

```bash
git stash drop stash@{0}
```

### Clear All Stashes

```bash
git stash clear
```

This **cannot be undone**, so use carefully.

---

## Common Real-World Workflow Example

1. Working on feature branch
2. Urgent bug appears on `main`
3. You run:

```bash
git stash
git switch main
```

4. Fix bug, commit, push
5. Return to work

```bash
git switch feature-branch
git stash pop
```

Your unfinished changes are restored.

---

## When Not to Use `git stash`

Avoid stashing when:

* work is actually complete (commit instead)
* team members need your partial work
* you are trying to avoid meaningful commits

Stash is **temporary**; it is not a substitute for commits.

---

## Interview Questions

### 1. What is `git stash` and why is it used?

**Answer:**
`git stash` temporarily saves uncommitted changes and restores the working directory to a clean state, allowing developers to switch tasks or branches without losing work.

---

### 2. What is the difference between `git stash apply` and `git stash pop`?

**Answer:**

* `apply` reapplies the stash but keeps it in the stash list
* `pop` reapplies the stash and removes it from the list

---

### 3. Does `git stash` include untracked files by default?

**Answer:**
No. By default only tracked modified files are stashed.
Untracked files must be included explicitly using `git stash -u`.

---

### 4. How do you view all stashed changes?

**Answer:**
Use:

```bash
git stash list
```

It displays all stash entries in stack order.

---

### 5. What happens if merge conflicts occur during `git stash pop`?

**Answer:**
Conflicts must be resolved manually just like normal merge conflicts, after which you stage resolved files and continue working.

---

## Summary

* `git stash` temporarily stores uncommitted changes

* It allows branch switching and updates without losing progress

* Stashes can be applied, popped, listed, or cleared

* Untracked files require `-u` to stash

* It is a temporary storage mechanism, not a replacement for commits

---
