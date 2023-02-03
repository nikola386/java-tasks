import com.nikola.university.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExamStudent {
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1", 3));
        questions.add(new Question("Question 2", 5));
        questions.add(new Question("Question 3", 4));
        questions.add(new Question("Question 4", 1));
        questions.add(new Question("Question 5", 2));

        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(new Exam(Subject.Networks, questions, Duration.ofHours(1)));

        ArrayList<Subject> subjects = new ArrayList<>(List.of(Subject.Networks, Subject.Programing, Subject.Math));
        Student student = new Student("Kiro", 21, "IT", subjects);

        Teacher teacher = new Teacher("House", Title.Dr, exams);
        genericInvokeMethod(teacher, "examStudent", student);
    }

    private static Object genericInvokeMethod(Object obj, String methodName, Object... params) {
        int paramCount = params.length;
        Method method;
        Object requiredObj = null;
        Class<?>[] classArray = new Class<?>[paramCount];
        for (int i = 0; i < paramCount; i++) {
            classArray[i] = params[i].getClass();
        }
        try {
            method = obj.getClass().getDeclaredMethod(methodName, classArray);
            method.setAccessible(true);
            requiredObj = method.invoke(obj, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return requiredObj;
    }
}