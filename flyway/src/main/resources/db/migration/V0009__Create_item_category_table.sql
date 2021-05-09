CREATE TABLE `item_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_category_id` bigint(20),
  PRIMARY KEY (`id`)
)