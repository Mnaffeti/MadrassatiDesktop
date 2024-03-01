-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 01 mars 2024 à 02:07
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `madrassati`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
                             `id` int(11) NOT NULL,
                             `nom` varchar(255) NOT NULL,
                             `description` varchar(1255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`) VALUES
                                                         (1, 'Langage de programmation', 'Un langage de programmation est un langage formel conçu pour formuler des algorithmes et produire des programmes informatiques qui les appliquent.'),
                                                         (2, 'Langage de balisage', 'Un langage de balisage est un langage informatique utilisé pour lécriture de documents comportant des balises, cest-à-dire des portions de texte encadrées par des chevrons ouvrants et fermants < et >.'),
                                                         (3, 'Système de gestion de base de données', 'Un système de gestion de base de données (abr. SGBD) est un logiciel système destiné à stocker et à partager des informations dans une base de données, en garantissant la qualité, la pérennité et la confidentialité des informations, tout en cachant la complexité des opérations.');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
                         `idCours` int(11) NOT NULL,
                         `LibeleCours` varchar(12) DEFAULT NULL,
                         `DateDebut` datetime DEFAULT NULL,
                         `DateFin` datetime DEFAULT NULL,
                         `idEnseignant` int(11) DEFAULT NULL,
                         `idModule` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`idCours`, `LibeleCours`, `DateDebut`, `DateFin`, `idEnseignant`, `idModule`) VALUES
                                                                                                       (1, 'JAVA GUI VAL', '2024-03-01 09:00:00', '2024-03-01 12:00:00', 4, 1),
                                                                                                       (3, 'C++', '2024-03-04 09:00:00', '2024-03-04 12:00:00', 5, 2),
                                                                                                       (8, 'Big Data', '2024-03-05 09:00:00', '2024-03-05 12:00:00', 4, 1),
                                                                                                       (9, 'GIT', '2024-03-05 14:00:00', '2024-03-05 17:00:00', 5, 2),
                                                                                                       (10, 'QT GUI', '2024-03-06 09:00:00', '2024-03-06 12:00:00', 6, 2),
                                                                                                       (11, 'Technique av', '2024-03-06 14:00:00', '2024-03-06 17:00:00', 4, 1),
                                                                                                       (12, 'Big Data', '2024-03-07 09:00:00', '2024-03-07 12:00:00', 5, 2),
                                                                                                       (13, 'ML', '2024-03-07 14:00:00', '2024-03-07 17:00:00', 6, 1),
                                                                                                       (14, 'UML', '2024-03-08 09:00:00', '2024-03-08 12:00:00', 4, 2),
                                                                                                       (15, 'GIT', '2024-03-08 14:00:00', '2024-03-08 17:00:00', 5, 2),
                                                                                                       (16, 'CI/CD', '2024-03-09 09:00:00', '2024-03-09 12:00:00', 6, 2),
                                                                                                       (17, 'ILETS PREP', '2024-03-09 14:00:00', '2024-03-09 17:00:00', 4, 2),
                                                                                                       (18, 'UML', '2024-03-10 09:00:00', '2024-03-10 12:00:00', 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
                              `nom` varchar(255) NOT NULL,
                              `prenom` varchar(255) NOT NULL,
                              `specialite` varchar(255) NOT NULL,
                              `idUtilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`nom`, `prenom`, `specialite`, `idUtilisateur`) VALUES
                                                                              ('الأحمد', 'أحمد', 'Mathematics', 4),
                                                                              ('الزهراء', 'فاطمة', 'Physics', 5),
                                                                              ('الصديق', 'يوسف', 'Chemistry', 6);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
                            `nom` varchar(255) NOT NULL,
                            `prenom` varchar(255) NOT NULL,
                            `age` int(11) NOT NULL,
                            `idFormation` int(11) NOT NULL,
                            `idUtilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`nom`, `prenom`, `age`, `idFormation`, `idUtilisateur`) VALUES
                                                                                    ('علي', 'محمد', 20, 1, 1),
                                                                                    ('هدى', 'فاطمة', 22, 2, 2),
                                                                                    ('خالد', 'يوسف', 21, 3, 3),
                                                                                    ('aaa', 'aaa', 22, 3, 12),
                                                                                    ('zezezae', 'ezaeaze', 10, 1, 21),
                                                                                    ('Mohamed Aziz', 'Naffeti', 23, 1, 22),
                                                                                    ('bb', 'bb', 2, 1, 25),
                                                                                    ('melek', 'homrani', 25, 2, 26);

-- --------------------------------------------------------

--
-- Structure de la table `exam`
--

CREATE TABLE `exam` (
                        `exam_id` int(11) NOT NULL,
                        `idUtilisateur` int(11) NOT NULL,
                        `idCours` int(11) NOT NULL,
                        `marks` decimal(5,2) DEFAULT NULL,
                        `exam_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `exam`
--

INSERT INTO `exam` (`exam_id`, `idUtilisateur`, `idCours`, `marks`, `exam_date`) VALUES
                                                                                     (1, 22, 10, 15.00, '2024-03-01'),
                                                                                     (2, 22, 12, 12.00, '2024-03-02'),
                                                                                     (3, 22, 13, 18.00, '2024-03-03'),
                                                                                     (4, 22, 14, 20.00, '2024-03-04'),
                                                                                     (5, 22, 15, 10.00, '2024-03-05'),
                                                                                     (6, 22, 16, 17.00, '2024-03-06'),
                                                                                     (7, 22, 17, 8.00, '2024-03-07'),
                                                                                     (9, 26, 3, 10.00, '2024-03-01'),
                                                                                     (11, 26, 9, 18.00, '2024-02-06');

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
                             `idFormation` int(11) NOT NULL,
                             `sujet` varchar(255) NOT NULL,
                             `description` varchar(255) NOT NULL,
                             `difficulte` varchar(255) NOT NULL,
                             `duree` int(11) NOT NULL,
                             `idCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`idFormation`, `sujet`, `description`, `difficulte`, `duree`, `idCategorie`) VALUES
                                                                                                          (1, 'ALT', 'Alternance Genie Logiciel', 'Facile', 20, 1),
                                                                                                          (2, 'PHD', 'PHD', 'HARD', 30, 2),
                                                                                                          (3, 'Engineering', 'Genie Logiciel', 'Difficile', 40, 1),
                                                                                                          (4, 'HTML', 'HTML, sigle de HyperText Markup Language, est le format de données conçu pour représenter les pages web.', 'Facile', 20, 2),
                                                                                                          (5, 'CSS', 'Les feuilles de style en cascade, généralement appelées CSS de langlais Cascading Style Sheets, forment un langage informatique qui décrit la présentation des documents HTML et XML.', 'Moyen', 30, 2),
                                                                                                          (6, 'JavaScript', 'JavaScript est un langage de programmation de scripts principalement employé dans les pages web interactives mais aussi coté serveur.', 'Difficile', 40, 2),
                                                                                                          (7, 'MySQL', 'MySQL est un système de gestion de bases de données relationnelles (SGBDR).', 'Facile', 20, 3),
                                                                                                          (8, 'Oracle', 'Oracle est un système de gestion de base de données relationnelle (SGBDR) qui depuis les années 2000 sest imposé comme un outil incontournable dans le monde professionnel.', 'Moyen', 30, 3),
                                                                                                          (9, 'SQL Server', 'Microsoft SQL Server est un système de gestion de base de données (SGBD) en langage SQL incorporant entre autres un SGBDR (SGBD relationnel ») développé et commercialisé par la société Microsoft.', 'Difficile', 40, 3),
                                                                                                          (10, 'License en informatique', 'Formatin licence informatique fondamentale', 'Meduim', 45, 1);

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
                          `idModule` int(11) NOT NULL,
                          `idFormation` int(11) NOT NULL,
                          `coeff` int(11) NOT NULL DEFAULT 1,
                          `LibModule` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`idModule`, `idFormation`, `coeff`, `LibModule`) VALUES
                                                                           (1, 1, 4, 'Math et Proba'),
                                                                           (2, 2, 1, 'OOP');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
                               `idUtilisateur` int(11) NOT NULL,
                               `email` varchar(30) NOT NULL,
                               `motdepasse` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `email`, `motdepasse`) VALUES
                                                                       (1, 'aziz@madrassati.tn', '123456789'),
                                                                       (2, 'ali@example.com', 'password123'),
                                                                       (3, 'huda@example.com', 'password456'),
                                                                       (4, 'khaled@example.com', 'password789'),
                                                                       (5, 'ahmed_teacher@example.com', 'securepasswo'),
                                                                       (6, 'fatima_teacher@example.com', 'securepasswo'),
                                                                       (7, 'youssef_teacher@example.com', 'securepasswo'),
                                                                       (9, 'yassine', 'yassine'),
                                                                       (11, 'azza@gmail.tn', '123456778910'),
                                                                       (12, 'aziza@ziza', 'zzzz'),
                                                                       (13, 'aaz', 'razrazr'),
                                                                       (14, 'azeaze', 'zaeaze'),
                                                                       (16, 'azeaze@zeazeazea', 'azezaea'),
                                                                       (17, 'azeaze@usa', 'azeaze'),
                                                                       (18, 'azeze', 'eazeaz'),
                                                                       (19, 'rere', 'eee'),
                                                                       (20, 'aa', 'dd'),
                                                                       (21, 'zeaze', 'eazeaze'),
                                                                       (22, 'mednaffeti@madrassati.tn', '123456789'),
                                                                       (25, 'aaziz@elelfe', 'bbbbbbbbb'),
                                                                       (26, 'melek@madrassati.tn', '123456789');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
    ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
    ADD PRIMARY KEY (`idCours`),
  ADD KEY `pfk1` (`idEnseignant`),
  ADD KEY `fk_cours_module` (`idModule`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
    ADD PRIMARY KEY (`idUtilisateur`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
    ADD PRIMARY KEY (`idUtilisateur`),
  ADD KEY `idFormation` (`idFormation`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Index pour la table `exam`
--
ALTER TABLE `exam`
    ADD PRIMARY KEY (`exam_id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`),
  ADD KEY `idCours` (`idCours`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
    ADD PRIMARY KEY (`idFormation`),
  ADD KEY `idCategorie` (`idCategorie`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
    ADD PRIMARY KEY (`idModule`),
  ADD KEY `fk_module_formation` (`idFormation`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `exam`
--
ALTER TABLE `exam`
    MODIFY `exam_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
    MODIFY `idFormation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
    MODIFY `idModule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
    ADD CONSTRAINT `fk_cours_module` FOREIGN KEY (`idModule`) REFERENCES `module` (`idModule`),
  ADD CONSTRAINT `pfk1` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignant` (`idUtilisateur`);

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
    ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
    ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`),
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `exam`
--
ALTER TABLE `exam`
    ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `etudiant` (`idUtilisateur`),
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`idCours`) REFERENCES `cours` (`idCours`);

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
    ADD CONSTRAINT `formation_ibfk_1` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `module`
--
ALTER TABLE `module`
    ADD CONSTRAINT `fk_module_formation` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`idFormation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
