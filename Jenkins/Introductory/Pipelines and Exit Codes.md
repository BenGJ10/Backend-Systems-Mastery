# Different Pipeline Stages and Exit Codes in Jenkins

## Overview

A **Jenkins pipeline** is a sequence of automated steps used to build, test, and deploy applications.

These steps are organized into **stages**, where each stage represents a logical phase in the software delivery process.

Each stage runs commands or scripts, and the result of these commands is communicated through **exit codes**, which indicate whether the step succeeded or failed.

Understanding **pipeline stages and exit codes** is essential for designing reliable CI/CD pipelines.

---

# Pipeline Stages in Jenkins

## What is a Stage?

A **stage** represents a major step in the CI/CD workflow.

Stages help:

* organize the pipeline
* visualize the build process
* detect where failures occur
* enable parallel execution

Each stage contains one or more **steps** that perform specific tasks.

---

## Typical Pipeline Stages

Most CI/CD pipelines follow a standard set of stages.

| Stage    | Purpose                                  |
| -------- | ---------------------------------------- |
| Checkout | Fetch source code from the repository    |
| Build    | Compile the application                  |
| Test     | Run automated tests                      |
| Package  | Create deployable artifacts              |
| Deploy   | Deploy the application to an environment |

These stages form the backbone of most automated pipelines.

---

## Example Jenkins Pipeline

Below is a simple Jenkins pipeline with multiple stages:

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/example/project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
```

Each stage runs sequentially unless configured otherwise.

---

## Common Pipeline Stages Explained

### 1. Checkout Stage

The **checkout stage** retrieves source code from a version control system.

Example:

```groovy
git 'https://github.com/example/project.git'
```

Supported repositories include:

* GitHub
* GitLab
* Bitbucket

This ensures the latest code is used for the build.

---

### 2. Build Stage

The **build stage** compiles the source code and prepares the application.

Examples:

| Language | Build Command                     |
| -------- | --------------------------------- |
| Java     | `mvn compile`                     |
| Node.js  | `npm install`                     |
| Python   | `pip install -r requirements.txt` |
| Go       | `go build`                        |

This stage transforms source code into executable or intermediate artifacts.

---

### 3. Test Stage

The **test stage** runs automated tests to verify application functionality.

Examples:

```bash
mvn test
npm test
pytest
```

Types of tests that may run:

* unit tests
* integration tests
* static code analysis

If tests fail, the pipeline typically stops.

---

### 4. Package Stage

The **package stage** bundles the application into a deployable format.

Examples:

| Application        | Package Type      |
| ------------------ | ----------------- |
| Java app           | JAR / WAR         |
| Node.js            | production bundle |
| Dockerized service | Docker image      |

Example command:

```bash
mvn package
```

This generates a deployable artifact.

---

### 5. Deploy Stage

The **deploy stage** delivers the application to a target environment.

Examples:

* deploy to a Kubernetes cluster
* upload artifacts to a server
* push Docker images to a registry

Example:

```bash
docker push my-app:latest
```

Deployments may target:

* staging
* production
* testing environments

---

## Understanding Exit Codes

### What is an Exit Code?

An **exit code** (or return code) is a number returned by a command after execution.

It indicates whether the command succeeded or failed.

| Exit Code | Meaning |
| --------- | ------- |
| 0         | Success |
| Non-zero  | Failure |

Most CI systems rely on exit codes to determine build status.

---

### How Jenkins Uses Exit Codes

When Jenkins executes a command in a pipeline:

```groovy
sh 'mvn test'
```

Jenkins checks the command's exit code.

If:

```
exit code = 0
```

The stage succeeds.

If:

```
exit code ≠ 0
```

The stage fails and the pipeline stops.

---

### Example: Successful Command

```bash
echo "Build successful"
```

Exit code:

```
0
```

Jenkins marks the step as **successful**.

---

### Example: Failed Command

```bash
exit 1
```

Exit code:

```
1
```

Jenkins marks the stage as **failed**.

---

### Common Exit Codes

Different programs use exit codes to indicate specific failures.

| Exit Code | Meaning                     |
| --------- | --------------------------- |
| 0         | Success                     |
| 1         | General error               |
| 2         | Misuse of shell command     |
| 127       | Command not found           |
| 130       | Script terminated by Ctrl+C |

Understanding these codes helps diagnose pipeline failures.

---

### Handling Exit Codes in Jenkins

Sometimes pipelines need to handle failures gracefully.

Example:

```groovy
sh 'npm test || true'
```

This prevents the pipeline from failing even if tests fail.

---

### Capturing Exit Codes

Jenkins can capture exit codes from shell commands.

Example:

```groovy
script {
    def status = sh(script: 'npm test', returnStatus: true)

    if (status != 0) {
        echo "Tests failed"
    }
}
```

This allows custom logic based on command results.

---


## Interview Questions

### 1. What is a pipeline stage in Jenkins?

**Answer:**

A stage represents a logical step in a CI/CD pipeline, such as build, test, or deploy.

---

### 2. What is an exit code?

**Answer:**

An exit code is a numeric value returned by a command indicating whether the command succeeded or failed.

---

### 3. What exit code indicates success?

**Answer:**

Exit code **0** indicates successful execution.

---

### 4. How does Jenkins detect build failures?

**Answer:**

Jenkins checks the exit code of commands executed in the pipeline. Non-zero exit codes mark the stage as failed.

---

### 5. Why are pipeline stages important?

**Answer:**

They organize CI/CD workflows, improve visibility, and help quickly identify failures.

---

## Summary

* Jenkins pipelines are divided into **stages**

* Each stage represents a step such as build, test, or deploy

* Commands executed in stages return **exit codes**

* Exit code **0 means success**, while non-zero values indicate failure

* Jenkins uses exit codes to determine pipeline status

* Proper stage design improves pipeline reliability and debugging

---
