package org.db.students;

import java.util.Map;
import org.db.students.VerifyInputData;

public class StudentCommandHandler {
    private StudentStorage studentStorage = new StudentStorage();
    public void processCommand(Command command){
        // Проверка и выход если проверку не прошло
        if (!VerifyInputData.verifyInputData(command)){
            // Сообщение оговорено в видео по домашнему заданию
            System.out.println("Не удалось распознать строку . Пожалуйста повторите попытку .");
            return;
        }
        Action action = command.getAction();
        switch(action) {
            case CREATE -> {
                procssCreateCommand(command);
                break;
            }
            case UPDATE -> {
                processUpdateCommand(command);
                break;
            }
            case DELETE -> {
                processDeleteCommand(command);
                break;
            }
            case STATS_BY_CITY -> {
                processStatsByCityCommand(command);
                break;
            }
            case STATS_BY_COURSE -> {
                processStatsByCourseCommand(command);
                break;
            }
            case SEARCH -> {
                processSearchCommand(command);
                break;
            }
            default -> {
                System.out.println("Действие " + action.name() + " не поддерживается !");
            }
        }
        System.out.println("Обработка комманды. "
                + "Действие: " + command.getAction().name()
                + ", данные: " + command.getData());
    }

    private void processSearchCommand(Command command){
        String surname = command.getData();
        studentStorage.search(surname);
    }
    private void processStatsByCityCommand(Command command){
        Map<String,Long> data = studentStorage.getCountByCity();
        studentStorage.printMap(data);
    }
    private void processStatsByCourseCommand(Command command){
        Map<String,Long> data = studentStorage.getCountByCourse();
        studentStorage.printMap(data);
    }

    private void procssCreateCommand(Command command) {
        String data = command.getData();
        String[] dataArray = data.split(",");
        Student student = new Student();
        student.setSurname(dataArray[0]);
        student.setName(dataArray[1]);
        student.setCourse(dataArray[2]);
        student.setCity(dataArray[3]);
        student.setAge(Integer.valueOf(dataArray[4]));
        studentStorage.createStudent(student);
        studentStorage.printAll();
    }

    private void processUpdateCommand(Command command) {
        String data = command.getData();
        String[] dataArray = data.split(",");
        Long id = Long.valueOf(dataArray[0]);
        Student student = new Student();
        student.setSurname(dataArray[1]);
        student.setName(dataArray[2]);
        student.setCourse(dataArray[3]);
        student.setCity(dataArray[4]);
        student.setAge(Integer.valueOf(dataArray[5]));
        studentStorage.updateStudent(id,student);
        studentStorage.printAll();
    }
     private void processDeleteCommand(Command command) {
         String data = command.getData();
         Long id = Long.valueOf(data);
         studentStorage.deleteStudent(id);
         studentStorage.printAll();
     }
}
