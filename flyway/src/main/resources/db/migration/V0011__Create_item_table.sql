CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `picture` varchar(255),
  `parent_category_id` bigint(20),
  `sub_category_id` bigint(20),
  `brand` varchar(255),
  `facility_id` bigint(20) NOT NULL,
  `internal_identifier` varchar(255),
  `description` LONGTEXT,
  PRIMARY KEY (`id`)
)