# **Documentation of bazar_api**
This project is useful to management of a bazaar's sales with a system of descount of the product's stock when a sales is generated.

Requires a DataBase MySQ, in this case called "bazar_db" with port 3306 for Local Run

Change variables in "bazar_api/src/main/resources/application.properties"

**Enviroment variables: <br>**
- DB_USER_NAME = admin <br>
- DB_PASSWORD = admin <br>
- DB_URL = jdbc:mysql://localhost:3306/bazar_db?useSSL=false&serverTimezone=UTC

if you use another port like 3307: <br>
- DB_URL = jdbc:mysql://localhost:3307/bazar_db?useSSL=false&serverTimezone=UTC

This project was deployed in http://vps-4263643-x.dattaweb.com:8080

Endpoints:


# Client Endpoints:
- Endpoint to get all the clients: <br>
**GET**   "/clientes"

- Endpoint to get an especific client: <br>
**GET**   "/clientes/{id_cliente}"

- Endpoint to get an especific client by DNI: <br>
**GET**   "/clientes/dni/{dni}"

- Endpoint to create a cliente: <br>
**POST**   "/clientes/crear"  <br>
Example of the json:

```
{
        "id_cliente": 0,
        "nombre": "Luis",
        "apellido": "Luna",
        "dni": "654"
}
```
- Endpoint to delete a cliente by id: <br>
**DELETE**   "/clientes/eliminar/{id}"

- Endpoint to edit a cliente: <br>
**PUT**   "/clientes/editar/{id}"  <br>
Example of the json:

```
{
        "id_cliente": 0,
        "nombre": "Luis",
        "apellido": "Luna",
        "dni": "756"
}
```

# Product Endpoints:
- Endpoint to get all the products: <br>
**GET**   "/productos"

- Endpoint to get an especific client: <br>
**GET**   "/productos/{codigo_producto}"

- Endpoint to get all the products that has 5 or less units: <br>
**GET**   "/productos/falta_stock"

- Endpoint to create a product: <br>
**POST**   "/productos/crear"  <br>
Example of the json: 

```
{
    "codigo_producto":"0",  
    "nombre": "Filete de Atún FLORIDA en Agua 110kcal Lata 150g" ,
    "marca":"FLORIDA" ,
    "costo":"5.50" ,
    "cantidad_disponible":"30"
}
```
- Endpoint to delete a product by id: <br>
**DELETE**   "/productos/eliminar/{codigo_producto}"

- Endpoint to edit a product: <br>
**PUT**   "/productos/editar/{codigo_producto}"  <br>
Example of the json:

```
{
    "codigo_producto":"0",  
    "nombre": "Filete de Atún FLORIDA en Agua 110kcal Lata 150g" ,
    "marca":"FLORIDA" ,
    "costo":"7" ,
    "cantidad_disponible":"40"
}
```
# Ventas (sales) Endpoints:
- Endpoint to get all the sales: <br>
**GET**   "/ventas"

- Endpoint to get an especific sale: <br>
**GET**   "/ventas/{codigo_venta}"

- Endpoint to get the list of products of a sale: <br>
**GET**   "/ventas/productos/{codigo_venta}"

- Endpoint to get the total mount of the sales of a day and the number of sales: <br>
**GET**   "/ventas/dia/{fecha_venta}"  <br>
Response: "La sumatoria del monto del día es: " + montoTotal + " soles <br>"+
               "La cantidad total de ventas del día: " + cont;

- Endpoint to get the sale withe the higger mount: <br>
**GET**   "/ventas/mayor_venta"  <br>
Example of response:
```
{
    "codigo_venta": 1,
    "total": 11,
    "cantidadDeProductos": 2,
    "nombreCliente": "Luis",
    "apellidoCliente": "Luna"
}
```
  
- Endpoint to create a sale: <br>
*You need create the clients and products previously before use this* <br>
**POST**   "/ventas/crear"  <br>
Example of the json:

```
{
    "codigo_venta": "0",
    "fecha_venta": "2024-02-05",
    "total" :"0",
    "unCliente" :
    {
        "id_cliente":4
    },
    "listaProductos": 
        [
            {           
                "codigo_producto":"3"
            },
            {           
                "codigo_producto":"3"

            },
            {           
                "codigo_producto":"3"

            }

        ]
    
}
```
- Endpoint to delete a sale by id : <br>
**DELETE**   "/ventas/eliminar/{codigo_venta}" <br>
*if you delete a sale, the stack of each product in the sale products are replenished.*

- Endpoint to edit a sale: <br>
**PUT**   "ventas/editar/{codigo_venta}"













