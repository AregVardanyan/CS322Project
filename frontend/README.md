# 🎓 Student Course Planner — Frontend (React)

This is the React frontend for the **Student Course Planner**, a smart assistant that generates optimized semester plans based on your degree curriculum, prerequisites, and personal preferences using Google Gemini 2.5.

---

## 🚀 Features

User authentication (JWT-based login & register)
atural language input for course planning
ptimized course recommendations powered by the backend 
Modern React UI with Axios and React Router


## 🛠️ Setup Instructions

### 1. Clone the Repository


git clone https://github.com/your-team/student-course-planner.git
cd student-course-planner/frontend

Install Dependencies

npm install

3. Start the Dev Server

npm run dev

The app will be running at http://localhost:5173

    ⚠Make sure your backend is running on http://localhost:8080 or update the Axios base URL in src/api/axios.js.

 Authentication

    Tokens (access & refresh) are stored in localStorage.

    On login, you'll be redirected to the planner.

    On logout (to be implemented), tokens are cleared