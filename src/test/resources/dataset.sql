insert into administrador values(1, 'pepito@email.com', '123');
insert into administrador values(2, 'juanita@email.com', '234');

insert into paciente values(1, 'juanitalopez@gmail.com', '1', 5, false, 'url_fot', 'juanita lopez', '345154888', 'Sin alergias', 2, '1995-11-28', 1);
insert into paciente values(2, 'pedrogonzalez@gmail.com', '2', 3, true, 'url_foto_2', 'pedro gonzalez', '555123456', 'alergia al polen', 1, '1990-08-15', 1);
insert into paciente values(3, 'mariaperez@gmail.com', '3', 4, false, 'url_foto_3', 'maria perez', '789987654', 'sin alergias conocidas', 3, '1988-03-20', 2);
insert into paciente values(4, 'carlosrodriguez@gmail.com', '4', 2, true, 'url_foto_4', 'carlos rodriguez', '654789321', 'alergia a los frutos secos', 1, '2000-05-10', 3);
insert into paciente values(5, 'lauragonzalez@gmail.com', '5', 1, false, 'url_foto_5', 'laura gonzalez', '987654321', 'Sin alergias', 2, '1998-12-05', 2);
insert into paciente values(6, 'andresramirez@gmail.com', '6', 3, false, 'url_foto_6', 'andres ramirez', '123456789', 'Sin alergias', 1, '1992-07-22', 1);
insert into paciente values(7, 'sarasanchez@gmail.com', '7', 4, true, 'url_foto_7', 'sara sanchez', '567890123', 'alergia al gluten', 2, '1985-04-17', 3);
insert into paciente values(8, 'davidlopez@gmail.com', '8', 5, false, 'url_foto_8', 'david lopez', '789012345', 'Sin alergias', 1, '1980-09-30', 2);
insert into paciente values(9, 'anamartinez@gmail.com', '9', 2, false, 'url_foto_9', 'ana martinez', '234567890', 'Sin alergias', 3, '1997-02-14', 3);
insert into paciente values(10, 'josehernandez@gmail.com', '10', 1, true, 'url_foto_10', 'jose hernandez', '456789012', 'alergia al marisco', 2, '1993-06-08', 2);

insert into medico values(1, 'juanfigueroa@clinicadelcarmen.com', '12345', 4, true, 'foto', 'juan figueroa', '315454578', 1 );
insert into medico values(2, 'carlosruiz@clinicadelcarmen.com', 'abcde', 10, true, 'carlos.jpg', 'Carlos Ruiz', '318888999', 1 );
insert into medico values(3, 'ganudi@clinicadelcarmen.com', 'password123', 18, true, 'gabi.jpg', 'Gabriela Nunez', '312345678', 4 );
insert into medico values(4, 'andresramirez@clinicadelcarmen.com', 'securepass', 7, true, 'andres.jpg', 'Andres Ramirez', '311234567', 8 );
insert into medico values(5, 'monicavargas@clinicadelcarmen.com', 'mysecretpw', 12, true, 'monica.jpg', 'Monica Vargas', '317890123', 9 );

insert into horario values(1, '08:00:00', '07:00:00', 1);
insert into horario values(2, '08:30:00', '07:00:00', 2);
insert into horario values(3, '09:00:00', '07:00:00', 3);
insert into horario values(4, '17:00:00', '07:00:00', 4);
insert into horario values(5, '17:00:00', '07:00:00', 5);

# la cita contiene: el código de la cita // el estado de la cita //
# la fecha de la cita // la fecha de creación de la cita //
# el motivo de la cita // Cédula del medico // Cédula del paciente
insert into cita value (4, '0', '2023-10-12 09:30', '2023-10-10 23:44', 'Realizar un chequeo cardíaco anual debido a antecedentes familiares de enfermedades cardíacas', 1, 4 );
insert into cita values (5, '0', '2023-10-20 10:30', '2023-10-10 12:45', 'cardiología', 1, 5);
insert into cita values (7, '2', '2023-10-12 16:45', '2023-10-17 19:30', 'ginecología', 4, 8);
insert into cita values (8, '1', '2023-10-11 07:30', '2023-10-20 13:45', 'neurología', 2, 8);

# el pqrs contiene: Código del pqr // estado del pqrs // motivo del pqrs // codigo de la cita
insert into pqrs values (1, 0, '2023-11-10', 'El doctor fue grosero 1', 6);
insert into pqrs values (2, 0, '2023-11-10', 'El doctor fue grosero 2', 7);
insert into pqrs values (3, 2, '2023-11-10', 'El doctor fue grosero 3', 8);
insert into pqrs values (5, 0, '2023-11-10', 'El doctor fue grosero 5', 5);

# Un mensaje contiene: codigoMensaje // ContenidoMensaje // fecha del mensaje // mensaje si hay que contestar a otro con anterioridad // codigo del pqr
insert into mensaje value (1, 'aquí escribo todas las razones para responder el pqr', '2023-11-10', null, 1);
insert into mensaje values (2, 'respuesta al mensaje 1', '2023-11-11', null, 3);
insert into mensaje values (3, 'mensaje independiente', '2023-11-12', null, 2);
insert into mensaje values (4, 'respuesta al mensaje 3', '2023-11-13', null, 2);
insert into mensaje values (5, 'otro mensaje independiente', '2023-11-14', null, 2);
insert into mensaje values (6, 'respuesta al mensaje 5', '2023-11-15', 2, 3);


insert into dia_libre values (1, '2023-10-11', 3);
insert into dia_libre values (2, '2023-10-12', 1);
insert into dia_libre values (3, '2023-12-01', 2);
insert into dia_libre values (4, '2023-12-04', 2);
insert into dia_libre values (5, '2023-12-05', 2);