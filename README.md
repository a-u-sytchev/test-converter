Запрос стоимости конвертации целевых валют по URL localhost:5000/api/v1/exchange

В параметрах необходимо указано 1 или более валют (currency). Валюта указывается в стандарте ISO 4217.
Список валют можно посмотреть здесь https://api.coinbase.com/v2/currencies.

Также в запросе обязательно указывается сумма, которую необходимо конвертировать (amount).

Примеры запросов:
http://localhost:5000/api/v1/exchange?currency=USD&amount=1
http://localhost:5000/api/v1/exchange?currency=RUB&currency=BYN&amount=100.78

Ответ API будет содержать сумму, сконвертированную по текущему курсу для каждой из целевых валют.