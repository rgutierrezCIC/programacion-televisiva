INSERT INTO tipo_programa (id, nombre, descripcion, fechaCreacion, fechaModificacion) VALUES
    (random_uuid(), 'Documental', 'Programas educativos y de información factual', '2022-05-15 10:00:00', '2022-05-15 10:00:00'),
    (random_uuid(), 'Noticiero', 'Programas de noticias y actualidad', '2022-06-20 11:00:00', '2022-06-20 11:00:00'),
    (random_uuid(), 'Serie', 'Series de ficción y entretenimiento', '2022-07-25 12:00:00', '2022-07-25 12:00:00');

INSERT INTO programa (id, nombre, descripcion, clasificacion, canal, fecha_inicio, fecha_fin, semanal, dias, favorito, tipo_programa_id) VALUES
    (random_uuid(), 'Noticias de la Mañana', 'Noticias diarias matutinas', 'TP', 'Canal 1', '2023-09-02 08:00:00', '2023-09-02 09:00:00', TRUE, 'Lunes,Martes,Miércoles,Jueves,Viernes', false, (SELECT id FROM tipo_programa WHERE nombre='Noticiero' LIMIT 1)),
    (random_uuid(), 'Documentales del Mundo', 'Documentales sobre naturaleza y cultura', 'TP', 'Canal 2', '2023-09-02 20:00:00', '2023-09-02 21:00:00', TRUE, 'Domingo', true, (SELECT id FROM tipo_programa WHERE nombre='Documental' LIMIT 1)),
    (random_uuid(), 'Serie de Misterio', 'Serie de ficción con elementos de suspense', '16', 'Canal 3', '2023-09-02 22:00:00', '2023-09-02 23:00:00', TRUE, 'Lunes', false, (SELECT id FROM tipo_programa WHERE nombre='Serie' LIMIT 1)),
    (random_uuid(), 'Cine de Medianoche', 'Películas para adultos con temas serios', '18', 'Canal 4', '2023-09-02 23:00:00', '2023-09-03 01:00:00', FALSE, 'Sábado', false, (SELECT id FROM tipo_programa WHERE nombre='Serie' LIMIT 1));