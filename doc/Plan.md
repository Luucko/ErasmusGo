# **Plan for ErasmusGo Project**  

## **Goal for the Next Six Days**  
Develop a functional prototype with key features and prepare for a polished presentation.  

---

## **Sprint Plan**  

### **Sprint 1: Initial Setup and Planning**  
**Day 0 (Today): Initial Preparation**  
- **Goals:**  
  - Define project objectives, user stories, and use cases.  
  - Organize the team and assign roles:  
    - Lucas Viaene: Project Manager/Coordinator  
    - Paul Blankenhorn: Developer/Tech Lead  
    - Bastien De Meulenaere: UX/UI and Documentation Specialist  
  - Create project structure:  
    - Set up the app in Android Studio with a basic menu layout.  
    - Initialize Firebase for authentication and database integration.  

- **Deliverables:**  
  - Completed project plan with defined objectives, user stories, and use cases.  
  - Basic app structure with menu items visible.  
  - Firebase configured and ready for integration.  

---

### **Sprint 2: Firebase Integration and Core Features**  
**Day 1: Firebase Integration and Authentication**  
- **Goals:**  
  - Implement user registration and login/logout functionality using Firebase.  
  - Configure Firebase authentication and link it to the app's UI.  
  - Create basic user profiles in Firebase (fields: name, email, nationality).  

- **Deliverables:**  
  - Functional login and registration screens.  
  - Users can log in, register, and log out successfully.  

---

### **Sprint 3: Core Functionalities - Calendar and Maps**  
**Day 2-3: School Calendar and Campus Map**  
- **Goals:**  
  - **Day 2:**  
    - Build the "School Calendar" feature:  
      - Use Firebase to fetch and display calendar data.  
      - Create a UI for displaying events and holidays.  
      - Allow tapping on dates to view event details.  
    - Start developing the "Campus Map" feature:  
      - Create a basic UI for the map screen.  
  - **Day 3:**  
    - Complete the "Campus Map" functionality:  
      - Add a search bar to locate buildings/classrooms.  
      - Display selected rooms/buildings on the map with directions (if possible).  

- **Deliverables:**  
  - A working school calendar feature integrated with Firebase.  
  - A functional campus map with a search feature for buildings/rooms.  

---

### **Sprint 4: Language, Transport, and Events**  
**Day 4-5: Language Learning, Public Transport, and Events**  
- **Goals:**  
  - **Day 4:**  
    - Implement the "Learn Portuguese" feature:  
      - Display a list of basic Portuguese phrases and external resources (e.g., links).  
    - Develop the "Public Transport" feature:  
      - Show transport options and basic schedules.  
  - **Day 5:**  
    - Create the "Events" feature:  
      - Fetch and display events using Firebase.  
      - Enable students to register for events and view their registrations.  

- **Deliverables:**  
  - A functional "Learn Portuguese" screen with external links.  
  - Working "Public Transport" and "Events" features.  

---

### **Sprint 5: Refinement and Presentation Preparation**  
**Day 6: Final Touches and Presentation Prep**  
- **Goals:**  
  - Conduct user testing on all implemented features and fix major bugs.  
  - Polish the app’s UI (icons, colors, consistency).  
  - Create a polished Figma mockup for any incomplete features.  
  - Prepare a 5-minute presentation:  
    - Highlight key features with screenshots and/or a live demo.  
    - Include diagrams (context, ER, and sequence diagrams).  

- **Deliverables:**  
  - Bug-free, presentation-ready app with essential features.  
  - Finalized presentation materials (slides, diagrams, demo).  

---

## **Detailed Checklist for Each Sprint**  

### **Sprint 1 Checklist: Initial Setup**  
- [ ] Define user stories and use cases.  
- [ ] Assign team roles and responsibilities.  
- [ ] Set up the project in Android Studio.  
- [ ] Implement basic menu structure in the app.  
- [ ] Initialize Firebase for backend functionality.  

---

### **Sprint 2 Checklist: Firebase Integration**  
- [ ] Set up Firebase authentication (email/password).  
- [ ] Create Firebase rules to ensure security (e.g., authenticated users only).  
- [ ] Implement login, registration, and logout functionality.  
- [ ] Test registration and login flows with sample users.  

---

### **Sprint 3 Checklist: Calendar and Maps**  
**Calendar:**  
- [ ] Create Firebase database structure for calendar events (e.g., date, title, description).  
- [ ] Implement a calendar view UI.  
- [ ] Enable users to view events for specific dates.  

**Campus Map:**  
- [ ] Develop a map screen layout.  
- [ ] Add functionality to search for rooms/buildings.  
- [ ] Test and refine the map search and display functionality.  

---

### **Sprint 4 Checklist: Language, Transport, and Events**  
**Learn Portuguese:**  
- [ ] Display a list of common phrases in Portuguese.  
- [ ] Provide links to external resources for further learning.  

**Public Transport:**  
- [ ] Fetch and display transport routes/schedules (hardcoded or external API).  
- [ ] Ensure clear presentation of transport options for campus routes.  

**Events:**  
- [ ] Create Firebase structure for event data (e.g., title, date, description, attendees).  
- [ ] Allow students to register for events and view a list of their registrations.  

---

### **Sprint 5 Checklist: Refinement and Presentation Prep**  
- [ ] Review all features for functionality and fix major bugs.  
- [ ] Update UI for consistency and polish (e.g., navigation, icons, branding).  
- [ ] Finalize diagrams (context, ER, sequence).  
- [ ] Prepare slides/screenshots for key features.  
- [ ] Rehearse the 5-minute demo to ensure smooth delivery.  

---

## **Expected Outcomes**  
By the end of the six days:  
- A functional prototype of ErasmusGo with core features (login, calendar, map, events).  
- Polished presentation materials and a clear demonstration of the app.  
- A clear roadmap for future iterations (e.g., deferred features).  
