# ComplaintDesk - Quick Start Guide

## 🚀 Quick Setup (5 Minutes)

### Step 1: Database Setup
```sql
-- Open MySQL and run:
CREATE DATABASE Complaint_Desk_db;
```

### Step 2: Update Database Password
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Run the Application
```powershell
cd ComplaintDesk
.\mvnw.cmd spring-boot:run
```

### Step 4: Access the Application
Open browser: http://localhost:8080

---

## 📱 Using the Application

### 1️⃣ Register Users
- Go to http://localhost:8080/register
- Create users with different roles:
  - **User**: Can create complaints
  - **Staff**: Can be assigned to complaints
  - **Admin**: Can assign complaints to staff

### 2️⃣ Login
- Go to http://localhost:8080/login
- Use the email and password you registered with

### 3️⃣ Create a Complaint (as User)
- Click "Complaints" → "Create New Complaint"
- Fill in: Title, Description, Category
- Enter your User ID (check /api/users/ to see your ID)
- Submit

### 4️⃣ Assign Complaint to Staff (as Admin)
- Click "Assignments" → "Create New Assignment"
- Select the complaint and staff member
- Submit

### 5️⃣ Submit Feedback (as User)
- Click "Feedback" → "Submit New Feedback"
- Select complaint and rate it (1-5 stars)
- Add comments and submit

---

## 🌐 Remote Access Setup

### Local Network Access
1. Find your local IP: `ipconfig` (look for IPv4 Address)
2. Share with others: `http://YOUR_IP:8080`
3. Ensure firewall allows port 8080

### Internet Access (Basic Options)

**Option A: ngrok (Easiest - for testing)**
```powershell
# Download ngrok from ngrok.com
ngrok http 8080
# Use the forwarding URL provided
```

**Option B: Port Forwarding on Router**
1. Login to your router admin panel
2. Forward port 8080 to your computer's local IP
3. Share your public IP: `http://YOUR_PUBLIC_IP:8080`

**Option C: Deploy to Cloud (Heroku/AWS/Azure)**
- Follow cloud provider's Spring Boot deployment guides
- Update `application.properties` with cloud database URL

---

## 📊 REST API Usage

### Get All Users
```bash
curl http://localhost:8080/api/users/
```

### Register User via API
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "role": "user"
  }'
```

### Get All Complaints
```bash
curl http://localhost:8080/complaints/api/all
```

### Create Complaint via API
```bash
curl -X POST http://localhost:8080/complaints/api/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Broken AC",
    "description": "AC not working in Room 101",
    "category": "Infrastructure",
    "createdBy": {"id": 1}
  }'
```

---

## 🔧 Troubleshooting

### Problem: "Can't connect to database"
**Solution**: 
1. Start MySQL: `mysql -u root -p`
2. Create database: `CREATE DATABASE Complaint_Desk_db;`
3. Check password in `application.properties`

### Problem: "Port 8080 already in use"
**Solution**: 
- Change port in `application.properties`: `server.port=8081`
- Or stop the process using port 8080

### Problem: "Build failed"
**Solution**: 
```powershell
# Clean and rebuild
.\mvnw.cmd clean install
```

### Problem: "Can't access from other devices"
**Solution**: 
- Check firewall settings (allow port 8080)
- Verify `server.address=0.0.0.0` in `application.properties`

---

## 📝 Default Categories

When creating complaints, use these categories:
- Infrastructure
- Academics
- Hostel
- IT Services
- Transport
- Others

## 🎯 Status Values

### Complaint Status:
- `open` - New complaint
- `in progress` - Being worked on
- `solved` - Resolved

### Assignment Status:
- `assigned` - Just assigned
- `in progress` - Staff is working
- `completed` - Task done

---

## 💡 Tips

1. **Find User IDs**: Visit http://localhost:8080/api/users/ to see all user IDs
2. **View All Data**: Use the REST API endpoints to view JSON data
3. **Simple UI**: All pages have navigation links at the top
4. **Database Auto-Create**: Tables are created automatically on first run

---

## 🔒 Security Note

⚠️ **This is a basic implementation for learning/testing.**

For production use, add:
- Password encryption (BCrypt)
- JWT authentication
- Spring Security
- Input validation
- HTTPS/SSL

---

## 📞 Need Help?

1. Check the main README.md for detailed documentation
2. Review error logs in the console
3. Verify MySQL is running
4. Check application.properties configuration

---

**Happy Coding! 🎉**
