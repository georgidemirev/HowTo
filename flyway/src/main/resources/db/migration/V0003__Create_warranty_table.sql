CREATE TABLE `warranty` (
  `id` varchar(255) NOT NULL,
  `duration_months` int(11) NOT NULL,
  `end_user_id` bigint(20) DEFAULT NULL,
  `expiration_date` datetime NOT NULL,
  `facility_id` bigint(20) NOT NULL,
  `issue_date` datetime NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `item_serial_number` varchar(255) NOT NULL,
  `warranty_condition_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)