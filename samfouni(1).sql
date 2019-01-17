-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 17 jan. 2019 à 22:56
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `samfouni`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite1`
--

DROP TABLE IF EXISTS `activite1`;
CREATE TABLE IF NOT EXISTS `activite1` (
  `Id_activite` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Type` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Adresse` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Pays` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Region` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Prix` double NOT NULL,
  `PlaceDisponible` int(11) NOT NULL,
  `valider` int(11) DEFAULT NULL,
  `idagence` int(11) NOT NULL,
  PRIMARY KEY (`Id_activite`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `activite1`
--

INSERT INTO `activite1` (`Id_activite`, `Nom`, `Type`, `Adresse`, `Pays`, `Region`, `Description`, `Prix`, `PlaceDisponible`, `valider`, `idagence`) VALUES
(1, 'rtv', 'tgr', 'rgt', 'rtg', 'gtr', 'gtr', 55, 3, 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

DROP TABLE IF EXISTS `favoris`;
CREATE TABLE IF NOT EXISTS `favoris` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `id_resto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_8933C432A76ED395` (`user_id`),
  KEY `IDX_8933C43267A41481` (`id_resto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `favoris`
--

INSERT INTO `favoris` (`id`, `user_id`, `id_resto`) VALUES
(1, 1, 28);

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

DROP TABLE IF EXISTS `hebergement`;
CREATE TABLE IF NOT EXISTS `hebergement` (
  `idHebergement` int(11) NOT NULL AUTO_INCREMENT,
  `idAgence` int(11) DEFAULT NULL,
  `nomAgence` text,
  `type_Hebergement` text,
  `nom_Hebergement` text,
  `nombre_etoile` int(11) DEFAULT NULL,
  `Adresse_Hebergement` text,
  `nombre_chambre` int(11) DEFAULT NULL,
  `prix_single` int(11) DEFAULT NULL,
  `prix_double` int(11) DEFAULT NULL,
  `taux_demi` int(11) DEFAULT NULL,
  `taux_complet` int(11) DEFAULT NULL,
  `tel` text,
  `description` text,
  `image` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`idHebergement`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hebergement`
--

INSERT INTO `hebergement` (`idHebergement`, `idAgence`, `nomAgence`, `type_Hebergement`, `nom_Hebergement`, `nombre_etoile`, `Adresse_Hebergement`, `nombre_chambre`, `prix_single`, `prix_double`, `taux_demi`, `taux_complet`, `tel`, `description`, `image`) VALUES
(8, 6, 'aa', 'aa', 'aa', 4, 'aa', 0, 25, 25, 15, 14, '44', 'aaaazd', 'a78a259a8b6e7f2e56ba08bb37fc7c07.jpeg'),
(9, 6, 'fh', 'hd', 'hello', 2, '55', 4, 52, 32, 41, 21, '45233340', 'gyu', '5b2b1f88b836a30127384d9de2d0ff63.jpeg'),
(10, 0, 'tahaVoyage', 'hotel', 'Lacigale', 5, 'tunisieTabarka', 223, 650, 750, 20, 25, '78000000', 'welcomeeeee', 'images.jpg'),
(11, 0, 'aa', 'zzz', 'eee', 5, 'aaaa', 222, 222, 222, 222, 222, '22222222', 'jhgfd', 'images.jpg'),
(12, 0, 'refq', 'qf', 'qrf', 2, 'og', 22, 22, 22, 22, 22, '22222222', 'kjhgfd', 'mmmmm.jpg'),
(13, 6, 'yh', 'hy', 'h', 5, 'tg', 58, 85, 58, 5, 8, '58585858', 'tbyt', 'images.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `reservationvol`
--

DROP TABLE IF EXISTS `reservationvol`;
CREATE TABLE IF NOT EXISTS `reservationvol` (
  `id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `idagence` int(11) NOT NULL,
  `idclient` int(11) NOT NULL,
  `nbplace` int(11) NOT NULL,
  `Prix` double NOT NULL,
  `$idVol` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `IDX_D72FC53CC3C99BF2` (`$idVol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_activite1`
--

DROP TABLE IF EXISTS `reservation_activite1`;
CREATE TABLE IF NOT EXISTS `reservation_activite1` (
  `idactivite` int(11) DEFAULT NULL,
  `Id_reservationa` int(11) NOT NULL AUTO_INCREMENT,
  `idagence` int(11) NOT NULL,
  `idclient` int(11) NOT NULL,
  `nbplace` int(11) NOT NULL,
  `Prix` double NOT NULL,
  PRIMARY KEY (`Id_reservationa`),
  KEY `IDX_F4FE1F9612A01D18` (`idactivite`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation_activite1`
--

INSERT INTO `reservation_activite1` (`idactivite`, `Id_reservationa`, `idagence`, `idclient`, `nbplace`, `Prix`) VALUES
(1, 1, 6, 34, 2, 99);

-- --------------------------------------------------------

--
-- Structure de la table `reservation_hebergement`
--

DROP TABLE IF EXISTS `reservation_hebergement`;
CREATE TABLE IF NOT EXISTS `reservation_hebergement` (
  `idReservationh` int(11) NOT NULL AUTO_INCREMENT,
  `idhebergement` int(11) NOT NULL,
  `idUtilisateur` int(11) DEFAULT NULL,
  `idagence` int(11) DEFAULT NULL,
  `nbrplace` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `typechambre` varchar(255) NOT NULL,
  `tauxchambre` varchar(255) NOT NULL,
  `nbrjour` int(11) NOT NULL,
  PRIMARY KEY (`idReservationh`),
  KEY `IDX_843E00C08E93AD33` (`idhebergement`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation_hebergement`
--

INSERT INTO `reservation_hebergement` (`idReservationh`, `idhebergement`, `idUtilisateur`, `idagence`, `nbrplace`, `prix`, `typechambre`, `tauxchambre`, `nbrjour`) VALUES
(137, 9, 34, 6, 1, 21, 'Single', 'tauxComplet', 1),
(138, 9, 34, 6, 1, 21, 'Single', 'tauxComplet', 1),
(139, 9, 34, 6, 1, 41, 'Double', 'tauxComplet', 1),
(140, 9, 34, 6, 1, 21, 'Single', 'tauxComplet', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reservervoyageorganise`
--

DROP TABLE IF EXISTS `reservervoyageorganise`;
CREATE TABLE IF NOT EXISTS `reservervoyageorganise` (
  `Id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_agence` int(11) DEFAULT NULL,
  `nbrplace` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `idVoyageorganise` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_reservation`),
  KEY `IDX_434D3D15D5593C4` (`idVoyageorganise`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservervoyageorganise`
--

INSERT INTO `reservervoyageorganise` (`Id_reservation`, `id_user`, `id_agence`, `nbrplace`, `prix`, `idVoyageorganise`) VALUES
(11, 34, 6, 2, 1, 32),
(12, 34, 6, 2, 1, 32);

-- --------------------------------------------------------

--
-- Structure de la table `resrevations`
--

DROP TABLE IF EXISTS `resrevations`;
CREATE TABLE IF NOT EXISTS `resrevations` (
  `id_res` int(11) NOT NULL AUTO_INCREMENT,
  `date_res` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nb_personnes` int(11) NOT NULL,
  `id_resto` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id_res`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `resrevations`
--

INSERT INTO `resrevations` (`id_res`, `date_res`, `nb_personnes`, `id_resto`, `id_agence`) VALUES
(10, '2018-12-30', -2, 26, 1),
(11, '2018-12-31', 2, 26, 1),
(13, '2018-12-31', 2, 28, 1),
(15, '2020-12-31', 2, 32, 1),
(16, '2019-01-31', 3, 28, 1);

-- --------------------------------------------------------

--
-- Structure de la table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
CREATE TABLE IF NOT EXISTS `restaurants` (
  `id_resto` int(11) NOT NULL AUTO_INCREMENT,
  `nb_places_tot_resto` int(11) NOT NULL,
  `nom_resto` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_resto` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `type_resto` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `specialite_resto` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id_resto`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `restaurants`
--

INSERT INTO `restaurants` (`id_resto`, `nb_places_tot_resto`, `nom_resto`, `adresse_resto`, `type_resto`, `specialite_resto`, `id_agence`) VALUES
(26, 3, 'gringos', 'menzah9', 'fast fook', 'Tunisienne', 3),
(27, 122, 'Square', 'Lac', 'resto bar', 'Française', 3),
(28, 13, 'via mercato', 'mourouj', 'resto bar', 'Italienne', 3),
(29, 13, 'picolo mundo', 'centre ville', 'fast food', 'Tunisienne', 3),
(30, 8, 'miamamia', 'ariana', 'familial', 'Asiatique', 3),
(32, 25, 'kafeine', 'ariana', 'resto bar', 'ssss', 3);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Sexe_user` text COLLATE utf8_unicode_ci,
  `Adresse_user` text COLLATE utf8_unicode_ci,
  `Telephone_user` int(11) DEFAULT NULL,
  `connecter_user` int(11) DEFAULT NULL,
  `rolejava` int(11) DEFAULT NULL,
  `Fax_agence` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `Sexe_user`, `Adresse_user`, `Telephone_user`, `connecter_user`, `rolejava`, `Fax_agence`) VALUES
(0, 'bb123', 'bb123', 'arij.mediouni@esprit.tn', 'bb@gmail.com', 1, NULL, '$2y$13$EUV8kCDXHbp5srO4QKmz7.u7gdx.SWiqmzYrR3bhvlv95fiq3hXsG', '2019-01-16 00:00:00', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_AGENT\";}', 'cccc', 'bbbbb', NULL, NULL, NULL, 0, 1, NULL),
(4, 'rania95', 'rania95', 'rania@gmail.com', 'rania@gmail.com', 1, NULL, '$2y$13$iqCcjlxagD2liHJjSHppQ.C94EQms66j04xFxuhOy9LcfxrIYSt3q', '2018-12-03 14:23:58', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'rania', 'mnissi', NULL, NULL, NULL, 0, 2, NULL),
(6, 'challakh95', 'challakh95', 'challakh@gmail.com', 'challakh@gmail.com', 1, NULL, '$2y$13$ZMfj2ZQAxyGITH4/V9YlDe28D7ANiJaS/fdjIP54vM8LJZhZFI9fu', '2018-12-07 09:21:51', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_AGENT\";}', 'amine', 'challakh', NULL, NULL, NULL, 0, 1, NULL),
(7, 'arij95', 'arij95', 'arij@gmail.com', 'arij.mediouni@esprit.tn', 1, NULL, '$2y$13$hnbgy9TvW2mIi9bIxoLm4Oy0R6CdCxmnPNLPDOXCNdn.TLy/KYTtO', '2018-12-07 09:20:44', NULL, NULL, 'a:0:{}', 'arij', 'mediouni', NULL, NULL, NULL, 0, 0, NULL),
(34, 'abdou', NULL, 'abdou@gmail.com', 'abdou@gmail.com', 0, NULL, '$2a$13$3D5veyCJ5KX2hG1LB5KzueH07bmAV71f.sH41i7QJwNuIxgDV9ByO', NULL, NULL, NULL, 'a:0:{}', 'abdou', 'hadjsalah', 'Homme', 'sousse', 53403110, 0, 0, NULL),
(35, 'taha', NULL, 'taha@gmail.com', 'taha@gmail.com', 0, NULL, '$2a$13$CDauJ.whnP5y7rK5/e4mput7DMBkOwkUZoEyNXAnEkAH1Y0gYcu5i', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_AGENT\";}', 'taha', NULL, NULL, 'tunis', 12312312, 0, 1, '12312312'),
(36, 'ariij', 'ariij', 'aaaa@gmail.com', 'aaaa@gmail.com', 1, NULL, 'ariij', '2019-01-16 00:00:00', NULL, NULL, ' a:0:{}', 'zaaa', 'eerrr', NULL, NULL, NULL, 0, NULL, NULL),
(38, 'aaaa', NULL, 'azerty@gmail.com', 'azerty@gmail.com', 1, NULL, '$2a$13$/bvNcFz3dktk12C1XbKpu.O3ToeB0RP1tX/kj9nOXWdBwJgDHyuti', NULL, NULL, NULL, 'a:0:{}', 'aaaa', 'aaaaa', 'Femme', 'aaaaa', 11111111, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vols`
--

DROP TABLE IF EXISTS `vols`;
CREATE TABLE IF NOT EXISTS `vols` (
  `id_vol` int(11) NOT NULL AUTO_INCREMENT,
  `date_depart` date NOT NULL,
  `date_arrive` date NOT NULL,
  `ville_depart` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ville_arrive` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Type` text COLLATE utf8_unicode_ci NOT NULL,
  `nb_places` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id_vol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `voyageorganise`
--

DROP TABLE IF EXISTS `voyageorganise`;
CREATE TABLE IF NOT EXISTS `voyageorganise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prix_voyage` int(11) NOT NULL,
  `date_depart` date NOT NULL,
  `date_retour` date NOT NULL,
  `origine` text COLLATE utf8_unicode_ci NOT NULL,
  `pays_destination` text COLLATE utf8_unicode_ci NOT NULL,
  `ville_destination` text COLLATE utf8_unicode_ci NOT NULL,
  `nb_places` int(11) NOT NULL,
  `hotel` text COLLATE utf8_unicode_ci NOT NULL,
  `id_agence` int(11) NOT NULL,
  `nom_agence` text COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `voyageorganise`
--

INSERT INTO `voyageorganise` (`id`, `prix_voyage`, `date_depart`, `date_retour`, `origine`, `pays_destination`, `ville_destination`, `nb_places`, `hotel`, `id_agence`, `nom_agence`, `image`) VALUES
(32, 1, '2019-01-18', '2019-01-27', 'hhgh', 'hhg', 'hnh', 0, 'hgg', 6, 'ggg', 'images.jpg'),
(33, 2, '2019-01-18', '2019-01-27', 'dd', 'ddd', 'ddd', 2, 'ddd', 6, 'ddd', 'telechargement.jpg');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservationvol`
--
ALTER TABLE `reservationvol`
  ADD CONSTRAINT `FK_D72FC53CC3C99BF2` FOREIGN KEY (`$idVol`) REFERENCES `vols` (`id_vol`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation_activite1`
--
ALTER TABLE `reservation_activite1`
  ADD CONSTRAINT `FK_F4FE1F9612A01D18` FOREIGN KEY (`idactivite`) REFERENCES `activite1` (`Id_activite`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation_hebergement`
--
ALTER TABLE `reservation_hebergement`
  ADD CONSTRAINT `FK_843E00C08E93AD33` FOREIGN KEY (`idhebergement`) REFERENCES `hebergement` (`idHebergement`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservervoyageorganise`
--
ALTER TABLE `reservervoyageorganise`
  ADD CONSTRAINT `FK_434D3D15D5593C4` FOREIGN KEY (`idVoyageorganise`) REFERENCES `voyageorganise` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
