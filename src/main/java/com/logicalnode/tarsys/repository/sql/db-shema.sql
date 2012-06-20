CREATE DATABASE `tarsysdb` /*!40100 DEFAULT CHARACTER SET latin1 */

/*Estudio*/
CREATE  TABLE `tarsysdb`.`estudio` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `categoria` VARCHAR(45) NOT NULL ,
  `empresa` VARCHAR(255) NULL DEFAULT NULL ,
  `nombre` VARCHAR(255) NOT NULL ,
  `numeroDeMedidor` INT(20) NULL DEFAULT 0 ,
  `observaciones` VARCHAR(255) NULL DEFAULT NULL ,
  `version` INT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) );

INSERT INTO `tarsysdb`.`estudio` (`categoria`, `empresa`, `nombre`, `numeroDeMedidor`, `observaciones`, `version`) VALUES ('261', 'ln', 'a', 1, 'ninguna', 1);
ALTER TABLE `tarsysdb`.`estudio` CHANGE COLUMN `numeroDeMedidor` `numeroDeMedidor` INT(50) NULL DEFAULT '0'  ;



/*Factura*/
CREATE  TABLE `tarsysdb`.`factura` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `estudio` BIGINT(20) NULL DEFAULT NULL ,
  `numero_de_factura` BIGINT(20) NOT NULL ,
  `version` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `numero_de_factura_UNIQUE` (`numero_de_factura` ASC) );

ALTER TABLE `tarsysdb`.`factura` CHANGE COLUMN `numero_de_factura` `numero_de_factura` BIGINT(50) NOT NULL  ;

  
  
  
/*Consumo*/
CREATE  TABLE `tarsysdb`.`consumo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `constante` DOUBLE NOT NULL ,
  `factura` BIGINT(20) NULL DEFAULT NULL ,
  `lectura` BIGINT(20) NOT NULL ,
  `tipo_de_consumo` VARCHAR(45) NULL DEFAULT NULL ,
  `version` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) );

INSERT INTO `tarsysdb`.`consumo` (`id`, `constante`, `factura`, `lectura`, `tipo_de_consumo`, `version`) VALUES (1, 0.5, 1, 55555, 'PotenciaReservada', 1);
INSERT INTO `tarsysdb`.`consumo` (`id`, `constante`, `factura`, `lectura`, `tipo_de_consumo`, `version`) VALUES (2, 0.8, 1, 2222, 'Energia', 1);
