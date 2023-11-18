insert into cuenta
values (1, 'pepito@email.com', '123');
insert into cuenta
values (2, 'juanita@email.com', '234');

insert into administrador
values (1);
insert into administrador
values (2);

insert into cuenta
values (11, 'juanitalopez@gmail.com', '1');
insert into cuenta
values (12, 'pedrogonzalez@gmail.com', '2');
insert into cuenta
values (3, 'mariaperez@gmail.com', '3');
insert into cuenta
values (4, 'carlosrodriguez@gmail.com', '4');
insert into cuenta
values (5, 'lauragonzalez@gmail.com', '5');
insert into cuenta
values (6, 'andresramirez@gmail.com', '6');
insert into cuenta
values (7, 'sarasanchez@gmail.com', '7');
insert into cuenta
values (8, 'davidlopez@gmail.com', '8');
insert into cuenta
values (9, 'anamartinez@gmail.com', '9');
insert into cuenta
values (10, 'juesnube@gmail.com', '10');
insert into cuenta
values (13, 'juanf@gmail.com', '10');
insert into cuenta
values (14, 'carlosr@gmail.com', '10');
insert into cuenta
values (15, 'gabi@gmail.com', '10');
insert into cuenta
values (16, 'andresr@gmail.com', '10');
insert into cuenta
values (17, 'monicav@gmail.com', '10');

insert into paciente
values ('1', 5, false, 'url_fot', 'juanita lopez', '345154888', 'Sin alergias', 2, '1995-11-28', 1, 11);
insert into paciente
values ('25', 3, false, 'url_foto_2', 'pedro gonzalez', '555123456', 'alergia al polen', 1, '1990-08-15', 1, 12);
insert into paciente
values ('3', 4, false, 'url_foto_3', 'maria perez', '789987654', 'sin alergias conocidas', 3, '1988-03-20', 2, 3);
insert into paciente
values ('4', 2, false, 'url_foto_4', 'carlos rodriguez', '654789321', 'alergia a los frutos secos', 1, '2000-05-10', 3,
        4);
insert into paciente
values ('5', 1, false, 'url_foto_5', 'laura gonzalez', '987654321', 'Sin alergias', 2, '1998-12-05', 2, 5);
insert into paciente
values ('6', 3, false, 'url_foto_6', 'andres ramirez', '123456789', 'Sin alergias', 1, '1992-07-22', 1, 6);
insert into paciente
values ('7', 4, false, 'url_foto_7', 'sara sanchez', '567890123', 'alergia al gluten', 2, '1985-04-17', 3, 7);
insert into paciente
values ('8', 5, false, 'url_foto_8', 'david lopez', '789012345', 'Sin alergias', 1, '1980-09-30', 2, 8);
insert into paciente
values ('9', 2, false, 'url_foto_9', 'ana martinez', '234567890', 'Sin alergias', 3, '1997-02-14', 3, 9);
insert into paciente
values ('10', 1, false, 'url_foto_10', 'jose hernandez', '456789012', 'alergia al marisco', 2, '1993-06-08', 2, 10);

insert into medico
values ('1', 4, true, 'foto', 'juan figueroa', '315454578', 1, 13);
insert into medico
values ('2', 10, true, 'carlos.jpg', 'Carlos Ruiz', '318888999', 1, 14);
insert into medico
values ('3', 18, true, 'gabi.jpg', 'Gabriela Nunez', '312345678', 4, 15);
insert into medico
values ('4', 7, true, 'andres.jpg', 'Andres Ramirez', '311234567', 8, 16);
insert into medico
values ('5', 12, true, 'monica.jpg', 'Monica Vargas', '317890123', 9, 17);


insert into horario
values (1, '08:00:00', '07:00:00', 13);
insert into horario
values (2, '08:30:00', '07:00:00', 14);
insert into horario
values (3, '09:00:00', '07:00:00', 15);
insert into horario
values (4, '17:00:00', '07:00:00', 16);
insert into horario
values (5, '17:00:00', '07:00:00', 17);

insert into cita
values (4, '0', '2023-10-12 09:30', '2023-10-10 23:44',
        'Realizar un chequeo cardíaco anual debido a antecedentes familiares de enfermedades cardíacas',
        14, 4);
insert into cita
values (5, '0', '2023-10-20 10:30', '2023-12-10 12:45', 'cardiología', 13, 5);
insert into cita
values (7, '2', '2023-10-12 16:45', '2023-10-17 19:30', 'ginecología', 15, 8);
insert into cita
values (8, '0', '2023-12-11 07:30', '2023-10-20 13:45', 'neurología', 16, 8);
insert into pqrs
values (1, 0, '2023-11-10', 'El doctor fue grosero 1', 4);
insert into pqrs
values (2, 0, '2023-11-10', 'El doctor fue grosero 2', 7);
insert into pqrs
values (3, 2, '2023-11-10', 'El doctor fue grosero 3', 8);
insert into pqrs
values (5, 0, '2023-11-10', 'El doctor fue grosero 5', 5);
insert into mensaje
values (1, 'aquí escribo todas las razones para responder el pqr', '2023-11-10', null, null, 2);
insert into mensaje
values (2, 'respuesta al mensaje 1', '2023-11-11', null, null, 3);
insert into mensaje
values (3, 'mensaje independiente', '2023-11-12', null, null, 2);
insert into mensaje
values (4, 'respuesta al mensaje 3', '2023-11-13', 1, 3, 2);
insert into mensaje
values (5, 'otro mensaje independiente', '2023-11-14', null, null, 5);


insert into dia_libre
values (1, '2023-10-11', 13);
insert into dia_libre
values (2, '2023-10-12', 14);
insert into dia_libre
values (3, '2023-12-01', 15);
insert into dia_libre
values (4, '2023-12-04', 16);
insert into dia_libre
values (5, '2023-12-05', 17);

insert into atencion
values (1, 'Aquí va el diagnostico', 'Aqui van las notas medicas', 'Aqui va el tratamiento', 7);