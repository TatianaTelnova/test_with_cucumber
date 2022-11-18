# language: ru
Функция: Тестирование REST Api

  Структура сценария: Проверка <поле> у <id>
    Дано сайт "https://reqres.in/"
    Тогда пользователь с id <id> имеет "<поле>" "<значение>"
    Примеры:
      | id | поле       | значение               |
      | 2  | email      | janet.weaver@reqres.in |
      | 1  | first_name | George                 |