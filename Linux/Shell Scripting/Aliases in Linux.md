# Aliases in Linux

## Overview

An **alias** in Linux is a shortcut that allows you to replace a long or complex command with a shorter, easier-to-remember name.

Aliases improve productivity by:

* reducing typing effort
* simplifying commonly used commands
* preventing mistakes with safer command defaults

They are widely used in daily Linux workflows, especially for system administrators and developers.

---

## What Is an Alias?

An alias maps a **custom name** to an existing command.

Example:

```bash
alias ll='ls -l'
```

Now instead of typing:

```bash
ls -l
```

You can simply run:

```bash
ll
```

---

## Viewing Existing Aliases

To see all aliases defined in your current shell session:

```bash
alias
```

Example output:

```bash
alias ll='ls -l'
alias la='ls -a'
alias l='ls -CF'
```

Many Linux distributions already include some default aliases.

---

## Creating an Alias

Syntax:

```bash
alias name='command'
```

Example:

```bash
alias gs='git status'
```

Now you can run:

```bash
gs
```

instead of:

```bash
git status
```

---

## Useful Alias Examples

### Safer File Deletion

```bash
alias rm='rm -i'
```

This asks for confirmation before deleting files.

---

### Human-Readable Disk Usage

```bash
alias df='df -h'
```

---

### Colored File Listing

```bash
alias ls='ls --color=auto'
```

---

### Navigation Shortcut

```bash
alias ..='cd ..'
alias ...='cd ../..'
```

---

## Removing an Alias

Use the `unalias` command.

```bash
unalias ll
```

To remove all aliases:

```bash
unalias -a
```

---

## Temporary vs Permanent Aliases

Aliases created in the terminal are **temporary**.

They disappear when the terminal session ends.

Example:

```bash
alias ll='ls -l'
```

---

## Making Aliases Permanent

Add aliases to shell configuration files.

Common locations:

| File              | Purpose                       |
| ----------------- | ----------------------------- |
| `~/.bashrc`       | Bash interactive shell config |
| `~/.bash_profile` | Login shell config            |
| `~/.zshrc`        | Zsh shell config              |

Example:

```bash
vi ~/.bashrc
```

Add:

```bash
alias ll='ls -l'
```

Reload configuration:

```bash
source ~/.bashrc
```

Now the alias persists across sessions.

---

## Alias Expansion

Aliases are expanded **before command execution**.

Example:

```bash
alias ll='ls -l'
```

When you run:

```bash
ll
```

The shell internally runs:

```bash
ls -l
```

---

## Alias vs Shell Functions

Aliases are simple command substitutions.

Functions allow more complex logic.

Example function:

```bash
backup() {
    cp "$1" "$1.bak"
}
```

Functions are more powerful than aliases.

---


## Interview Questions

### 1. What is an alias in Linux?

**Answer:**
An alias is a shortcut that replaces a command with a custom name.

---

### 2. How do you list all aliases?

**Answer:**

```bash
alias
```

---

### 3. How do you remove an alias?

**Answer:**

```bash
unalias name
```

---

### 4. Where are persistent aliases stored?

**Answer:**
In shell configuration files such as `~/.bashrc`.

---

### 5. What is the difference between an alias and a function?

**Answer:**
Aliases are simple command substitutions, while functions allow more complex logic.

---

## Summary

* Aliases provide shortcuts for long commands

* Created using the `alias` command

* Removed using `unalias`

* Temporary unless added to shell configuration files

* Useful for improving productivity and reducing typing effort

---
