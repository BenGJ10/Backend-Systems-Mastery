# Docker Networks

## Overview

Docker networks enable communication between:

- containers  
- containers and the host  
- containers and external systems  

Networking is a core part of containerized backend systems, especially in microservice architectures where multiple services must interact securely and reliably.

Docker provides built-in network drivers to support different use cases.

---

## Why Docker Networking Matters

Without proper networking:

- services cannot communicate  
- APIs cannot reach databases  
- containers cannot be exposed externally  
- microservices architecture breaks  

Networking controls isolation, service discovery, and traffic routing.

---

## Default Docker Networks

When Docker is installed, it automatically creates three networks:

```bash
docker network ls
````

You’ll typically see:

* bridge
* host
* none

Each serves a different purpose.

---

## 1️⃣ Bridge Network

### What It Is

The default network for containers.

When you run:

```bash
docker run nginx
```

It connects to the default `bridge` network.

---

### Characteristics

* provides private IP addresses
* allows outbound internet access
* supports port mapping
* limited automatic DNS

Suitable for simple applications.

---

### Limitation

Containers on the default bridge cannot resolve each other by name unless linked manually.

---

## 2️⃣ User-Defined Bridge Network

### Why Use It?

Custom bridge networks provide:

* automatic DNS resolution
* better isolation
* cleaner microservice communication

---

### Create a Custom Network

```bash
docker network create app-network
```

Run containers inside it:

```bash
docker run -d --network app-network --name db postgres
docker run -d --network app-network --name api backend-app
```

Now `api` can connect to:

```
db:5432
```

Docker handles DNS automatically.

---

### Best Practice

Always use user-defined networks for multi-container systems.

---

## 3️⃣ Host Network

### What It Does

The container shares the host’s network stack.

```bash
docker run --network host nginx
```

Now:

* no port mapping needed
* container uses host ports directly

---

### Pros

* higher performance
* no NAT overhead

---

### Cons

* no isolation
* port conflicts possible
* not supported on Docker Desktop (Mac/Windows)

Mostly used in specialized cases.

---

## 4️⃣ None Network

### What It Does

Disables networking completely.

```bash
docker run --network none nginx
```

The container:

* cannot access internet
* cannot communicate externally

Useful for security-sensitive workloads.

---

## Exposing Containers to the Host

Using port mapping:

```bash
docker run -p 8080:80 nginx
```

Format:

```
host_port:container_port
```

This allows external traffic to reach the container.

---

## Inspecting a Network

```bash
docker network inspect app-network
```

Shows:

* connected containers
* subnet
* gateway
* driver

Useful for debugging connectivity issues.

---

## Removing a Network

```bash
docker network rm app-network
```

Only possible if no containers are attached.

---

## Network Drivers Overview

| Driver  | Use Case                         |
| ------- | -------------------------------- |
| bridge  | Default container networking     |
| host    | Direct host networking           |
| none    | No networking                    |
| overlay | Multi-host communication (Swarm) |
| macvlan | Assign real MAC addresses        |

Overlay is used in clustered environments.

---

# Common Mistakes

### Using Default Bridge for Complex Systems

Limits service discovery.

---

### Using `localhost` Between Containers

Containers must use service names.

---

### Forgetting Port Mapping

Container runs but is unreachable.

---

## Interview Questions

### 1. What is the default Docker network?

**Answer:**
The default network is the bridge network.

---

### 2. Why are user-defined bridge networks preferred?

**Answer:**
They provide automatic DNS and better isolation.

---

### 3. What does host network mode do?

**Answer:**
It makes the container share the host’s network stack.

---

### 4. What happens if you use `--network none`?

**Answer:**
The container has no network connectivity.

---

### 5. How do you expose a container to the host?

**Answer:**
Using the `-p host_port:container_port` flag.

---

## Summary

* Docker networks enable container communication

* Bridge is default but limited

* User-defined bridge networks are recommended

* Host mode removes isolation

* Proper networking design is critical in backend systems

---