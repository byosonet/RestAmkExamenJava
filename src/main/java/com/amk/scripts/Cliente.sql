CREATE TABLE `cliente` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`apellido` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`email` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`fechaNacimiento` DATE NULL DEFAULT NULL,
	`sexo` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`telefono` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`)
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB
;