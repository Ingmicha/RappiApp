# Readme
Capas de la aplicacion

  - Data
    - Api
        - Client
        - Retrofit
    - Model
  - INterator
  - Presenter
  - View
    - Activity
    - Adapter
    - Fragment
    - Dialog
# Data (Capa de RED)

En esta capa se encuentra la configuracion del api y el cliente para el consumo de las peticiones REST.

En el CLIENT se encuentran 2 clases, una interfaz y su implementacion, en su implementacion se realiza los metodos para consumir las diferentes peticiones REST, tipo de valor a retornar y parametros que se envian por el api.

En RETROFIT se encuentra la configracion del cliente para realizar las peticiones, con su base url, converter, callback y el hhtpcliente que se va a usar.

Tambien en DATA se encuentra el modelo de los objetos que se manejan en la aplicacion, en este caso son 2, un objeto RESULT y un objeto MOVIE.

# INTERATOR

Aqui se manejan los servicios de los cuales el presnetador va a consumir ya responder dependiendo de que metodo de la api se desee consumir.

# PRESNETER

Aqui manejo las respuestas al consumir las Api, si traen o no resultados y se envia la informacion que se valla a manejar sobre la vista, con una interfaz donde se manejan los progressbar, cuando no encuentra un resultado etc.

# VIEW

Se manejan la UI que el usuario ve, se creo un fragmento y una activity, donde el fragmento llama al presentador y se le implementa la interfaz que maneja el presentador para la comunicacion entre el presentador y la vista.

