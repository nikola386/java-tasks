1)	Create new project with name University.
2)	Please create the following classes under University project - Student, Teacher, Exam and Question.
- The Student class should have the following fields: name, age, academic major, and list of academic subjects. The class should also have one method called “answerQuestion” that will accept one question as a parameter and will return random integer value between 1 and 5.
- The Teacher class should have the following fields: name, academic title and list of exams. The class should also have one PRIVATE method called “examStudent” that will accept one Student object as a parameter. The method will randomly choose one exam from the list of prepared exams and the student must take that exam. The method must print “Correct answer” message when the provided answer is correct and print “Wrong answer” otherwise.
- The Exam class should have the following fields: name of the subject, list of questions and time for the exam.
- Тhe Question class should have the following fields: question and correct answer.
- Please make sure that all class variables and methods have appropriate access modifiers.
3)	Now export the University project to JAR file.
4)	After that create new project with name SofiaUniversity.
5)	Then add the JAR file (University project) to the CLASSPATH of SofiaUniversity project.
6)	Create new class (ExamStudent for example) with a main method. After that create objects from all available classes: Student, Teacher, Exam and Question.
      Choose your student victim and pass it as a parameter to examStudent method of the Teacher object.
