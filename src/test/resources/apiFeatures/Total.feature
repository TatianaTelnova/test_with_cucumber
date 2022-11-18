# language: ru
Функция: Тестирование REST Api

  Сценарий: Проверка количества users
  Дано сайт "https://reqres.in/"
  Когда считаю "total_pages" и сохраняю в "total_pages"
    И считаю "total" и сохраняю в "total"
    Тогда сумма users в "total_pages" равна "total"