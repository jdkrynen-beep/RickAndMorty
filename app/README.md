# 🛸 Rick and Morty - Application Android

<div align="center">
  <img src="https://rickandmortyapi.com/api/character/avatar/1.jpeg" alt="Rick Sanchez" width="150"/>
  <img src="https://rickandmortyapi.com/api/character/avatar/2.jpeg" alt="Morty Smith" width="150"/>
  <img src="https://rickandmortyapi.com/api/character/avatar/3.jpeg" alt="Summer Smith" width="150"/>
</div>

Application Android native développée dans le cadre du **TP B3 EPSI** - Année 2024/2025.

Une application mobile moderne affichant les personnages de la série *Rick and Morty* en utilisant l'API publique officielle.

---

##  Fonctionnalités

###  Fonctionnalités principales

- **Liste des personnages** : Affichage de 20 personnages avec images, noms, espèces et statuts
- **Page de détails** : Informations complètes sur chaque personnage (statut, espèce, genre, origine, localisation)
- **Appels API REST** : Récupération des données en temps réel depuis l'API Rick and Morty
- **Gestion des erreurs** : Messages d'erreur clairs en cas de problème de connexion
- **Écran de chargement** : Indicateur visuel pendant le chargement des données

###  Interface utilisateur

- **Material Design 3** : Interface moderne et épurée
- **Thème personnalisé** : Couleurs inspirées de Rick and Morty (vert portail, bleu, jaune)
- **Mode sombre** : Support automatique du thème sombre du système
- **Navigation fluide** : Transitions entre les écrans avec bouton retour
- **Images circulaires** : Avatars stylés avec ombres portées
- **Badges de statut** : Indicateurs colorés (vert pour "Alive", rouge pour "Dead")
- **Icônes Material** : Icônes pour chaque type d'information (statut, espèce, genre, etc.)

---

## 🛠 Technologies utilisées

### Langage et frameworks

- **Kotlin** : Langage de programmation moderne pour Android
- **Jetpack Compose** : Framework UI déclaratif (dernière génération)
- **Material Design 3** : Système de design Google

### Architecture et bibliothèques

- **Architecture MVVM** : Séparation claire des responsabilités
    - `ViewModel` : Gestion de la logique métier
    - `View` : Interface utilisateur (Composables)
    - `Model` : Modèles de données
- **Retrofit 2.9.0** : Client HTTP pour les appels API REST
- **Gson** : Sérialisation/désérialisation JSON
- **Coil 2.5.0** : Chargement et cache des images
- **Kotlin Coroutines** : Gestion de l'asynchrone

### API externe

- **Rick and Morty API** : [https://rickandmortyapi.com](https://rickandmortyapi.com)
    - Endpoint utilisé : `/api/character`
    - Format : JSON
    - Authentification : Aucune (API publique)

---

## 📂 Structure du projet
```
RickAndMorty/
├── app/
│   └── src/
│       └── main/
│           ├── java/fr/epsi/krynen/rickandmorty/
│           │   ├── Character.kt                  # Modèles de données
│           │   ├── CharactersListActivity.kt     # Écran principal (liste)
│           │   ├── CharacterDetailsActivity.kt   # Écran de détails
│           │   ├── CharactersListViewModel.kt    # ViewModel (logique)
│           │   ├── Theme.kt                      # Thème personnalisé
│           │   └── network/
│           │       └── RickAndMortyApiService.kt # Service API Retrofit
│           ├── res/                              # Ressources
│           └── AndroidManifest.xml               # Configuration de l'app
├── build.gradle.kts                              # Configuration Gradle
└── README.md                                     # Ce fichier
```

---

##  Installation et lancement

### Prérequis

- **Android Studio** : Hedgehog (2023.1.1) ou supérieur
- **JDK** : Version 17 ou supérieure
- **SDK Android** : API 24 (Android 7.0) minimum, API 35 recommandé
- **Connexion Internet** : Nécessaire pour récupérer les données de l'API

### Étapes d'installation

1. **Cloner le projet**
```bash
   git clone https://github.com/jdkrynen-beep/RickAndMorty.git
   cd RickAndMorty
```

2. **Ouvrir dans Android Studio**
    - `File > Open`
    - Sélectionner le dossier `RickAndMorty`
    - Cliquer sur "Trust Project"

3. **Synchronisation Gradle**
    - Attendre la synchronisation automatique (1-2 minutes)
    - Si nécessaire : `File > Sync Project with Gradle Files`

4. **Lancer l'application**
    - Créer ou sélectionner un émulateur Android
    - Cliquer sur le bouton ▶️ Run
    - L'application se compile et se lance (20-40 secondes au premier lancement)

---

##  Captures d'écran

### Écran principal - Liste des personnages
- Toolbar verte avec titre "Rick and Morty"
- Liste scrollable de 20 personnages
- Images circulaires avec ombres
- Badges de statut colorés (Alive/Dead/Unknown)
- Informations : Nom, espèce, statut

### Écran de détails
- Toolbar avec nom du personnage et bouton retour
- Grande image du personnage (250x250dp)
- Section "Informations" avec disposition en grille 2x2 :
    - Statut 
    - Espèce
    - Genre 
    - Type  (si disponible)
- Section "Localisation" :
    - Origine 
    - Localisation actuelle 
- Icônes Material colorées pour chaque information

### Écran de chargement
- Spinner circulaire animé
- Message "Chargement des personnages..."

### Écran d'erreur
- Icône d'avertissement ⚠️
- Message d'erreur explicite
- Suggestions à l'utilisateur

---

##  Thème et couleurs

### Palette de couleurs (inspirée de Rick and Morty)

#### Mode clair
- **Primary** : `#00B5CC` (Bleu portail)
- **Secondary** : `#97CE4C` (Vert acide)
- **Tertiary** : `#F8E64E` (Jaune)
- **Background** : `#F5F5F5` (Gris clair)
- **Toolbar** : `#97CE4C` (Vert)

#### Mode sombre
- **Primary** : `#00B5CC`
- **Secondary** : `#97CE4C`
- **Background** : `#1C1C1C` (Gris foncé)
- **Surface** : `#2C2C2C`
- **Toolbar** : `#004D5A` (Bleu-vert foncé)

---

##  Détails techniques

### Gestion des états (MVVM)
```kotlin
sealed interface CharactersUiState {
    data class Success(val characters: List<Character>) : CharactersUiState
    object Error : CharactersUiState
    object Loading : CharactersUiState
}
```

L'application utilise un pattern d'état sealed pour gérer les 3 états possibles :
- **Loading** : Chargement en cours
- **Success** : Données chargées avec succès
- **Error** : Erreur réseau ou serveur

### Appels API avec Retrofit
```kotlin
interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): CharacterResponse
}
```

- Fonction `suspend` pour appels asynchrones avec Coroutines
- Support de la pagination (page 1 par défaut)
- Conversion automatique JSON → Kotlin avec Gson

### Chargement d'images avec Coil
```kotlin
AsyncImage(
    model = character.image,
    contentDescription = "Image de ${character.name}",
    modifier = Modifier.size(60.dp)
)
```

- Chargement asynchrone
- Cache automatique
- Placeholder et gestion d'erreurs

---


##  Dépendances principales
```kotlin
// Retrofit pour les appels API
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Coil pour le chargement d'images
implementation("io.coil-kt:coil-compose:2.5.0")

// ViewModel et Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

// Jetpack Compose
implementation(platform("androidx.compose:compose-bom:2024.10.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
```

---

##  Problèmes connus et solutions

### Problème : L'émulateur est lent
**Solution** :
- Utiliser un émulateur avec API 28-30 (plus léger)
- Réduire la RAM de l'émulateur à 2048 MB
- Activer Graphics: Software au lieu de Hardware
- Ou utiliser un téléphone Android physique en mode développeur

### Problème : Erreur de synchronisation Gradle
**Solution** :
- `File > Invalidate Caches > Invalidate and Restart`
- `Build > Clean Project` puis `Build > Rebuild Project`
- Vérifier la connexion Internet

### Problème : Les images ne s'affichent pas
**Solution** :
- Vérifier que la permission `INTERNET` est dans `AndroidManifest.xml`
- Vérifier la connexion réseau de l'émulateur
- Attendre quelques secondes (chargement asynchrone)


### Améliorations techniques

- 🧪 **Tests unitaires** : Couvrir le ViewModel et les use cases
- 🧪 **Tests UI** : Automatiser les tests d'interface
- 🗄️ **Cache local** : Sauvegarder les données avec Room pour mode hors ligne
- 🔄 **Refresh pull-to-refresh** : Actualiser les données en tirant vers le bas
- 🌐 **Multi-langues** : Internationalisation (i18n)
- ♿ **Accessibilité** : Améliorer le support TalkBack et contraste
- 🚀 **CI/CD** : Pipeline automatisé avec GitHub Actions

---

## Auteur

**Jean Daniel Krynen**  
Étudiant en B3 Développement Fullstack  
EPSI - Promotion 2024/2025

---

##  Licence

Ce projet est réalisé dans un cadre pédagogique.  
L'API Rick and Morty est fournie gracieusement par [rickandmortyapi.com](https://rickandmortyapi.com).

---

##  Remerciements

- **Rick and Morty API** : Pour l'API gratuite et bien documentée
- **Félix Boquet** : Pour le sujet du TP
- **Anthropic (Claude)** : Pour l'assistance au développement
- **Communauté Android** : Pour les ressources et documentation

---

##  Ressources utiles

### Documentation officielle
- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org)
- [Material Design 3](https://m3.material.io)

### Bibliothèques
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://coil-kt.github.io/coil/)
- [Rick and Morty API](https://rickandmortyapi.com/documentation)

### Tutoriels
- [Jetpack Compose Basics](https://developer.android.com/courses/pathways/jetpack-compose-for-android-developers)
- [MVVM Architecture](https://developer.android.com/topic/architecture)
- [Retrofit Guide](https://square.github.io/retrofit/)

---

<div align="center">
  Made with 💚 for EPSI B3 - 2024/2025

 Merci pour votre lecture !
</div>
```
