CREATE TABLE `business_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `facility_id` bigint(20) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `registration_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
)