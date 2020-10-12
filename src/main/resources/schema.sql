DROP TABLE IF EXISTS BASEENTITY;
DROP TABLE IF EXISTS CAR;
DROP TABLE IF EXISTS AIRPLANE;
DROP TABLE IF EXISTS COSTEVALUATION;

create table BASEENTITY (
  id identity primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200)
);

create table CAR (
  power DOUBLE,
  year_of_issue YEAR,
  base_entity_id INT not null primary key,
  foreign key(base_entity_id) references BASEENTITY(id)
);

create table AIRPLANE (
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuelCapacity INT,
  seats INT,
  base_entity_id INT not null primary key,
  foreign key(base_entity_id) references BASEENTITY(id)
);

create table COSTEVALUATION (
  id identity primary key,
  date date ,
  value dec(20),
  evaluation_object_id int not null,
  foreign key(evaluation_object_id) references BASEENTITY(id)
);
