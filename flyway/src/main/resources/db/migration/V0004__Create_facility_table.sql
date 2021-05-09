CREATE TABLE `facility` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_id` bigint(20) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `parent_facility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)