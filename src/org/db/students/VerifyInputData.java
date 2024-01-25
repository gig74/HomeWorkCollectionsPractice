package org.db.students;

import org.db.students.exceptions.DataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyInputData {
    private static final String CYRILLIC_NAME_PATTERN = "([А-ЯЁ][а-яё]+(-[А-ЯЁ][а-яё]+)?)" ;
    private static final String CYRILLIC_CITY_PATTERN = "(([а-яА-ЯёЁ]*(\\s*)\\([а-яА-ЯёЁ\\s]*\\))|([а-яА-ЯёЁ\\-0-9]*)|([а-яА-ЯёЁ]+[\\-|\\s]?[а-яА-ЯёЁ]*[\\-|\\s]?[а-яА-ЯёЁ]*[\\-|\\s]?[а-яА-ЯёЁ]*))";
    private static final String COURSE_PATTERN= "([^,]+)";
    private static final String AGE_PATTERN = "([1-2]?[\\d]{1,2})";

    private static final String ID_PATTERN = "(\\d{1,19})";

    private static final String CYRILLIC_MESSAGE = " * - Ввод на русском языке с соблюдением регистра и правил русского языка" ;
    private static final String COURSE_MESSAGE = " ** - Любые символы, кроме запятой";
    private static final String AGE_MESSAGE = " *** - Количество полных лет (только цифры)";

    public static boolean verifyInputData (Command command) {

        Action action = command.getAction();
        String data = command.getData();
        switch(action) {
            case CREATE -> {
                boolean ret = true ;
                try {
                    String createTemplate = "^" + CYRILLIC_NAME_PATTERN + "," + CYRILLIC_NAME_PATTERN + "," + COURSE_PATTERN
                            + "," + CYRILLIC_CITY_PATTERN + "," + AGE_PATTERN + "$";
                    Pattern createPattern = Pattern.compile(createTemplate);
                    Matcher createMatcher = createPattern.matcher(data);
                    if (!createMatcher.find()) {
                        throw new DataException("");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(" Для создания необходимо ввести данные в следующем виде: ");
                    System.out.println(" <Фамилия*>,<Имя*>,<Название курса**>,<Город*>,<Возраст***>");
                    System.out.println(CYRILLIC_MESSAGE);
                    System.out.println(COURSE_MESSAGE);
                    System.out.println(AGE_MESSAGE);
                    ret = false;
                }
                finally {
                    return ret;
                }
            }
            case DELETE -> {
                try {
                    Long id = Long.valueOf(data);
                    return true;
                }
                catch (Exception e)
                {
                    System.out.println(" Для удаления необходимо ввести данные в следующем виде: <Цифровой идентификатор> ");
                    return false;
                }
            }
            case UPDATE -> {
                boolean ret = true ;
                try {
                    String updateTemplate = "^" + ID_PATTERN + "," + CYRILLIC_NAME_PATTERN + "," + CYRILLIC_NAME_PATTERN + ","
                            + COURSE_PATTERN + "," + CYRILLIC_CITY_PATTERN + "," + AGE_PATTERN + "$";
                    Pattern updatePattern = Pattern.compile(updateTemplate);
                    Matcher updateMatcher = updatePattern.matcher(data);
                    if (!updateMatcher.find()) {
                        throw new DataException("");
                    } else {
                        Long id = Long.valueOf(updateMatcher.group(1));
                    }
                }
                catch (Exception e)
                {
                    System.out.println(" Для изменения необходимо ввести данные в следующем виде: ");
                    System.out.println(" <Цифровой идентификатор>,<Фамилия*>,<Имя*>,<Название курса**>,<Город*>,<Возраст***>");
                    System.out.println(CYRILLIC_MESSAGE);
                    System.out.println(COURSE_MESSAGE);
                    System.out.println(AGE_MESSAGE);
                    ret = false;
                }
                finally {
                    return ret;
                }
            }
            case SEARCH -> {
                boolean ret = true ;
                try {
                    String searchTemplate = "^" + "(" + CYRILLIC_NAME_PATTERN + "(" + "," + CYRILLIC_NAME_PATTERN + ")?)?" + "$";
                    Pattern searchPattern = Pattern.compile(searchTemplate);
                    Matcher searchMatcher = searchPattern.matcher(data);
                    if (!searchMatcher.find()) {
                        throw new DataException("");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(" Для поиска по фамилии необходимо ввести данные в следующем виде: ");
                    System.out.println(" [<Фамилия*>[,<Фамилия-Доп*>]]");
                    System.out.println(CYRILLIC_MESSAGE);
                    ret = false;
                }
                finally {
                    return ret;
                }
            }
            default -> {
                return true;
            }
        }
    }
}
