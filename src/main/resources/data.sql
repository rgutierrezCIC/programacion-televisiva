INSERT INTO tipo_programa (id, nombre, descripcion, fecha_creacion, fecha_modificacion) VALUES
    (random_uuid(), 'Documental', 'Programas educativos y de información factual', '2022-05-15 10:00:00', null),
(random_uuid(), 'Noticiero', 'Programas de noticias y actualidad', '2022-06-20 11:00:00', null),
(random_uuid(), 'Serie', 'Series de ficción y entretenimiento', '2022-07-25 12:00:00', null),
(random_uuid(), 'Comedia', 'Programas de comedia y entretenimiento', '2022-08-30 13:00:00', null),
(random_uuid(), 'Película', 'Películas de diversos géneros', '2022-09-10 14:00:00', null),
(random_uuid(), 'Animación', 'Series y películas animadas', '2022-10-05 15:00:00', null),
(random_uuid(), 'Debate', 'Programas de debate y análisis', '2022-11-15 16:00:00', null),
(random_uuid(), 'Deportes', 'Programas y eventos deportivos', '2022-12-20 17:00:00', null),
(random_uuid(), 'Reality Show', 'Programas de telerrealidad', '2023-01-10 18:00:00', null),
(random_uuid(), 'Infantil', 'Programas para niños y niñas', '2023-02-25 19:00:00', null);

INSERT INTO programa (id, nombre, descripcion, clasificacion, canal, fecha_inicio, fecha_fin, semanal, dias, favorito, tipo_programa_id) VALUES
    (random_uuid(), 'Noticias de la Mañana', 'Noticias diarias matutinas', 'TP', 'Canal 1', '2023-09-02 08:00:00', '2023-09-02 09:00:00', TRUE, ARRAY['Lunes','Martes','Miércoles','Jueves','Viernes'], false, (SELECT id FROM tipo_programa WHERE nombre='Noticiero' LIMIT 1)),
(random_uuid(), 'Documentales del Mundo', 'Documentales sobre naturaleza y cultura', 'TP', 'Canal 2', '2023-09-02 20:00:00', '2023-09-02 21:00:00', TRUE, 'Domingo', true, (SELECT id FROM tipo_programa WHERE nombre='Documental' LIMIT 1)),
(random_uuid(), 'Serie de Misterio', 'Serie de ficción con elementos de suspense', '16', 'Canal 3', '2023-09-02 22:00:00', '2023-09-02 23:00:00', TRUE, 'Lunes', false, (SELECT id FROM tipo_programa WHERE nombre='Serie' LIMIT 1)),
(random_uuid(), 'Cine de Medianoche', 'Películas para adultos con temas serios', '18', 'Canal 4', '2023-09-02 23:00:00', '2023-09-03 01:00:00', FALSE, 'Sábado', false, (SELECT id FROM tipo_programa WHERE nombre='Serie' LIMIT 1)),
(random_uuid(), 'Tardes de Comedia', 'Comedias clásicas y modernas', 'TP', 'Canal 5', '2023-09-02 16:00:00', '2023-09-02 18:00:00', TRUE, 'Sábado', true, (SELECT id FROM tipo_programa WHERE nombre='Comedia' LIMIT 1)),
(random_uuid(), 'Noticias Nocturnas', 'Resumen de noticias del día', 'TP', 'Canal 1', '2023-09-02 22:00:00', '2023-09-02 23:00:00', TRUE, ARRAY['Lunes','Martes','Miércoles','Jueves','Viernes'], false, (SELECT id FROM tipo_programa WHERE nombre='Noticiero' LIMIT 1)),
(random_uuid(), 'Películas de Acción', 'Películas de acción y aventura', '12', 'Canal 6', '2023-09-02 20:00:00', '2023-09-02 22:00:00', TRUE, 'Viernes', true, (SELECT id FROM tipo_programa WHERE nombre='Película' LIMIT 1)),
(random_uuid(), 'Series Animadas', 'Series animadas para toda la familia', 'TP', 'Canal 7', '2023-09-02 10:00:00', '2023-09-02 12:00:00', TRUE, 'Sábado', true, (SELECT id FROM tipo_programa WHERE nombre='Animación' LIMIT 1)),
(random_uuid(), 'Debates Políticos', 'Debates y análisis político', 'TP', 'Canal 8', '2023-09-02 21:00:00', '2023-09-02 22:00:00', TRUE, 'Miércoles', false, (SELECT id FROM tipo_programa WHERE nombre='Debate' LIMIT 1)),
(random_uuid(), 'Cine Clásico', 'Películas clásicas de todos los tiempos', 'TP', 'Canal 9', '2023-09-02 18:00:00', '2023-09-02 20:00:00', TRUE, 'Domingo', true, (SELECT id FROM tipo_programa WHERE nombre='Película' LIMIT 1));