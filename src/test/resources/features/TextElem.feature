# language: ru
Функция: Проверка текста элемента

  Предыстория:
    Дано открыта страница Demo личного кабинета
    Когда кликаю "Войти в лк"

  Сценарий: Проверка текста элемента в Личном Кабинете
    Когда кликаю "Войти"
    Тогда имя пользователя равно "Королёва Ольга"