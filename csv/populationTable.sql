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

/*select junio.nombreCompleto, junio.salario, julio.salario from
(select distinct f.nombreCompleto, sum(salario) as salario from funcionario f where mes='junio' 
order by f.nombreCompleto group by f.nombreCompleto) junio,
(select distinct f.nombreCompleto, sum(salario) as salario from funcionario f where mes='julio' 
order by f.nombreCompleto group by f.nombreCompleto) julio;*/