CREATE TABLE `etudiant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `nm_apogee` int NOT NULL,
  `téléphone` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `date_naissance` varchar(50) DEFAULT NULL,
  `fillière` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
)