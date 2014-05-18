/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.29-log : Database - w951_db_zsbus_news
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`w951_db_zsbus_news` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `w951_db_zsbus_news`;

/*Table structure for table `zsbus_news_outside` */

DROP TABLE IF EXISTS `zsbus_news_outside`;

CREATE TABLE `zsbus_news_outside` (
  `outside_id` varchar(32) NOT NULL COMMENT '外链ID',
  `outside_pid` varchar(32) DEFAULT NULL COMMENT '外链父ID',
  `outside_name` varchar(20) DEFAULT NULL COMMENT '外链名称',
  `outside_sort` int(11) DEFAULT NULL COMMENT '外链排序',
  `outside_url` varchar(200) DEFAULT NULL COMMENT '外链地址',
  `outside_createname` varchar(20) DEFAULT NULL COMMENT '创建人',
  `outside_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`outside_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻外链';

/*Data for the table `zsbus_news_outside` */

insert  into `zsbus_news_outside`(`outside_id`,`outside_pid`,`outside_name`,`outside_sort`,`outside_url`,`outside_createname`,`outside_createdate`) values ('402881d04604186f0146041a97080000','e7051116c921456db6f8ca925198bc2a','公交客运服务',1,'#','admin','2014-05-16 16:16:29'),('402881d04604186f0146041fb3870008','e7051116c921456db6f8ca925198bc2a','长途客运服务',2,'#','admin','2014-05-16 16:22:04'),('e7051116c921456db6f8ca925198bc2a','0','系统目录',0,NULL,'SYSTEM','2014-05-16 15:59:27');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
