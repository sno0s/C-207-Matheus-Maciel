SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `BatalhaDeRima` DEFAULT CHARACTER SET utf8 ;
USE `BatalhaDeRima` ;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Batalha` (
  `idBatalha` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `local` VARCHAR(45) NULL,
  `diaDaSemana` VARCHAR(45) NULL,
  PRIMARY KEY (`idBatalha`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Edicao` (
  `idEdicao` INT NOT NULL AUTO_INCREMENT,
  `numEdicao` INT NULL,
  `data` VARCHAR(45) NULL,
  `Batalha_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEdicao`, `Batalha_nome`),
  INDEX `fk_Edicao_Batalha_idx` (`Batalha_nome` ASC) VISIBLE,
  CONSTRAINT `fk_Edicao_Batalha`
    FOREIGN KEY (`Batalha_nome`)
    REFERENCES `BatalhaDeRima`.`Batalha` (`idBatalha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Participante` (
  `idParticipante` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `idade` INT NULL,
  `vulgo` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idParticipante`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Edicao_has_Participante` (
  `idEdicao` INT NOT NULL,
  `idParticipante` INT NOT NULL,
  PRIMARY KEY (`idEdicao`, `idParticipante`),
  INDEX `fk_Edicao_has_Participante_Participante1_idx` (`idParticipante` ASC) VISIBLE,
  INDEX `fk_Edicao_has_Participante_Edicao1_idx` (`idEdicao` ASC) VISIBLE,
  CONSTRAINT `fk_Edicao_has_Participante_Edicao1`
    FOREIGN KEY (`idEdicao`)
    REFERENCES `BatalhaDeRima`.`Edicao` (`Batalha_nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Edicao_has_Participante_Participante1`
    FOREIGN KEY (`idParticipante`)
    REFERENCES `BatalhaDeRima`.`Participante` (`idParticipante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Vencedores` (
  `idVencedores` INT NOT NULL AUTO_INCREMENT,
  `Vencedorescol` VARCHAR(45) NULL,
  `idEdicao` INT NOT NULL,
  `idParticipante` INT NOT NULL,
  PRIMARY KEY (`idVencedores`, `idParticipante`, `idEdicao`),
  INDEX `idEdicao_idx` (`idEdicao` ASC) VISIBLE,
  INDEX `idParticipante_idx` (`idParticipante` ASC) VISIBLE,
  CONSTRAINT `idEdicao`
    FOREIGN KEY (`idEdicao`)
    REFERENCES `BatalhaDeRima`.`Edicao` (`idEdicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idParticipante`
    FOREIGN KEY (`idParticipante`)
    REFERENCES `BatalhaDeRima`.`Participante` (`idParticipante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Estrutura` (
  `Coberto` TINYINT NULL,
  `Microfone` TINYINT NULL,
  `Batalha_idBatalha` INT NOT NULL,
  PRIMARY KEY (`Batalha_idBatalha`),
  CONSTRAINT `fk_Estrutura_Batalha1`
    FOREIGN KEY (`Batalha_idBatalha`)
    REFERENCES `BatalhaDeRima`.`Batalha` (`idBatalha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

