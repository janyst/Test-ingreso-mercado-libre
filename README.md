Soluci�n: se crea Servicio Rest, con las siguientes tecnolog�as java 7(jersey), MYSQL. Se tomo en consideraci�n que los planetas parten o inician en el Angulo 0. El Jobs se ejecutara una vez por d�a actualizando la informaci�n ya existente en BD

URL
Estado del Dia: 
http://testmeliweather.us-east-1.elasticbeanstalk.com/rest/weather/state?dia=28
Resumen del clima por 10 a�os: 
http://testmeliweather.us-east-1.elasticbeanstalk.com/rest/weather/summary?civilizacion=VULCANO
http://testmeliweather.us-east-1.elasticbeanstalk.com/rest/weather/summary?civilizacion=FEREGI
http://testmeliweather.us-east-1.elasticbeanstalk.com/rest/weather/summary?civilizacion=BETOSOIDE


Problema:
En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada
civilizaci�n vive en paz en su respectivo planeta.
Dominan la predicci�n del clima mediante un complejo sistema inform�tico.
A continuaci�n el diagrama del sistema solar
Premisas:
El planeta Ferengi se desplaza con una velocidad angular de 1 grados/d�a en sentido
horario. Su distancia con respecto al sol es de 500Km.
El planeta Betasoide se desplaza con una velocidad angular de 3 grados/d�a en sentido
horario. Su distancia con respecto al sol es de 2000Km.
El planeta Vulcano se desplaza con una velocidad angular de 5 grados/d�a en sentido
anti�horario, su distancia con respecto al sol es de 1000Km.
Todas las �rbitas son circulares.
Cuando los tres planetas est�n alineados entre s� y a su vez alineados con respecto al sol, el
sistema solar experimenta un per�odo de sequ�a

Realizar un programa inform�tico para poder predecir en los pr�ximos 10 a�os:
1. �Cu�ntos per�odos de sequ�a habr�?
2. �Cu�ntos per�odos de lluvia habr� y qu� d�a ser� el pico m�ximo de lluvia?
3. �Cu�ntos per�odos de condiciones �ptimas de presi�n y temperatura habr�?