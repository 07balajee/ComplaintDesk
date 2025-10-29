# 🚀 ComplaintDesk - Deployment Instructions

## ✅ Project is Ready to Deploy!

The application has been successfully built and is ready for local or remote deployment.

---

## 📦 What You Have

**JAR File Location:**
```
ComplaintDesk/target/ComplaintDesk-0.0.1-SNAPSHOT.jar
```

This is a standalone executable JAR that contains everything needed to run the application.

---

## 🏠 Local Deployment

### Option 1: Run with Maven (Development)
```powershell
cd E:\ComplaintDesk\ComplaintDesk
.\mvnw.cmd spring-boot:run
```

### Option 2: Run JAR directly (Production)
```powershell
cd E:\ComplaintDesk\ComplaintDesk
java -jar target/ComplaintDesk-0.0.1-SNAPSHOT.jar
```

**Access:** http://localhost:8080

---

## 🌐 Remote Deployment Options

### Option A: Local Network Deployment

**1. Run the application:**
```powershell
java -jar ComplaintDesk-0.0.1-SNAPSHOT.jar
```

**2. Find your local IP address:**
```powershell
ipconfig
# Look for IPv4 Address (e.g., 192.168.1.100)
```

**3. Configure Windows Firewall:**
```powershell
# Open PowerShell as Administrator
New-NetFirewallRule -DisplayName "ComplaintDesk" -Direction Inbound -LocalPort 8080 -Protocol TCP -Action Allow
```

**4. Share the URL with others on your network:**
```
http://YOUR_LOCAL_IP:8080
# Example: http://192.168.1.100:8080
```

---

### Option B: Internet Deployment (Using ngrok)

**1. Download ngrok:**
- Visit: https://ngrok.com/download
- Extract ngrok.exe

**2. Run your application:**
```powershell
java -jar ComplaintDesk-0.0.1-SNAPSHOT.jar
```

**3. In another terminal, run ngrok:**
```powershell
ngrok http 8080
```

**4. Share the ngrok URL:**
```
Ngrok will display a URL like:
https://abc123.ngrok.io

Share this URL with anyone on the internet!
```

**Note:** Free ngrok URLs expire when you close the terminal. Get a free account for persistent URLs.

---

### Option C: Router Port Forwarding

**1. Find your public IP:**
- Visit: https://whatismyipaddress.com/

**2. Login to your router:**
- Usually: http://192.168.1.1 or http://192.168.0.1
- Check your router manual for login details

**3. Configure port forwarding:**
- Forward external port 8080 to internal IP:8080
- Protocol: TCP
- Target: Your computer's local IP

**4. Share your public URL:**
```
http://YOUR_PUBLIC_IP:8080
```

**Security Note:** This exposes your application to the internet. Only use for testing or with proper security measures.

---

### Option D: Cloud Deployment

#### Heroku Deployment

**1. Create Procfile:**
```
web: java -jar target/ComplaintDesk-0.0.1-SNAPSHOT.jar
```

**2. Create Heroku app:**
```bash
heroku create complaintdesk-app
```

**3. Add MySQL addon:**
```bash
heroku addons:create cleardb:ignite
```

**4. Update application.properties:**
```properties
spring.datasource.url=${CLEARDB_DATABASE_URL}
```

**5. Deploy:**
```bash
git push heroku main
```

---

#### AWS Deployment (EC2)

**1. Launch EC2 instance:**
- Choose Ubuntu 22.04 LTS
- Instance type: t2.micro (free tier)
- Configure security group: Allow port 8080

**2. Connect to instance:**
```bash
ssh -i your-key.pem ubuntu@YOUR_EC2_IP
```

**3. Install Java:**
```bash
sudo apt update
sudo apt install openjdk-21-jdk -y
```

**4. Install MySQL:**
```bash
sudo apt install mysql-server -y
sudo mysql -u root
CREATE DATABASE Complaint_Desk_db;
CREATE USER 'complaintdesk'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL ON Complaint_Desk_db.* TO 'complaintdesk'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

**5. Upload JAR file:**
```bash
scp -i your-key.pem ComplaintDesk-0.0.1-SNAPSHOT.jar ubuntu@YOUR_EC2_IP:~
```

**6. Run application:**
```bash
java -jar ComplaintDesk-0.0.1-SNAPSHOT.jar
```

**7. Access:**
```
http://YOUR_EC2_PUBLIC_IP:8080
```

---

#### Azure Deployment

**1. Create Azure Web App:**
```bash
az webapp create --resource-group myResourceGroup --plan myAppServicePlan --name complaintdesk --runtime "JAVA:21-java21"
```

**2. Deploy JAR:**
```bash
az webapp deploy --resource-group myResourceGroup --name complaintdesk --src-path ComplaintDesk-0.0.1-SNAPSHOT.jar --type jar
```

**3. Configure database connection string in Azure Portal**

**4. Access:**
```
https://complaintdesk.azurewebsites.net
```

---

## 🗄️ Database Setup for Remote Deployment

### Option 1: Remote MySQL Server

**Update `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://REMOTE_HOST:3306/Complaint_Desk_db?useSSL=true&serverTimezone=UTC
spring.datasource.username=remote_user
spring.datasource.password=remote_password
```

### Option 2: Cloud Database

**MySQL Cloud Options:**
- AWS RDS
- Azure Database for MySQL
- Google Cloud SQL
- PlanetScale
- ClearDB (Heroku)

**Example for AWS RDS:**
```properties
spring.datasource.url=jdbc:mysql://your-rds-endpoint.amazonaws.com:3306/Complaint_Desk_db
spring.datasource.username=admin
spring.datasource.password=your_password
```

---

## 🔧 Configuration for Remote Deployment

### Environment Variables

Set these environment variables instead of hardcoding in properties:

```powershell
# Windows PowerShell
$env:DB_URL="jdbc:mysql://localhost:3306/Complaint_Desk_db"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_password"
$env:SERVER_PORT="8080"
```

```bash
# Linux/Mac
export DB_URL="jdbc:mysql://localhost:3306/Complaint_Desk_db"
export DB_USERNAME="root"
export DB_PASSWORD="your_password"
export SERVER_PORT="8080"
```

**Update application.properties:**
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
server.port=${SERVER_PORT:8080}
```

---

## 🔒 Security for Production

### Before deploying to production:

**1. Enable HTTPS:**
```properties
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your_password
server.ssl.key-store-type=PKCS12
```

**2. Add Spring Security (pom.xml):**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**3. Hash passwords:**
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

**4. Enable CSRF protection**
**5. Use JWT for authentication**
**6. Add input validation**

---

## 📊 Monitoring & Logging

### Access Spring Boot Actuator

**Endpoint:** http://localhost:8080/actuator

**Available endpoints:**
- `/actuator/health` - Application health
- `/actuator/info` - Application info
- `/actuator/metrics` - Application metrics

### View Logs

```powershell
# Real-time logs
java -jar ComplaintDesk-0.0.1-SNAPSHOT.jar > logs.txt

# Or use logging framework
java -jar ComplaintDesk-0.0.1-SNAPSHOT.jar --logging.file.name=application.log
```

---

## 🧪 Testing Remote Deployment

### Test Checklist:
- ✅ Application starts without errors
- ✅ Database connection successful
- ✅ Login page loads
- ✅ Register new user works
- ✅ Create complaint works
- ✅ REST API responds
- ✅ Can access from other devices

### Test Commands:

**Test health endpoint:**
```bash
curl http://YOUR_IP:8080/actuator/health
```

**Test API:**
```bash
curl http://YOUR_IP:8080/api/users/
```

**Test from another device:**
```
Open browser: http://YOUR_IP:8080
```

---

## 🔄 Running as a Service

### Windows Service (using NSSM)

**1. Download NSSM:**
- https://nssm.cc/download

**2. Install service:**
```powershell
nssm install ComplaintDesk "C:\Program Files\Java\jdk-21\bin\java.exe" "-jar C:\path\to\ComplaintDesk-0.0.1-SNAPSHOT.jar"
```

**3. Start service:**
```powershell
nssm start ComplaintDesk
```

### Linux Service (systemd)

**1. Create service file:**
```bash
sudo nano /etc/systemd/system/complaintdesk.service
```

**2. Add content:**
```ini
[Unit]
Description=ComplaintDesk Application
After=network.target

[Service]
User=ubuntu
ExecStart=/usr/bin/java -jar /home/ubuntu/ComplaintDesk-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always

[Install]
WantedBy=multi-user.target
```

**3. Enable and start:**
```bash
sudo systemctl enable complaintdesk
sudo systemctl start complaintdesk
sudo systemctl status complaintdesk
```

---

## 📱 Quick Access URLs

Once deployed, share these URLs:

- **Home/Login:** http://YOUR_IP:8080/
- **Register:** http://YOUR_IP:8080/register
- **Complaints:** http://YOUR_IP:8080/complaints
- **Assignments:** http://YOUR_IP:8080/assignments
- **Feedback:** http://YOUR_IP:8080/feedback
- **API:** http://YOUR_IP:8080/api/users/

---

## ❓ Troubleshooting Remote Access

### Problem: Can't access from other devices

**Solutions:**
1. Check firewall rules
2. Verify server.address=0.0.0.0 in properties
3. Confirm application is running
4. Test with: `curl http://localhost:8080`

### Problem: Connection timeout

**Solutions:**
1. Check if port 8080 is open
2. Verify router port forwarding
3. Check cloud security groups
4. Test locally first

### Problem: Database connection failed

**Solutions:**
1. Ensure MySQL is running
2. Check database credentials
3. Verify database URL
4. Check network connectivity

---

## 🎉 You're Ready to Deploy!

**Choose your deployment method:**
1. **Quick Test:** Use ngrok (5 minutes)
2. **Local Network:** Configure firewall (10 minutes)
3. **Internet:** Port forwarding (30 minutes)
4. **Cloud:** AWS/Azure/Heroku (1 hour)

**For help, refer to:**
- README.md - Full documentation
- QUICKSTART.md - Quick reference
- SUMMARY.md - Implementation overview

---

**Happy Deploying! 🚀**
