# Access Control Lists (ACL) in Linux

## Overview

Traditional Linux permissions use a simple model:

```
Owner | Group | Others
```

But what if you need:

* A specific user to access a file
* Multiple users with different permissions
* Fine-grained control beyond basic groups

This is where **Access Control Lists (ACLs)** come in.

ACLs provide **advanced, flexible permission management** on top of standard Unix permissions.

---

## Why ACL Is Needed

Traditional permissions limitation:

If you want:

* `ben` → full access
* `developers` group → read access
* `alice` (not in developers group) → read-only

Standard permissions cannot handle this cleanly.

ACL solves this problem.

---

## What Is ACL?

An **Access Control List (ACL)** allows you to define permissions for:

* specific users
* specific groups
* multiple entries per file

It extends the traditional permission model.

ACL support exists in modern file systems like:

* ext4
* XFS
* Btrfs

---

## Checking ACL Support

Verify filesystem supports ACL:

```bash
tune2fs -l /dev/sda1 | grep "Default mount options"
```

Or check mount options:

```bash
mount | grep acl
```

If not enabled:

```bash
sudo mount -o remount,acl /
```

---

## Viewing ACLs

Use:

```bash
getfacl filename
```

Example:

```bash
getfacl file.txt
```

Output:

```
# file: file.txt
# owner: ben
# group: developers
user::rw-
user:alice:r--
group::r--
mask::r--
other::---
```

---

## Understanding ACL Output

| Entry      | Meaning                      |
| ---------- | ---------------------------- |
| user::     | Owner permissions            |
| user:alice | Specific user permission     |
| group::    | Group permission             |
| mask::     | Maximum effective permission |
| other::    | Others permission            |

---

## Setting ACL Permissions

### Grant Specific User Access

```bash
setfacl -m u:alice:r file.txt
```

Gives user `alice` read permission.

---

### Grant Group Access

```bash
setfacl -m g:developers:rw file.txt
```

---

### Remove ACL Entry

```bash
setfacl -x u:alice file.txt
```

---

### Remove All ACLs

```bash
setfacl -b file.txt
```

---

### Default ACLs (For Directories)

Default ACL ensures new files inherit permissions.

Example:

```bash
setfacl -d -m u:alice:rw project/
```

Now any new file inside `project/` inherits this ACL.

Very useful for shared team directories.

---

## ACL vs Traditional Permissions

| Feature              | Traditional | ACL      |
| -------------------- | ----------- | -------- |
| Owner                | ✅           | ✅        |
| One group            | ✅           | ✅        |
| Multiple users       | ❌           | ✅        |
| Multiple groups      | ❌           | ✅        |
| Fine-grained control | Limited     | Advanced |

ACL is an extension — not a replacement.

---

## ACL and `ls -l` Output

If a file has ACL enabled:

```bash
ls -l
```

You’ll see:

```
-rw-r--r--+ file.txt
```

The `+` indicates extended ACL entries exist.

---

## Important Concept: Mask

The `mask` limits maximum effective permissions.

Even if:

```
user:alice:rwx
```

If:

```
mask::r--
```

Alice only gets read access.

Mask acts as an upper bound.

---

## Security Best Practices

* Avoid overusing ACLs
* Document custom ACL rules
* Use groups when possible
* Use ACLs for special exceptions only

Overcomplicated ACL structures can become hard to audit.

---

## Interview Questions

### 1. What problem does ACL solve?

**Answer:**
It allows fine-grained permissions for multiple users beyond the basic owner-group-other model.

---

### 2. How do you check ACL entries?

**Answer:**
Using `getfacl`.

---

### 3. What does `+` in `ls -l` mean?

**Answer:**
The file has extended ACL permissions.

---

### 4. What is a default ACL?

**Answer:**
An ACL applied automatically to new files in a directory.

---

### 5. What is the mask in ACL?

**Answer:**
It defines the maximum effective permissions for users and groups.

---

## Summary

* ACL extends Linux’s basic permission model

* Allows multiple user-specific permissions

* Managed using `setfacl` and `getfacl`

* `+` indicates extended ACLs

* Default ACL helps manage shared directories

* Useful in enterprise and team environments

---
