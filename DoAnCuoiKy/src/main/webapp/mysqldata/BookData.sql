-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: abook
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (1,'Tiểu thuyết','Những tác phẩm phản ánh bức tranh xã hội và vấn đề cuộc sống con người'),(2,'Truyện ngắn','Những tác phẩm có dung lượng ngắn');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_code` varchar(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity_in_stock` int DEFAULT '0',
  `image_url` varchar(255) DEFAULT NULL,
  `description` text,
  `category_id` int DEFAULT NULL,
  `can_show` tinyint(1) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_code` (`book_code`),
  KEY `fk_book_category` (`category_id`),
  CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'8934974164449','Giao Thừa (Tái Bản 2019)','Nguyễn Ngọc Tư','NXB Trẻ',95000.00,100,'image_195509_1_9945.webp','Cuốn sách này sẽ giới thiệu đến bạn đọc 17 câu truyện ngắn như: Bởi yêu thương, Cái nhìn khắc khoải, Chuyện vui điện ảnh, Cuối mùa nhan sắc, Dòng nhớ, Đời như ý, Giao thừa, Hiu hiu gió bấc...',2,1,'2025-12-21 14:00:11','2025-12-21 14:00:11'),(2,'9786043079517','Gió Lạnh Đầu Mùa','Thạch Lam','Văn Học',51000.00,100,'9786043079517.jpg','\"Gió Lạnh Đầu Mùa\" là tập truyện ngắn của Thạch Lam(1910 – 1942). Trong \"Gió Lạnh Đầu Mùa\", tình người ấm áp như chiếc áo mùa đông đã nảy nở trong lòng hai đưa trẻ: Hai chị em Lan, Sơn mặc áo ấm ra chợ chơi với bọn trẻ nhà nghèo thấy Hiên con bé hàng xóm co ro bên cột quán mặc manh áo rách tả tơi bèn chạy về nhà lấy áo bông cũ đem cho nó mặc. Mẹ Lan thấy nhà Hiên nghèo khổ bèn cho mẹ nó mượn năm hào may áo…',2,1,'2025-12-21 14:00:11','2025-12-21 14:00:11'),(3,'8935075959187','Mưa Đỏ','Chu Lai','Quân Đội Nhân Dân',205000.00,100,'muado.webp','Những miền cảm xúc đan xen giữa nụ cười - nước mắt, nỗi đau - niềm vui, sự sống - cái chết, những thăng hoa - mất mát, sự hy sinh của những người cha, người chồng, người con, những người lính, những đồng chí, đồng đội đã không tiếc máu xương trong cuộc chiến đấu 81 ngày đêm bảo vệ thành Cổ Quảng Trị, bảo vệ Tổ quốc với những gian khổ, thiểu thốn lẫn những mất mát đau thương. Đó là một tiểu đội có 7 người lính với 7 tính cách, số phận, suy nghĩ và xuất thân khác nhau. Có người lãng tử, có người bộc trực, có anh lính nhút nhát, có anh lính gan dạ nhưng hơn tất cả họ là một gia đình, luôn có nhau dù đang giữa ranh giới mong manh sự sống và cái chết cận kề.',1,1,'2025-12-21 14:00:11','2025-12-21 14:00:11'),(4,'8935212370189','Hồ Điệp Và Kình Ngư','Tuế Kiến','Văn Học',155000.00,100,'bia-2d_ho-diep-va-kinh-ngu_17307_1.webp','Một câu chuyện cuốn hút ngay từ những trang đầu tiên - Khi tình yêu trở thành sợi dây mong manh giữa sinh tử, phản bội và hy vọng. Khi một nàng hồ điệp nhỏ bé chạm trán với kình ngư mạnh mẽ, liệu đó là định mệnh hay chỉ là một giấc mộng chóng tàn?',1,1,'2025-12-21 14:00:11','2025-12-21 14:00:11'),(5,'8935235234338','Nhật Ký Đặng Thùy Trâm (Tái Bản 2022)','Đặng Thùy Trâm','NXB Hội Nhà Văn',90000.00,100,'8935235234338.webp','Một cuốn nhật kí nhặt được bên xác của một nữ Việt Cộng đã suýt bị người lính Mỹ ném vào lửa, nhưng người phiên dịch đã khuyên anh ta nên giữ lại vì \"trong đó có lửa\". Nhật kí Đặng Thùy Trâm là những ghi chép hàng ngày của một người nữ bác sĩ về cuộc sống của chị nơi chiến tuyến. Cuốn nhật kí là thế giới riêng của người trí thức nhạy cảm mà không yếu đuối, tha thiết với cuộc sống mà không hề sợ hãi trước những gian nan. Ở đó ta vẫn gặp những băn khoăn trăn trở trước tình yêu, trước cuộc sống phức tạp hàng ngày, những nỗi buồn, nỗi nhớ nhung, sự cô đơn của một người con gái, nhưng đồng thời chúng ta cũng thấy được một ý chí mãnh liệt, những lời nói tự động viên cảnh tỉnh, một lòng can đảm phi thường - những điều đã làm nên một thế hệ anh hùng.',1,1,'2025-12-21 14:00:11','2025-12-21 14:00:11'),(6,'8935235228351','Cây Cam Ngọt Của Tôi','José Mauro de Vasconcelos','Nhã Nam',108000.00,100,'image_217480.webp','Với một đứa trẻ, thế giới không giới hạn trong một bữa ăn, mà thế giới cần có hào quang của tình thương. Bạn có bao giờ cảm thấy bị lạc lõng trong chính ngôi nhà của mình? Một câu chuyện chạm đến tận cùng cảm xúc',1,1,'2025-12-21 14:00:11','2025-12-21 14:00:11');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `user_id` (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` text,
  `role` enum('customer','admin') DEFAULT 'customer',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'huuthinh','123456','21130635@sv.hcmuaf.edu.vn','0911233456','Tp HCM','customer','2025-12-21 19:50:15'),(2,'admin','12233444567','admin@gmail.com','091159250','Tp HCM','admin','2025-12-21 19:50:15'),(3,'get_right','01218257097a1c3',NULL,NULL,NULL,'customer','2025-12-21 00:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'abook'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-06 18:00:15
