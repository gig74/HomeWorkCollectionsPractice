# HomeWorkCollectionsPractice
Домашнее задание по теме "Практический проект по работе с коллекциями"

# Постановка задачи
В домашнем задании продолжаем работу над консольным приложением 
для управления данными студентов на курсах.

## Задание 1.

Для всех пунктов, подразумевающих ввод данных сделать проверку ввода. Если введенные данные не могут быть интерпретированы, то вывести сообщение и перейти к выбору вариантов действия.


## Задание 2.

Реализовать действие 5 (статистика по количеству студентов на разных курсах) по аналогии с действием 4 (статистика по количеству студентов из разных городов).</br>

Пример:

- Ввод: 5

- Вывод: Москва - 5 Краснодар - 3


## Задание 3.

Изменить реализацию поиска по фамилии:

- Если введена пустая строка, то вывести полный список студентов.
Если введена только одна фамилия, то выполнить точный поиск студентов по фамилии.
Если введены 2 фамилии, разделенные запятой (,), то вывести всех студентов, чьи фамилии в алфавитном порядке >= первой фамилии и <= второй фамилии.
Пример:

- Ввод: Абрикосов,Персиков

- Вывод: Все студенты, чьи фамилии >= Абрикосов и <= Персиков

 Если ввод не может быть интерпретирован, как один из вариантов выше, то вывести соответствующее сообщение и перейти к выбору вариантов.
## Описание ключевых файлов
- Main.java - запускаемое консольное приложение
- Action.java - описание объекта списка возможных действий пользователя
- Command.java - реализация класса для объекта "действие пользователя"
- Student.java - реализация класса для объекта "студент"
- StudentCommandHandler.java - реализация класса обработчика запросов пользователя
- StudentStorage.java - реализация класса хранилища студентов
- StudentSurnameStorage.java - реализация класса хранилища упорядоченных фамилий студентов
- VerifyInputData.java - реализация класса для проверки введённых пользователем данных
- StudentsSample.txt - перечень студентов для контрольного примера