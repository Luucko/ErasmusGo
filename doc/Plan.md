# Plan for ErasmusGo project

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

## **Step 2: Define User Stories**
**Yes, starting with User Stories is a great idea!** It will help prioritize and define your work. Write these using the template:

- **As a [type of user], I want to [goal], so that I can [benefit].**

### Example User Stories for ErasmusGo:
1. **As a new Erasmus student, I want to locate classrooms on a map, so that I can find my classes easily.**
2. **As a student, I want to view a list of upcoming events, so I can participate and meet others.**
3. **As a student, I want to access a calendar with school deadlines and meetings, so that I stay organized.**
4. **As a student from another country, I want to find others from my country, so that I can connect with them.**
5. **As a student, I want to recover my account if I forget my password, so that I can continue using the app.**

Write down **10-15 stories** based on your use cases. This will act as the foundation for your functional requirements.

---

## **Step 3: Prioritize User Stories (Agile Style)**
Use **MoSCoW prioritization**:
- **Must have:** Essential to launch the app (e.g., maps, authentication, profile creation, etc.).
- **Should have:** Useful features (e.g., events, language course info, etc.).
- **Could have:** Nice-to-have features (e.g., list of discounts, public transportation info, etc.).
- **Won’t have (for now):** Features you defer to later iterations.

### Example Prioritization:
#### Must Have:
- Authentication and registration
- User profile (name, photo, email)
- Map of buildings and rooms
- School calendar
- First steps at the university (paperwork, student account)

#### Should Have:
- Events and meetings
- Public transportation information
- Language courses

#### Could Have:
- Discounts list
- Connecting with students from the same country

---

## **Step 4: Sprint 1 (Preparation and Initial Deliverables)**
Focus on the first sprint deliverables mentioned:

1. **Contextualization:**
   - Describe Erasmus students’ needs and your app’s goal to address those needs.
   - Example: "ErasmusGo is a mobile app designed to simplify the first steps of Erasmus students at IPCA, addressing challenges like navigation, language barriers, and integration into the academic community."

2. **Business Objectives:**
   - Example: "Improve the onboarding experience of Erasmus students, enhance their integration into the IPCA community, and streamline access to essential information."

3. **Stakeholders:**
   - Erasmus students
   - IPCA administration
   - Teachers
   - Local businesses offering discounts (optional)

4. **Technical Architecture:**
   - Define the stack: Kotlin + Jetpack Compose, Firebase for backend, SQLite for local storage.

5. **Feasibility Study:**
   - Mention the choice of tools (e.g., Kotlin is ideal for Android; Firebase simplifies authentication).

6. **Context Diagram:**
   - Show the app’s interactions with users, external APIs (Firebase), and local storage (SQLite).

7. **Functional and Non-Functional Requirements:**
   - Functional: What the app should do (e.g., user registration, showing maps, etc.).
   - Non-Functional: Performance, security, maintainability, etc.

---

## **Step 5: Create Initial Wireframes/Mockups**
Using **Figma** or a similar tool, create simple screens for:
1. **Login/Registration**
2. **Profile Setup**
3. **Home Screen with Main Features**
4. **Map Screen**
5. **Calendar Screen**

---

## **Step 6: Start Agile Implementation**
Break the work into smaller tasks (user stories), assign them, and set deadlines. For example:

### Week Plan:
1. **Day 1-2:**
   - Document user stories, prioritize, and complete Sprint 1 deliverables (context, objectives, diagrams, etc.).
   - Start creating mockups in Figma.

2. **Day 3-4:**
   - Implement basic app structure (e.g., login screen, Firebase setup).
   - Work on use case diagrams, ER diagram, and domain model.

3. **Day 5-6:**
   - Develop core features (maps, calendar).
   - Complete documentation (sequence diagrams, class diagrams, etc.).
   - Prepare for the presentation (mockups, screenshots, initial implementation demo).

---

## **Step 7: Tools and Tips**
### Tools:
1. **Documentation:** Microsoft Word, Google Docs
2. **Diagramming:** Lucidchart, Draw.io, or Visio
3. **Mockups:** Figma, Adobe XD
4. **Project Management:** Trello, Notion, or Jira
5. **Development:** Android Studio (Kotlin with Jetpack Compose)
6. **Database:** SQLite for local, Firebase for authentication

### Tips:
- Start small but functional. Even a basic login and map can make a great demo.
- Document as you go; don’t leave it for the end.
- Regular check-ins to ensure everyone’s on track.
- Prepare a polished 5-minute app demo for presentation.
