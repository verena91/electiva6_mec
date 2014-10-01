COPY funcionario (mes, anho, nroDocumento, nombreCompleto, objetoGasto, nombreObjetoGasto, estado, antiguedad, 
concepto, dependencia, cargo, rubro, montorubro, cantidad, salario) 
FROM '/home/natalia/Documentos/opendata/electiva6_mec/csv/junio.csv' 
DELIMITER ',' CSV HEADER;

  
COPY funcionario (mes, anho, nroDocumento, nombreCompleto, objetoGasto, nombreObjetoGasto, estado, antiguedad, 
concepto, dependencia, cargo, rubro, montorubro, cantidad, salario) 
FROM '/home/natalia/Documentos/opendata/electiva6_mec/csv/julio.csv' 
DELIMITER ',' CSV HEADER;

COPY funcionario (mes, anho, nroDocumento, nombreCompleto, objetoGasto, nombreObjetoGasto, estado, antiguedad, 
concepto, dependencia, cargo, rubro, montorubro, cantidad, salario) 
FROM '/home/natalia/Documentos/opendata/electiva6_mec/csv/agosto.csv' 
DELIMITER ',' CSV HEADER;

create index mes on funcionario(mes);

/**PARA EXTRAER TODOS LOS DATOS DE LA BASE DE DATOS EN UN CSV **/
COPY funcionario (mes, anho, nroDocumento, nombreCompleto, objetoGasto, nombreObjetoGasto, estado, antiguedad, 
concepto, dependencia, cargo, rubro, montorubro, cantidad, salario) 
TO '/home/natalia/funcionarios.csv' WITH
DELIMITER ',' CSV HEADER;