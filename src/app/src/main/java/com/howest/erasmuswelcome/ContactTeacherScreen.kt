package com.howest.erasmuswelcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ContactTeacherScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContactTeacherScreen(){
        var teacherDB: FirebaseDBHelper = FirebaseDBHelper()
        var teacherList: List<FirebaseDBHelper.Teacher> = teacherDB.getTeachers()

        var selectedDegree by remember { mutableStateOf("") }
        var selectedCourse by remember { mutableStateOf("") }
        var expandedDegree by remember { mutableStateOf(false) }
        var expandedCourse by remember { mutableStateOf(false) }
        var degrees: ArrayList<String> = ArrayList<String>();
        teacherList.forEach { e ->
            degrees.addAll(e.degree)
        }
        var courses: ArrayList<String> = ArrayList<String>();
        teacherList.forEach { e ->
            courses.addAll(e.courses)
        }



        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedDegree,
                    onExpandedChange = { expandedDegree = !expandedDegree }
                ) {
                    OutlinedTextField(
                        value = selectedDegree,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Degree") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDegree)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedDegree,
                        onDismissRequest = { expandedDegree = false }
                    ) {
                        degrees.forEach { degree ->
                            DropdownMenuItem(
                                text = { Text(degree) },
                                onClick = {
                                    selectedDegree = degree
                                    expandedDegree = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandedCourse,
                    onExpandedChange = { expandedCourse = !expandedCourse }
                ) {
                    OutlinedTextField(
                        value = selectedCourse,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Course") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCourse)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCourse,
                        onDismissRequest = { expandedCourse = false }
                    ) {
                        courses.forEach { course ->
                            DropdownMenuItem(
                                text = { Text(course) },
                                onClick = {
                                    selectedCourse = course
                                    expandedCourse = false
                                }
                            )
                        }


                    }


                }

                if (selectedDegree.isNotEmpty() && selectedCourse.isNotEmpty()) {
                    var selectedTeacher: FirebaseDBHelper.Teacher? = null

                    teacherList.forEach { teacher ->
                        if (teacher.courses.contains(selectedCourse) && teacher.degree.contains(
                                selectedDegree
                            )
                        ) {
                            selectedTeacher = teacher
                        }
                    }
                    if (selectedTeacher != null) {

                        Text(
                            text = selectedTeacher!!.name

                        )
                        Text(
                            text = "Email:"+ selectedTeacher!!.email

                        )
                    }
                }

            }

        }
    }
}