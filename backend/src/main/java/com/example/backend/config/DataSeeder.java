package com.example.backend.config;

import com.example.backend.entity.Chapter;
import com.example.backend.entity.Lesson;
import com.example.backend.entity.Subject;
import com.example.backend.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final SubjectRepository subjectRepository;

    public DataSeeder(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (subjectRepository.count() > 0) {
            return;
        }

        subjectRepository.save(programmation());
        subjectRepository.save(databases());
        subjectRepository.save(networks());
        subjectRepository.save(operatingSystems());
        subjectRepository.save(softwareEngineering());
        subjectRepository.save(mathematics());
    }

    private Subject programmation() {
        Subject subject = new Subject(
                "Programmation",
                "Introduction à la programmation : variables, structures de contrôle, fonctions et bases de la programmation orientée objet."
        );

        Chapter chapter1 = new Chapter("Bases du langage", 1);
        chapter1.addLesson(new Lesson(
                "Variables et types de données",
                html(
                        "Les variables",
                        "Une variable est un nom qui désigne une valeur en mémoire. Avant d'utiliser une variable, il faut souvent déclarer son type.",
                        "Exemples de types",
                        list("int : nombres entiers", "double : nombres décimaux", "boolean : vrai ou faux", "String : texte"),
                        "Exemple en Java",
                        code("""
                                int age = 20;
                                String nom = "Amina";
                                boolean inscrit = true;
                                System.out.println(nom + " a " + age + " ans.");
                                """)
                ),
                1
        ));
        chapter1.addLesson(new Lesson(
                "Conditions et boucles",
                html(
                        "Les conditions",
                        "Les instructions if et else permettent d'exécuter un bloc seulement si une condition est vraie.",
                        "Les boucles",
                        "Les boucles for et while répètent un traitement. On les utilise pour parcourir une liste ou répéter un calcul.",
                        "Exemple",
                        code("""
                                for (int i = 1; i <= 5; i++) {
                                    if (i % 2 == 0) {
                                        System.out.println(i + " est pair");
                                    }
                                }
                                """)
                ),
                2
        ));

        Chapter chapter2 = new Chapter("Fonctions et objets", 2);
        chapter2.addLesson(new Lesson(
                "Écrire une fonction",
                html(
                        "Rôle d'une fonction",
                        "Une fonction regroupe un traitement réutilisable. Elle peut recevoir des paramètres et renvoyer un résultat.",
                        "Bonnes pratiques",
                        list("Donner un nom clair", "Faire une seule tâche", "Limiter le nombre de paramètres"),
                        "Exemple",
                        code("""
                                public static int somme(int a, int b) {
                                    return a + b;
                                }
                                """)
                ),
                1
        ));
        chapter2.addLesson(new Lesson(
                "Classes et objets",
                html(
                        "Programmation orientée objet",
                        "Une classe décrit un modèle. Un objet est une instance de cette classe, avec ses propres valeurs.",
                        "Exemple d'étudiant",
                        code("""
                                public class Etudiant {
                                    private String nom;
                                    public Etudiant(String nom) {
                                        this.nom = nom;
                                    }
                                    public String getNom() {
                                        return nom;
                                    }
                                }
                                """)
                ),
                2
        ));

        subject.addChapter(chapter1);
        subject.addChapter(chapter2);
        return subject;
    }

    private Subject databases() {
        Subject subject = new Subject(
                "Bases de données (SGBD)",
                "Concepts des bases de données relationnelles, langage SQL et introduction à PL/SQL."
        );

        Chapter chapter1 = new Chapter("Introduction aux bases de données", 1);
        chapter1.addLesson(new Lesson(
                "Concepts fondamentaux",
                html(
                        "Qu'est-ce qu'une base de données ?",
                        "Une base de données est un ensemble organisé d'informations. Un SGBD (Système de Gestion de Bases de Données) permet de les stocker, les interroger et les protéger.",
                        "Objectifs d'un SGBD",
                        list("Éviter les doublons inutiles", "Garantir la cohérence des données", "Permettre des recherches rapides", "Gérer les accès de plusieurs utilisateurs"),
                        "Vocabulaire de base",
                        "Une table contient des lignes (enregistrements) et des colonnes (attributs). Une clé primaire identifie chaque ligne de façon unique."
                ),
                1,
                "/files/intro-sgbd.pdf"
        ));
        chapter1.addLesson(new Lesson(
                "Modèle relationnel",
                html(
                        "Tables et relations",
                        "Dans le modèle relationnel, les données sont représentées par des tables liées entre elles.",
                        "Clés",
                        list("Clé primaire : identifiant unique d'une ligne", "Clé étrangère : référence vers une autre table"),
                        "Exemple",
                        "La table etudiant(id, nom) peut être liée à la table inscription(id, etudiant_id, matiere_id). etudiant_id est une clé étrangère."
                ),
                2
        ));

        Chapter chapter2 = new Chapter("SQL", 2);
        chapter2.addLesson(new Lesson(
                "Les requêtes SELECT",
                html(
                        "Lire des données",
                        "L'instruction SELECT sert à consulter les lignes d'une table. On peut filtrer avec WHERE et trier avec ORDER BY.",
                        "Exemple",
                        code("""
                                SELECT nom, description
                                FROM subjects
                                WHERE nom LIKE '%données%'
                                ORDER BY nom;
                                """),
                        "Jointures",
                        "JOIN permet de combiner plusieurs tables grâce à une clé commune, par exemple chapter.subject_id = subject.id."
                ),
                1
        ));
        chapter2.addLesson(new Lesson(
                "INSERT, UPDATE et DELETE",
                html(
                        "Modifier les données",
                        "SQL ne sert pas seulement à lire : on peut aussi ajouter, modifier et supprimer des lignes.",
                        "Exemples",
                        code("""
                                INSERT INTO subjects (name, description)
                                VALUES ('Réseaux informatiques', 'Cours de réseaux');

                                UPDATE subjects
                                SET description = 'Cours mis à jour'
                                WHERE id = 3;

                                DELETE FROM lessons
                                WHERE id = 99;
                                """),
                        "Attention",
                        "DELETE et UPDATE doivent toujours être utilisés avec une condition WHERE, sinon toutes les lignes peuvent être affectées."
                ),
                2
        ));

        Chapter chapter3 = new Chapter("PL/SQL", 3);
        chapter3.addLesson(new Lesson(
                "Les variables et les structures conditionnelles",
                html(
                        "Bloc PL/SQL",
                        "PL/SQL ajoute des instructions procédurales autour de SQL : variables, conditions et boucles.",
                        "Exemple",
                        code("""
                                DECLARE
                                    v_note NUMBER := 14;
                                BEGIN
                                    IF v_note >= 10 THEN
                                        DBMS_OUTPUT.PUT_LINE('Validé');
                                    ELSE
                                        DBMS_OUTPUT.PUT_LINE('Non validé');
                                    END IF;
                                END;
                                """)
                ),
                1
        ));
        chapter3.addLesson(new Lesson(
                "Les curseurs",
                html(
                        "Parcourir un résultat",
                        "Un curseur permet de traiter les lignes d'une requête une par une.",
                        "Idée générale",
                        list("Déclarer le curseur", "L'ouvrir", "Lire chaque ligne (FETCH)", "Le fermer"),
                        "Usage",
                        "On utilise un curseur lorsqu'un traitement doit s'appliquer à chaque enregistrement retourné par un SELECT."
                ),
                2
        ));

        subject.addChapter(chapter1);
        subject.addChapter(chapter2);
        subject.addChapter(chapter3);
        return subject;
    }

    private Subject networks() {
        Subject subject = new Subject(
                "Réseaux informatiques",
                "Fonctionnement des réseaux : modèles OSI et TCP/IP, adressage IP et services courants."
        );

        Chapter chapter1 = new Chapter("Modèles de communication", 1);
        chapter1.addLesson(new Lesson(
                "Le modèle OSI",
                html(
                        "Sept couches",
                        "Le modèle OSI découpe la communication en 7 couches, de l'application jusqu'au support physique.",
                        "Couches principales",
                        list("Application", "Présentation", "Session", "Transport", "Réseau", "Liaison", "Physique"),
                        "Intérêt",
                        "Ce découpage aide à comprendre où se situent les protocoles : HTTP est applicatif, IP est réseau, Ethernet est liaison."
                ),
                1
        ));
        chapter1.addLesson(new Lesson(
                "TCP/IP et l'adressage",
                html(
                        "Pile TCP/IP",
                        "Internet utilise surtout TCP/IP. Une adresse IPv4 identifie une machine dans un réseau, par exemple 192.168.1.10.",
                        "Ports",
                        "Un port précise le service : 80 ou 443 pour le web, 5432 pour PostgreSQL, 8080 pour cette API Spring Boot."
                ),
                2
        ));

        subject.addChapter(chapter1);
        return subject;
    }

    private Subject operatingSystems() {
        Subject subject = new Subject(
                "Systèmes d'exploitation",
                "Rôle d'un système d'exploitation : processus, mémoire, fichiers et utilisateurs."
        );

        Chapter chapter1 = new Chapter("Processus et mémoire", 1);
        chapter1.addLesson(new Lesson(
                "Les processus",
                html(
                        "Définition",
                        "Un processus est un programme en cours d'exécution. Le système d'exploitation planifie le processeur entre plusieurs processus.",
                        "États fréquents",
                        list("Prêt", "En exécution", "En attente")
                ),
                1
        ));
        chapter1.addLesson(new Lesson(
                "Gestion de la mémoire",
                html(
                        "Mémoire virtuelle",
                        "Le système donne à chaque processus l'illusion d'une mémoire privée. Les pages peu utilisées peuvent être écrites sur le disque.",
                        "Fichiers",
                        "Le système de fichiers organise les données en dossiers et fichiers, avec des droits de lecture et d'écriture."
                ),
                2
        ));

        subject.addChapter(chapter1);
        return subject;
    }

    private Subject softwareEngineering() {
        Subject subject = new Subject(
                "Génie logiciel",
                "Méthodes pour concevoir, développer et maintenir un logiciel de qualité."
        );

        Chapter chapter1 = new Chapter("Cycle de vie d'un logiciel", 1);
        chapter1.addLesson(new Lesson(
                "Les étapes d'un projet",
                html(
                        "Vue d'ensemble",
                        "Un projet logiciel passe souvent par l'analyse des besoins, la conception, le développement, les tests et la maintenance.",
                        "Pourquoi c'est important",
                        "Sans méthode, un programme devient vite difficile à comprendre et à modifier, surtout en équipe."
                ),
                1
        ));
        chapter1.addLesson(new Lesson(
                "Qualité et tests",
                html(
                        "Tester tôt",
                        "Les tests vérifient que le logiciel fait ce qui est attendu. On peut tester une fonction, une API ou un écran complet.",
                        "Exemple pour cette application",
                        list("L'API renvoie la liste des matières", "Une matière inconnue donne une erreur 404", "La page d'accueil affiche les cartes de matières")
                ),
                2
        ));

        subject.addChapter(chapter1);
        return subject;
    }

    private Subject mathematics() {
        Subject subject = new Subject(
                "Mathématiques",
                "Outils mathématiques utiles en informatique : logique, ensembles et bases d'algèbre linéaire."
        );

        Chapter chapter1 = new Chapter("Logique et ensembles", 1);
        chapter1.addLesson(new Lesson(
                "Logique booléenne",
                html(
                        "Vrai et faux",
                        "Les opérateurs ET, OU et NON sont utilisés en mathématiques et en programmation.",
                        "Table de vérité (ET)",
                        list("vrai ET vrai = vrai", "vrai ET faux = faux", "faux ET faux = faux"),
                        "Lien avec le code",
                        code("if (age >= 18 && inscrit) { /* ... */ }")
                ),
                1
        ));
        chapter1.addLesson(new Lesson(
                "Ensembles et relations",
                html(
                        "Ensembles",
                        "Un ensemble est une collection d'éléments distincts, par exemple l'ensemble des étudiants d'un cours.",
                        "Relations",
                        "Une relation lie des éléments entre eux. Les bases de données relationnelles s'appuient sur cette idée."
                ),
                2
        ));

        subject.addChapter(chapter1);
        return subject;
    }

    private String html(String title, String paragraph, String subtitle, String body, String extraTitle, String extraBody) {
        return heading(title) + paragraph(paragraph) + heading(subtitle) + body + heading(extraTitle) + extraBody;
    }

    private String html(String title, String paragraph, String subtitle, String body) {
        return heading(title) + paragraph(paragraph) + heading(subtitle) + body;
    }

    private String heading(String text) {
        return "<h2>" + text + "</h2>";
    }

    private String paragraph(String text) {
        return "<p>" + text + "</p>";
    }

    private String list(String... items) {
        StringBuilder builder = new StringBuilder("<ul>");
        for (String item : items) {
            builder.append("<li>").append(item).append("</li>");
        }
        return builder.append("</ul>").toString();
    }

    private String code(String text) {
        return "<pre><code>" + text.strip() + "</code></pre>";
    }
}
