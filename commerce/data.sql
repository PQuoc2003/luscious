
INSERT INTO `product` (`id`, `description`, `name`, `price`, `type`) VALUES
(1, 'Pizza làm ở Ý, hương vị thơm ngon', 'Pizza Ý', 20, 'PIZZA'),
(5, 'Pizza  với nhiều lớp phô mai', 'Pizza Phô Mai', 25, 'PIZZA'),
(6, 'Thức uống quốc dân', 'Coca Cola', 5, 'DRINK'),
(7, 'Pizza với gà', 'Pizza Gà', 25, 'PIZZA'),
(8, 'Pizza bò ngon lắm , thử ngay', 'Pizza Bò', 25, 'PIZZA'),
(9, 'Ngon lắm', 'Pepsi', 5, 'DRINK'),
(10, 'Nước cam cũng ngon', 'Fanta', 7, 'DRINK'),
(11, 'Burger bò ngon lắm', 'Burger Bò', 15, 'BURGER'),
(12, 'Burger gấp đôi vị ngon', 'Burger Bò 2 lớp', 20, 'BURGER');

INSERT INTO `user` (`username`, `password`, `role`) VALUES
('phuquoc', '1234567890', 'ROLE_ADMIN'),
('phuquoc1', '1234567890', 'ROLE_USER'),
('phuquoc2', '1234567890', 'ROLE_USER'),
('phuquoc3', '1234567890', 'ROLE_USER'),
('phuquoc4', '1234567890', 'ROLE_USER'),
('phuquoc5', '1234567890', 'ROLE_USER'),
('phuquoc6', '1234567890', 'ROLE_USER');


INSERT INTO `cart` (`id`, `status`, `product_id`, `username`) VALUES
(12, b'0', 6, 'phuquoc'),
(13, b'0', 10, 'phuquoc3'),
(14, b'0', 5, 'phuquoc3'),
(15, b'0', 11, 'phuquoc5'),
(16, b'0', 1, 'phuquoc5'),
(17, b'0', 12, 'phuquoc2'),
(18, b'0', 7, 'phuquoc6'),
(19, b'0', 10, 'phuquoc6'),
(20, b'0', 6, 'phuquoc6');


INSERT INTO `orders` (`id`, `address`, `phone`, `status`, `total`, `username`) VALUES
(2, '568 Hải Thượng Lãn Ông', '123456', b'1', 20, 'phuquoc2'),
(3, '23 Kênh Tân Hoá', '123345', b'1', 20, 'phuquoc1'),
(4, 'Lạc Long Quận , TP.HCM', '1234', b'0', 5, 'phuquoc4'),
(5, '19 Nguyễn Hữu Thọ', '123345', b'0', 5, 'phuquoc6'),
(6, 'Lạc Long Quận , TP.HCM', '123345', b'0', 45, 'phuquoc');

INSERT INTO `order_detail` (`id`, `order_id`, `product_id`) VALUES
(2, 2, 1),
(3, 3, 1),
(4, 4, 6),
(5, 5, 6),
(6, 6, 7),
(7, 6, 12),
(8, 3, 7);

