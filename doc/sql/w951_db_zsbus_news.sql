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

/*Table structure for table `zsbus_news_article` */

DROP TABLE IF EXISTS `zsbus_news_article`;

CREATE TABLE `zsbus_news_article` (
  `article_id` varchar(32) NOT NULL COMMENT '文章ID',
  `category_id` varchar(32) DEFAULT NULL COMMENT '所属分类',
  `article_title` varchar(50) DEFAULT NULL COMMENT '文章标题',
  `article_subtitle` varchar(150) DEFAULT NULL COMMENT '文章简介',
  `article_author` varchar(20) DEFAULT NULL COMMENT '文章作者',
  `article_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `article_content` text COMMENT '文章内容',
  PRIMARY KEY (`article_id`),
  KEY `FK_Article_Category_On_CategoryId` (`category_id`),
  CONSTRAINT `FK_Article_Category_On_CategoryId` FOREIGN KEY (`category_id`) REFERENCES `zsbus_news_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

/*Data for the table `zsbus_news_article` */

/*Table structure for table `zsbus_news_category` */

DROP TABLE IF EXISTS `zsbus_news_category`;

CREATE TABLE `zsbus_news_category` (
  `category_id` varchar(32) NOT NULL COMMENT '分类ID',
  `category_pid` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `category_name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `category_sort` int(11) DEFAULT NULL COMMENT '分类排序',
  `category_createname` varchar(20) DEFAULT NULL COMMENT '创建人',
  `category_createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类';

/*Data for the table `zsbus_news_category` */

insert  into `zsbus_news_category`(`category_id`,`category_pid`,`category_name`,`category_sort`,`category_createname`,`category_createdate`) values ('be4133413e5949bcb5acae4b01dd62a5','0','系统目录',0,'SYSTEM','2014-05-15 11:47:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
