# Приложение для конвертации валют
URL https://localhost:5000/api/v1/

## Расчет стоимости конвертации
Метод GET
URI `/exchange`
Параметры:
* `sourceCurrency` идентификатор исходной валюты.
* `targetCurrency` идентификатор целевой валюты. Можно указать 1 или несколько.
* `amount` сумма исходной валюты для конвертации в целевую(-ые). Можно указать как целое число, так и десятичное. Не может быть меньше нуля.


Идентификаторы валюты указывается в стандарте ISO 4217. Посмотреть здесь https://api.coinbase.com/v2/currencies.
Все параметры обязательны.

В ответ вернется список с расчетом конвертации суммы исходной валюты в каждую целевую валюту, указанную в запросе.

## Примеры запросов.
GET http://localhost:5000/api/v1/exchange?sourceCurrency=RUB&targetCurrency=USD&amount=100
```json
[
  {
    "currency":"USD",
    "amount":1.09
  }
]
```

GET http://localhost:5000/api/v1/exchange?sourceCurrency=RUB&targetCurrency=BYN&targetCurrency=USD&targetCurrency=EUR&&amount=132.78
```json
[
  {
    "currency":"BYN",
    "amount":4.76
  },
  {
    "currency":"USD",
    "amount":1.45
  },
  {
    "currency":"EUR",
    "amount":1.34
  }
]
```
