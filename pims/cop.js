let goToBottom = setInterval(() => window.scrollBy(0, 400), 1000);

clearInterval(goToBottom);

let arrayVideos = [];

const links = document.querySelectorAll('a');

for (const link of links){

    if (link.id === "video-title"){

        console.log("-");

        link.href = link.href.split('&list=')[0];

        arrayVideos.push(link.title + ';' + link.href);

        console.log(link.title + "-" + link.href);
    }
}