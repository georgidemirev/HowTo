CREATE TABLE `warranty_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `facility_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255),
  `text` LONGTEXT,
  PRIMARY KEY (`id`)
)