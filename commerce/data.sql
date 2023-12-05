
INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `type`) VALUES
(1, 'Pizza làm ở Ý, hương vị thơm ngon', '/images/cart1.png', 'Pizza Ý', 20, 'PIZZA'),
(5, 'Pizza  với nhiều lớp phô mai', '/images/cart1.png', 'Pizza Phô Mai', 25, 'PIZZA'),
(6, 'Thức uống quốc dân', '/images/pro_img5.jpg', 'Coca Cola', 5, 'DRINK'),
(7, 'Pizza với gà', '/images/cart1.png', 'Pizza Gà', 25, 'PIZZA'),
(8, 'Pizza bò ngon lắm , thử ngay', '/images/cart1.png', 'Pizza Bò', 25, 'PIZZA'),
(9, 'Ngon lắm', '/images/pepsi.jpg', 'Pepsi', 5, 'DRINK'),
(10, 'Nước cam cũng ngon', '/images/fanta.jpg', 'Fanta plus', 7, 'DRINK'),
(11, 'Burger bò ngon lắm', '/images/burger_bo.jpg', 'Burger Bò', 15, 'BURGER'),
(12, 'Burger gấp đôi vị ngon', '/images/burger_bo.jpg', 'Burger Bò 2 lớp', 20, 'BURGER'),
(13, 'Nước cam ngon lắm', '/images/fanta.jpg', 'Fanta', 10, 'DRINK'),
(20, 'Pizza từ Ý, rất ngon', '/images/cart1.png', 'Pizza Ý', 20, 'PIZZA'),
(21, 'Thức uống quốc dân', '/images/pro_img5.jpg', 'Coca Cola', 5, 'DRINK'),
(22, 'Ngon lắm', '/images/Burgers.jpg', 'Burger Mỹ', 20, 'BURGER'),
(23, 'Ngon lắm', '/images/Burgers.jpg', 'Burger Bò', 20, 'BURGER');

-- --------------------------------------------------------

INSERT INTO `user` (`username`, `password`, `role`) VALUES
('phuquoc1', '1234567890', 'ROLE_USER'),
('phuquoc12', '$2a$10$4UIzFNuVsFUysQZcEo1di.jrNNzFTwzJAUwBkHhB/PdZQh85nn0Pi', 'ROLE_USER'),
('phuquoc13', '$2a$10$2HHpRMoNkoltaJZCdnuPSemjIocMiHLK6PuAYWDw7gwKeR1haDZra', 'ROLE_USER'),
('phuquoc2', '1234567890', 'ROLE_USER'),
('phuquoc3', '1234567890', 'ROLE_USER'),
('phuquoc4', '1234567890', 'ROLE_USER'),
('phuquoc5', '1234567890', 'ROLE_USER'),
('phuquoc56', '$2a$10$E86alIBKXcG1fo7yBhoVI.DpiOCNRUp5w1f51RoOdGJ6nO254GNqm', 'ROLE_USER'),
('phuquoc6', '1234567890', 'ROLE_USER'),
('Quang123', '$2a$10$.415IRLCs8q/NA2d.a0yTey3f8d825UB/AQjT4v9DpFa9.QEIgYjK', 'ROLE_USER');


INSERT INTO `orders` (`id`, `address`, `phone`, `status`, `total`, `username`) VALUES
(7, '568 Hải Thượng Lãn Ông', '123456', b'1', 77, 'phuquoc56'),
(8, '568 Hải Thượng Lãn Ông', '123345', b'1', 30, 'admin'),
(10, '72 Tạ Uyên', '932434', b'0', 30, 'phuquoc12'),
(11, '19 Nguyễn Hữu Thọ', '54634', b'0', 70, 'phuquoc13'),
(12, '161 Âu Cơ , Tân Phú', '4343', b'0', 45, 'Quang123');


INSERT INTO `order_detail` (`id`, `quantity`, `order_id`, `product_id`) VALUES
(9, 1, 7, 1),
(10, 2, 7, 5),
(11, 1, 7, 10),
(12, 1, 8, 6),
(13, 1, 8, 7),
(14, 1, 10, 9),
(15, 1, 10, 5),
(16, 1, 11, 7),
(17, 2, 11, 1),
(18, 1, 11, 9),
(19, 1, 12, 8),
(20, 1, 12, 12);

INSERT INTO `cart` (`id`, `quantity`, `status`, `product_id`, `username`) VALUES
(13, 1, b'0', 10, 'phuquoc3'),
(14, 1, b'0', 5, 'phuquoc3'),
(15, 1, b'0', 11, 'phuquoc5'),
(16, 1, b'0', 1, 'phuquoc5'),
(17, 1, b'0', 12, 'phuquoc2'),
(18, 1, b'0', 7, 'phuquoc6'),
(19, 1, b'0', 10, 'phuquoc6'),
(20, 1, b'0', 6, 'phuquoc6'),
(28, 1, b'0', 6, 'phuquoc12'),
(29, 1, b'0', 12, 'phuquoc12');

