CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user` VALUES (1,'seller1@mycompany.com','$2a$10$yypw8MFZmqnsd3u75JB4TueFVI/fS7vVwv5KOeOB31d1/3mTxC1Ye','ROLE_SELLER'),(2,'buyer1@mycompany.com','$2a$10$yypw8MFZmqnsd3u75JB4TueFVI/fS7vVwv5KOeOB31d1/3mTxC1Ye','ROLE_BUYER'),(3,'buyer2@mycompany.com','$2a$10$yypw8MFZmqnsd3u75JB4TueFVI/fS7vVwv5KOeOB31d1/3mTxC1Ye','ROLE_BUYER'),(4,'seller2@mycompany.com','$2a$10$yypw8MFZmqnsd3u75JB4TueFVI/fS7vVwv5KOeOB31d1/3mTxC1Ye','ROLE_SELLER');

CREATE TABLE `deal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKescqrp6gh6m5y10682vk41q7w` (`buyer_id`),
  KEY `FK9l52iua7n4xtmeebgc1afxlq0` (`seller_id`),
  CONSTRAINT `FK9l52iua7n4xtmeebgc1afxlq0` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKescqrp6gh6m5y10682vk41q7w` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longblob NOT NULL,
  `filename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `uploaded_at` datetime NOT NULL,
  `deal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtehkadngi0m6x1d07budtysua` (`deal_id`),
  CONSTRAINT `FKtehkadngi0m6x1d07budtysua` FOREIGN KEY (`deal_id`) REFERENCES `deal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `document_view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `viewed_at` datetime NOT NULL,
  `viewed_till` datetime DEFAULT NULL,
  `document_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs9350fbqlhvmyc4rob5v01o5n` (`document_id`),
  CONSTRAINT `FKs9350fbqlhvmyc4rob5v01o5n` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

