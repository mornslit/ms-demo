DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `countryname` varchar(255) DEFAULT NULL COMMENT '名称',
  `countrycode` varchar(255) DEFAULT NULL COMMENT '代码',
--  `status` int(11) NOT NULL ,
  `status` int(11) NOT NULL default 0,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `countrycode` (`countrycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国家信息';