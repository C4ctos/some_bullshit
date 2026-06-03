let goToBottom = setInterval(() => window.scrollBy(0, 400), 1000);

clearInterval(goToBottom);

let arrayVideos = [];

const links = document.querySelectorAll('a');

for (const link of links){

    if (link.id === "video-title"){

        link.href = link.href.split('&list=')[0];

        arrayVideos.push(link.title + ';' + link.href);

        console.log("\n \n" + link.title + "\n" + link.href + "\n \n");
    }
}