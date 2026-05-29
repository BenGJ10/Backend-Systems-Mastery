# Most Important `kubectl` Commands Cheat Sheet

## 1. Cluster Information

### Check cluster info

```bash
kubectl cluster-info
```

### Check Kubernetes version

```bash
kubectl version
```

### Show current context

```bash
kubectl config current-context
```

### List all contexts

```bash
kubectl config get-contexts
```

### Switch context

```bash
kubectl config use-context <context-name>
```

---

## 2. Listing & Viewing Resources

### List Nodes

```bash
kubectl get nodes
```

### List Pods

```bash
kubectl get pods
```

### List Pods in all namespaces

```bash
kubectl get pods -A
```

### List Deployments

```bash
kubectl get deployments
```

### List Services

```bash
kubectl get svc
```

### List Namespaces

```bash
kubectl get ns
```

### List Everything

```bash
kubectl get all
```

### Wide Output

Shows extra details like IPs and node names.

```bash
kubectl get pods -o wide
```

### Watch Live Changes

```bash
kubectl get pods -w
```

---

## 3. Detailed Information

### Describe Pod

```bash
kubectl describe pod <pod-name>
```

### Describe Deployment

```bash
kubectl describe deployment <deployment-name>
```

### Describe Node

```bash
kubectl describe node <node-name>
```

---

## 4. Creating Resources

### Create Deployment

```bash
kubectl create deployment nginx --image=nginx
```

### Expose Deployment as Service

```bash
kubectl expose deployment nginx --port=80 --type=NodePort
```

### Create Namespace

```bash
kubectl create namespace dev
```

---

## 5. Running Containers Quickly

### Run Temporary Pod

```bash
kubectl run nginx --image=nginx
```

### Run Interactive Ubuntu Container

```bash
kubectl run ubuntu --image=ubuntu -it -- bash
```

### Run Busybox for Debugging

```bash
kubectl run busybox --image=busybox -it -- sh
```

---

## 6. YAML-Based Operations

### Apply YAML File

(creates or updates)

```bash
kubectl apply -f app.yaml
```

### Apply Entire Directory

```bash
kubectl apply -f k8s/
```

### Create from YAML

```bash
kubectl create -f app.yaml
```

---

## 7. Deleting Resources

### Delete Pod

```bash
kubectl delete pod <pod-name>
```

### Delete Deployment

```bash
kubectl delete deployment <deployment-name>
```

### Delete Service

```bash
kubectl delete svc <service-name>
```

### Delete Namespace

```bash
kubectl delete ns <namespace-name>
```

### Delete Using YAML

```bash
kubectl delete -f app.yaml
```

### Delete Everything in Namespace

```bash
kubectl delete all --all
```

---

## 8. Logs & Debugging

### View Pod Logs

```bash
kubectl logs <pod-name>
```

### Follow Logs Live

```bash
kubectl logs -f <pod-name>
```

### Logs from Specific Container

```bash
kubectl logs <pod-name> -c <container-name>
```

---

## 9. Execute Commands Inside Containers

### Open Shell Inside Pod

```bash
kubectl exec -it <pod-name> -- bash
```

### If Bash Not Available

```bash
kubectl exec -it <pod-name> -- sh
```

### Run Single Command

```bash
kubectl exec <pod-name> -- ls
```

---

## 10. Scaling Applications

### Scale Deployment

```bash
kubectl scale deployment nginx --replicas=3
```

---

## 11. Updating Applications

### Set New Image

```bash
kubectl set image deployment/nginx nginx=nginx:latest
```

### Rollout Status

```bash
kubectl rollout status deployment/nginx
```

### Rollback Deployment

```bash
kubectl rollout undo deployment/nginx
```

### Rollout History

```bash
kubectl rollout history deployment/nginx
```

---

## 12. Port Forwarding

### Access Pod Locally

```bash
kubectl port-forward pod/<pod-name> 8080:80
```

### Access Service Locally

```bash
kubectl port-forward svc/<service-name> 8080:80
```

---

## 13. Copy Files

### Copy File to Pod

```bash
kubectl cp file.txt <pod-name>:/tmp/file.txt
```

### Copy File from Pod

```bash
kubectl cp <pod-name>:/tmp/file.txt ./file.txt
```

---

## 14. Namespaces

### Run Command in Namespace

```bash
kubectl get pods -n dev
```

### Set Default Namespace

```bash
kubectl config set-context --current --namespace=dev
```

---

## 15. Resource Monitoring

### Pod Resource Usage

(requires Metrics Server)

```bash
kubectl top pods
```

### Node Resource Usage

```bash
kubectl top nodes
```

---

## 16. Useful Shortcuts

| Full Form   | Shortcut |
| ----------- | -------- |
| pods        | po       |
| services    | svc      |
| deployments | deploy   |
| namespaces  | ns       |

Example:

```bash
kubectl get po
kubectl get svc
kubectl get deploy
```

---

## 17. Generate YAML Quickly

### Dry Run YAML Generation

```bash
kubectl create deployment nginx --image=nginx --dry-run=client -o yaml
```

Save to file:

```bash
kubectl create deployment nginx --image=nginx --dry-run=client -o yaml > deployment.yaml
```

---

## 18. Editing Live Resources

### Edit Deployment

```bash
kubectl edit deployment nginx
```

### Edit Service

```bash
kubectl edit svc nginx
```

---

## 19. Common Real Workflow

### Deploy Application

```bash
kubectl create deployment myapp --image=nginx
```

### Verify Pod

```bash
kubectl get pods
```

### Expose App

```bash
kubectl expose deployment myapp --port=80 --type=NodePort
```

### Check Service

```bash
kubectl get svc
```

### Scale App

```bash
kubectl scale deployment myapp --replicas=3
```

### View Logs

```bash
kubectl logs -f <pod-name>
```

### Delete Everything

```bash
kubectl delete deployment myapp
kubectl delete svc myapp
```

---

## 20. Extremely Important Interview Commands

### Imperative Pod Creation

```bash
kubectl run nginx --image=nginx
```

### Generate YAML Without Creating

```bash
kubectl run nginx --image=nginx --dry-run=client -o yaml
```

### Explain Resource Fields

```bash
kubectl explain pod
```

Nested fields:

```bash
kubectl explain pod.spec
```

---

## Golden Rule

Most Kubernetes work revolves around:

```bash
kubectl get
kubectl describe
kubectl apply
kubectl delete
kubectl logs
kubectl exec
```

---

