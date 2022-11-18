# language: ru
Функция: Тестирование REST Api

  Структура сценария: Проверка создания user
    Дано сайт "https://reqres.in/"
    Тогда при создании user c "<name>", "<job>" статус 201 и поля верные
    Примеры:
      | name     | job           |
      | morpheus | zion resident |

  Структура сценария: Проверка модификации user
    Дано сайт "https://reqres.in/"
    Тогда при модификации user <id> данных "<name>", "<job>" статус 200 и поля верные
    Примеры:
      | id | name | job           |
      | 1  | neo  | zion resident |

  Структура сценария: Проверка удаления user
    Дано сайт "https://reqres.in/"
    Тогда при удалении user <id> статус 207
    Примеры:
      | id |
      | 1  |