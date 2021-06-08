# Arquitetura microservices com webflux


## Fluxo
- Para criar produto:
```
POST /products
--json
{
    "code" : "5889"
}
```
- Adiconar estoque
```
PUT /operations/add/{code}/{qtde}
```
- Retirar estoque
```
PUT /operations/exit/{code}/{qtde}
```
- Busca product
```
GET - /products/{code}
--retorno
{
    "id": "60bfc0e24015e576f4aa4555",
    "code": "5889",
    "qtde": 26
}
```
