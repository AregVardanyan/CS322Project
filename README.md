Student Course Planner – Final Project with Gemini 2.5

Overview
--------
This project implements a smart assistant called Student Course Planner using Google Gemini 2.5. It helps students generate optimized semester schedules based on:

- Degree curriculum
- Course prerequisites
- Student preferences (e.g., workload, topic interests, availability)

The system accepts natural language input and returns personalized course schedule recommendations.

Technical Stack
---------------
- Backend: Java (Spring Boot)
- Frontend: Optional CLI/Web/Chatbot
- LLM: Google Gemini 2.5 API
- Version Control: GitHub (branches, PRs, reviews)

API Endpoints
-------------

1. POST /auth/login
   Description: Authenticates user and returns JWT tokens.
   Request Body:
     {
       "username": "student1",
       "password": "securePassword"
     }
   Response:
     {
       "accessToken": "jwt-token",
       "refreshToken": "jwt-refresh-token"
     }

2. POST /auth/refresh
   Description: Refreshes the access token using a valid refresh token.
   Request Body:
     {
       "refreshToken": "jwt-refresh-token"
     }
   Response:
     {
       "accessToken": "new-access-token"
     }

3. POST /auth/register
   Description: Registers a new user.
   Request Body:
     {
       "username": "student1",
       "password": "securePassword",
       "email": "student1@example.com"
     }
   Response:
     "User registered successfully"

4. POST /api/plan
   Description: Generates a personalized semester course plan based on preferences.
   Headers:
     Authorization: Bearer <access-token>
   Request Body:
     {
       "prefTopics": ["AI", "Databases"],
       "year": 3,
       "semester": "FALL",
       "prefLoad": "LIGHT"
     }
   Response:
     "Recommended semester plan with course codes and descriptions"

Data Model Summary
------------------

Course:
- id
- faculty
- code
- prerequisites (list of Courses)
- topic
- sections (list of Section)

Section:
- id
- course (Course)
- code
- year
- semester (enum)
- schedule (enum)
- hours (enum)

UserSection:
- id
- user (User)
- section (Section)
- grade (enum)

Gemini 2.5 Integration
----------------------
- Natural language prompts are sent to Gemini via PlannerService.
- Gemini 2.5 processes preferences, curriculum, and prerequisites.
- A structured semester plan is returned.

GitHub Workflow
---------------
- Each team member: at least 2 commits
- 5 PR reviews per person minimum
- No direct commits to main branch
- All features/fixes via PRs
- Do not delete merged branches

Deliverables
------------
1. Functional MVP of the planner
2. Public GitHub repository
3. Complete README file
4. Final demo presentation (5–7 minutes per team)

Bonus Features
--------------
- Dynamic adjustment from real university data
- Follow-up support with elective suggestions
- Export semester plan to .ics calendar format

Goals
-----
- Apply LLMs in real-world software
- Practice collaborative software engineering
- Build a functional, user-centered course planning tool
