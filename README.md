# NewsBreeze

NewsBreeze is an unofficial newsApi client that gets the latest breaking news to you.

![icon](https://user-images.githubusercontent.com/100690010/192261890-3394b61e-0f68-4c5f-a815-01fb02a9ba07.png)

## About App

NewsBreeze is a real-time updating news application. This app uses [NewsAPI](https://newsapi.org/) to get various sources and each source can provide major headlines.
It uses [Volley](https://google.github.io/volley/) to fetch news sources and news headlines from the API and displays in a RecyclerView.
App checks for fresh data from API and API is called to get the latest headlines.

This app is developed in Android Studio using Kotlin and XML for designing.

## Features

The android app lets you:
- get the latest breaking news articles and show them in a list.
- on clicking on an article, shows the full article.
- all items in the list have a “save to read later” button which saves the articles for reading later in a persistent format until the app is closed.
- the breaking news list has a search by title feature.
- save to read later has a list of all news saved and clicking on them shows the full article.
- sort by date and publication
- update the news list by clicking on NewsBreeze text on main screen.

## Descisions and Assumptions

- It is mandatory that the list shows default results after a user searched for a particular topic so, NewsBreeze text on main page reloads the default news list on 
clicking.
- The color of the bookmark icon changes when a news is saved either by clicking the save button or the bookmark button.
- The sort feature lets you enter the starting and ending date in yyyy-mm-dd format to get a list of news of that period.
- The backgorund image is darkened in news description activity so that the title could be read comfortably.
- Save button in news description also saves the news.
- The bookmark button on news description activity takes you to the saved news page containing list of all saved news.
- A splash screen appears when the application is launched.
- The round icon of the app is also changed to the logo above.

## Screenshots

Splash Screen

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192336536-3344fe4b-2c35-4282-bfc2-4bc8e4afdfd7.png" />

Main Page

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192337091-e3aac6ad-4015-45ca-83ed-220847cb5f07.png" />

Enabled Searchbar

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192337439-9159006b-9904-40d7-9a1f-9bd518dc268d.png" />

Sort By Date

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192337776-bfb9526d-39da-445a-ac53-cce789fa7af9.png" />

News Description

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192337914-5875b6c0-58d4-4789-8694-cfb0a554438c.png" />

Bookmark Color in Saved State

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192338028-ff320ad5-c2d2-4fdb-b75e-f72edbbeef5f.png" />

Saved News List

<img height="400px" src="https://user-images.githubusercontent.com/100690010/192338165-421cae96-a233-4296-80f7-c0e99495d5b9.png" />
