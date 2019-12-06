const cheerio = require("cheerio");
const request = require("request-promise");
const ObjectsToCsv = require("objects-to-csv");
const path = "https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Zawody";

async function getJobsData() {
    const html = await request(path)

    const representativesOfGivenProfessions = cheerio('tr > td > a', html)
        .map(async (index, element) => {
        const member = element.children[0].data;
        return {member}
    })
    .get()

    return Promise.all(representativesOfGivenProfessions)
}

getJobsData()
    .then(data => {
        let transformed = new ObjectsToCsv(data)
        return transformed.toDisk("./jobs.csv");
    })
    .then(() => {
        console.log("Scraping zrobiony!");
      })
    .catch(error => {
        console.log(error)
    })
