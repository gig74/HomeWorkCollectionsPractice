package org.db.students;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentStorage {
    private Map<Long, Student> studentStorageMap = new HashMap<>();
    private StudentSurnameStorage studentSurnameStorage = new StudentSurnameStorage();
    private Long currentId = 0L;

    /**
     * Создание данных о студенте
     * @param student данные о студенте
     * @return уникальный сгеннерированный идентификатор студента
     */
    public Long createStudent(Student student) {
        Long nextId = getNExtId();
        studentStorageMap.put(nextId,student);
        studentSurnameStorage.studentCreated(nextId,student.getSurname());
        return nextId;
    }

    /**
     * Обновление данных о студенте
     * @param id уникальный сгеннерированный идентификатор студента
     * @param student данные о студенте
     * @return  true если данные были обновлены, false если студент не был найден
     */
    public boolean updateStudent(Long id,Student student) {
        if(!studentStorageMap.containsKey(id)){
            return false;
        } else {
            String newSurname = student.getSurname();
            String oldSurname = studentStorageMap.get(id).getSurname();
            studentStorageMap.put(id, student);
            studentSurnameStorage.studentUpdated(id,newSurname,oldSurname);
            return true;
        }
    }

    /**
     * Удаляет данные о студенте
     * @param id идентификатор студента
     * @return true если студент был удален
     * false если студент не был найден по идентификатору
     */
    public boolean deleteStudent(Long id){
        Student removed = studentStorageMap.remove(id);
        if (removed != null) {
            String surname = removed.getSurname();
            studentSurnameStorage.studentDeleted(id, surname);
        }
        return (removed != null) ;
    }

    public Long getNExtId() {
        currentId = currentId + 1;
        return currentId;
    }

    public void printAll(){
        System.out.println(studentStorageMap);
    }

    public void search(String surname) {
        Set<Long> students = null;
        if (surname == null || surname.equals("")) {
            students = studentStorageMap.keySet(); // Быстрее и ресурсов меньше требуется. и относительно однообразно .
            //students = studentSurnameStorage.getStudentAll(surname); // Реализация через хранилище фамилий . Не понравилось .
        } else {
            String[] dataArray = surname.split(",");
            if (dataArray.length == 1) {
                students = studentSurnameStorage.getStudentBySurnamesEqualsThan(surname);
            }
            else {
                students = studentSurnameStorage.getStudentByBtwSurname1AndSurnames2(dataArray[0],dataArray[1]);
            }
            //students = studentSurnameStorage.getStudentBySurnamesLessOrEqualsThan(surname); // из видеоурока для меньше или равно
        }
        for(Long studentID: students) {
            Student student = studentStorageMap.get(studentID);
            System.out.println(student);
        }
    }

    public  void printMap(Map<String,Long> data) {
        data.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + "-" + e.getValue());
        });
    }

    public Map<String,Long> getCountByCity() {
        Map<String , Long> res = studentStorageMap.values().stream()
                .collect(Collectors.toMap(
                        student -> student.getCity(),
                        student -> 1L,
                        (count1,count2) -> count1 + count2
                ));
        return res;
    }
    public Map<String,Long> getCountByCourse() {
        // Первоначальная реализация из видеурока . Пригодится .
//        Map<String,Long> res = new HashMap<>();
//        for(Student student : studentStorageMap.values()) {
//            String key = student.getCourse();
//            Long count = res.getOrDefault(key, 0L);
//            count++;
//            res.put(key,count);
//        }
   Map<String , Long> res = studentStorageMap.values().stream()
                .collect(Collectors.toMap(
                        student -> student.getCourse(),
                        student -> 1L,
                        (count1,count2) -> count1 + count2
                ));
        return res;
    }
}
