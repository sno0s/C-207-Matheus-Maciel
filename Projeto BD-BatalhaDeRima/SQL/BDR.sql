SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `BatalhaDeRima` DEFAULT CHARACTER SET utf8;
USE `BatalhaDeRima`;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Batalha` (
  `idBatalha` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `local` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `diaDaSemana` VARCHAR(45) NULL,
  PRIMARY KEY (`idBatalha`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Edicao` (
  `idEdicao` INT NOT NULL AUTO_INCREMENT,
  `numEdicao` INT NULL,
  `data` VARCHAR(45) NULL,
  `idBatalha` INT NOT NULL,
  PRIMARY KEY (`idEdicao`),
  INDEX `fk_Edicao_Batalha_idx` (`idBatalha` ASC) VISIBLE,
  CONSTRAINT `fk_Edicao_Batalha`
    FOREIGN KEY (`idBatalha`)
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
    REFERENCES `BatalhaDeRima`.`Edicao` (`idEdicao`)
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
  `idEdicao` INT NOT NULL,
  `idParticipante` INT NOT NULL,
  PRIMARY KEY (`idVencedores`, `idParticipante`, `idEdicao`),
  INDEX `idEdicao_idx` (`idEdicao` ASC) VISIBLE,
  INDEX `idParticipante_idx` (`idParticipante` ASC) VISIBLE,
  CONSTRAINT `fk_Vencedores_Edicao`
    FOREIGN KEY (`idEdicao`)
    REFERENCES `BatalhaDeRima`.`Edicao` (`idEdicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vencedores_Participante`
    FOREIGN KEY (`idParticipante`)
    REFERENCES `BatalhaDeRima`.`Participante` (`idParticipante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BatalhaDeRima`.`Estrutura` (
  `Coberto` TINYINT NULL,
  `Microfone` TINYINT NULL,
  `idBatalha` INT NOT NULL,
  PRIMARY KEY (`idBatalha`),
  CONSTRAINT `fk_Estrutura_Batalha`
    FOREIGN KEY (`idBatalha`)
    REFERENCES `BatalhaDeRima`.`Batalha` (`idBatalha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

#criando usu[ario admin geral 
create user 'adm' identified by 'admin';
grant all privileges on batalhaderima.* to 'adm';
#Ver todas as batalhas e suas informações
select 
	Batalha.nome as Nome,
    Batalha.local as Local,
    Batalha.estado as Estado,
    Batalha.diaDaSemana as Dia_da_semana
from
	Batalha;

#Ver todas as batalhas de um determinado estado
select 
	Batalha.nome as Nome,
    Batalha.local as Local,
    Batalha.estado as Estado,
    Batalha.diaDaSemana as Dia_da_semana
from
	Batalha
where 
	batalha.estado = 'SP';

#Mostrando os vencedores de todas as edições de uma batalha específica
SELECT 
    Edicao.numEdicao as Edicao,
    Edicao.data as Data,
    Batalha.nome AS Batalha,
    Participante.vulgo AS Vencedor
FROM 
    Edicao
INNER JOIN 
    Batalha ON Edicao.idBatalha = Batalha.idBatalha
INNER JOIN 
    Vencedores ON Edicao.idEdicao = Vencedores.idEdicao
INNER JOIN 
    Participante ON Vencedores.idParticipante = Participante.idParticipante
WHERE 
    Batalha.nome = 'Batalha da Leste';
    
#Mostrando todas as vitórias de um MC específico
SELECT 
    Edicao.numEdicao as Edicao,
    Edicao.data as Data,
    Batalha.nome AS Batalha,
    Participante.vulgo AS Vencedor
FROM 
    Edicao
INNER JOIN 
    Batalha ON Edicao.idBatalha = Batalha.idBatalha
INNER JOIN 
    Vencedores ON Edicao.idEdicao = Vencedores.idEdicao
INNER JOIN 
    Participante ON Vencedores.idParticipante = Participante.idParticipante
WHERE 
    Participante.vulgo = 'Lil vi';

#Criando Principais Batalhas
Insert into Batalha (idBatalha, nome, local, estado, diaDaSemana) values
(1, 'Batalha Da Aldeia', 'Praça dos Estudantes de Barueri', 'SP','Segunda-feira'),
(2, 'Batalha Da Norte', 'Praça Margarida de Albuquerque Gimenez','SP','Sexta-feira'),
(3, 'Batalha Da Leste', 'Metrô Itaquera','SP','Sábado');

#Criando participantes
insert into Participante (idParticipante, nome, idade, vulgo, estado) values
(1, 'Nicolas', 18, 'Lil vi', 'São Paulo'),
(2, 'João Pedro', 18, 'Jotape', 'São Paulo'),
(3, 'Eduardo Santos Lima', 18, 'Dudu', 'Espírito Santo'),
(4, 'Pedro Barreto', 18, 'Barreto', 'São Paulo'),
(5, 'Breno Fonseca', 18, 'Brennuz', 'São Paulo'),
(6, 'Sidney Gabriel Silva Pinto', 18, 'Sid', 'São Paulo'),
(7, 'Luiz Felipe Santana', 18, 'Jaya Luuck', 'Bahia'),
(8, 'Miguel Vinicius Gomes de Lima', 18, 'Mikezin', 'São Paulo');

#Criando Edições

insert into Edicao (idEdicao, numEdicao, data, idBatalha) values
(1, 375, '10/06/2024', 1),
(2, 374, '03/06/2024', 1),
(3, 373, '27/05/2024', 1),
(4, 372, '24/05/2024', 2),
(5, 371, '27/05/2024', 2),
(6, 370, '24/05/2024', 3),
(7, 369, '27/05/2024', 3);

insert into Vencedores (idVencedores, idEdicao, idParticipante) values
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1);