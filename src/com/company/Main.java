package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import com.company.exceptions.*;

public class Main {

    public static void main(String[] args) {



            Faculty forestryFaculty = new Faculty("Forestry Faculty", 1);
            Faculty EconomicEngineeringFaculty = new Faculty("Economic Engineering Faculty", 1);

            Group firstGroup = new Group(forestryFaculty, 1);
            Group secondGroup = new Group(EconomicEngineeringFaculty, 2);

            Student LoganLang = new Student("Logan Lang", firstGroup, 1);
            Student ElvinPitts = new Student("Elvin Pitts", firstGroup, 2);
            Student RichardBarker = new Student("Richard Barker", secondGroup, 3);
            Student GordonMcDowell = new Student("Gordon McDowell", secondGroup, 4);

            Faculty[] faculties = new Faculty[]{forestryFaculty, EconomicEngineeringFaculty};

            Group[] groups = new Group[]{firstGroup, secondGroup};

            Student[] students = new Student[]{LoganLang, ElvinPitts, RichardBarker, GordonMcDowell};

            Random random = new Random();

            for (int i = 0; i < students.length; i++) {
                students[i].getGrades().put("EPAM", Math.abs(random.nextInt()) % 10);
            }

            //1
            double averageGrade = 0;
            int countOfGrades = 0;
            int studentId = 1;

        Student student;
        for (var currentStudent: students) {
            if(currentStudent.getId() == studentId){
                student = currentStudent;
                for (HashMap.Entry<String, Integer> entry : student.getGrades().entrySet()) {
                    averageGrade += entry.getValue();
                    countOfGrades++;
                }
                averageGrade = averageGrade / countOfGrades;

                System.out.println("The average grade:" + averageGrade + " |  ID: " + studentId);

            }
        }


            if(faculties.length == 0)
                try {
                    throw new NoFacultiesException("University has no faculties");
                } catch (NoFacultiesException e) {
                    System.out.println(e.getMessage());
                    return;
                }

            for(var i = 0; i < students.length; i++){
                if(students[i].getGrades().entrySet().size() == 0)
                    try {
                        throw new NoDisciplinesException("Student has no disciplines");
                    } catch (NoDisciplinesException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
            }

            //2
            averageGrade = 0;
            countOfGrades = 0;
            Group group = firstGroup;
            Faculty faculty = forestryFaculty;
            try {
                for (int i = 0; i < groups.length; i++) {
                    if (groups[i].getFaculty() == faculty)
                        break;
                    if (i == groups.length - 1)
                        throw new NoGroupsException("Faculty has no students");
                }
                for (int i = 0; i < students.length; i++) {
                    if (students[i].getGroup() == group)
                        break;
                    if (i == students.length - 1)
                        throw new NoStudentsException("Group has no students");
                }
                for (int i = 0; i < students.length; i++) {
                    if (students[i].getGroup().getId() == group.getId() && group.getFaculty() == faculty) {
                        if (students[i].getGrades().get("EPAM") < 0 || students[i].getGrades().get("EPAM") > 10)
                            throw new IncorrectGradeException("Incorrect grade ", students[i].getGrades().get("EPAM"));
                        averageGrade += students[i].getGrades().get("EPAM");
                        countOfGrades++;
                    }
                }
            } catch (IncorrectGradeException e) {
                System.out.println(e.getMessage());
            }
            catch (NoStudentsException e){
                System.out.println(e.getMessage());
            } catch (NoGroupsException e) {
                System.out.println(e.getMessage());
            }

            averageGrade = averageGrade / countOfGrades;

            System.out.println("The average grade: " + averageGrade  + " | Group ID: " + group.getId());


            //Third task
            averageGrade = 0;
            countOfGrades = 0;

            try {
                for (int i = 0; i < students.length; i++) {
                    if (students[i].getGrades().get("EPAM") != null) {
                        if (students[i].getGrades().get("EPAM") < 0 || students[i].getGrades().get("EPAM") > 10)
                            throw new IncorrectGradeException("Incorrect grade ", students[i].getGrades().get("EPAM"));
                        averageGrade += students[i].getGrades().get("EPAM");
                        countOfGrades++;
                    }
                }
                averageGrade = averageGrade / countOfGrades;
            } catch (IncorrectGradeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("The average grade EPAM: " + averageGrade);



    }
}
