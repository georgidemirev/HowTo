CREATE TABLE `country` (
  `id` bigint(20) NOT NULL,
  `code_iso` varchar(2) NOT NULL,
  `code_iso3` varchar(3),
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)