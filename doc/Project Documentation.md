# ErasmusGo

## **Team Organization**
To ensure clarification on roles and responsibilities, we devided our team into three roles:

1. **Lucas Viaene: Project Manager/Coordinator:** 
   - Manages the timeline, organizes sprints, and ensures everyone is on track.
   - Oversees documentation and merges everyone's work into a single report.
   
2. **Paul Blankenhorn: Developer/Tech Lead:**
   - Focuses on the mobile app’s coding and technical aspects.
   - Prepares diagrams like sequence diagrams and models closely tied to development.

3. **Bastien De Meulenaere: UX/UI and Documentation Specialist:**
   - Creates wireframes/mockups, diagrams, and user stories.
   - Supports documentation, testing, and any needed refinements.

---

## **User Stories**
To correctly define our work, we created user stories for the different stakeholders that could possibly use our application. Using the MoSCoW approach, we prioritize these user stories within our project scope:

### **Must have**: Essential to launch the app.
- As a new Erasmus student, I want to be guided through the first steps I need to complete on campus, such as registering and activating my personal account, so that I can settle in quickly.
- As a new Erasmus student, I want to be able to login to my personal account
- As an administrator, I want to be able to change a student’s information if necessary, so that the data remains accurate. 

### **Should have**: Useful features.
- As a new Erasmus student, I want to get in contact with my professors before attending the first lecture, so that I can clarify any doubts about the course.
- As a student, I want to access a calendar with holidays and important dates, so that I stay organized.
- As a non-native Portuguese-speaking student, I want to learn some basic Portuguese to survive and to receive information on how to learn more Portuguese.
- As a student, I want to find out how I can get to school using public transport, so I can plan my commute.
- As an administrator, I want to be able to add new upcoming events, so that the Erasmus students can see them in their calendars. 

### **Could have**: Nice-to-have features.
- As a new Erasmus student, I want to locate classrooms on a map, so that I can find my classes easily.
- As a student, I want to view a list of upcoming events, so I can participate and meet others.
- As a student from another country, I want to find others from my country, so that I can connect with them.
- As a student, I want to know what discounts I qualify for by being a student, so that I can save money.

### **Won’t have** (for now): Features you defer to later iterations.
- As a student, I want to recover my password if I forget it, so that I can regain access to my account without contacting an administrator.

---

## System Structure
### Landing Page:
#### 1. Login button (leads to home screen) 
#### 2. I am a new student (leads to account setup screen) 

### Main Menu:
#### 1. Profile (leads to profile information screen) 
#### 2. Communication (category)
- Contact Teachers (find contact information of your teachers)
- Find Peers (find students from same country)
#### 3. Campus Information (category)
- Academic Calendar (show academic year calendar)
- Map of Buildings and Rooms (show map of classes/rooms and buildings)
#### 4. Transportation & Discounts (category)
- Public Transportation (shows public transportation options and availability)
- Student Discounts (shows discount student qualify for) 
#### 5. Learning & Events (category)
- Learn Portuguese (Display basic portugese words/sentences and refer to portugese learning platform) 
- Upcoming Events & Activities (display upcoming events & activities)

---

## Use Cases
Here’s a list of use cases, defined in detail.

### **Use Case: Getting the Student Account Ready** (Must Have)
- **Actor**: New Erasmus Student  
- **Goal**: Guide new students through essential steps like registration and account setup.  
- **Preconditions**: Student has installed the app.  
- **Main Flow**:
  1. Student selects "How do I activate my account?" on the landing page.
  2. App guides the student through creating or activating their account.
- **Postconditions**: Student has a working account and can login.

### **Use Case: Communication with Teachers** (Should Have)
- **Actor**: Student  
- **Goal**: Allow students to view teacher contact details.  
- **Preconditions**: Student is logged into the application.  
- **Main Flow**:
  1. Student navigates to "Contact Teachers" section inside the "Communication" category.
  2. Application displays a list of teachers with their details (name, email, office number).
  3. Student clicks a teacher’s name to see additional information (optional).  
- **Postconditions**: Student retrieves teacher’s contact details.  
- **Alternative Path**: If the student is not logged in, they are redirected to the login screen.

### **Use Case: School Calendar** (Should Have)
- **Actor**: Student  
- **Goal**: Provide an accessible school calendar with important dates.  
- **Preconditions**: Administrator has updated the calendar.  
- **Main Flow**:
  1. Student navigates to the "Academic Calendar" section inside the "Campus Information" category.
  2. App displays a calendar view with marked events and holidays.
  3. Student taps on a date to view details.  
- **Postconditions**: Student views the calendar.

### **Use Case: Public Transportation Information** (Should Have)
- **Actor**: Student  
- **Goal**: Help students find public transport options to campus.  
- **Preconditions**: App has data on local transport routes.  
- **Main Flow**:
  1. Student accesses the "Public Transport" section inside the "Transportation & Discounts" category.
  2. App displays transport routes and schedules.  
- **Postconditions**: Student plans their journey to campus.  

### **Use Case: Language Information** (Should Have)
- **Actor**: Student  
- **Goal**: Provide resources for learning basic Portuguese.  
- **Preconditions**: App is connected to external resources.  
- **Main Flow**:
  1. Student opens the "Learn Portuguese" section inside the "Learning & Events" category.
  2. App displays basic phrases and external links (e.g., to PracticePortuguese.com).  
- **Postconditions**: Student gains access to Portuguese learning materials.  

### **Use Case: Map of Buildings and Rooms** (Could Have)
- **Actor**: Student  
- **Goal**: Allow students to search for and locate specific classrooms or facilities on campus.  
- **Preconditions**: Student accesses the application.  
- **Main Flow**:
  1. Student navigates to the "Map of Buildings and Rooms" section inside the "Campus Information" category.
  2. Student searches for a room or building.
  3. Application displays the location on a map with directions.  
- **Postconditions**: Student views the desired location on the map.  
- **Alternative Path**: If the searched room/building doesn’t exist, display a "Room not found" message.

### **Use Case: Events Together (Freetime)** (Could Have)
- **Actor**: Student  
- **Goal**: View and register for upcoming events.  
- **Preconditions**: Administrator has uploaded event information.  
- **Main Flow**:
  1. Student accesses the "Upcoming Events & Activities" section inside the "Learning & Events" category.
  2. App displays a list of events with descriptions and dates.
  3. Student registers for an event.  
- **Postconditions**: Student successfully registers for an event.  

### **Use Case: Connecting with Students from the Same Country** (Could Have)
- **Actor**: Student  
- **Goal**: Allow students to connect with peers from their home country.  
- **Preconditions**: Student profile includes nationality.  
- **Main Flow**:
  1. Student accesses the "Find Peers" section inside the "Communication" category.
  2. App displays a list of students with the same nationality.
  3. Student selects a peer to view their profile.  
- **Postconditions**: Student connects with another peer.  

### **Use Case: Discounts List** (Could Have)
- **Actor**: Student  
- **Goal**: Show a list of student discounts.  
- **Preconditions**: Discounts are predefined in the database.  
- **Main Flow**:
  1. Student accesses the "Student Discounts" section inside the "Transportation & Discounts" category.
  2. App displays a categorized list of discounts (e.g., food, transport, activities).  
- **Postconditions**: Student views discount information.
