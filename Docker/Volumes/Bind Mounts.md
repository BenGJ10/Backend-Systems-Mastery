# Docker Bind Mounts

## Overview

A **bind mount** is a Docker storage mechanism that maps a **specific directory or file from the host machine directly into a container**.  

Unlike Docker volumes, bind mounts are **not managed by Docker** — they rely entirely on the host filesystem.

Bind mounts are widely used during development because they allow **real-time file synchronization** between the host and the container.

---

## What Is a Bind Mount?

A bind mount creates a direct link between:

```
Host Path  ↔  Container Path
````

Example:

```bash
docker run -v /home/user/app:/app nginx
```

Meaning:

* `/home/user/app` → host directory
* `/app` → container directory

Any change on the host is instantly visible inside the container.

---

## How Bind Mounts Work

When a bind mount is attached:

* the container reads and writes directly to the host filesystem
* files are not stored inside Docker-managed storage
* removing the container does **not** delete the host data

However, deleting the host files removes them for the container as well.

---

## When to Use Bind Mounts

Bind mounts are ideal for:

* local development
* live code reloading
* configuration injection
* debugging
* log inspection

Example: Running a Node.js app with hot reload.

---

## Creating a Bind Mount

### Using `-v` Syntax

```bash
docker run -v $(pwd):/app node
```

Maps the current directory into the container.

---

### Using `--mount` Syntax (Recommended)

```bash
docker run \
  --mount type=bind,source=$(pwd),target=/app \
  node
```

Advantages:

* clearer syntax
* fewer mistakes
* preferred in production scripts

---

## Bind Mount Behavior Warning

If the container path already contains files, the bind mount **overrides them**.

Example:

* container has `/app/config.json`
* host directory is empty
* after mount → file disappears inside container

The original file is not deleted — just hidden.

---

## Bind Mounts vs Volumes

| Feature              | Bind Mount    | Volume          |
| -------------------- | ------------- | --------------- |
| Managed by Docker    | No            | Yes             |
| Depends on host path | Yes           | No              |
| Portability          | Lower         | Higher          |
| Security             | Less isolated | Better isolated |
| Typical Use          | Development   | Production data |

---

## Security Considerations

Bind mounts provide containers **direct access to host files**, which introduces risk.

Potential issues:

* accidental file modification
* exposure of sensitive data
* privilege escalation

Use read-only mounts when possible:

```bash
docker run -v $(pwd):/app:ro nginx
```

Prevents container writes.

---

## Common Mistakes

### Using Bind Mounts for Databases

Creates tight coupling with host filesystem and complicates migration.

---

### Mounting Sensitive Directories

Example:

```
/etc
/root
/home
```

Can expose critical system data.

---

### Breaking Container Portability

Containers relying on host paths may fail on another machine.

---

## Interview Questions

### 1. What is a Docker bind mount?

**Answer:**
A bind mount maps a host file or directory directly into a container.

---

### 2. How is a bind mount different from a volume?

**Answer:**
Bind mounts rely on host paths, while volumes are managed by Docker.

---

### 3. When should bind mounts typically be used?

**Answer:**
Primarily during development for live file syncing.

---

### 4. What is a major risk of bind mounts?

**Answer:**
They allow containers direct access to host files.

---

### 5. Can bind mounts be read-only?

**Answer:**
Yes, by adding the `:ro` flag.

---

## Summary

* Bind mounts connect host directories directly to containers

* They enable real-time file access

* Best suited for development workflows

* Less portable and secure than volumes

* Use cautiously in production environments

---