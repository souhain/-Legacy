CREATE TABLE IF NOT EXISTS `cliente2` (
    `id_cliente2` int  unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(45),
    `address` varchar(45),
    `email` varchar(100),
    `phone` varchar(45)
);

CREATE TABLE IF NOT EXISTS `cuenta2` (
    `id_cuenta2` int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `saldo` decimal(10,2),
    `ejecutive_email` varchar(100),
    `cliente2_id_cliente2` int unsigned NOT NULL,
 
  FOREIGN KEY(`cliente2_id_cliente2`) REFERENCES cliente2(`cliente2`),
);

DELIMITER //
CREATE PROCEDURE `nuevo_cliente`(IN name varchar(45),
IN address varchar(45),
IN email varchar(45),
IN phone varchar(10),  
IN saldo DECIMAL(10,2),
IN ejecutive_email varchar(45),
OUT p_cliente2 int(11),
OUT p_cuenta2 int(11),
OUT p_saldo DECIMAL(10,2))
BEGIN
   INSERT INTO cliente2 (name, address, email, phone) values (name, address, email, phone);
   INSERT INTO cuenta2 (saldo, ejecutive_email, cliente2_id_cliente2) values (saldo, ejecutive_email, LAST_INSERT_ID());

   SELECT cuenta2.saldo, cuenta2.id_cuenta2, cuenta2.cliente2_id_cliente2
   INTO p_saldo, p_cuenta2, p_cliente2
   FROM cuenta2
   WHERE cuenta2.id_cuenta2 = LAST_INSERT_ID();
END //
DELIMITER ;