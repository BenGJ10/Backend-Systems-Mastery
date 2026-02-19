# User and Group Management in Linux

## Overview

Linux is a **multiuser operating system**, meaning multiple users can access and operate the system simultaneously.

User and group management is critical for:

* system security
* permission control
* server administration
* DevOps workflows
* production-grade backend systems

Every process in Linux runs as a **specific user**, and permissions are enforced based on that identity.

---

## Why User Management Matters

In production systems:

* Web servers run as `www-data`
* Databases run as `mysql` or `postgres`
* System services run as `root` or dedicated service accounts

Proper user separation:

* prevents privilege escalation
* isolates services
* reduces security risks

---

## User Types in Linux

### 1️⃣ Root User

* Superuser
* UID = 0
* Full system access

Dangerous if misused.

---

### 2️⃣ System Users

Used by services and background processes.

Examples:

* `www-data`
* `mysql`
* `nobody`

Usually have no login shell.

---

### 3️⃣ Regular Users

Human users who log in and use the system.

Example:

```
ben
admin
developer
```

---

## Understanding UID and GID

![UID and GID](https://static1.squarespace.com/static/5a01100f692ebe0459a1859f/t/5f9123d2b807353e905b4fe6/1603347440376/BSY%2BSecurity%2BClass%2BDiagrams%2B-%2B_etc_passwd%2B%28L%29.jpg?format=1500w)

Each user has:

* **UID** (User ID)
* **Primary GID** (Group ID)

View current user:

```bash
id
```

Example output:

```
uid=1001(ben) gid=1001(ben) groups=1001(ben),27(sudo)
```

---

## Important System Files

Linux stores user and group information in:

* `/etc/passwd`
* `/etc/shadow`
* `/etc/group`

---

### `/etc/passwd`

Stores user account details.

Example entry:

```
ben:x:1001:1001:Ben:/home/ben:/bin/bash
```

Fields:

```
username:password_placeholder:UID:GID:comment:home_directory:shell
```

Passwords are NOT stored here (security reasons).

---

### `/etc/shadow`

Stores encrypted passwords.

Accessible only by root.

---

### `/etc/group`

Stores group definitions.

Example:

```
sudo:x:27:ben
```

---

## Creating Users

### Add New User

```bash
sudo useradd username
```

Create with home directory:

```bash
sudo useradd -m username
```

Set password:

```bash
sudo passwd username
```

---

### Create User with Specific Shell

```bash
sudo useradd -m -s /bin/bash username
```

---

## Deleting Users

```bash
sudo userdel username
```

Remove user with home directory:

```bash
sudo userdel -r username
```

---

## Managing Groups

### Create Group

```bash
sudo groupadd developers
```

---

### Add User to Group

```bash
sudo usermod -aG developers ben
```

⚠️ Important: Use `-aG` (append).
Without `-a`, user may be removed from other groups.

---

### Remove User from Group

```bash
sudo gpasswd -d ben developers
```

---

## Primary vs Secondary Groups

Each user has:

* One primary group
* Multiple secondary groups

Primary group → defined in `/etc/passwd`
Secondary groups → defined in `/etc/group`

---

## Switching Users

### Switch to Another User

```bash
su username
```

### Switch to Root

```bash
sudo su
```

Or:

```bash
sudo -i
```

---

## Sudo Access

The `sudo` mechanism allows users to run commands as root.

Controlled by:

```
/etc/sudoers
```

Safer way to edit:

```bash
sudo visudo
```

Users must belong to `sudo` group (on Debian-based systems).

---

## Locking and Unlocking Users

### Lock Account

```bash
sudo passwd -l username
```

### Unlock Account

```bash
sudo passwd -u username
```

Useful for temporary suspension.

---


## Security Best Practices

* Never use root for daily tasks
* Create dedicated service users
* Use minimal privileges
* Regularly audit `/etc/passwd`
* Disable unused accounts
* Use strong passwords

---

## Interview Questions

### 1. What is UID 0?

**Answer:**
UID 0 represents the root user.

---

### 2. Where are passwords stored?

**Answer:**
In `/etc/shadow`.

---

### 3. Difference between primary and secondary groups?

**Answer:**
Primary group is assigned at account creation; secondary groups provide additional permissions.

---

### 4. What does `usermod -aG` do?

**Answer:**
Adds a user to a group without removing existing groups.

---

### 5. Why is `visudo` preferred?

**Answer:**
It validates syntax before saving, preventing sudo misconfiguration.

---

## Summary

* Linux is a multiuser system

* Each user has UID and group associations

* `/etc/passwd`, `/etc/shadow`, `/etc/group` store account data

* `useradd`, `usermod`, `groupadd` manage accounts

* `sudo` provides controlled privilege escalation

* Proper user management is critical for system security

---
