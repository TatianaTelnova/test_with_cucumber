# language: ru
Функция: Подсчет количества элементов

  Структура сценария: Подсчет количества элементов на странице с частыми вопросами
    Дано открыта страница с частыми вопросами
    Когда считаю "<элементы>"
    Тогда результат равен <количество>
    Примеры:
      | элементы | количество |
      | темы     | 17         |
      | вопросы  | 100        |

  Сценарий: Подсчет количества адресов банкоматов
    Дано открыта страница с адресами банкоматов
    Когда кликаю на кнопку Списком
    Тогда количество адресов банкоматов равно 4