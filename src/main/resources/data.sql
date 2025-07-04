INSERT INTO categories (id, name) VALUES
(1, 'Textil'),
(2, 'Print');

INSERT INTO products (name, price, image_url, featured, category_id) VALUES
('Lámina "Titis"', 29.99, 'https://imagen.jpg', 1, 2),
('Camiseta "Envejecer con las amigas"', 49.99, 'https://imagen.jpg', 0, 1);

INSERT INTO users (username, email, password) VALUES
('Lara', 'lara@correo.com', 'Lara123.'),
('Sofía', 'soffinutria@nutria.com', 'Nutria321.');

INSERT INTO cart (id, user_id, total_price) VALUES
(1, 1, 0.0),
(2, 2, 0.0);