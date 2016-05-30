DROP DATABASE IF EXISTS `horsebetting`;

CREATE DATABASE `horsebetting` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `horsebetting`;

CREATE TABLE `usertable` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) COLLATE utf8_bin NOT NULL,
  `login` varchar(128) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  `balance` double DEFAULT NULL,
  `userRole` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `horsetable` (
  `horseId` int(11) NOT NULL AUTO_INCREMENT,
  `horseName` varchar(20) COLLATE utf8_bin NOT NULL,
  `color` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`horseId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `racetable` (
  `raceId` int(11) NOT NULL AUTO_INCREMENT,
  `raceName` varchar(45) COLLATE utf8_bin NOT NULL,
  `raceDate` datetime(4) NOT NULL,
  `raceWinner` int(11) DEFAULT NULL,
  PRIMARY KEY (`raceId`),
  KEY `HorseIdFK_idx` (`raceWinner`),
  CONSTRAINT `HorseIdFK` FOREIGN KEY (`raceWinner`) REFERENCES `horsetable` (`horseId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `raceline` (
  `lineNum` int(11) NOT NULL,
  `raceId` int(11) NOT NULL,
  `horseId` int(11) NOT NULL,
  `odd` double NOT NULL,
  PRIMARY KEY (`raceId`,`lineNum`),
  KEY `RaceLineHorseIdFK_idx` (`horseId`),
  KEY `RaceLineRaceIdFK_idx` (`raceId`),
  CONSTRAINT `RaceLineHorseIdFK` FOREIGN KEY (`horseId`) REFERENCES `horsetable` (`horseId`),
  CONSTRAINT `RaceLineRaceIdFK` FOREIGN KEY (`raceId`) REFERENCES `racetable` (`raceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `oddtable` (
  `transId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `amount` double NOT NULL,
  `raceId` int(11) NOT NULL,
  `lineNum` int(11) NOT NULL,
  `transDate` datetime(4) NOT NULL,
  PRIMARY KEY (`transId`),
  KEY `OddTableUserIdFK_idx` (`userId`),
  KEY `OddTableRaceLineFK_idx` (`raceId`,`lineNum`),
  CONSTRAINT `OddTableRaceLineFK` FOREIGN KEY (`raceId`, `lineNum`) REFERENCES `raceline` (`raceId`, `lineNum`),
  CONSTRAINT `OddTableUserIdFK` FOREIGN KEY (`userId`) REFERENCES `usertable` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

USE `horsebetting`;

DELIMITER $$

DROP TRIGGER IF EXISTS horsebetting.raceline_BEFORE_INSERT$$
USE `horsebetting`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `horsebetting`.`raceline_BEFORE_INSERT` BEFORE INSERT ON `raceline` FOR EACH ROW
BEGIN
Set new.lineNum = IFNULL((select max(lineNum) as lineNum from raceline 
								where raceline.raceId = new.raceId) + 1, 1);
END$$
DELIMITER ;

USE `horsebetting`;

DELIMITER $$

DROP TRIGGER IF EXISTS horsebetting.oddtable_AFTER_INSERT$$
USE `horsebetting`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `horsebetting`.`oddtable_AFTER_INSERT` AFTER INSERT ON `oddtable` FOR EACH ROW
BEGIN
	UPDATE usertable SET balance = balance - NEW.amount WHERE userId = NEW.userId;
END$$
DELIMITER ;

USE `horsebetting`;

DELIMITER $$

DROP TRIGGER IF EXISTS horsebetting.racetable_AFTER_UPDATE$$
USE `horsebetting`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `horsebetting`.`racetable_AFTER_UPDATE` AFTER UPDATE ON `racetable` FOR EACH ROW
BEGIN
	DECLARE done INT DEFAULT FALSE;
	DECLARE sum double;
    DECLARE userId INT;
    DECLARE curUserId INT;	
    DECLARE amount double;
    DECLARE odd double;
    
	DECLARE oCur Cursor FOR SELECT o.amount, o.userId, rl.odd from oddtable o
							JOIN raceline rl ON o.raceId = rl.raceId AND o.lineNum = rl.lineNum  
                            where o.raceId = NEW.raceId AND rl.horseId = NEW.raceWinner
                            order by o.userId;     
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
    
	IF(NEW.raceWinner IS NOT NULL) THEN   
    SET sum = 0;
    SET curUserId = 0;
		OPEN oCur;
			ins_loop: LOOP
				FETCH oCur into amount, userId, odd;
                IF done THEN 
					LEAVE ins_loop;
				END IF;   
                IF (curUserId = 0) THEN SET curUserId = userId; END IF;
                
                IF (curUserId <> userId) THEN
					update usertable set balance = balance +  sum 
							where usertable.userId = curUserId;
                            
					SET sum = 0;
                    SET curUserId = userId;
                END IF;
                
                SET sum = sum + amount * odd;                				                
                END LOOP;
		CLOSE oCur;
        update usertable set balance = balance +  sum 
				where usertable.userId = curUserId;
    END IF;    
END$$
DELIMITER ;











