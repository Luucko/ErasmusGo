package com.howest.erasmuswelcome

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseDBHelper {

    // Data class for Teachers
    data class Teacher(val id: Int,val name:String , val email: String,val degree:List<String>,val courses:List<String>);

    private val databaseTeachers: DatabaseReference = FirebaseDatabase.getInstance().getReference("teachers");


    fun getTeachers():List<Teacher> {

        return fillDB();
    }

    fun fillDB():List<Teacher>{



        //einmaliger testcode
        val teachers = listOf(
            FirebaseDBHelper.Teacher(
                id = 1,
                name = "Dr. Emily Carter",
                email = "emily.carter@example.com",
                degree = listOf(
                    "Bachelor of Science in Mathematics",
                    "Master of Science in Physics",
                    "PhD in Astrophysics"
                ),
                courses = listOf(
                    "Calculus 101",
                    "Linear Algebra",
                    "Quantum Mechanics",
                    "Astrophysics 201"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 2,
                name = "Prof. Michael Thompson",
                email = "michael.thompson@example.com",
                degree = listOf(
                    "Bachelor of Arts in History",
                    "Master of Arts in Sociology"
                ),
                courses = listOf(
                    "World History 101",
                    "Modern Sociology",
                    "Anthropology Basics"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 3,
                name = "Dr. Sarah Johnson",
                email = "sarah.johnson@example.com",
                degree = listOf(
                    "Bachelor of Science in Computer Science",
                    "PhD in Artificial Intelligence"
                ),
                courses = listOf(
                    "Introduction to Programming",
                    "Machine Learning 101",
                    "Artificial Intelligence Basics",
                    "Data Structures"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 4,
                name = "Prof. David Brown",
                email = "david.brown@example.com",
                degree = listOf(
                    "Bachelor of Science in Biology",
                    "Master of Science in Genetics"
                ),
                courses = listOf(
                    "Biology Basics",
                    "Genetics and Evolution",
                    "Molecular Biology",
                    "Ecology and Conservation"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 5,
                name = "Dr. Laura Smith",
                email = "laura.smith@example.com",
                degree = listOf(
                    "Bachelor of Arts in Literature",
                    "Master of Fine Arts in Creative Writing"
                ),
                courses = listOf(
                    "Creative Writing Workshop",
                    "English Literature 101",
                    "Modern Poetry",
                    "Shakespeare Studies"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 6,
                name = "Prof. John Wilson",
                email = "john.wilson@example.com",
                degree = listOf(
                    "Bachelor of Engineering in Electrical Engineering",
                    "Master of Engineering in Robotics"
                ),
                courses = listOf(
                    "Introduction to Robotics",
                    "Electrical Circuits",
                    "Automation Systems",
                    "Signal Processing"
                )
            ),
            FirebaseDBHelper.Teacher(
                id = 7,
                name = "Dr. Anna Williams",
                email = "anna.williams@example.com",
                degree = listOf(
                    "Bachelor of Science in Psychology",
                    "PhD in Cognitive Neuroscience"
                ),
                courses = listOf(
                    "Psychology Basics",
                    "Cognitive Neuroscience",
                    "Behavioral Psychology",
                    "Research Methods in Psychology"
                )
            )
        )

        teachers.forEach { println(it) }
        FirebaseDatabase.getInstance().getReference("Teacher").setValue(teachers)

        databaseTeachers.setValue(teachers)
        return teachers;

    }
}