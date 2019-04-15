# NRLApp
This app is based on MVVM design pattern.
It includes two activities - MainActivity(entry point) & PlayerDetailsActivity MainActivity displays the top players from two different teams.
PlayerDetailsActivity takes a closer look at the selected player at the first screen.
Both the activities use their respective ViewModel classes to fetch the data from the backend.
ViewModel uses RxJava2/retrofit liberary to to manage the network operations.
Glide is used to download the image, but unfortunately provided player headshot links are not working.
