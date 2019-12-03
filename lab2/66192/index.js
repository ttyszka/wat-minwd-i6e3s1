const request = require('request');
const cheerio = require('cheerio');
const fs = require('fs');
const writeStream = fs.createWriteStream('post.csv');

let property = {
    type: '',
    rentalType: '',
    location: '',
    district: ''
};

property.type = process.argv[2] === 'd' ? 'dom' : 'mieszkanie';
property.rentalType = process.argv[3] === 'w' ? 'wynajem' : 'sprzedaz';
property.location = process.argv[4] === undefined ? 'warszawa' : process.argv[4];
property.district = process.argv[5] === undefined ? '' : process.argv[5];

request('https://www.otodom.pl/' + property.rentalType + '/' + property.type +'/' + property.location + '/' + property.district, (error, response, html) => {
    if (!error && response.statusCode == 200) {
        const $ = cheerio.load(html);

        $('.offer-item-details').each((i, el) => {
            const link = $(el).find('a').attr('href');

            request(link, (error, response, html) => {
                if (!error && response.statusCode == 200) {
                    const $ = cheerio.load(html);
                    const name = $('.css-18igut2').text();
                    const location = $('.css-12hd9gg').text();
                    const price = $('.css-1vr19r7').text();
                    const detail = $('.section-overview').find('li').text();
                    writeStream.write(`${name}\n` + `${location}\n` + `${price}\n` + `${detail}\n\n`);
                }
            });
        });
    }
    console.log('Scraping done');
});

